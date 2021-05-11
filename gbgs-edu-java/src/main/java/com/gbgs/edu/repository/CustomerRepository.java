package com.gbgs.edu.repository;

import com.gbgs.edu.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {
    List<Customer> customerList;

    {
        customerList = new ArrayList<>();
        customerList.add(new Customer("ABC", 101));
        customerList.add(new Customer("DEF", 102));
        customerList.add(new Customer("GHI", 103));
        customerList.add(new Customer("JKL", 104));
        customerList.add(new Customer("MNO", 105));
    }

    public List<Customer> getCustomer(String customer) {
        return customerList.stream().collect(Collectors.groupingBy(Customer::getName)).get(customer);
    }
}
