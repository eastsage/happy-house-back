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

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;


    @PostMapping("join")
    public ResponseEntity join(@RequestBody User user) {
        log.info("user = {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int status = userService.saveUser(user);
        if (status < 1) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
        return ResponseEntity.ok().body("회원가입 성공!");
    }



}
