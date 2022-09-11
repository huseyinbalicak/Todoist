package com.appcent.todoist.controller;

import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.dto.update.UserUpdateRequestDto;
import com.appcent.todoist.response.RestResponse;
import com.appcent.todoist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity findAll(){
        List<UserResponseDto> userResponseDtoList = userService.findAll();
       return ResponseEntity.ok(RestResponse.of(userResponseDtoList));
    }

   /* @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findById(id);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }


  /*  @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return new ResponseEntity<>(userService.save(userSaveRequestDto), HttpStatus.CREATED);
    }
*/
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto){
        UserResponseDto userResponseDto = userService.save(userSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }


    @PutMapping
    public ResponseEntity update(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        UserResponseDto userResponseDto = userService.update(userUpdateRequestDto);
        return ResponseEntity.ok(RestResponse.of(userResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
