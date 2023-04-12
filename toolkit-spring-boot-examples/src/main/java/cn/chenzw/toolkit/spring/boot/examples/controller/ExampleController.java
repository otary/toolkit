package cn.chenzw.toolkit.spring.boot.examples.controller;

import cn.chenzw.toolkit.logging.annotation.MethodLogging;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzw
 */
@RestController
@RequestMapping("/examples")
public class ExampleController {

    @MethodLogging
    @GetMapping("/get")
    public String get(String name) {
        return "hello, " + name;
    }
}
