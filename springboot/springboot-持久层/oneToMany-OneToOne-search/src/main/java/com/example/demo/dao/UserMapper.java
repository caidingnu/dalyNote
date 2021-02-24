package com.example.demo.dao;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface UserMapper {

//      ################################################## xml 方式

    /**
     * 一对多
     *
     * @return
     */
    User xmlOneToManylist();

    /**
     * 一对一
     *
     * @return
     */
    Role xmlOneToOneRole();


//   ################################################## 注解方式


    /**
     * 一对一  //one to one
     *
     * @return
     */
    @Select("select * from role where role_id=#{roleId}")
    @Results(id = "zhujieOneToOneRoleMap", value = {
            //id表示主键
            @Result(id = true, column = "role_id", property = "roleId"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "role_id", property = "user", one = @One(select = "com.example.demo.dao.UserMapper.selectByRoleId"))
    })
    Role zhujieOneToOneRole(Integer roleId);

    /**
     * 一对一辅助查询
     *
     * @param roleId
     * @return
     */
    @Select("select * from sys_user where id = (select user_id from role where role_id =#{roleId}) ")
    User selectByRoleId(Integer roleId);


    /**
     * 一对多
     *
     * @return
     */
    //one to many
    @Select("select * from sys_user where id=#{id}")
    @Results({
            @Result(column = "id", property = "roles", many = @Many(select = "com.example.demo.dao.UserMapper.zhujieOneToManylistRole",fetchType =FetchType.EAGER ))
    })
    User zhujieOneToManylist(Integer id);

    /**
     * 一对多辅助查询
     * @param id
     * @return
     */
    @Select("select * from role where user_id =#{id}")
    List<Role> zhujieOneToManylistRole(Integer id);

//################################   传递多个参数。
    /**
     * 首先我们给这张表增加age（年龄）和gender（性别）两个参数。当我们需要根据age和gender查询学生的午餐，
     * 这时需要改写column属性的格式。等号左侧的userAge和userGender对应java接口的参数，右侧的对应数据库字段名。
     * 即将查到的my_student表中age和gender字段的值，分别赋给getLunchByAgeAndGender方法中的age和gender参数，
     * 去查询对应的name（午餐名）。
     * @return
     */
//    @Select("select id, name, age, gender from my_student")
//    @Results({
//            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
//            @Result(column="class_id", property="classId", jdbcType=JdbcType.INTEGER),
//            @Result(column="{userAge=user_age,userGender=user_gender}", property="lunch",
//                    one=@One(select="com.example.demo.mapper.StudentMapper.getLunchByAgeAndGender")),
//    })
//    List<User> selectAllAndLunch();
//
//    @Select("select name from lunch where student_age = #{userAge} and student_gender = #{userGender}")
//    String getLunchByAgeAndGender(@Param("userAge") int userAge, @Param("userGender") int userGender);
}