package by.beresten.resources.service.resource;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface ResourceService {

    Integer saveResource(MultipartFile multipartFile);

    MultipartFile getResourceByKey(Integer key);

    Set<Integer> deleteResourcesByKey(Set<Integer> key);
}
