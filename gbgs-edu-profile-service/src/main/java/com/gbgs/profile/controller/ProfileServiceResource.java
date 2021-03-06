package com.gbgs.profile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Author: santosh.kumar
 * Date: 1st NOV 2020
 * Comment:
 */
@RestController
@RequestMapping("profile/v0/")
public class ProfileServiceResource {

    @GetMapping("/{userId}")
    public String getProfile(@Valid @PathVariable long userId) {
        return String.valueOf(userId);
    }
}
