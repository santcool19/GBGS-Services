package com.gbgs.edu.finrep;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Configuration
public class KafkaProducer {
    @Bean
    public Supplier<Exposure> generateFinrepReport() {
        List<Deal> deals = new ArrayList<>();
        Exposure exposure = new Exposure();
        exposure.setExosureId("1001");

        Deal deal1 = new Deal();
        deal1.setDealId(101);
        deal1.setDealAmount(new Long(101));

        Deal deal2 = new Deal();
        deal2.setDealId(102);
        deal2.setDealAmount(new Long(102));

        deals.add(deal1);
        deals.add(deal2);
        exposure.setDeals(deals);

        return new Supplier<Exposure>() {
            @Override
            public Exposure get() {
                return exposure;
            }
        };
    };
/*
    @Bean
    public Supplier<Flux<Long>> numberProducer() {
        return () -> Flux.range(1, 1000)
                .map(i -> (long) i)
                .delayElements(Duration.ofSeconds(1));
    };

 */
}
