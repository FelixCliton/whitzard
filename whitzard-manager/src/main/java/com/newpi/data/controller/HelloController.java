package com.newpi.data.controller;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2020/11/22 6:41 PM
 * @desc:
 */
@RestController
@Slf4j
@RestControllerAdvice
@RequestMapping("hello")
public class HelloController {

    @GetMapping("sayHello")
    public String sayHello() {
        return "hello";
    }


    @GetMapping("sayHi")
    public String sayHi(@RequestParam("name") String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new RuntimeException();
        }
        return "hi";
    }

    @GetMapping("hpa-test-cpu")
    public String hpaTestCpu() {
        float x = 0.0001F;
        for (int i = 0; i <= 1000000; i++) {
            x += Math.sqrt(x);
        }
        return "OK!";
    }

    @GetMapping("hpa-test-cpu")
    public String hpaTestMemory() {
        float x = 0.0001F;
        for (int i = 0; i <= 1000000; i++) {
            x += Math.sqrt(x);
        }
        return "OK!";
    }
}
