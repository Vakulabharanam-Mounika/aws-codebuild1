package com.example.demo;

import com.example.demo.model.Todo;
import com.example.demo.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TodoService1Application implements CommandLineRunner {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService1Application(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoService1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        todoRepository.save(new Todo(1,"compile",true));
        todoRepository.save(new Todo(2,"testing",true));
        todoRepository.save(new Todo(3,"debug",false));
        todoRepository.save(new Todo(4,"deploy",false));
    }
}
