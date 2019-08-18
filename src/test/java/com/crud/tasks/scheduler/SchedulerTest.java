package com.crud.tasks.scheduler;

import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerTest {
    @Autowired
    EmailScheduler emailScheduler;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void countTasksToEmailTest() {
        //Given
        long countFromTaskRepository = taskRepository.count();

        //When
        String countFromEmailScheduler = emailScheduler.countTasksToEmail();

        //Then
        assertThat(countFromEmailScheduler).isIn(countFromTaskRepository + " task", countFromTaskRepository + " tasks");
    }
}
