package co.istad.bankingapp.api.accounttype;

import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto> findAll();
}
