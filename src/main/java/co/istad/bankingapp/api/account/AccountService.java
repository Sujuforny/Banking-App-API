package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.AccountDto;
import co.istad.bankingapp.api.account.web.CreateNewAccountDto;
import co.istad.bankingapp.api.account.web.UpdateAccountNameDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
      AccountDto createNewAccounts(CreateNewAccountDto createNewAccountDto);
      AccountDto findAccountById(Integer id);

      AccountDto editAccountNameById(Integer id , UpdateAccountNameDto updateAccountNameDto);
      Integer deleteAccountById(Integer id);
}
