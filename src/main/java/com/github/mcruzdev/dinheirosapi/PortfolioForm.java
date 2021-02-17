package com.github.mcruzdev.dinheirosapi;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class PortfolioForm {

    @NotBlank
    private String name;

    @DecimalMin(value = "0.0")
    private BigDecimal value;

    public PortfolioForm() {
    }

    public PortfolioForm(String name, String userId, BigDecimal value) {
        this.name = name;
        this.value = value;
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

    public Portfolio toPortfolio() {

        return new Portfolio(name, value);
    }
}
