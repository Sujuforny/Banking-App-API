package co.istad.bankingapp.api.account;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    private final String  tableName="accounts";


    public String buildSelectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildInsert(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("account_no","#{a.accountNo}");
            VALUES("account_name","#{a.accountName}");
            VALUES("profile","#{a.profile}");
            VALUES("pin","#{a.pin}");
            VALUES("password", "#{a.password}");
            VALUES("phone_number","#{a.phoneNumber}");
            VALUES("account_type","#{a.accountType}");
        }}.toString();
    }
    public String buildSelectByAccountNo(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("account_no =#{accountNo}");
        }}.toString();
    }
    public String buildUpdateById(){
        return new SQL(){{
            UPDATE(tableName);
            SET("account_name=#{upDateAccountNameDto.accountName}");
            WHERE("id=#{id}");
        }}.toString();
    }
    public String  buildDeleteById(){
        return new SQL(){{
             DELETE_FROM(tableName);
             WHERE("id=#{id}");
        }}.toString();
    }
}
