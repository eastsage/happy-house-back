package com.happyhome.controller;

import com.happyhome.model.user.SecurityUser;
import com.happyhome.model.user.User;
import com.happyhome.model.user.dto.UserUpdateDto;
import com.happyhome.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/temp")
    public ResponseEntity temp(String id, String pass) {
        log.info("id = {}, pass = {}", id, pass);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        log.info("add user = {}", user);
        int status = userService.saveUser(user);
        if (status > 0) {
            return ResponseEntity.ok().body("hello new user!");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.ok("logout 성공");
    }

    @Secured(value = {"ROLE_CUSTOMER", "ROLE_SELLER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity showUserDetails(@PathVariable String id) {
        User user = userService.findByUserId(id);
        log.info("{}", user);
        return ResponseEntity.ok().body(user);
    }

    @Secured(value = {"ROLE_CUSTOMER", "ROLE_SELLER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id, HttpSession session) {
        User sessionUser = getSessionUser(session);
        User user = userService.findByUserId(sessionUser.getName());

        int status = userService.deleteUser(user);
        log.info("status = {}", status);
        if (status > 0) {
            return ResponseEntity.ok().body("delete complete!");
        }
        return ResponseEntity.badRequest().body("delete fail!");
    }

    @Secured(value = {"ROLE_CUSTOMER", "ROLE_SELLER", "ROLE_ADMIN"})
    @PostMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody UserUpdateDto userUpdateDto, HttpSession session) {
        User sessionUser = getSessionUser(session);
        User user = userService.findByUserId(sessionUser.getName());
        log.info("{}", userUpdateDto);

        user.setId(userUpdateDto.getId());
        user.setName(userUpdateDto.getName());
        user.setAddress(userUpdateDto.getAddress());
        user.setNumber(userUpdateDto.getNumber());
        int status = userService.UpdateUser(user);
        if (status > 0) {
            return ResponseEntity.ok().body("update complete!");
        }
        return ResponseEntity.badRequest().body("update fail!");
    }

    private User getSessionUser(HttpSession session) {
        SecurityContext value = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = value.getAuthentication();
        log.info("auth = {}", authentication);
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        log.info("principal = {}", principal);
        User user = principal.getUser();
        return user;
    }


}
