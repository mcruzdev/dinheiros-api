package com.github.mcruzdev.dinheirosapi;

import java.math.BigDecimal;

public class PortfolioResponse {

    private String id;
    private String name;
    private BigDecimal value;

    public PortfolioResponse() {
    }

    public PortfolioResponse(String id, String name, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public static PortfolioResponse from(Portfolio portfolio) {
        return new PortfolioResponse(portfolio.getId(), portfolio.getName(), portfolio.getValue());
    }
}
