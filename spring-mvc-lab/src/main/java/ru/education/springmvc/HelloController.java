package ru.education.springmvc;

import java.time.OffsetDateTime;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(
            value = "/hello",
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"
    )
    public String hello(
            @RequestParam(name = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping(
            value = "/about",
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"
    )
    public String about() {
        return "Киприн Сергей, РИМ-250950";
    }

    @GetMapping(
            value = "/time",
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"
    )
    public String time() {
        return OffsetDateTime.now().toString();
    }

    @GetMapping(
            value = "/uppercase",
            produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"
    )
    public String uppercase(@RequestParam(name = "text") String text) {
        return text.toUpperCase();
    }
}
