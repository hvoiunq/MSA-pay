package com.fastcampuspay.membership;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    void test() {
        System.out.println("Hello World!");
    }

}
