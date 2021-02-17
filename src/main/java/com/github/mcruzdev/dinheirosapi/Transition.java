package com.github.mcruzdev.dinheirosapi;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

public class Transition {

    @Id
    private String id;
    private String portfolioId;
    private Instant date;
    private BigDecimal value;
    private TransitionType type;
    private RevenueExpenseGroup revenueExpenseGroup;
    private String description;

    @Deprecated
    public Transition() {
    }

    public Transition(String portfolioId, BigDecimal value, TransitionType type, RevenueExpenseGroup revenueExpenseGroup, String description) {
        this.id = ObjectId.get().toString();
        this.portfolioId = portfolioId;
        this.date = OffsetDateTime.now().toInstant();
        this.value = value;
        this.type = type;
        this.revenueExpenseGroup = revenueExpenseGroup;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public Instant getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public TransitionType getType() {
        return type;
    }

    public RevenueExpenseGroup getExpenseType() {
        return revenueExpenseGroup;
    }

    public String getDescription() {
        return description;
    }

    public RevenueExpenseGroup getRevenueExpenseGroup() {
        return revenueExpenseGroup;
    }
}
