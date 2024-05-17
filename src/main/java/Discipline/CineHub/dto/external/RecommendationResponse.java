package Discipline.CineHub.dto.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Setter
@Getter
@Data
public class RecommendationResponse {
    private Long id;
    private String url;

    public RecommendationResponse(Long id, String url) {
        this.id = id;
        this.url = url;
    }

}
