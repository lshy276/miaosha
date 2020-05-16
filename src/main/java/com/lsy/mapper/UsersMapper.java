package com.lsy.mapper;

import com.lsy.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 李帅豫 on 2020/4/22.
 */
public interface UsersMapper {

    @Select("select * from user where name = #{name} and password = #{password}")
    Users login(Users users);

}
