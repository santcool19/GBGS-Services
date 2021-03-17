package com.gbgs.edu.finrep.jdbc;

import com.gbgs.edu.finrep.model.Deal;
import com.gbgs.edu.finrep.model.Exposure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DealRepositoryImpl implements DealRepository {
    private static final Logger logger = LoggerFactory.getLogger(DealRepositoryImpl.class);

    @Override
    public Exposure getExposure() {
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
        return exposure;
    }
}
