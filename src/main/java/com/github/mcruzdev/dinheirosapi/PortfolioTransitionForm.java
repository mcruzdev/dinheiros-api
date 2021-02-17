package com.github.mcruzdev.dinheirosapi;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PortfolioTransitionForm {

    @DecimalMin("0.01")
    private BigDecimal value;
    @NotNull
    private TransitionType type;
    @NotNull
    private RevenueExpenseGroup revenueExpenseGroup;
    @NotBlank
    private String description;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TransitionType getType() {
        return type;
    }

    public void setType(TransitionType type) {
        this.type = type;
    }

    public RevenueExpenseGroup getRevenueExpenseGroup() {
        return revenueExpenseGroup;
    }

    public void setRevenueExpenseGroup(RevenueExpenseGroup revenueExpenseGroup) {
        this.revenueExpenseGroup = revenueExpenseGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
