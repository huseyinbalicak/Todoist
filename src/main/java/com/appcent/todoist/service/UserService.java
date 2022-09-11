package com.appcent.todoist.service;

import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.dto.update.UserUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.mapper.UserMapper;
import com.appcent.todoist.model.User;
import com.appcent.todoist.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    public List<UserResponseDto> findAll(){
        List<User> userList = userRepository.findAll();
        return UserMapper.INSTANCE.convertToUserResponseDtoList(userList);
    }

    public UserResponseDto save(UserSaveRequestDto userSaveRequestDto){
        User user = UserMapper.INSTANCE.convertToUser(userSaveRequestDto);
        //String password = encoder.encode(user.getPassword());
       // user.setPassword(password);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.convertToUserResponseDto(user);
    }

    public UserResponseDto update(UserUpdateRequestDto userUpdateRequestDto) {

        boolean isExist = isExist(userUpdateRequestDto.getId());
        if (!isExist){
            throw new EntityNotFoundException("User not found");
        }

        User user = UserMapper.INSTANCE.convertToUser(userUpdateRequestDto);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.convertToUserResponseDto(user);
    }

    public void delete(Long id) {
        if (id == null) {
            log.error("User id is null");
            throw new EntityNotFoundException("No user found with ID = " + id);
        }
        userRepository.deleteById(id);
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
