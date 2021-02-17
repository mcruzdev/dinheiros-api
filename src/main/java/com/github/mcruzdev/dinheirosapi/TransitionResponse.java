package com.github.mcruzdev.dinheirosapi;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class TransitionResponse {

    private String id;
    private String portfolioName;
    private TransitionType type;
    private BigDecimal value;
    private Instant date;
    private String description;
    private RevenueExpenseGroup revenueExpenseGroup;

    public TransitionResponse() {
    }

    public TransitionResponse(String id, String portfolioName, TransitionType type, BigDecimal value, Instant date, String description, RevenueExpenseGroup revenueExpenseGroup) {
        this.id = id;
        this.portfolioName = portfolioName;
        this.type = type;
        this.value = value;
        this.date = date;
        this.description = description;
        this.revenueExpenseGroup = revenueExpenseGroup;
    }

    public static TransitionResponse from(Transition transition, List<Portfolio> portfolios) {

        Optional<Portfolio> optionalPortfolio = portfolios.stream()
                .filter(p -> p.getId().equals(transition.getPortfolioId()))
                .findFirst();

        if (optionalPortfolio.isPresent()) {
            return new TransitionResponse(
                    transition.getId(),
                    optionalPortfolio.get().getName(),
                    transition.getType(),
                    transition.getValue(),
                    transition.getDate(),
                    transition.getDescription(),
                    transition.getRevenueExpenseGroup());
        }

        throw new RuntimeException();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public TransitionType getType() {
        return type;
    }

    public void setType(TransitionType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RevenueExpenseGroup getRevenueExpenseGroup() {
        return revenueExpenseGroup;
    }

    public void setRevenueExpenseGroup(RevenueExpenseGroup revenueExpenseGroup) {
        this.revenueExpenseGroup = revenueExpenseGroup;
    }
}
