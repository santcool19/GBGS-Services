package com.gbgs.edu.finrep.controller;

import com.gbgs.edu.finrep.service.FinrepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/finrep")
public class FinrepResource {

    @Autowired
    private FinrepService finrepService;

    @PostMapping(value = "/subgroup")
    public void generateFinrepReport(@RequestParam("scope") String scope, @RequestParam("date") String date) {
        finrepService.generateFinrepReport(scope, date);
    }

    @PostMapping(value = "/group")
    public void generateFinrepReport(@RequestParam("date") String date) {
        this.finrepService.generateFinrepReport(date);
    }
}