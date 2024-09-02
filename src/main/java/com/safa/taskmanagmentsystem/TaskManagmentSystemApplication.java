package com.safa.taskmanagmentsystem;

import com.safa.taskmanagmentsystem.config.YamlConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class TaskManagmentSystemApplication implements CommandLineRunner {

    private YamlConfig yamlConfig;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("YAML Properties " + yamlConfig.toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskManagmentSystemApplication.class, args);
    }

}
