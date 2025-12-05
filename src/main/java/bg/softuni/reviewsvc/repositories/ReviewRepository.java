package bg.softuni.reviewsvc.repositories;

import bg.softuni.reviewsvc.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByVillaId(UUID villaId);
}
