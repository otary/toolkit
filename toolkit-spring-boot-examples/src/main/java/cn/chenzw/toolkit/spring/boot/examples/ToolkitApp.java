package cn.chenzw.toolkit.spring.boot.examples;

import cn.chenzw.toolkit.spring.boot.starter.annotation.EnableToolkit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenzw
 */
@EnableToolkit
@SpringBootApplication
public class ToolkitApp {

    public static void main(String[] args) {
        SpringApplication.run(ToolkitApp.class);
    }
}
