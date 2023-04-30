package co.istad.bankingapp.api.accounttype;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectById")
    @Result(column = "name",property = "name")
    AccountType selectAccountTypeBYId(@Param("id")Integer id);
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectAll")
    @Result(column = "name",property = "name")
    List<AccountType> selectAllAccountType();

}
