package API.GetReviews;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;


/***************
 *Nitin Mittal (12 Dec-2019)
 * This class is used to implementing design patterns in  our code. So we are making all the rest assured like specification with
 * the help of this class using calling getter and setter methods
 */

public class GetReviewsTestManager {
    private String baseUri;
    private String resource;
    private String contentType;


    RequestSpecification requestSpecification;

    private GetReviewsTestManager()
    {
        //Nothing required
    }
    //Singleton Pattern Implementation
    private static GetReviewsTestManager getReviewsTestManager;

    public static GetReviewsTestManager getInstance()

    {
        if (getReviewsTestManager!=null)
            return getReviewsTestManager;
        else
            return getReviewsTestManager=new GetReviewsTestManager();
    }

    public GetReviewsTestManager setContentType(String contentType)
    {
        getReviewsTestManager.contentType=contentType;
        return getReviewsTestManager;
    }

    //Builder Pattern implementation
    public GetReviewsTestManager setBaseUri(String baseUri) {

        getReviewsTestManager.baseUri = baseUri;
        RestAssured.baseURI= this.baseUri;
        return getReviewsTestManager;
    }

    public GetReviewsTestManager setResource(String resource) {
        getReviewsTestManager.resource = resource;
        return getReviewsTestManager;

    }

    public GetReviewsTestManager build() throws Exception

    {
        if(baseUri==null)
        {
            throw new Exception("baseUri is null");
        }

       //All specification for requests like pathparams, queryparams need to be set here.
        getReviewsTestManager.requestSpecification=
                given().contentType(contentType);


        return getReviewsTestManager;

    }

    public Response execute()
    {
        return requestSpecification
                .post(resource);

    }

}
