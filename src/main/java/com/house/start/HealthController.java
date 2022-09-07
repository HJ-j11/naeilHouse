package com.house.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// healthCheck가 가능한 컨트롤러
@RestController
public class HealthController {
    @GetMapping
    public String healthCheck() {
        return "health ok";
    }
}
