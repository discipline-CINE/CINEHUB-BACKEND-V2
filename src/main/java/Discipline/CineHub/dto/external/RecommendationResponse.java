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
    private String username;
    private String name;
    private String url;

    public RecommendationResponse(Long id, String username, String name, String url) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.url = url;
    }

}
