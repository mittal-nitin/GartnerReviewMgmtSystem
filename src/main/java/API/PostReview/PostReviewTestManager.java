package API.PostReview;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

/***************
 *Nitin Mittal (12 Dec-2019)
 * This class is to maintain overall test of Post Review API by creating all the getting and setting here, So that we can have only maintainable and understandable code in Test Method.
 *
 */
public class PostReviewTestManager {

private String baseUri;
private String resource;
private String contentType;
private Map<String, String> pathParameterMap=new HashMap<String, String>();
private Map<String, String> queryParameterMap=new HashMap<String, String>();
private Map<String, String> headerMap=new HashMap<String, String>();
private PostReviewRequestPOJO bodyObj;
RequestSpecification requestSpecification;

private PostReviewTestManager()
{
    //Nothing required
}
//Singlton Pattern Implementation
private static PostReviewTestManager postReviewTestManagerObj;


public static PostReviewTestManager getInstance()

    {
        if (postReviewTestManagerObj!=null)
            return postReviewTestManagerObj;
        else
            return postReviewTestManagerObj=new PostReviewTestManager();
    }

    public PostReviewTestManager setContentType(String contentType)
    {
        postReviewTestManagerObj.contentType=contentType;
        return postReviewTestManagerObj;
    }

    //Builder Pattern implementation
    public PostReviewTestManager setBaseUri(String baseUri) {
        postReviewTestManagerObj.baseUri = baseUri;
        return postReviewTestManagerObj;
    }

    public PostReviewTestManager setPathParameters(Map<String, String> pathParameterMap) {
        postReviewTestManagerObj.pathParameterMap = pathParameterMap;
        return postReviewTestManagerObj;
    }

    public PostReviewTestManager setHeader(Map<String, String> headerMap) {
       postReviewTestManagerObj.headerMap = headerMap;
       return postReviewTestManagerObj;
    }

    public PostReviewTestManager setQueryParameterMap(Map<String, String> queryParameterMap) {
        postReviewTestManagerObj.queryParameterMap = queryParameterMap;
        return postReviewTestManagerObj;
    }

    public PostReviewTestManager setResource(String resource) {
        postReviewTestManagerObj.resource = resource;
        return postReviewTestManagerObj;

    }
    public PostReviewTestManager setBody(PostReviewRequestPOJO obj)
    {
        postReviewTestManagerObj.setBody(obj);
        return postReviewTestManagerObj;
    }


    public PostReviewTestManager build() throws Exception

    {
        if(baseUri==null)
        {
            throw new Exception("baseUri is null");
        }
        RestAssured.baseURI= baseUri;
        requestSpecification=
                given()
                    .pathParams(pathParameterMap)
                    .headers(headerMap)
                    .queryParams(queryParameterMap)
                    .contentType(contentType)
                    .body(bodyObj);

        return postReviewTestManagerObj;

    }

    public Response execute()
    {
        return requestSpecification
                .post(resource);

    }

}
