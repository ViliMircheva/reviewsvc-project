package bg.softuni.reviewsvc.service;

import bg.softuni.reviewsvc.entities.Review;
import bg.softuni.reviewsvc.repositories.ReviewRepository;
import bg.softuni.reviewsvc.web.errors.ReviewNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "reviewsByVilla", key = "#villaId")
    public List<Review> getReviewsByVilla(UUID villaId) {
        log.info("Loading reviews from DB for villa {}", villaId);
        return reviewRepository.findAllByVillaId(villaId);
    }

    @CacheEvict(cacheNames = "reviewsByVilla", key = "#review.villaId")
    public Review save(Review review) {
        log.info("Saving new review for villa {} by reviewer {}",
                review.getVillaId(), review.getReviewerId());
        return reviewRepository.save(review);
    }

    @CacheEvict(cacheNames = "reviewsByVilla", allEntries = true)
    @Transactional
    public void deleteById(UUID id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review " + id + " not found");
        }
        log.info("Deleting single review {}", id);
        reviewRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "reviewsByVilla", key = "#villaId")
    @Transactional
    public void deleteAllByVillaId(UUID villaId) {
        log.info("Deleting ALL reviews for villa {}", villaId);
        List<Review> all = reviewRepository.findAllByVillaId(villaId);
        reviewRepository.deleteAll(all);
    }
}
