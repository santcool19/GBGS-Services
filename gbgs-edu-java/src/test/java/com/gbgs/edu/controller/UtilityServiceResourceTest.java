package com.gbgs.edu.controller;

import com.gbgs.edu.model.Customer;
import com.gbgs.edu.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UtilityServiceResourceTest {
    List<Customer> customerList;
    @InjectMocks
    private UtilityServiceResource utilityServiceResource;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void init() {
        utilityServiceResource = new UtilityServiceResource();
        MockitoAnnotations.initMocks(this);
        customerList = new ArrayList<>();
        customerList.add(new Customer("ABC", 101));
    }

    @Test
    public void testGetUserById() {
        when(customerRepository.getCustomer("ABC")).thenReturn(customerList);
        List<Customer> customerList = utilityServiceResource.getCustomet("ABC");
        //verify(customerRepository).getCustomer("ABC");
        Assert.assertEquals(1, customerList.size());
    }

    @Test
    public void testGetCustomet() {
        //Assert.assertEquals(utilityServiceResource.getCustomet("ABC").size(), 1);
    }
}
