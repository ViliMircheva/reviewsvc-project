package bg.softuni.reviewsvc.web;

import bg.softuni.reviewsvc.entities.Review;
import bg.softuni.reviewsvc.dto.ReviewCreateDto;
import bg.softuni.reviewsvc.dto.ReviewDto;
import bg.softuni.reviewsvc.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController { private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{villaId}")
    public List<ReviewDto> getReviews(@PathVariable UUID villaId) {
        return reviewService.getReviewsByVilla(villaId)
                .stream()
                .map(r -> {
                    ReviewDto dto = new ReviewDto();
                    dto.setId(r.getId());
                    dto.setVillaId(r.getVillaId());
                    dto.setReviewerId(r.getReviewerId());
                    dto.setRating(r.getRating());
                    dto.setComment(r.getComment());
                    dto.setCreatedOn(r.getCreatedOn());
                    return dto;
                })
                .toList();
    }

    @PostMapping
    public UUID createReview(@Valid @RequestBody ReviewCreateDto dto) {
        Review r = new Review();
        r.setVillaId(dto.getVillaId());
        r.setReviewerId(dto.getReviewerId());
        r.setRating(dto.getRating());
        r.setComment(dto.getComment());
        return reviewService.save(r).getId();
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable UUID id) {
        reviewService.deleteById(id);
    }

    @DeleteMapping("/villa/{villaId}")
    public void deleteAllForVilla(@PathVariable UUID villaId) {
        reviewService.deleteAllByVillaId(villaId);
    }

}
