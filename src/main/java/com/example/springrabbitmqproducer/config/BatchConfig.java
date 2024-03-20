package com.example.springrabbitmqproducer.config;


import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.amazonaws.services.s3.AmazonS3;
import com.example.springrabbitmqproducer.batch.FileProcessingTasklet;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Configuration
@AllArgsConstructor
@NoArgsConstructor
@EnableBatchProcessing
@EnableScheduling
@Slf4j
@PropertySource("classpath:/application.properties")
public class BatchConfig {


	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job batchjob;

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	public AmazonS3 reader() throws IOException {
		return appContext.getBean(AmazonS3.class);
	}
	

	/**
	 * 
	 */
	
	@Scheduled(cron = "${cron-expression}")
	public void perform()  {
		
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
	                .toJobParameters();
			jobLauncher.run(batchjob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			//log.error("BatchConfig.perform",e);
		}
	}

	/**
	 * @param amazonS3
	 * @return
	 */
	@Bean
	public FileProcessingTasklet fileProcessingTasklet(AmazonS3 amazonS3) {
		return new FileProcessingTasklet(amazonS3);
	}

	/**
	 * @return
	 */
	@Bean
	public Step stepOne() {
		return steps.get("stepOne").tasklet(fileProcessingTasklet(amazonS3)).build();
	}

	/**
	 * @return
	 */
	@Bean
	public Job job() {
		return jobs.get("job").incrementer(new RunIdIncrementer()).start(stepOne())

				.build();
	}

}
