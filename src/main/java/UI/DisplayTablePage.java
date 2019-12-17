package UI;

import UI.helper.ReviewInformationOnTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/***************
 *Nitin Mittal (16 Dec-2019)
 *
 */


public class DisplayTablePage extends BasePage {

    WebDriver driver;

    @FindBy(xpath ="div[@class='table_header' and text()='Submitted reviews']" )
    WebElement tableHeader;
    @FindBy(xpath ="div[@class='table_header' and text()='Review Text']" )
    WebElement headerFieldReviewText;
    @FindBy(xpath ="div[@class='table_header' and text()='Review Rating']" )
    WebElement tableHeaderReviewRating;
    @FindBy(xpath ="div[@class='table_header' and text()='Review Language']" )
    WebElement tableHeaderReviewLanguage;
    @FindBy(xpath ="div[@class='table_header' and text()='Is a Duplicate']" )
    WebElement tableHeadeIsDuplicate;

    @FindBys(@FindBy(className = "table_row"))
    List<WebElement> tableRow;

    @FindBys(@FindBy(xpath ="div[@class='table_cell' and text()='Review Text']/following-sibling::div[@class='table_cell']"))
    List<WebElement> valueofReviewText;
    @FindBys(@FindBy(xpath ="div[@class='table_cell' and text()='Review Rating']/div/following-sibling::div[@class='table_cell']" ))
    List<WebElement> valueofReviewRating;
    @FindBys(@FindBy(xpath ="div[@class='table_cell' and text()='Review Language/following-sibling::div[@class='table_cell']']" ))
    List<WebElement> valueofReviewLanguage;
    @FindBys(@FindBy(xpath ="div[@class='table_cell' and text()='Is a Duplicate/following-sibling::div[@class='table_cell']']" ))
    List<WebElement> valueofISDuplicate;

    public DisplayTablePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isHeaderPresent()
    {
        if(tableHeader.isDisplayed()&&headerFieldReviewText.isDisplayed()&&tableHeadeIsDuplicate.isDisplayed()&&tableHeaderReviewLanguage.isDisplayed()&&tableHeaderReviewRating.isDisplayed())
        {
            return true;
        }
        return false;

    }

    public int getRowCount()
    {
       return tableRow.size();
    }

    public List<ReviewInformationOnTable> getTableRows ()
    {

        //To Store multiple rows of table in list object format
        List<ReviewInformationOnTable> tableRows=new ArrayList<ReviewInformationOnTable>();
        for(int i=0;i<valueofReviewText.size();i++)
        {
            ReviewInformationOnTable reviewInformationOnTable=new ReviewInformationOnTable();
            reviewInformationOnTable.setReviewText(valueofReviewText.get(i).getText());
            reviewInformationOnTable.setIsDuplicate(valueofISDuplicate.get(i).getText());
            reviewInformationOnTable.setReviewLanguage(valueofReviewLanguage.get(i).getText());
            reviewInformationOnTable.setReviewRating(valueofReviewRating.get(i).getText());
            tableRows.add(reviewInformationOnTable);
        }
        return tableRows;
    }





}



