package Discipline.CineHub.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AwsS3Service {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final S3Client s3Client;

    public AwsS3Service(@Value("${aws.s3.region}") String region) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();
    }

    public URL upload(MultipartFile file, String directoryName) {
        try {
            String fileName = generateUniqueFileName(file.getOriginalFilename());
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(directoryName + "/" + fileName)
                    .build();
            PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));
            return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(request.key())).toURI().toURL();
        } catch (Exception e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    private String generateUniqueFileName(String originalFilename) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(originalFilename.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    @PreDestroy
    public void closeS3Client() {
        s3Client.close();
    }
}
