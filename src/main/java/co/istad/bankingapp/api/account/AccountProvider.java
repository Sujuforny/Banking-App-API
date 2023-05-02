package co.istad.bankingapp.api.account;

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
}
