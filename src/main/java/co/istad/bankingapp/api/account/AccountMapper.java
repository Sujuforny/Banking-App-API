package co.istad.bankingapp.api.account;

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
            @Result(column = "transfer_limit",property = "transferLimit")
    })
    Account selectById(@Param("id") Integer id);
}
