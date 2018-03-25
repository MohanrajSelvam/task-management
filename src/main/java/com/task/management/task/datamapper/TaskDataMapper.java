package com.task.management.task.datamapper;

import com.task.management.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDataMapper extends JpaRepository<Task,Long> {
}
