package co.istad.bankingapp.api.account;

import co.istad.bankingapp.api.account.web.CreateNewAccountDto;
import co.istad.bankingapp.api.account.web.UpdateAccountNameDto;
import co.istad.bankingapp.api.accounttype.AccountType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    @SelectProvider(type = AccountProvider.class , method = "buildSelectById")
    @Results(id = "accountResultMap",value = {
            @Result(column = "account_no",property = "accountNo"),
            @Result(column = "account_name",property = "accountName"),
            @Result(column = "profile",property = "profile"),
            @Result(column = "phone_number",property = "phoneNumber"),
            @Result(column = "transfer_limit",property = "transferLimit"),
            @Result(column = "account_type",property = "accountType.id")
    })
    Account selectAccountById(@Param("id") Integer id);

    @InsertProvider(type = AccountProvider.class, method = "buildInsert")
    void createNewAccount(@Param("a") CreateNewAccountDto createNewAccountDto);

    @SelectProvider(type = AccountProvider.class, method = "buildSelectByAccountNo")
    @ResultMap("accountResultMap")
    Account selectAccountByAccountNo(@Param ("accountNo") String accountNo);

    @UpdateProvider(type = AccountProvider.class,method = "buildUpdateById")
    void updateAccountById(@Param("id") Integer id ,@Param("upDateAccountNameDto") UpdateAccountNameDto updateAccountNameDto);

    @DeleteProvider(type = AccountProvider.class,method = "buildDeleteById")
    Boolean deleteAccountById(Integer id);
}
