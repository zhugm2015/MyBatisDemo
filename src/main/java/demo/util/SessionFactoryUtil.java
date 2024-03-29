package demo.util;

import demo.data.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author mingfei.net@Gmail.com
 *         17:47, 3/13/15.
 */
public class SessionFactoryUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * get SqlSessionFactory instance via MyBatis Java classes.
     *
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    /**
     * get SqlSessionFactory instance via XML configurations.
     *
     */

    public static SqlSessionFactory getSqlSessionFactoryViaXML() {
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);

            /**
             *    If not set properties in mybatis-config.xml,
             *    use build(inputStream, properties) method.
             */

//            Properties properties = new Properties();
//            properties.load(new FileInputStream("src/main/resources/jdbc.properties"));
//            return new SqlSessionFactoryBuilder().build(inputStream, properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getSqlSessionFactory());
        System.out.println(getSqlSessionFactoryViaXML());
    }
}
