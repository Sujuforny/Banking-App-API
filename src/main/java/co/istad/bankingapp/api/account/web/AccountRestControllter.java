package co.istad.bankingapp.api.account.web;

import co.istad.bankingapp.api.account.AccountService;
import co.istad.bankingapp.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
