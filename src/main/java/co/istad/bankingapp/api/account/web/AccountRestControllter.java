package co.istad.bankingapp.api.account.web;

import co.istad.bankingapp.api.account.AccountService;
import co.istad.bankingapp.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountRestControllter {
    private final AccountService accountService;
    @GetMapping("/{id}")
    public BaseRest<?> findAccountById(@PathVariable Integer id){
        AccountDto accountDto=  accountService.findAccountById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User have been Found..!")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
    @PostMapping
    public BaseRest<?> createNewAccounts(@RequestBody CreateNewAccountDto createNewAccountDto){
        AccountDto accountDto =  accountService.createNewAccounts(createNewAccountDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Created..!")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
    @PutMapping("/{id}")
    public BaseRest<?> editAccountNameById(@PathVariable Integer id,@RequestBody UpdateAccountNameDto updateAccountNameDto){
        AccountDto accountDto =  accountService.editAccountNameById(id,updateAccountNameDto);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been update name successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountDto)
                .build();
    }
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteAccountById(@PathVariable Integer id){
        accountService.deleteAccountById(id);
        return BaseRest
                .builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account have been Deleted..!")
                .timestamp(LocalDateTime.now())
                .data(id)
                .build();
    }


}
