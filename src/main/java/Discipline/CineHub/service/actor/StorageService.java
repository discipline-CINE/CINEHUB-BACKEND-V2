package Discipline.CineHub.service.actor;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
@Slf4j
public class StorageService {
  @Value("${aws.s3.bucketName}")
  private String bucketName;

  @Value("discipline-actor-male")
  private String bucketNameMale;

  @Value("discipline-actor-female")
  private String bucketNameFemale;

  private AmazonS3 s3Client;

  @Autowired
  public StorageService(AmazonS3 s3Client) {
    this.s3Client = s3Client;
  }

  public URL uploadFile(MultipartFile file) {
    File fileObj = convertMultiPartFileToFile(file);
    String tmpName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    String fileName = createFileName(tmpName);
    s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
    fileObj.delete();

    URL url = s3Client.getUrl(bucketName, fileName);

    return url;
  }

  public URL uploadFileMale(MultipartFile file) {
    File fileObj = convertMultiPartFileToFile(file);
    String tmpName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    String fileName = createFileName(tmpName);
    s3Client.putObject(new PutObjectRequest(bucketNameMale, fileName, fileObj));
    fileObj.delete();

    URL url = s3Client.getUrl(bucketName, fileName);

    return url;
  }

  public URL uploadFileFemale(MultipartFile file) {
    File fileObj = convertMultiPartFileToFile(file);
    String tmpName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    String fileName = createFileName(tmpName);
    s3Client.putObject(new PutObjectRequest(bucketNameFemale, fileName, fileObj));
    fileObj.delete();

    URL url = s3Client.getUrl(bucketName, fileName);

    return url;
  }

  public byte[] downloadFile(String fileName) {
    S3Object s3Object = s3Client.getObject(bucketName, fileName);
    S3ObjectInputStream inputStream = s3Object.getObjectContent();
    try {
      byte[] content = IOUtils.toByteArray(inputStream);
      return content;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String deleteFile(String fileName) {
    s3Client.deleteObject(bucketName, fileName);
    return fileName + " removed ...";
  }

  private File convertMultiPartFileToFile(MultipartFile file) {
    File convertedFile = new File(file.getOriginalFilename());
    try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.error("Error converting multipartFile to file", e);
    }
    return convertedFile;
  }

  // 파일 이름이 같으면 저장이 안 된다. 따라서 파일이름 앞에 UUID를 붙인다.
  private String createFileName(String fileName){
    return UUID.randomUUID() + fileName;
  }

}





















