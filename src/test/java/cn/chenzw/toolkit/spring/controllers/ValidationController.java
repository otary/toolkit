package cn.chenzw.toolkit.spring.controllers;

import cn.chenzw.toolkit.domain.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @GetMapping("/throw-bind-exception")
    public void throwBindException(@Validated UserDto userDto) {

    }
}
