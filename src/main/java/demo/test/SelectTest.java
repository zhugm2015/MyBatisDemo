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
public class SelectTest {

    private static SqlSession sqlSession;

    static {
        SqlSessionFactory sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactory();
        sqlSession =  sqlSessionFactory.openSession();
    }

    private static void close() {
        sqlSession.close();
    }

    private static User selectById() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.getUserById(1);
    }

    private static User selectOne() {
        return sqlSession.selectOne("demo.mapper.UserMapper.getUserById", 1);
    }
    public static void main(String[] args) {
        try {
            System.out.println("selectById: " + selectById());
            System.out.println("selectOne: " + selectOne());
        } finally {
            close();
        }
    }
}
