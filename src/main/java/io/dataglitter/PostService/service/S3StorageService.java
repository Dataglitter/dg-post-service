package io.dataglitter.PostService.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import io.dataglitter.PostService.config.S3Configuration;
import io.dataglitter.PostService.repository.FileStorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by reddys on 04/12/2018.
 */
@Service
public class S3StorageService implements FileStorageRepository {

    private AmazonS3 amazonS3;

    private S3Configuration s3Configuration;

    private TransferManager transferManager;

    private Random rand = new Random();

    public S3StorageService(S3Configuration s3Configuration) {
        this.s3Configuration = s3Configuration;
        this.amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(s3Configuration.getRegion())
                .build();

        transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .withMultipartUploadThreshold((long) (5 * 1024 * 1025))
                .build();
    }

    @Override
    public String store(MultipartFile file, Boolean profilePic) throws IOException {

        String filename = rand.nextInt(10000) + "_" + StringUtils.cleanPath(file.getOriginalFilename());

        if(profilePic){
            filename = this.s3Configuration.getProfile() + filename;
        } else {
            filename = this.s3Configuration.getPost() + filename;
        }

        File transformedFile = this.convertMultiPartToFile(file);

        this.amazonS3.putObject(
                new PutObjectRequest(this.s3Configuration.getAssetBucketName(), filename, transformedFile)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        transformedFile.delete();

        return filename;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
