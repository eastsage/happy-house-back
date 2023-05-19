package com.happyhome.model;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

@Slf4j
@Getter @Setter
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public SecurityUser(User user) {
        super(user.getId(), user.getPass(), new ArrayList<GrantedAuthority>(
                Arrays.asList(() -> user.getRole())));
        log.info("SecurityUser member.username = {}", user.getId());
        log.info("SecurityUser member.password = {}", user.getPass());
        this.user = user;
    }

}
