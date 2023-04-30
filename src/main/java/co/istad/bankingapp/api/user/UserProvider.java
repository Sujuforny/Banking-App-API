package co.istad.bankingapp.api.user;

import lombok.ToString;
import org.apache.ibatis.jdbc.SQL;

import java.util.Set;

public class UserProvider {
    private final String tableName ="users";

    public String buildSelectByStudentCardId(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("student_card_id ILIKE CONCAT('%', #{studentCardId}, '%')");
        }}.toString();
    }
    public String buildUpdateIsDeletedByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted = #{status}");
            WHERE("id= #{id}");
        }}.toString();
    }

    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");

        }}.toString();
    }
    public String buildSelectById(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}","is_deleted = FALSE");
        }}.toString();
    }
    public String buildUpdateById(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name=#{u.name}");
            SET("gender=#{u.gender}");
            WHERE("id=#{id}"); // Add your own WHERE condition here
        }}.toString();
    }
    public String buildSelectAllUsers(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted = FALSE");

        }}.toString();
    }
    public String buildSelectByName(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name ILIKE CONCAT('%', #{name}, '%')");
        }}.toString();
    }

}
