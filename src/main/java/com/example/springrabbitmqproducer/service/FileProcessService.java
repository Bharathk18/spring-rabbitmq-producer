package com.example.springrabbitmqproducer.service;

import java.util.Scanner;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springrabbitmqproducer.config.RabbitMqConfig;
import com.example.springrabbitmqproducer.model.BucketData;
import com.example.springrabbitmqproducer.model.DealerType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(transactionManager = "transactionManager")
@SuppressWarnings("squid:S1226")
public class FileProcessService {

	
	@Autowired
	private AwsS3Service awsS3Service;
	
	@Autowired
	private RabbitTemplate template;
	
	private void processingFiles(BucketData bucketData) {
		try(Scanner scanner=new Scanner(bucketData.getData())){
			while(scanner.hasNextLine()) {
				String line=scanner.nextLine();
				if(line!=null) {
					line=line.replace("\"", "");
					String[] values = line.split("\\|", -1);
					if (!values[0].equals("POITypeCd") || !values[1].equals("POITypeName")
							|| !values[2].equals("POITypeDescription")){
						DealerType dealerType=new DealerType();
						dealerType.setPoiTypeCd(values[0]);
						dealerType.setPoiTypeName(values[1]);
						dealerType.setPoiTypeDescription(values[2]);
						template.convertAndSend(RabbitMqConfig.exchange, RabbitMqConfig.routingKey, dealerType);
						
				}
			}
		}
			//moving the files to archive
			awsS3Service.uploadFile(bucketData);
			
			//then delete the file
			awsS3Service.deleteFromInboundS3(bucketData);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
}
