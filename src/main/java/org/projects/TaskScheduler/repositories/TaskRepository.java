package org.projects.TaskScheduler.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Repository
public class TaskRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public Task saveTask(Task task){
        dynamoDBMapper.save(task);
        return task;
    }

    public Task getTaskById(String taskId){
        System.out.println("Task ID: " + taskId + ", Type: " + taskId.getClass().getSimpleName());
        return dynamoDBMapper.load(Task.class, taskId);
    }

    public String addUserEmail(String taskId, String emailId){
        Task task = dynamoDBMapper.load(Task.class, taskId);
        task.getEmails().add(emailId);
        dynamoDBMapper.save(task, new DynamoDBSaveExpression()
                .withExpectedEntry("taskId", new ExpectedAttributeValue(
                        new AttributeValue().withS(taskId)
                )));
        return "Email id has been added";
    }

    public String delete(String taskId){
        Task task = dynamoDBMapper.load(Task.class, taskId);
        dynamoDBMapper.delete(task);
        return "task is deleted";
    }

    public String update(Task task, String taskId){
    dynamoDBMapper.save(task, new DynamoDBSaveExpression()
            .withExpectedEntry("taskId", new ExpectedAttributeValue(
                    new AttributeValue().withS(taskId)
            )));
    return taskId;
    }

    public List<Task> findTasksDueforNotification(LocalDateTime currentTime){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("scheduleTime <= :currentTime AND completed = :completed")
                .withExpressionAttributeValues(Map.of(
                        ":currentTime", new AttributeValue().withS(currentTime.toString()),
                                ":completed", new AttributeValue().withBOOL(false)
                ));
        return dynamoDBMapper.scan(Task.class, scanExpression);
    }

}
