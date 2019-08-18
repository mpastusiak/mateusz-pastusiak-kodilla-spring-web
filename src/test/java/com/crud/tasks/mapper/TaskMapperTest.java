package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test description");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("test title", task.getTitle());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "test title", "test description");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("test title", taskDto.getTitle());
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "test title", "test description");
        Task task2 = new Task(2L, "test title no 2", "desctiption");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        ArrayList<TaskDto> tasksDtos = new ArrayList<>();

        //When
        tasksDtos.addAll(taskMapper.mapToTaskDtoList(tasks));

        //Then
        assertEquals(2, tasksDtos.size());
    }
}