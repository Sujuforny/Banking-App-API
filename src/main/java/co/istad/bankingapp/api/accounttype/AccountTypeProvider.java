package co.istad.bankingapp.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private final String tableName ="account_types";

    public String buildUpdateAccountType(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name=#{accountType.name}");
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildInsert(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{accountType.name}");
        }}.toString();
    }

    public String buildDeleteById(){
        return new  SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildSelectByName(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name ILIKE CONCAT('%', #{name}, '%')");
        }}.toString();
    }
    public String buildSelectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildSelectAll(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
}
