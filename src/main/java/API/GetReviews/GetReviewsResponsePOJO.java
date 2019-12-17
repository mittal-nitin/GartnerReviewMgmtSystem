package API.GetReviews;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
/***************
 *Nitin Mittal (12 Dec-2019)
 * This class contains POJO of API.GetReviews API
 *
 */



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "reviewRecords"
})
public class GetReviewsResponsePOJO {

    @JsonProperty("reviewRecords")
    private List<ReviewPOJO> reviewPOJOS = new ArrayList<ReviewPOJO>();

    @JsonProperty("reviewRecords")
    public List<ReviewPOJO> getReviewPOJOS()
    {
        return reviewPOJOS;
    }
    public void setReviewPOJOS(List<ReviewPOJO> reviewPOJOS)
    {
        this.reviewPOJOS=reviewPOJOS;
    }


}
