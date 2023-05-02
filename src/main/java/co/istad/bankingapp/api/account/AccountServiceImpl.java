package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;
    @Override
    public AccountDto findAccountById(Integer id) {
        Account account = accountMapper.selectById(id);
        return accountMapStruct.accountToAccountDto(account);
    }
}
