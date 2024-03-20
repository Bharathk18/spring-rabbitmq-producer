package com.example.springrabbitmqproducer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@ConfigurationProperties(prefix = "app")
@NoArgsConstructor
@Component
@Getter
@Setter
public class AwsAppConfig {
	private String awsAccessKeyId;
	private String awsSecretAccessKey;
	private String awsS3BucketName;
	private String awsS3Region;
	private String awsS3BucketFolder;
	private String awsS3OutboundArchivePath;
	private String awsS3OutboundErrorPath;
	public String getAwsAccessKeyId() {
		return awsAccessKeyId;
	}
	public void setAwsAccessKeyId(String awsAccessKeyId) {
		this.awsAccessKeyId = awsAccessKeyId;
	}
	public String getAwsSecretAccessKey() {
		return awsSecretAccessKey;
	}
	public void setAwsSecretAccessKey(String awsSecretAccessKey) {
		this.awsSecretAccessKey = awsSecretAccessKey;
	}
	public String getAwsS3BucketName() {
		return awsS3BucketName;
	}
	public void setAwsS3BucketName(String awsS3BucketName) {
		this.awsS3BucketName = awsS3BucketName;
	}
	public String getAwsS3Region() {
		return awsS3Region;
	}
	public void setAwsS3Region(String awsS3Region) {
		this.awsS3Region = awsS3Region;
	}
	public String getAwsS3BucketFolder() {
		return awsS3BucketFolder;
	}
	public void setAwsS3BucketFolder(String awsS3BucketFolder) {
		this.awsS3BucketFolder = awsS3BucketFolder;
	}
	public String getAwsS3OutboundArchivePath() {
		return awsS3OutboundArchivePath;
	}
	public void setAwsS3OutboundArchivePath(String awsS3OutboundArchivePath) {
		this.awsS3OutboundArchivePath = awsS3OutboundArchivePath;
	}
	public String getAwsS3OutboundErrorPath() {
		return awsS3OutboundErrorPath;
	}
	public void setAwsS3OutboundErrorPath(String awsS3OutboundErrorPath) {
		this.awsS3OutboundErrorPath = awsS3OutboundErrorPath;
	}
	

}
