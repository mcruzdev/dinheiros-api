package com.github.mcruzdev.dinheirosapi;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class TransferForm {

    @NotBlank
    private String userId;
    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @DecimalMin("0.01")
    private BigDecimal value;

    public TransferForm() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Transfer toTransfer(PortfolioForm from, PortfolioForm to) {
        return new Transfer(from.toPortfolio(), to.toPortfolio(), value);
    }
}
