package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountTypeService {
     AccountTypeDto editAccountTypeById(Integer id,AccountTypeDto accountTypeDto);
     AccountTypeDto insertNewAccountType(AccountTypeDto accountTypeDto);
     List<AccountTypeDto> findAllAccountType();
     AccountTypeDto findAccountTypeById(Integer id);
     List<AccountTypeDto> findAccountTypeByName(String name);

     Integer deleteAccountTypeById(Integer id);
}
