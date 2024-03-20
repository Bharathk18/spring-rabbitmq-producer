package com.example.springrabbitmqproducer.batch;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.example.springrabbitmqproducer.model.BucketData;
import com.example.springrabbitmqproducer.service.AwsS3Service;
import com.example.springrabbitmqproducer.service.FileProcessService;
import com.example.springrabbitmqproducer.util.CommonUtil;

public class FileProcessingTasklet implements Tasklet {

	
	@Autowired
	private AmazonS3 amazonS3;
	
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private AwsS3Service awsS3Service;
	
	@Autowired
	private FileProcessService fileProcessService;
	
	public FileProcessingTasklet(AmazonS3 amazonS3) {
		this.amazonS3=amazonS3;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		BucketData bucketData= commonUtil .getBucketData();
		List<String> keys = awsS3Service.getS3Keys(bucketData.getInboundFolder());
		if(keys!=null && keys.size()!=0) {
			keys.forEach(key->{
				String fileName=new File(key).getName();
				processFile(readS3Object(key,fileName));
			});
		}
		
		System.out.println("batch run scuccessfully");
		return null;
	}

	private void processFile(BucketData bucketData) {
		fileProcessService.processingFiles(bucketData);
	}

	private BucketData readS3Object(String key,String fileName) {
		BucketData bucketdData=commonUtil.getBucketData();
		S3Object object = amazonS3.getObject(new GetObjectRequest(bucketdData.getBucketName(), key));
		InputStream objectData= object.getObjectContent();
		  try {
			byte[] fileContents= IOUtils.toByteArray(objectData);
			String fileData=new String(fileContents);
			bucketdData.setData(fileData);
			bucketdData.setFileName(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bucketdData;
	}
	
	
}
