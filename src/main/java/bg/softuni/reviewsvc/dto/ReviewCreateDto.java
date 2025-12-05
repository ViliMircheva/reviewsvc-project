package bg.softuni.reviewsvc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class ReviewCreateDto {

    @NotNull
    private UUID villaId;

    @NotNull
    private UUID reviewerId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;

    public @NotNull UUID getVillaId() {
        return villaId;
    }

    public void setVillaId(@NotNull UUID villaId) {
        this.villaId = villaId;
    }

    public @NotNull UUID getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(@NotNull UUID reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Min(1)
    @Max(5)
    public int getRating() {
        return rating;
    }

    public void setRating(@Min(1) @Max(5) int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
