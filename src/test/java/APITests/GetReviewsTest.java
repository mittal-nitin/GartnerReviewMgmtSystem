package APITests;

import API.GetReviews.GetReviewsResponsePOJO;
import API.GetReviews.GetReviewsTestManager;
import API.PostReview.PostReviewRequestPOJO;
import API.PostReview.PostReviewTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.kohsuke.rngom.parse.host.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import sun.misc.Request;
import tools.GenericDataProvider;
import tools.PropertiesManager;
import tools.CSVAnnotation;

import java.util.HashMap;
import java.util.Map;
import com.jayway.jsonpath.*;


/***************
 *Nitin Mittal (12 Dec-2019)
 * This class contains Test Cases for Get Review API
 *
 *
 * We are using rest assured here so designing acording to rest assured request
 */

public class GetReviewsTest extends BaseTest {
    @Test(dataProvider = "dataproviderForTestCase",dataProviderClass = GenericDataProvider.class,description="Getting all different reviews based on the scenarios", groups = {"GetReviewsTest"})
    @CSVAnnotation.CSVFileParameters(delimiter = "###", path = "test-data/GETReviewTest.csv")
   public  void GetReviews(int rowNo, Map<String, String> testData) throws Exception
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
             Assert.assertEquals(postRes.statusCode(),HttpStatus.SC_OK,"Post Fails");
         }

         //Now Getting and Asserting accordingly

        Response getRes= GetReviewsTestManager.getInstance().setBaseUri(PropertiesManager.getProperty("baseURI")).setResource("/partners/api/review").setContentType("application/JSON").build().execute();
        //Assertions
        //Asserting on status code
        Assert.assertEquals(getRes.statusCode(),testData.get("expectedStatusCode"),"Get API returning status code : "+getRes.statusCode()+" But expected to return : "+testData.get(("expectedStatusCode")));

        GetReviewsResponsePOJO respObj=getRes.as(GetReviewsResponsePOJO.class);
        Assert.assertEquals(respObj.getReviewPOJOS().size(),testData.get("expectedNumberOfLinesInOutput"), "Assertion Fails as number of lines in output don't match with expected");


    }
    @AfterMethod
    public void cleanUp()
    {
       //TODO:-Note:- //Here we have to either call the purge database API if any or clear the database using jdbc call to clean up the environment after each test case
        //TODO:- CSV contains only 4 Test cases rest of the test cases can also be provided from test plan shared along with this assignment
    }
}
