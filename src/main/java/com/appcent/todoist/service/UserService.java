package com.appcent.todoist.service;

import com.appcent.todoist.model.User;
import com.appcent.todoist.repository.UserRepository;
import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.dto.update.UserUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> findAll(){
        List<User> userList = userRepository.findAll();
        return UserMapper.INSTANCE.convertToUserResponseDtoList(userList);
    }

    public UserResponseDto save(UserSaveRequestDto userSaveRequestDto){
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserMapper.INSTANCE.convertToUserResponseDto(user);
    }

    public UserResponseDto update(Long id,UserUpdateRequestDto userUpdateRequestDto) {

        boolean isExist = isExist(id);
        if (!isExist){
            throw new EntityNotFoundException("User not found");
        }


        User user = UserMapper.INSTANCE.convertToUser(userUpdateRequestDto);
        user.setId(id);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.convertToUserResponseDto(user);
    }


    public User getOneUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
    public void delete(Long id) {
        if (id == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("No user found with ID = " + id);
        }
        userRepository.deleteById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public boolean isExist(Long id){
        return userRepository.existsById(id);
    }

    public User findByIdWithControl(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("No user found with ID = " + id);
        }
    }

    public UserResponseDto findById(Long id){
        User user = findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserResponseDto(user);
    }

}