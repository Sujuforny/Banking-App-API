package co.istad.bankingapp.api.user;

import co.istad.bankingapp.api.user.web.CreateUserDto;
import co.istad.bankingapp.api.user.web.EditUserDto;
import co.istad.bankingapp.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface UserMapStruct {
    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User editUserToUser(EditUserDto editUserDto);
    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);
}
