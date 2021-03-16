package com.gbgs.edu.application.casestudy;

import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileReader {
    public static void main(String[] args) throws FileNotFoundException {
        new FileReader().csvFileReader("C:\\NamSan\\Assesment\\GBGlobalSolution\\edu\\src\\main\\resources\\Test.csv");
    }

    private void csvFileReader(String filePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(filePath));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().skip(1).map(maptoObject()).collect(Collectors.toList()).forEach(str -> System.out.println(str));
    }

    private Function<String, Record> maptoObject() {
        return str -> {
            String[] strArr = str.split(",");
            return new Record(strArr[0], strArr[1]);
        };
    }
}

@Data
@ToString
class Record {
    int amount;
    String name;

    public Record(String amount, String name) {
        this.amount = Integer.valueOf(amount);
        this.name = name;
    }
}