package com.base.alexjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    @GetMapping("/th")
    public String thymeStart() {
        return "hello";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Alex") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
