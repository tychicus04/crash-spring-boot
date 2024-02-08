package com.tychicus.springcoredemo.rest;


import com.tychicus.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

//    define  a private field for the dependency
    private Coach myCoach;

    @Autowired
    public void demoController(@Qualifier("trackCoach") Coach theCoach) {
        myCoach = theCoach;
    }

    @GetMapping("/daily-work-out")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

}
