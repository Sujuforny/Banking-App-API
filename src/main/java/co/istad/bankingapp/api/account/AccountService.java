package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.AccountDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
      AccountDto findAccountById(Integer id);
}
