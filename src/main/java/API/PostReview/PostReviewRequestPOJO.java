package API.PostReview;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/***************
 *Nitin Mittal (12 Dec-2019)
 * This class is to maintain Request POJO of API.PostReview API
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "rating"
})
public class PostReviewRequestPOJO {

    @JsonProperty("text")
    private String text;
    @JsonProperty("rating")
    private int rating;


    @JsonProperty("text")
    public String getText() {
        return text;
    }
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("rating")
    public int getRating() {
        return rating;
    }
    @JsonProperty("rating")
    public void setRating(int rating) {
        this.rating = rating;
    }
}
