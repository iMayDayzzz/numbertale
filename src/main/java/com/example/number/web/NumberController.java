package com.example.number.web;

import com.example.number.model.NumberTale;
import com.example.number.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NumberController {

    @Autowired
    private NumberService service;

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        NumberTale number = service.get(id);
        model.addAttribute("number", number);
        return "number";
    }

    @GetMapping("/top-10")
    public String getTopNumbers(Model model) {
        List<NumberTale> top10 = service.getTop10();
        model.addAttribute("numbers", top10);
        return "topNumbers";
    }

    @GetMapping("/latency")
    public String getAverageLatency(Model model) {
        int averageLatency = service.getAverageLatency();
        model.addAttribute("average", averageLatency);
        return "averageLatency";
    }

    @GetMapping("/success")
    public String getAverageSuccess(Model model) {
        double averageSuccessRate = service.getAverageSuccessRate();
        model.addAttribute("success", averageSuccessRate);
        return "averageSuccess";
    }

}
