package com.appcent.todoist.controller;

import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.response.RestResponse;
import com.appcent.todoist.security.dto.LoginRequestDto;
import com.appcent.todoist.security.dto.RefreshRequest;
import com.appcent.todoist.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        return ResponseEntity.ok(RestResponse.of( authenticationService.login(loginRequestDto)));
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto){

        return ResponseEntity.ok(RestResponse.of(authenticationService.register(userSaveRequestDto)));
    }

    @PostMapping("/refresh")
    public ResponseEntity refresh(@RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.ok(RestResponse.of(authenticationService.refresh(refreshRequest)));

    }

}