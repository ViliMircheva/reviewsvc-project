package bg.softuni.reviewsvc.web;

import bg.softuni.reviewsvc.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void testCreateAndGetReviews() throws Exception {
        UUID villaId = UUID.randomUUID();
        UUID reviewerId = UUID.randomUUID();

        String json = """
                {
                  "villaId":"%s",
                  "reviewerId":"%s",
                  "rating":5,
                  "comment":"Super"
                }
                """.formatted(villaId, reviewerId);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/reviews/" + villaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comment").value("Super"));
    }
}
