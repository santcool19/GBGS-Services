package com.vinsguru.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConsumer {

    @Bean
    public Employees employeesConsumer(Employees employees) {
        employees.employees.forEach(emp -> System.out.println("Employee Name: " + emp.name));
        return employees;
    }

    ;

    /*
    @Bean
    public Consumer<KStream<String, Long>> squaredNumberConsumer() {
        return stream -> stream.foreach((key, value) -> System.out.println("Square Number Consumed : " + value));
    };

     */

}
