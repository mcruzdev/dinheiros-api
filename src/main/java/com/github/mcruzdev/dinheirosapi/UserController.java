package com.github.mcruzdev.dinheirosapi;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserController(final UserRepository userRepository,
                          final ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> create(@Valid @RequestBody NewUserForm form) {
        User newUser = form.toUser();
        userRepository.save(newUser);
        return ResponseEntity.created(URI.create(String.format("/users/%s", newUser.getId()))).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginForm form) {
        Optional<User> optionalUser = userRepository.findByEmail(form.getEmail());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        UserResponse userResponse = this.modelMapper.map(optionalUser.get(), UserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getByName(@RequestParam("name") @NotBlank String name) {

        Optional<User> userOptional = userRepository.findByName(name);

        if (!userOptional.isPresent()) {
            throw new RuntimeException();
        }

        User user = userOptional.get();

        List<PortfolioResponse> portfolios = user.getPortfolios().stream()
                .map(PortfolioResponse::from).collect(Collectors.toList());

        List<TransitionResponse> transitions = user.getTransitions().stream()
                .map(transition -> TransitionResponse.from(transition, user.getPortfolios()))
                .collect(Collectors.toList());

        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), portfolios, transitions);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") @NotBlank String id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new RuntimeException();
        }

        User user = userOptional.get();

        List<PortfolioResponse> portfolios = user.getPortfolios().stream()
                .map(PortfolioResponse::from).collect(Collectors.toList());

        List<TransitionResponse> transitions = user.getTransitions().stream()
                .map(transition -> TransitionResponse.from(transition, user.getPortfolios()))
                .collect(Collectors.toList());

        UserResponse userResponse = new UserResponse(user.getId(), user.getName(), portfolios, transitions);

        return ResponseEntity.ok(userResponse);
    }
}
