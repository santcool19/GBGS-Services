package com.gbgs.edu.application.controller;

import com.gbgs.edu.application.kafka.producer.KafkaProducerService;
import com.gbgs.edu.application.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UtilityServiceResource {
    List<Customer> customerList;
    @Autowired
    KafkaProducerService kafkaProducerService;

    {
        customerList = new ArrayList<>();
        customerList.add(new Customer("ABC", 101));
        customerList.add(new Customer("DEF", 102));
        customerList.add(new Customer("GHI", 103));
        customerList.add(new Customer("JKL", 104));
        customerList.add(new Customer("MNO", 105));
    }

    @RequestMapping(value = "v0/{name}", method = RequestMethod.GET)
    public boolean findFile() throws InterruptedException {

        for (Customer customer : customerList) {
            kafkaProducerService.send(customer);
        }
        return true;
    }

    @RequestMapping(value = "v1", method = RequestMethod.GET)
    public boolean wordCount() throws InterruptedException {

        for (Customer customer : customerList) {
            kafkaProducerService.send(customer);
        }
        return true;
    }

}
