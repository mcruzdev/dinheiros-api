package com.github.mcruzdev.dinheirosapi;

import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class Portfolio {

    private String id;
    private String name;
    private BigDecimal value;

    @Deprecated
    public Portfolio() {
    }

    public Portfolio(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
        this.id = ObjectId.get().toString();
    }

    public static Portfolio copy(Portfolio portfolio) {
        return new Portfolio(portfolio.getName(), portfolio.getValue());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void subtract(BigDecimal value) {

        if (this.value.subtract(value).doubleValue() >= 0.00) {
            this.value = this.value.subtract(value);
        }
    }

    public void add(BigDecimal value) {
        this.value = this.value.add(value);
    }
}
