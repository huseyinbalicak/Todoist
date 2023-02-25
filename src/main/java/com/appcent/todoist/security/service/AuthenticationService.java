package com.appcent.todoist.security.service;

import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.model.User;
import com.appcent.todoist.response.AuthResponse;
import com.appcent.todoist.security.JwtTokenGenerator;
import com.appcent.todoist.security.dto.LoginRequestDto;
import com.appcent.todoist.security.dto.RefreshRequest;
import com.appcent.todoist.security.dto.RefreshToken;
import com.appcent.todoist.security.enums.EnumJwtConstant;
import com.appcent.todoist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse register(UserSaveRequestDto userSaveRequestDto) {
        AuthResponse authResponse = new AuthResponse();
        if (userService.getOneUserByUserName(userSaveRequestDto.getUserName()) != null) {
            authResponse.getRestErrorResponse().setMessage("Username already in use.");
            return authResponse;
        }
        User user = new User();
        user.setUserName(userSaveRequestDto.getUserName());
        user.setPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        user.setEmail(userSaveRequestDto.getEmail());
        user.setLastName(userSaveRequestDto.getLastName());
        user.setFirstName(userSaveRequestDto.getFirstName());
        userService.saveOneUser(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userSaveRequestDto.getUserName(), userSaveRequestDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenGenerator.generateJwtToken(auth);

        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return authResponse;


    }



    public AuthResponse login(LoginRequestDto loginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        User user = userService.getOneUserByUserName(loginRequestDto.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + token);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return authResponse;
    }



    public AuthResponse refresh( RefreshRequest refreshRequest) {
        AuthResponse response = new AuthResponse();
        RefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtTokenGenerator.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());
            return response;
        } else {
            response.setMessage("refresh token is not valid.");
            return response;
        }

    }


}
