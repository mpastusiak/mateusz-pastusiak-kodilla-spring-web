package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    private static final Integer ONE = 1;
    private static final Integer TWO = 2;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My thymeleaf variable");
        model.put("one", ONE);
        model.put("two", TWO);
        model.put("twoToString", TWO.toString());
        return "index";
    }
}
