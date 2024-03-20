package com.example.springrabbitmqproducer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springrabbitmqproducer.config.AwsAppConfig;
import com.example.springrabbitmqproducer.model.BucketData;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtil {
	
	@Autowired
	private AwsAppConfig awsAppConfig;
	
	public BucketData getBucketData() {
		BucketData bucketData=new BucketData();
		bucketData.setBucketName(awsAppConfig.getAwsS3BucketName());
		bucketData.setInboundFolder(awsAppConfig.getAwsS3BucketFolder());
		bucketData.setArchiveFolder(awsAppConfig.getAwsS3OutboundArchivePath());
		bucketData.setErrorFolder(awsAppConfig.getAwsS3OutboundErrorPath());
		return bucketData;
	}

}
