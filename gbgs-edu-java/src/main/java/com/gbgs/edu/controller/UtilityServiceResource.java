package com.gbgs.edu.controller;

import com.gbgs.edu.kafka.producer.KafkaProducerService;
import com.gbgs.edu.model.Customer;
import com.gbgs.edu.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customer", method = RequestMethod.GET)
public class UtilityServiceResource {
    @Autowired
    KafkaProducerService kafkaProducerService;

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "/v1/{customer}", method = RequestMethod.GET)
    public List<Customer> getCustomet(@PathVariable("customer") String customer) {
        return customerRepository.getCustomer(customer);
    }

    @RequestMapping(value = "/v0/{customer}", method = RequestMethod.GET)
    public boolean sendCustomer(@PathVariable("customer") String customerName) throws InterruptedException {
        for (Customer customer : customerRepository.getCustomer(customerName)) {
            kafkaProducerService.send(customer);
        }
        return true;
    }
}
