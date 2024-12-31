package org.projects.TaskScheduler.models;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Task {
    private UUID taskId;
    private String taskName;
    private String description;
    private LocalDateTime scheduleTime;
    private boolean completed;
    private List<String> emails;

    public UUID getTaskId(){
        return taskId;
    }

    public String getTaskName(){
        return taskName;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getScheduleTime(){
        return scheduleTime;
    }

    public List<String> getEmails(){
        return emails;
    }
    public boolean getCompleted(){
        return completed;
    }

    public void setTaskId(UUID taskId){
        this.taskId=taskId;
    }

    public void setTaskName(String name){
        this.taskName=name;
    }

    public void setDescription(String desc){
        this.description=desc;
    }

    public void setScheduleTime(LocalDateTime time){
        this.scheduleTime=time;
    }

    public void setCompleted(boolean completed){
        this.completed=completed;
    }

    public void setEmails(List<String> emailids){
        emails.addAll(emailids);
    }

}
