package demo.mapper;

import org.apache.ibatis.annotations.Select;
import demo.model.User;

/**
 * @author mingfei.net@Gmail.com
 *         18:16, 3/13/15.
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int id);

}
