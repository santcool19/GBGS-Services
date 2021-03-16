package com.gbgs.edu.finrep.processor.rwa;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class RwaProcessor {
    @Bean
    public Function<KStream<String, Exposure>, KStream<String, Exposure>> rwaProcessor() {
        return exposure -> exposure.filter((k,v) -> v.exosureId.contains("00"));
    };

    /*
    @Bean
    public Function<KStream<String, Long>, KStream<String, Long>> evenNumberSquareProcessor() {
        return kStream -> kStream
                .filter((k, v) -> v % 2 == 0)
                .peek((k, v) -> System.out.println("Squaring Even : " + v))
                .mapValues(v -> v * v);
    };
     */


}
