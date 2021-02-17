package com.github.mcruzdev.dinheirosapi;

import java.math.BigDecimal;

public class EffectTransition {

    private String portfolioId;
    private TransitionType type;
    private BigDecimal value;
    private User user;
    private RevenueExpenseGroup revenueExpenseGroup;
    private String description;

    public EffectTransition(
            String portfolioId,
            TransitionType type,
            BigDecimal value,
            User user,
            RevenueExpenseGroup revenueExpenseGroup,
            String description) {

        this.portfolioId = portfolioId;
        this.type = type;
        this.value = value;
        this.user = user;
        this.revenueExpenseGroup = revenueExpenseGroup;
        this.description = description;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public TransitionType getType() {
        return type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public RevenueExpenseGroup getExpenseType() {
        return revenueExpenseGroup;
    }

    public String getDescription() {
        return description;
    }

    public Transition toTransition() {
        return new Transition(portfolioId, value, type, revenueExpenseGroup, description);
    }

    public static class Builder {

        private String portfolioId;
        private BigDecimal value;
        private TransitionType type;
        private User user;
        private RevenueExpenseGroup revenueExpenseGroup;
        private String description;

        public Builder setPortfolioId(String portfolioId) {
            this.portfolioId = portfolioId;
            return this;
        }

        public Builder setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder setType(TransitionType type) {
            this.type = type;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setRevenueExpenseGroup(RevenueExpenseGroup revenueExpenseGroup) {
            this.revenueExpenseGroup = revenueExpenseGroup;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public EffectTransition createEffectTransition() {
            return new EffectTransition(portfolioId, type, value, user, revenueExpenseGroup, description);
        }
    }
}
