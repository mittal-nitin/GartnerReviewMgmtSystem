package UITests;

import API.GetReviews.GetReviewsResponsePOJO;
import API.GetReviews.GetReviewsTestManager;
import API.PostReview.PostReviewRequestPOJO;
import API.PostReview.PostReviewTestManager;
import UI.DisplayTablePage;
import UI.helper.ReviewInformationOnTable;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tools.CSVAnnotation;
import tools.GenericDataProvider;
import tools.PropertiesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DisplayTableTest {
    WebDriver driver;
    @BeforeMethod
    public void setup()
    {
        System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
        driver = new FirefoxDriver();

        String baseUrl = "Customer Site Address Where Table is present";
        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);


    }
    // declaration and instantiation of objects/variables
    @Test(dataProvider = "dataproviderForTestCase",dataProviderClass = GenericDataProvider.class,description="Getting all different reviews based on the scenarios", groups = {"Display Table"})
    @CSVAnnotation.CSVFileParameters(delimiter = "###", path = "test-data/DisplayTableTest.csv")
    public void verifyTableandItsContent(int rowNo, Map<String, String> testData) throws Exception
    {
        //Posting all the data required for test case exeution
        String[] texts=testData.get("texts").split(",");
        String[] ratings=testData.get("ratings").split(",");
        PostReviewRequestPOJO postReviewRequestPOJO=new PostReviewRequestPOJO();
        for (int i=0; i<texts.length;i++)
        {

            postReviewRequestPOJO.setRating(Integer.parseInt(ratings[i]));
            postReviewRequestPOJO.setText(texts[i]);
            //Firstly posting the required data for test case and then assert on getting it
            Response postRes= PostReviewTestManager.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setContentType("application/JSON").setResource("/api/reviews").setBody(postReviewRequestPOJO).build().execute();
            Assert.assertEquals(postRes.statusCode(), HttpStatus.SC_OK,"Post Fails");
        }

        //Now Getting and Asserting accordingly

        Response getRes= GetReviewsTestManager.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setResource("/partners/api/review").setContentType("application/JSON").build().execute();
        Assert.assertEquals(getRes.statusCode(), HttpStatus.SC_OK,"Post Fails");
        GetReviewsResponsePOJO respObj=getRes.as(GetReviewsResponsePOJO.class);


        //UI Test Starts from here

        DisplayTablePage displayTablePage=new DisplayTablePage(driver);

       //Asserting UI part of table wheather header is present or  not

        Assert.assertTrue(displayTablePage.isHeaderPresent());

        //Assert Number of Rows in UI with Get API
        Assert.assertEquals(displayTablePage.getRowCount(),respObj.getReviewPOJOS().size());

        //Assert that if any review is presented twice then isDuplicate must be true
        List<ReviewInformationOnTable> tableRows =displayTablePage.getTableRows();

        for (ReviewInformationOnTable singleRowEntry:tableRows) {
            if (singleRowEntry.getReviewText().contains(singleRowEntry.getReviewText())) {
                //Dupicate entry IsDuplicate must be true for this
                Assert.assertEquals(singleRowEntry.getIsDuplicate(), "YES", "Is Duplicate is  not YES for duplicate entry");

            }
        }
}
    @AfterMethod
    public void cleanUp()
    {
        //TODO:-Note:- //Here we have to either call the purge database API if any or clear the database using jdbc call to clean up the environment after each test case
        //TODO:- CSV contains only 4 Test cases rest of the test cases can also be provided from test plan shared along with this assignment
    }
}
