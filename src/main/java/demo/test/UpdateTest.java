package demo.test;

import demo.model.User;
import demo.util.SessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author mingfei.net@Gmail.com
 *         23:10, 3/19/15.
 */
public class UpdateTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    static {
        sqlSessionFactory = SessionFactoryUtil.getSqlSessionFactoryViaXML();
        sqlSession = sqlSessionFactory.openSession();
    }

    private static int updateUser() {
        return sqlSession.update("user.update", new User(1, "张三", "密码"));
    }

    public static void main(String[] args) {
        try {
            System.out.println(updateUser());
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
