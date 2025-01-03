package org.projects.TaskScheduler.models;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "task")
@Getter
@Setter
public class Task {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String taskId;

    @DynamoDBAttribute
    private String taskName;

    @DynamoDBAttribute
    private String description;

    @DynamoDBAttribute
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.S) //have to handle LocalDateTime differently to let dynamodb understand the format
    private LocalDateTime scheduleTime;

    @DynamoDBAttribute
    private boolean completed;

    @DynamoDBAttribute
    private List<String> emails;


}
