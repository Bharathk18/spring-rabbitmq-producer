package com.example.springrabbitmqproducer.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.springrabbitmqproducer.config.AwsAppConfig;
import com.example.springrabbitmqproducer.model.BucketData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AwsS3Service {

	@Autowired
	private AwsAppConfig awsAppConfig;
	
	@Autowired
	private AmazonS3 amazonS3;

	
	public List<String> getS3Keys(final String bucketFolder) {
		ObjectListing objectListing = amazonS3.listObjects(awsAppConfig.getAwsS3BucketName(), bucketFolder);

		List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
		List<String> keys = objectSummaries.stream().map(objectSummary -> objectSummary.getKey())
				.collect(Collectors.toList());

		return keys;
	}
	
	public void uploadFile(BucketData bucketData) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
		
		CopyObjectRequest request = new CopyObjectRequest(bucketData.getBucketName(), bucketData.getInboundFolder()+bucketData.getFileName(), bucketData.getBucketName(),
				bucketData.getArchiveFolder()+bucketData.getFileName());
		request.setNewObjectMetadata(objectMetadata);
				amazonS3.copyObject(request);
	}

	public void deleteFromInboundS3(BucketData bucketData) {
		amazonS3.deleteObject(new DeleteObjectRequest(bucketData.getBucketName(), bucketData.getInboundFolder()+bucketData.getFileName()));
	} 
	
}
