package co.istad.bankingapp.api.accounttype.web;

import co.istad.bankingapp.api.accounttype.AccountTypeService;
import co.istad.bankingapp.api.user.UserService;
import co.istad.bankingapp.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-type")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
    @PostMapping
    public BaseRest<?> createNewAccountType(@RequestBody AccountTypeDto accountTypeDto){
       accountTypeService.insertNewAccountType(accountTypeDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been created successfully.")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> FindAllAccountType(){
        List<AccountTypeDto>accountTypeDtoList = accountTypeService.findAllAccountType();
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Found..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findAccountTypeById(@PathVariable Integer id){
        AccountTypeDto accountTypeDto= accountTypeService.findAccountTypeById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Found..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
    @GetMapping("/{name}/account-type-name")
    public BaseRest<?> findAccountTypeByName(@PathVariable String name){
        List<AccountTypeDto>accountTypeDtoList =  accountTypeService.findAccountTypeByName(name);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Found..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDtoList)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> editAccountTypeById(@RequestBody AccountTypeDto accountTypeDto,@PathVariable Integer id){
        System.out.println(id);
        System.out.println(accountTypeDto);
       AccountTypeDto accountTypeDto1= accountTypeService.editAccountTypeById(id,accountTypeDto);

        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Edited..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto1)
                .build();
    }
    @DeleteMapping("/{id}")
    public  BaseRest<?> deleteAccountTypeById(@PathVariable Integer id){
        accountTypeService.deleteAccountTypeById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been deleted successfully. ")
                .timestamp(LocalDateTime.now())
                .data(id)
                .build();
    }
}
