package Discipline.CineHub.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendationDto {

    @JsonProperty("imagePath")
    private String imagePath;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("url")
    private String url;

    @JsonProperty("input_image")
    private String inputImage;
}