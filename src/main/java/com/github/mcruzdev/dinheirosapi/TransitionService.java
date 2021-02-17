package com.github.mcruzdev.dinheirosapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class TransitionService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void effect(@Valid EffectTransition effectTransition) {
        User user = effectTransition.getUser();
        user.effectTransition(effectTransition.toTransition());
        this.userRepository.save(user);
    }
}
