package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public AccountTypeDto editAccountTypeById(Integer id ,AccountTypeDto accountTypeDto) {
        if (accountTypeMapper.isExitsById(id)) {
          AccountType accountType = accountTypeMapStruct.accountTypeDtoToAccountType(accountTypeDto) ;
          accountTypeMapper.updateAccountType(id, accountType);
            return this.findAccountTypeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Account Type  with '%d' is not found",id));

    }

    @Override
    public AccountTypeDto insertNewAccountType(AccountTypeDto accountTypeDto) {
       accountTypeMapper.insertNewAccountType(accountTypeDto);
        return  accountTypeDto;
    }

    @Override
    public List<AccountTypeDto> findAllAccountType() {
        List<AccountType> accountTypeList =  accountTypeMapper.selectAllAccountType();
        return accountTypeMapStruct.accountTypeListToAccountTypeDtoList(accountTypeList);
    }

    @Override
    public AccountTypeDto findAccountTypeById(Integer id) {
        if (accountTypeMapper.isExitsById(id)) {
            AccountType accountType = accountTypeMapper.selectAccountTypeBYId(id);
            return accountTypeMapStruct.AcountTypeToAccountTypeDto(accountType);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Account Type  with '%d' is not found",id));

    }

    @Override
    public List<AccountTypeDto> findAccountTypeByName(String name) {
        if(accountTypeMapper.isExitsByName(name)){
            List<AccountType> accountTypeList = accountTypeMapper.selectAccountTypeBYName(name);
            accountTypeMapStruct.accountTypeListToAccountTypeDtoList(accountTypeList);
            return accountTypeMapStruct.accountTypeListToAccountTypeDtoList(accountTypeList);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Account Type  with '%s' is not found",name));
    }

    @Override
    public Integer deleteAccountTypeById(Integer id) {
        if (accountTypeMapper.isExitsById(id)){
            accountTypeMapper.deleteAccountTypeById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Account Type  with '%d' is not found",id));
    }
}
