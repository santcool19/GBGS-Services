package com.gbgs.edu.application.programm.ds;

import com.gbgs.edu.application.model.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8 {
    private static void java8() {
        int[] intArr = new int[5];
        intArr[0] = 2;
        intArr[1] = 1;
        intArr[2] = 2;
        intArr[3] = 4;
        intArr[4] = 8;
        List<String> abc = new ArrayList<>();
        
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(372, "Venkat", 1));
        studentList.add(new Student(2, "Sachin", 4));
        studentList.add(new Student(2345, "Ganguly", 6));
        studentList.add(new Student(72, "Karthik", 2));
        Map result1 = sortListFunction("Test").andThen(getMapFunction("Test")).apply(studentList);
        Map<Integer, Long> occuranceMap = Arrays.stream(intArr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        LinkedHashMap<Integer, Long> sorted = occuranceMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println("Result 1 : " + sorted);
        studentList.stream().collect(Collectors.groupingBy(Student::getId));
    }

    private static Function<List<Student>, List<Student>> sortListFunction(String sort) {
        return (list) -> {
            return list.stream().sorted(Comparator.comparing(Student::getId).reversed()).collect(Collectors.toList());
        };
    }

    private static Function<List<Student>, Map<Integer, String>> getMapFunction(String sort) {
        return (list) -> {
            return list.stream().collect(Collectors.toMap(Student::getId, Student::getName, (o, n) -> n));
        };
    }
}
