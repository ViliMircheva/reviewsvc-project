package bg.softuni.reviewsvc.repositories;

import bg.softuni.reviewsvc.entities.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReviewRepositoryIT {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testSaveAndFindByVillaId() {
        UUID villaId = UUID.randomUUID();

        Review r = new Review();
        r.setVillaId(villaId);
        r.setReviewerId(UUID.randomUUID());
        r.setRating(5);
        r.setComment("Test");
        reviewRepository.save(r);

        List<Review> result = reviewRepository.findAllByVillaId(villaId);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getComment()).isEqualTo("Test");
    }
}
