package com.happyhome.controller;

import com.google.gson.Gson;
import com.happyhome.model.user.User;
import com.happyhome.service.UserService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        log.info("join user = {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int status = userService.saveUser(user);
        if (status < 1) {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
        return ResponseEntity.ok().body("회원가입 성공!");
    }

    @GetMapping("info/{username}")
    @Secured("ROLE_CUSTOMER")
    public ResponseEntity<Map<String, Object>> info(Authentication authentication, @PathVariable String username) {
        User user = userService.findByUsername(username);
        user.setPassword("HIDDEN");
        Map<String, Object> response = new HashMap<>();
        if (user == null) {
            response.put("message", "일치하는 유저가 없습니다");
            return ResponseEntity.status(401).body(response);
        }
        response.put("userInfo", user);
        response.put("message", "success");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/logout/{username}")
    public ResponseEntity<Map<String, String>> logout(@PathVariable String username) {
        SecurityContextHolder.getContext().setAuthentication(null);

        Map<String, String> response = new HashMap<>();
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }
}
