package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public List<AccountTypeDto> findAllAccountType() {
        List<AccountType> accountTypeList =  accountTypeMapper.selectAllAccountType();
        return accountTypeMapStruct.accountTypeListToAccountTypeDtoList(accountTypeList);
    }

    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
       AccountType accountType= accountTypeMapper.selectAccountTypeBYId(id);

        return null;
    }
}
