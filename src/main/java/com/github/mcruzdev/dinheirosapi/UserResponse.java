package com.github.mcruzdev.dinheirosapi;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private String id;
    private String name;
    private List<PortfolioResponse> portfolios = new ArrayList<>();
    private List<TransitionResponse> transitions = new ArrayList<>();

    public UserResponse() {
    }

    public UserResponse(String id, String name, List<PortfolioResponse> portfolios, List<TransitionResponse> transitions) {
        this.id = id;
        this.name = name;
        this.portfolios = portfolios;
        this.transitions = transitions;
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

    public List<PortfolioResponse> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<PortfolioResponse> portfolios) {
        this.portfolios = portfolios;
    }

    public List<TransitionResponse> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<TransitionResponse> transitions) {
        this.transitions = transitions;
    }
}
