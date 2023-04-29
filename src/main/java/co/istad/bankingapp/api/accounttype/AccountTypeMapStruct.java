package co.istad.bankingapp.api.accounttype;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto> toDtoList(List<AccountType> model);
    AccountTypeDto toDto(AccountType model);
}
