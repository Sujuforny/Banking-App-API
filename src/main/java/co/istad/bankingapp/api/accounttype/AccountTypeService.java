package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountTypeService {
    List<AccountTypeDto> findAllAccountType();
    AccountTypeDto findAccountTypeById(Integer id);
}
