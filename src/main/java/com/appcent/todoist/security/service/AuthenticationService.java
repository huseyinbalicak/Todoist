package com.appcent.todoist.security.service;

import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.mapper.UserMapper;
import com.appcent.todoist.model.User;
import com.appcent.todoist.security.JwtTokenGenerator;
import com.appcent.todoist.security.dto.LoginRequestDto;
import com.appcent.todoist.security.enums.EnumJwtConstant;
import com.appcent.todoist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final BCryptPasswordEncoder encoder;


    public UserResponseDto register(UserSaveRequestDto userSaveRequestDto) {

        User user = UserMapper.INSTANCE.convertToUser(userSaveRequestDto);
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);

        return userService.save(userSaveRequestDto);
    }

    public String login(LoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getIdentityNo().toString(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }


}
