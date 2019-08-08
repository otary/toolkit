package cn.chenzw.toolkit.spring.controllers;

import cn.chenzw.toolkit.domain.dto.UserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 参数校验
 */
@RestController
@RequestMapping("/validation")
@Validated
public class ValidationController {

    @GetMapping("/throw-bind-exception")
    public void throwBindException(@Validated UserDto userDto) {

    }

    @PostMapping("/throw-method-argument-not-valid-exception")
    public void throwMethodArgumentNotValidException(@RequestBody @Validated UserDto userDto) {

    }

    @GetMapping("/throw-constraint-violation-exception")
    public void throwConstraintViolationException(@RequestParam(required = false) @NotNull String name) {

    }
}
