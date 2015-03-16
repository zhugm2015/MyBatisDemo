package demo.test;

import demo.mapper.UserMapper;
import demo.model.User;
import demo.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author mingfei.net@Gmail.com
 *         17:55, 3/13/15.
 */
public class Test {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }
}
