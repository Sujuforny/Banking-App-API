package co.istad.bankingapp.api.accounttype;

import co.istad.bankingapp.api.accounttype.web.AccountTypeDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@Mapper
public interface AccountTypeMapper {
    @UpdateProvider(type = AccountTypeProvider.class ,method = "buildUpdateAccountType")
    void updateAccountType(@Param("id") Integer id ,@Param("accountType") AccountType accountType);
    @InsertProvider(type = AccountTypeProvider.class ,method = "buildInsert")
    void insertNewAccountType(@Param("accountType") AccountTypeDto accountTypeDto);
    @DeleteProvider(type = AccountTypeProvider.class,method = "buildDeleteById")
    void deleteAccountTypeById(@Param("id")Integer id);
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectByName")
    List<AccountType> selectAccountTypeBYName(@Param("name")String name);

    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectById")
    @Result(column = "name",property = "name")
    AccountType selectAccountTypeBYId(@Param("id")Integer id);
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectAll")
    @Result(column = "name",property = "name")
    List<AccountType> selectAllAccountType();

    @Select("SELECT EXISTS(SELECT *FROM account_types WHERE name ILIKE CONCAT('%',#{name},'%'))")
    Boolean isExitsByName(@Param("name") String name);
    @Select("SELECT EXISTS(SELECT *FROM account_types WHERE id =#{id})")
    Boolean isExitsById(@Param ("id") Integer id);
}
