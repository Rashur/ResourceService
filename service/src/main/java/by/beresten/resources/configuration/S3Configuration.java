package by.beresten.resources.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:application.yaml")
public class S3Configuration {

    @Value("${aws.s3.endpoint}")
    private String s3Endpoint;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.s3.access.key}")
    private String s3AccessKey;

    @Value("${aws.s3.access.id}")
    private String s3AccessKeyId;


    @Bean
    public AmazonS3 getAmazonS3() {
        var s3Credentials = new BasicAWSCredentials(s3AccessKeyId, s3AccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Endpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(s3Credentials))
                .withPathStyleAccessEnabled(true)
                .build();
    }
}
