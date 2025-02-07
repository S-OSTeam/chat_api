package sos.chat_api.domain.s3code;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsFileService {

    private final AmazonS3Client amazonS3Client;
    private  String bucketName;

    public String upload(MultipartFile file) {
        if(file.isEmpty()){
            throw new AmazonS3Exception("File is empty");
        }

        return this.uploadFile(file);
    }

    private String uploadFile(MultipartFile file) {
        this.validateFile(file.getOriginalFilename());
        try{
            return this.uploadToS3(file);
        }catch (IOException e){
            throw new AmazonS3Exception("Failed to upload file", e);
        }
    }

    private void validateFile(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if(lastDot == -1){
            throw new AmazonS3Exception("Invalid file name");
        }

        String extension = fileName.substring(lastDot + 1).toLowerCase();
        List<String> allowdExtension = Arrays.asList("jpg","jpeg","png","gif");

        if(!allowdExtension.contains(extension)){
            throw new AmazonS3Exception("Invalid file name");
        }
    }


    private String uploadToS3(MultipartFile file) throws IOException {
        String orginalFilename = file.getOriginalFilename();
        String extension = orginalFilename.substring(orginalFilename.lastIndexOf('.'));

        String s3FileName = UUID.randomUUID().toString().substring(0,10) + orginalFilename;

        InputStream inputStream = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try{
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,s3FileName,byteArrayInputStream,objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3Client.putObject(putObjectRequest);
        }catch(Exception e){
            throw new AmazonS3Exception("Failed to upload file", e);
        }finally {
            byteArrayInputStream.close();
            inputStream.close();
        }

        return amazonS3Client.getUrl(bucketName,s3FileName).toString();
    }

}
