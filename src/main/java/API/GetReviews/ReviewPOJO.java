package API.GetReviews;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/***************
 *Nitin Mittal (12 Dec-2019)
 * This class contains POJO of single Review output
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "rating",
        "language"
})
public class ReviewPOJO {

    @JsonProperty("text")
    private String text;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("language")
    private String language;

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


    @JsonProperty("language")
    public String getLanguage()
    {
        return language;
    }
    @JsonProperty("language")
    public void setLanguage(String language)
    {
        this.language=language;
    }
}
