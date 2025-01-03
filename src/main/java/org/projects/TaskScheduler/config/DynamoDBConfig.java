package org.projects.TaskScheduler.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {
    @Value("${aws.serviceEndpoint}")
    private String serviceEndpoint;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;
    @Bean
    public DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(buildAmazonDynamoDb());
    }

    public AmazonDynamoDB buildAmazonDynamoDb(){
        return  AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        serviceEndpoint,
                        region
                ))
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        accessKey,
                                        secretKey
                                )
                        )
                ).build();

    }

}
