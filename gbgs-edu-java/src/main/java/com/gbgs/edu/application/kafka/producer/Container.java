package com.gbgs.edu.application.kafka.producer;

import com.gbgs.edu.application.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class Container {
    public String id;
    public List<Student> data;
}
