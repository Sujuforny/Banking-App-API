package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.AccountDto;
import co.istad.bankingapp.api.account.web.CreateNewAccountDto;
import co.istad.bankingapp.api.account.web.UpdateAccountNameDto;
import co.istad.bankingapp.api.accounttype.AccountType;
import co.istad.bankingapp.api.accounttype.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountMapper accountMapper;
    private final AccountMapStruct accountMapStruct;


    @Override
    public AccountDto createNewAccounts(CreateNewAccountDto createNewAccountDto) {
        accountMapper.createNewAccount(createNewAccountDto);
        Account account =  accountMapper.selectAccountByAccountNo(createNewAccountDto.accountNo());
        AccountType accountType=  accountTypeMapper.selectAccountTypeBYId(account.getAccountType().getId());
        account.setAccountType(accountType);
        return accountMapStruct.accountToAccountDto(account);
    }

    @Override
    public AccountDto findAccountById(Integer id) {
        Account account = accountMapper.selectAccountById(id);
        AccountType accountType=  accountTypeMapper.selectAccountTypeBYId(account.getAccountType().getId());
        account.setAccountType(accountType);
        return accountMapStruct.accountToAccountDto(account);

    }

    @Override
    public AccountDto editAccountNameById(Integer id, UpdateAccountNameDto updateAccountNameDto) {
        accountMapper.updateAccountById(id ,updateAccountNameDto);
        return this.findAccountById(id);
    }

    @Override
    public Integer deleteAccountById(Integer id) {
        accountMapper.deleteAccountById(id);
        return id;
    }
}
