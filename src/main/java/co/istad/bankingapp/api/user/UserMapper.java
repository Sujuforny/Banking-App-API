package co.istad.bankingapp.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void insert(@Param("u") User user);

    @SelectProvider(type =UserProvider.class, method ="buildSelectById")
//    @Result(column = "student_card_id",property = "studentCardId")
//    @Result(column = "is_student",property = "isStudent")
    @Results(id = "userResultMap",value = {
                @Result(column = "student_card_id",property = "studentCardId"),
                @Result(column = "is_student",property = "isStudent")
    })
    Optional<User> selectById(@Param("id") Integer id);



    @UpdateProvider(type = UserProvider.class, method = "buildUpdateById") // Use @Update annotation with the update SQL string as the value
    void edit(@Param("id") Integer id,@Param("u") User user); // Add updateSql parameter to accept the update SQL string



    @Select("SELECT EXISTS(SELECT *FROM users WHERE id =#{id} AND is_deleted = FALSE )")
    Boolean isExitsById(@Param ("id") Integer id);
    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id")Integer id);


    @UpdateProvider (type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletesById(@Param("id")Integer id ,  @Param("status") boolean status);
    @SelectProvider(type = UserProvider.class,method = "buildSelectAllUsers")
    @ResultMap("userResultMap")
    List<User> findAllUsers();
}
