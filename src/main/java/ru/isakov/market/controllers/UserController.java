package ru.isakov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.isakov.market.models.dtos.NewUserDto;
import ru.isakov.market.models.dtos.UserDto;
import ru.isakov.market.error_handling.ResourceNotFoundException;
import ru.isakov.market.models.entities.User;
import ru.isakov.market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserDto getCurrentUsername(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        return new UserDto(currentUser.getUsername(), currentUser.getEmail());
    }

//    @PostMapping("/register")
//    public void register(@RequestBody NewUserDto newUserDto) {
//        userService.registerUser(newUserDto);
//    }
}
