package by.beresten.resources.service.s3;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Set;

public interface S3Service {

    String upload(MultipartFile multipartFile);

    InputStream getResourceByKey(String key);

    Set<String> deleteResourcesByKey(Set<String> setKeys);
}
