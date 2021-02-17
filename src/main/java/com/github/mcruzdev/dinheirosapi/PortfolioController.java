package com.github.mcruzdev.dinheirosapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.Optional;

@RestController
public class PortfolioController {

    @Autowired
    private TransitionService transitionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/{id}/portfolios")
    public ResponseEntity<Void> create(@Valid @RequestBody PortfolioForm form, @PathVariable("id") @NotBlank String id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new RuntimeException();
        }

        User user = userOptional.get();

        Portfolio portfolio = form.toPortfolio();

        user.addPortfolio(portfolio);

        userRepository.save(user);

        return ResponseEntity.created(URI.create(String.format("/users/%s/portfolios/%s",
                user.getId(), portfolio.getId()))).build();

    }

    @PatchMapping("/users/{id}/portfolios/{portfolioId}")
    public ResponseEntity<Void> transition(@Valid @RequestBody PortfolioTransitionForm form,
                                           @NotBlank @PathVariable("id") String id,
                                           @NotBlank @PathVariable("portfolioId") String portfolioId) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException();
        }

        User user = optionalUser.get();

        EffectTransition effectTransition = new EffectTransition.Builder()
                .setPortfolioId(portfolioId)
                .setValue(form.getValue())
                .setType(form.getType())
                .setRevenueExpenseGroup(form.getRevenueExpenseGroup())
                .setUser(user)
                .setDescription(form.getDescription())
                .createEffectTransition();

        transitionService.effect(effectTransition);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/portfolios/value")
    public ResponseEntity<Void> transfer(@Valid TransferForm form, @PathVariable("id") String id) {

        Optional<User> optionalUser = this.userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new RuntimeException();
        }

        User user = optionalUser.get();

        Optional<Portfolio> fromOptional = user.findPortfolioById(form.getFrom());
        Optional<Portfolio> toOptional = user.findPortfolioById(form.getFrom());

        if (!fromOptional.isPresent() || !toOptional.isPresent()) {
            throw new RuntimeException();
        }

        Portfolio from = fromOptional.get();
        Portfolio to = fromOptional.get();

        Transfer transfer = new Transfer(from, to, form.getValue());
        user.updatePortfolio(transfer.getFrom());
        user.updatePortfolio(transfer.getTo());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
