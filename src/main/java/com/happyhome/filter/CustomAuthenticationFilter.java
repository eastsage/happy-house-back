//package com.happyhome.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.happyhome.model.user.dto.UserDto;
//import java.io.IOException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    public CustomAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
//        super(requiresAuthenticationRequestMatcher);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException, IOException {
//
//        // objectMapper.readValue(request.getReader(), dto클래스.class);를 통해 값 꺼내오기 가능
//        // 인증 객체를 생성하는데 필요한 데이터들 가져오기
//        UserDto userDto = objectMapper.readValue(request.getReader(), UserDto.class);
//
////        return getAuthenticationManager().authenticate(new CustomAuthenticationToken(account, password));
//        return getAuthenticationManager().authenticate(
//                new UsernamePasswordAuthenticationToken(userDto.getId(), userDto.getPass()));
//    }
//}
