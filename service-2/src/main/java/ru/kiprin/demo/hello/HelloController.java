package ru.kiprin.demo.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    private List<String> stringList;
    private Map<Integer, String> stringMap;

    @GetMapping("/update-array")
    public List<String> updateArrayList(@RequestParam("s") String s) {
        if (stringList == null) {
            stringList = new ArrayList<>();
        }
        stringList.add(s);
        return stringList;
    }

    @GetMapping("/show-array")
    public List<String> showArrayList() {
        return stringList;
    }

    @GetMapping("/update-map")
    public Map<Integer, String> updateHashMap(@RequestParam("s") String s) {
        if (stringMap == null) {
            stringMap = new HashMap<>();
        }
        stringMap.put(stringMap.size(), s);
        return stringMap;
    }

    @GetMapping("/show-map")
    public Map<Integer, String> showHashMap() {
        return stringMap;
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        int listSize = (stringList == null) ? 0 : stringList.size();
        int mapSize = (stringMap == null) ? 0 : stringMap.size();
        return "ArrayList: " + listSize + ", HashMap: " + mapSize;
    }
}
