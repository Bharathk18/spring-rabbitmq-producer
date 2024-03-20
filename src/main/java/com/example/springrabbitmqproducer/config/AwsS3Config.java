package com.example.springrabbitmqproducer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class AwsS3Config {
	@Autowired
	private Environment env;
	/**
	 * @return
	 * Gets the AWS S3 details
	 * and credentials
	 */
	/**
	 * @return
	 */
	@Primary
	@Bean
	public AmazonS3 getAmazonS3Cient() {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(env.getProperty("awsAccessKeyId"),
				env.getProperty("awsSecretAccessKey"));
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(env.getProperty("awsS3Region")))
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
	}
}
