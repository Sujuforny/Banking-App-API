package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> accountTypeListToAccountTypeDtoList(List<AccountType> accountTypeList);
    AccountTypeDto AcountTypeToAccountTypeDto (AccountType accountType);
    AccountType accountTypeDtoToAccountType(AccountTypeDto accountTypeDto);
}




