package com.example.springrabbitmqproducer.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketData {

	private String bucketName;
	private String inboundFolder;
	private String archiveFolder;
	private String errorFolder;
	private String fileName;
	private String data;
	private String eventName;
	private String fileNameCheck;
	private List<String> keys;
	private String countryCode;
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getInboundFolder() {
		return inboundFolder;
	}
	public void setInboundFolder(String inboundFolder) {
		this.inboundFolder = inboundFolder;
	}
	public String getArchiveFolder() {
		return archiveFolder;
	}
	public void setArchiveFolder(String archiveFolder) {
		this.archiveFolder = archiveFolder;
	}
	public String getErrorFolder() {
		return errorFolder;
	}
	public void setErrorFolder(String errorFolder) {
		this.errorFolder = errorFolder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getFileNameCheck() {
		return fileNameCheck;
	}
	public void setFileNameCheck(String fileNameCheck) {
		this.fileNameCheck = fileNameCheck;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
