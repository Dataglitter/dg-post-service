package io.dataglitter.PostService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by reddys on 04/12/2018.
 */
@Configuration
public class S3Configuration {

    @Value("${aws.s3.assetBucketName}")
    private String assetBucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.profile}")
    private String profile;

    @Value("${aws.s3.post}")
    private String post;

    public String getAssetBucketName() {
        return assetBucketName;
    }

    public String getRegion() {
        return region;
    }

    public String getProfile() {
        return profile;
    }

    public String getPost() {
        return post;
    }
}
