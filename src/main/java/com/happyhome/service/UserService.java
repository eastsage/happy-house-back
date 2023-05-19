package com.happyhome.service;

import com.happyhome.mapper.UserMapper;
import com.happyhome.model.SecurityUser;
import com.happyhome.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";
    private final static String ERROR_NO_ACCOUNT = "[ERROR] 계정 정보를 불러올 수 없습니다.";
    private final UserMapper userMapper;

    public int saveUser(User user) {
        int status = userMapper.saveUser(user);
        return status;
    }

    public int deleteUser(User user) {
        return userMapper.deleteUser(user);
    }

    public int UpdateUser(User user) {
        return userMapper.UpdateUser(user);
    }

    public User findByUserId(String id) {
        User user = userMapper.findByUserId(id);
        log.info("{}", user);
        return user;
    }

    @Override
    public SecurityUser loadUserByUsername(String id) throws UsernameNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException(ERROR_NO_ACCOUNT);
        }
        User user = userMapper.findByUserId(id);
        log.info("{}", user);
        if (user == null) {
            throw new IllegalArgumentException(ERROR_NO_USER);
        }

        return new SecurityUser(user);
    }
}
