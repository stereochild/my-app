package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskMapperTest {

    @Test
    public void testMapToTask() {
        //Given
        TaskMapper mapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1l, "Do Task", "Do this task very careful");

        //When
        Task task = mapper.mapToTask(taskDto);

        //Then
        assertEquals((Long)1l, task.getId());
        assertEquals("Do Task", task.getTitle());
        assertEquals("Do this task very careful", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        TaskMapper mapper = new TaskMapper();
        Task task = new Task(2l, "Do Task", "..." );

        //When
        TaskDto taskDto = mapper.mapToTaskDto(task);

        //Then
        assertEquals((Long)2l, taskDto.getId());
        assertEquals("Do Task", taskDto.getTitle());
        assertEquals("...", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(3l, "Do", "your job"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, taskDtoList.size());
        assertEquals((Long)3l, taskDtoList.get(0).getId());
        assertEquals("Do", taskDtoList.get(0).getTitle());
        assertEquals("your job", taskDtoList.get(0).getContent());
    }
}
