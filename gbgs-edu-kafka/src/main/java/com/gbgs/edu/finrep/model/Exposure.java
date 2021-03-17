package com.gbgs.edu.finrep.model;

import java.util.List;

public class Exposure {
    String exosureId;
    List<Deal> deals;

    public String getExosureId() {
        return exosureId;
    }

    public void setExosureId(String exosureId) {
        this.exosureId = exosureId;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }


}
