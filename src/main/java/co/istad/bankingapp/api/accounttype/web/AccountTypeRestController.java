package co.istad.bankingapp.api.accounttype.web;

import co.istad.bankingapp.api.accounttype.AccountTypeService;
import co.istad.bankingapp.api.user.UserService;
import co.istad.bankingapp.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-type")
public class AccountTypeRestController {
    private final AccountTypeService accountTypeService;
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
}
