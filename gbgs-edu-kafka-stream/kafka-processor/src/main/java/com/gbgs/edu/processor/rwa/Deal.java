package com.gbgs.edu.finrep.processor.rwa;

public class Deal {
    int dealId;
    Long dealAmount;
    float rwa;
    String originCountry;

    public float getRwa() {
        return rwa;
    }

    public void setRwa(float rwa) {
        this.rwa = rwa;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public Long getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Long dealAmount) {
        this.dealAmount = dealAmount;
    }
}
