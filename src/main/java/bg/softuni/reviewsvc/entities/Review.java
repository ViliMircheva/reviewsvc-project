package bg.softuni.reviewsvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private UUID villaId;

    @NotNull
    @Column(nullable = false)
    private UUID reviewerId;

    @Min(1) @Max(5)
    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
