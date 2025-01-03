package org.projects.TaskScheduler.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.projects.TaskScheduler.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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

}
