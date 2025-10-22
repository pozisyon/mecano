package com.annuaire.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
    @GetMapping({"/", "/admin", "/public", "/home"})
    public String forward() {
        return "forward:/index.html";
    }
}
