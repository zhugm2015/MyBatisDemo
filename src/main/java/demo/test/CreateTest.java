package demo.test;

import demo.data.UserMapper;
import demo.model.User;
import demo.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author mingfei.net@Gmail.com
 *         17:00, 3/18/15.
 */
public class CreateTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    static {
        sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactoryViaXML();
        sqlSession = sqlSessionFactory.openSession();
    }

    /**
     * create data via XML configuration.
     *
     * @param args
     */
    private static int insertUserViaXML() {
        return sqlSession.insert("user.insert", new User("测试1", "testPassword1"));
    }

    /**
     * create data vis Java class.
     * @param args
     */
    private static int insertUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.insertUser(new User("测试2", "testPassword2"));
//        sqlSession.commit();
        return i;
    }

    public static void main(String[] args) {
        try {
            System.out.println("insertUserViaXML :" + insertUserViaXML());
//            System.out.println("insertUser: " + insertUser());//todo
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
