package com.appcent.todoist.service;

import com.appcent.todoist.model.User;
import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.dto.update.UserUpdateRequestDto;
import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldFindAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();
        assertEquals(0, userResponseDtoList.size());
    }

    @Test
    void shouldFindAllWhenReturnsUsers() {
        User user = mock(User.class);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<UserResponseDto> userResponseDtoList = userService.findAll();
        assertEquals(1, userResponseDtoList.size());
    }

    @Test
    void shouldFindById() {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserResponseDto result = userService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldFindAllWhenUserListISNull() {
        when(userRepository.findAll()).thenReturn(null);
        List<UserResponseDto> userResponseDtoList = userService.findAll();
        assertNull(userResponseDtoList);
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(userRepository.findById(anyLong())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userService.findById(-1L));
    }

    @Test
    void shouldSave() {
        UserSaveRequestDto userSaveRequestDto = mock(UserSaveRequestDto.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(userRepository.save(any())).thenReturn(user);
        UserResponseDto result = userService.save(userSaveRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdate() {

        UserUpdateRequestDto userUpdateRequestDto = mock(UserUpdateRequestDto.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(userRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        when(userRepository.save(any())).thenReturn(user);
        UserResponseDto result = userService.update(user.getId(),userUpdateRequestDto);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldNotUpdate() {
        UserUpdateRequestDto userUpdateRequestDto = mock(UserUpdateRequestDto.class);
        User user = mock(User.class);
        when(userRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        assertThrows(EntityNotFoundException.class, () -> userService.update(user.getId(),userUpdateRequestDto));

    }

    @Test
    void shouldExist() {
        when(userRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        boolean isExist = userService.isExist(1L);
        assertTrue(isExist);
    }

    @Test
    void shouldNotExist() {
        when(userRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);
        boolean isExist = userService.isExist(1L);
        assertFalse(isExist);
    }

    @Test
    void shouldDelete(){
        doNothing().when(userRepository).deleteById(anyLong());
        userService.delete(1L);
        verify(userRepository).deleteById(anyLong());
    }

    @Test
    void shouldNotDelete()
    {
        doThrow(EntityNotFoundException.class).when(userRepository).deleteById(anyLong());
        assertThrows(EntityNotFoundException.class,()->userService.delete(anyLong()));
        verify(userRepository).deleteById(anyLong());


    }



}