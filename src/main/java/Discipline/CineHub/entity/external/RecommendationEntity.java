package Discipline.CineHub.entity.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@Getter
@Data
@Entity
@NoArgsConstructor
@Table(name = "recommendation")
public class RecommendationEntity {

    @Id
    @JsonIgnore
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "distance")
    private double distance;

    @Column(name = "url")
    private String url;

    @Column(name = "input_image")  // 새로운 필드 추가
    private String inputImage;
}
