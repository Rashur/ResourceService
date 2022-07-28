package by.beresten.resources.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Set;

@Service
public class S3ServiceImpl implements S3Service {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    @Autowired
    public S3ServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @SneakyThrows
    @Override
    public String upload(MultipartFile multipartFile) {
        var metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        amazonS3.putObject(bucketName,
                multipartFile.getOriginalFilename(),
                multipartFile.getInputStream(),
                metadata);
        return multipartFile.getOriginalFilename();
    }

    @Override
    public InputStream getResourceByKey(String key) {
        return amazonS3.getObject(bucketName, key).getObjectContent().getDelegateStream();
    }

    @Override
    public Set<String> deleteResourcesByKey(Set<String> setKeys) {
        setKeys.forEach(key -> amazonS3.deleteObject(bucketName, key));
        return setKeys;
    }
}
