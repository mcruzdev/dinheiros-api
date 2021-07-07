
package com.github.mcruzdev.dinheirosapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private List<Portfolio> portfolios = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();

    @Deprecated
    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Portfolio> getPortfolios() {
        return new ArrayList<>(portfolios);
    }

    public List<Transition> getTransitions() {
        return new ArrayList<>(transitions);
    }

    public void addPortfolio(Portfolio portfolio) {
        this.portfolios.add(portfolio);
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public Optional<Portfolio> findPortfolioById(String id) {
        return this.portfolios.stream().filter(portfolio -> portfolio.getId().equals(id)).findFirst();
    }

    public void updatePortfolio(Portfolio portfolio) {
        this.portfolios = this.portfolios.stream()
                .map(p -> p.getId().equals(portfolio.getId()) ? portfolio : p)
                .collect(Collectors.toList());
    }

    public void effectTransition(Transition transition) {

        String portfolioId = transition.getPortfolioId();
        TransitionType type = transition.getType();
        BigDecimal value = transition.getValue();
        RevenueExpenseGroup revenueExpenseGroup = transition.getExpenseType();
        String description = transition.getDescription();

        this.portfolios = this.portfolios.stream()
                .map(p -> {
                    if (p.getId().equals(portfolioId)) {

                        if (TransitionType.ENTER.equals(type)) {
                            p.add(value);
                        } else {
                            p.subtract(value);
                        }

                        return p;
                    }
                    return p;
                })
                .collect(Collectors.toList());

        this.transitions.add(new Transition(portfolioId, value, type, revenueExpenseGroup, description));
    }

    public String getPortfolioNameById(String portfolioId) {
        Optional<Portfolio> first = this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst();

        if (first.isPresent()) {
            return first.get().getName();
        }

        throw new RuntimeException();
    }
}
