package org.projects.TaskScheduler.repositories;

import org.projects.TaskScheduler.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TaskRepository extends CrudRepository<Task, UUID> {

}
