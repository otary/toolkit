package cn.chenzw.toolkit.spring.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @GetMapping("/say")
    public String say() {
        return "hello!";
    }

    @PostMapping("/receive")
    public String receive(@RequestBody String msg) {
        return msg;
    }
}
