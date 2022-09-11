package com.appcent.todoist.mapper;

import com.appcent.todoist.dto.UserResponseDto;
import com.appcent.todoist.dto.save.UserSaveRequestDto;
import com.appcent.todoist.dto.update.UserUpdateRequestDto;
import com.appcent.todoist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDto convertToUserResponseDto(User user);

    List<UserResponseDto> convertToUserResponseDtoList (List<User> userList);

    User convertToUser(UserSaveRequestDto userSaveRequestDto);

    User convertToUser(UserUpdateRequestDto userUpdateRequestDto);
}
