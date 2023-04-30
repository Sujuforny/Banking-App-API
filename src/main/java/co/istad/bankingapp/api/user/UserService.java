package co.istad.bankingapp.api.user;

import co.istad.bankingapp.api.user.web.CreateUserDto;
import co.istad.bankingapp.api.user.web.EditUserDto;
import co.istad.bankingapp.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //Create User. user input Data(@RequestBody) That have Data type Object(~CreateUserDto~) And return type Object(~UserDto~)
    //Note! Why return UserDto?=>User can see information UserDTO(String name,String gender,String studentCardId,Boolean isStudent)
    UserDto createNewUser(CreateUserDto createUserDto);

    //Find User by id. user input id(@PathVariable) in URL return type Object(~UserDto~)
    UserDto findUserById(Integer id);

    //Find User by Name. user input name(@PathVariable) in URL return type List(~UserDto~)
    List<UserDto> findByName(String name);

    //Find User by student card ID. user input id card(@PathVariable) in URL return type List(~UserDto~)
    List<UserDto> findByStudentCardId(String studentCardId);

    //Delete User by id. user input id(@PathVariable) in URL return type Integer
    //Note! Why return Integer?=>User can see ID have deleted.
    Integer deleteUserById(Integer id);

    //Disable status Is_delete.id(@PathVariable),status(@RequestBody)
    Integer updateIsDeletedStatusById (Integer id, boolean status);

    //Edit User. user input Data That have Data type Object(~CreateUserDto~) And return type Object(~UserDto~)
    UserDto editUser(Integer id, EditUserDto editUserDto);

    PageInfo<UserDto> findAllUsers(int page, int limit);
}
