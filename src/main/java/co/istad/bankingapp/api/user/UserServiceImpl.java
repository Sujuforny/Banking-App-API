package co.istad.bankingapp.api.user;

import co.istad.bankingapp.api.user.web.CreateUserDto;
import co.istad.bankingapp.api.user.web.EditUserDto;
import co.istad.bankingapp.api.user.web.UserDto;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;
    @Override
    // Create method insert new user and insert data type <CreateUserDto>
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);

        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d is not found",id)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public UserDto editUser(Integer id, EditUserDto editUserDto) {
        boolean isExisted = userMapper.isExitsById(id);
        if(isExisted){
            User user = userMapStruct.editUserToUser(editUserDto);
            userMapper.edit(id,user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d is not found",id));
    }

    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit) {
        PageInfo<User> userPageInfo= PageHelper.startPage(page, limit)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        userMapper.findAllUsers();
                    }
                });
        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean isFound = userMapper.isExitsById(id);
        if (isFound){
         userMapper.deleteById(id);
         return id;
        }throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d is not found",id));
    }

    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExisted = userMapper.isExitsById(id);
        if(isExisted){
            userMapper.updateIsDeletesById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with %d is not found",id));

    }
}