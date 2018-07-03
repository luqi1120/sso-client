package com.ohaotian.ssoclientrest.mapper;

import com.ohaotian.ssoclientrest.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created: luQi
 * Date:2018-4-27 14:49
 */
public interface UserMapper {

    @Select("select * from cas_user where username = #{username}")
    User select(String username);
}
