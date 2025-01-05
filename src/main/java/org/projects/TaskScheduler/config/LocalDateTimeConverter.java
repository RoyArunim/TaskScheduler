package org.projects.TaskScheduler.config;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;

public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {
    @Override
    public String convert(LocalDateTime time) {
        return time != null ? time.toString() : null;
    }

    @Override
    public LocalDateTime unconvert(String stringValue) {
        return stringValue != null ? LocalDateTime.parse(stringValue) : null;
    }
}
