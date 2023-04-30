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

    @SelectProvider(type = UserProvider.class,method = "buildSelectByName")
    @ResultMap("userResultMap")
    List<User> selectByName(@Param("name") String name);
    @SelectProvider(type = UserProvider.class, method = "buildSelectByStudentCardId")
    @ResultMap("userResultMap")
    List<User> selectByStudentCardId( String studentCardId);
    @SelectProvider(type = UserProvider.class,method = "buildSelectAllUsers")
    @ResultMap("userResultMap")
    List<User> findAllUsers();

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateById") // Use @Update annotation with the update SQL string as the value
    void edit(@Param("id") Integer id,@Param("u") User user); // Add updateSql parameter to accept the update SQL string




    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id")Integer id);


    @UpdateProvider (type = UserProvider.class, method = "buildUpdateIsDeletedByIdSql")
    void updateIsDeletesById(@Param("id")Integer id ,  @Param("status") boolean status);



    @Select("SELECT EXISTS(SELECT *FROM users WHERE id =#{id} AND is_deleted = FALSE )")
    Boolean isExitsById(@Param ("id") Integer id);

    @Select("SELECT EXISTS(SELECT *FROM users WHERE name ILIKE CONCAT('%',#{name},'%') AND is_deleted = FALSE )")
    Boolean isExitsByName(@Param("name") String name);

    @Select("SELECT EXISTS(SELECT *FROM users WHERE student_card_id ILIKE CONCAT('%',#{studentCardId},'%') AND is_deleted = FALSE )")
    Boolean isExitsByStudentCardId(@Param("studentCardId") String studentCardId);
}
