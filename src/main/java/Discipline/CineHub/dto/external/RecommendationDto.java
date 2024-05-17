package Discipline.CineHub.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class RecommendationDto {

    private Long id;

    @JsonProperty("inputImage")
    private String inputImage;

    @JsonProperty("url")
    private String url;

    @JsonProperty("imagePath")
    private String imagePath;

    @JsonProperty("distance")
    private Double distance;

    public RecommendationDto(Long id, @JsonProperty("inputImage") String inputImage, @JsonProperty("url") String url) {
        this.id = id;
        this.inputImage = inputImage;
        this.url = url;
    }
}