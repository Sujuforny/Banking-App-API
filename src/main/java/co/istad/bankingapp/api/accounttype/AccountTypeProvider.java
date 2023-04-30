package co.istad.bankingapp.api.accounttype;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    private final String tableName ="account_types";
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
