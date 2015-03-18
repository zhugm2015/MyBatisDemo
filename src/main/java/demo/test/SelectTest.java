package demo.test;

import demo.data.UserMapper;
import demo.model.User;
import demo.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.websocket.Session;

/**
 * @author mingfei.net@Gmail.com
 *         17:55, 3/13/15.
 */
public class SelectTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    private static final int ID = 4;

    static {
//        sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactory();
        sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactoryViaXML();
        sqlSession =  sqlSessionFactory.openSession();
    }

    private static void close() {
        sqlSession.close();
    }

    /**
     * use UserMapper class configuration.
     * @return
     */
    private static User selectById() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.getUserById(1);
    }

    /**
     * use MyBatis native method selectOne.
     * @return
     */
    private static User selectOne() {
        return sqlSession.selectOne("demo.data.UserMapper.getUserById", ID);
    }

    /**
     * user userMapper.xml configuration.
     * @return
     */
    private static User selectUserViaXML() {
        return sqlSession.selectOne("demo.data.UserMapper.selectUserViaXML", ID);
    }

    public static void main(String[] args) {
        try {
            System.out.println("selectById: " + selectById());
            System.out.println("selectOne: " + selectOne());
            System.out.println("selectUserViaXML: " + selectUserViaXML());
        } finally {
            close();
        }
    }
}
