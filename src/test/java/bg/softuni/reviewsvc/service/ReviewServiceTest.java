package bg.softuni.reviewsvc.service;

import bg.softuni.reviewsvc.entities.Review;
import bg.softuni.reviewsvc.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void testGetReviewsByVillaDelegatesToRepository() {
        UUID villaId = UUID.randomUUID();
        when(reviewRepository.findAllByVillaId(villaId))
                .thenReturn(List.of(new Review()));

        List<Review> result = reviewService.getReviewsByVilla(villaId);

        assertThat(result).hasSize(1);
        verify(reviewRepository).findAllByVillaId(villaId);
    }
}
