package by.beresten.resources.service.resource;

import by.beresten.resources.model.Resource;
import by.beresten.resources.repository.ResourceRepository;
import by.beresten.resources.service.s3.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService{

    private final S3Service s3Service;
    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceServiceImpl(S3Service s3Service, ResourceRepository resourceRepository) {
        this.s3Service = s3Service;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Integer saveResource(MultipartFile multipartFile) {
        var key = s3Service.upload(multipartFile);
        var resource = new Resource();
        resource.setS3Key(key);
        var resourceId = resourceRepository.save(resource).getId();
        return resourceId;
    }

    @Override
    public MultipartFile getResourceByKey(Integer key) {
        return null;
    }

    @Override
    public Set<Integer> deleteResourcesByKey(Set<Integer> key) {
        return null;
    }
}
