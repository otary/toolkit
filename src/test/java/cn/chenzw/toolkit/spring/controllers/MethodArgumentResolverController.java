package cn.chenzw.toolkit.spring.controllers;

import cn.chenzw.toolkit.domain.entity.User;
import cn.chenzw.toolkit.spring.resolver.argument.annotation.RequestBodyBase64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method-args-resolver")
public class MethodArgumentResolverController {

    @PostMapping("/base64-body")
    public User base64BodyArgs(@RequestBodyBase64 User user) {
        return user;
    }
}
