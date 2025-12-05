package bg.softuni.reviewsvc.scheduling;

import bg.softuni.reviewsvc.repositories.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ReviewMaintenanceScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(ReviewMaintenanceScheduler.class);

    private final ReviewRepository reviewRepository;

    public ReviewMaintenanceScheduler(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Scheduled(fixedRate = 60_000)
    public void logReviewStats() {
        long count = reviewRepository.count();
        log.info("[SCHEDULER] Total reviews in DB: {}", count);
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void nightlyHealthCheck() {
        log.info("[SCHEDULER] Nightly health check at {}", LocalDateTime.now());
    }
}
