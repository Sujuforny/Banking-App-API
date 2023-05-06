package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.AccountDto;
import co.istad.bankingapp.api.account.web.CreateNewAccountDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapStruct {

    AccountDto accountToAccountDto (Account account);
    Account AccountDtoToAccount (AccountDto accountDto);
    List<Account> accountDtoListToAccountList (List<AccountDto> accountDtoList);
    List<AccountDto> accountListToAccountDtoList (List<Account> accountList);
//    Account createNewAccountDtoToAccount (CreateNewAccountDto createNewAccount);
}
