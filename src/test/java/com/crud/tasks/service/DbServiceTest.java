package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void shouldGetEmptyTaskList() {
        //Given
        List<Task> tasks = new ArrayList<>();

        when(dbService.getAllTasks()).thenReturn(tasks);

        //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(0, taskList.size());
    }

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task firstTask = new Task(1l, "Task title", "Task content");
        Task secondTask = new Task(2l, "Task title", "Task content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(firstTask);
        tasks.add(secondTask);

        when(dbService.getAllTasks()).thenReturn(tasks);

        //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(2, taskList.size());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task firstTask = new Task(1l, "Task title", "Task content");
        Task secondTask = new Task(2l, "Title task", "Content task");

        List<Task> tasks = new ArrayList<>();
        tasks.add(firstTask);
        tasks.add(secondTask);

        when(dbService.getTask(1l)).thenReturn(ofNullable(firstTask));
        when(dbService.getTask(2l)).thenReturn(ofNullable(secondTask));

        //When
        Optional<Task> resultedTask = dbService.getTask(2l);

        //Then
        assertEquals(secondTask.getId(), resultedTask.get().getId());
        assertEquals(secondTask.getTitle(), resultedTask.get().getTitle());
        assertEquals(secondTask.getContent(), resultedTask.get().getContent());
    }

    @Test
    public void shouldSaveTask() {
        //When
        Task task = new Task(1l, "New tittle", "New content");
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        //Then
        Task resultedTask = dbService.saveTask(task);

        //When
        assertEquals(task.getId(), resultedTask.getId());
        assertEquals(task.getTitle(), resultedTask.getTitle());
        assertEquals(task.getContent(), resultedTask.getContent());
    }

//    @Test
//    public void shouldDeleteTask() {
//        //Given
//        Task firstTask = new Task(1l, "Task title", "Task content");
//        Task secondTask = new Task(2l, "Task title", "Task content");
//        List<Task> tasks = new ArrayList<>();
//        tasks.add(firstTask);
//        tasks.add(secondTask);
//
//        //When
//        dbService.deleteById(2l);
//
//        //Then
//        assertFalse(tasks.contains(secondTask));
//    }
}
