package co.istad.bankingapp.api.user.web;

import co.istad.bankingapp.api.user.UserService;
import co.istad.bankingapp.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;
    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id ,@RequestBody IsDeletedDto dto) {
        Integer deletedID = userService.updateIsDeletedStatusById(id,dto.status());
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been deleted...!")
                .timestamp(LocalDateTime.now())
                .data(deletedID)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id) {
        Integer delectId = userService.deleteUserById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been Found")
                .timestamp(LocalDateTime.now())
                .data(delectId)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?>  finUserById(@PathVariable Integer id){
        UserDto findId = userService.findUserById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been Found")
                .timestamp(LocalDateTime.now())
                .data(findId)
                .build();
    }

    @PostMapping
    public BaseRest<?> cerateNewUser(@RequestBody @Valid CreateUserDto createUserDto){//User should input data <CreateUserDto>
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been created successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> editUser(@PathVariable Integer id, @RequestBody @Valid EditUserDto editUserDto){//User should input data <EditUserDto>
        UserDto userDto = userService.editUser(id,editUserDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been edit successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name = "page",required = false,defaultValue = "1") int page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "20") int limit){
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been edit successfully.")
                .timestamp(LocalDateTime.now())
                .data(userService.findAllUsers(page, limit))
                .build();
    }


}
