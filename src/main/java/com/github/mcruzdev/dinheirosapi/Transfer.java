package com.github.mcruzdev.dinheirosapi;

import java.math.BigDecimal;

public class Transfer {

    private Portfolio from;
    private Portfolio to;
    private BigDecimal value;

    public Transfer() {
    }

    public Transfer(Portfolio from, Portfolio to, BigDecimal value) {
        this.from = from;
        this.to = to;
        this.value = value;
        transfer();
    }

    private void transfer() {

        if (this.from.getValue().doubleValue() < value.doubleValue()) {
            throw new RuntimeException();
        }

        this.from = new Portfolio(from.getName(), from.getValue().subtract(value));
        this.to = new Portfolio(to.getName(), to.getValue().add(value));
    }

    public Portfolio getFrom() {
        return from;
    }

    public Portfolio getTo() {
        return Portfolio.copy(to);
    }

    public BigDecimal getValue() {
        return value;
    }
}
