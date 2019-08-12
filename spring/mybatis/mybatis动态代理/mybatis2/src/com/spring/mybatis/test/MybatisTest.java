package test;

import com.spring.mybatis.pojo.Menu;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * desc：   直接使用mybatis(原始)
 * author CDN
 * create 2019-06-09 23:45
 * version 1.0.0
 */
public class MybatisTest {

    private SqlSessionFactory sqlSessionFactory = null;

    @Test
    public void test() throws IOException {
// 1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 2. 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfigYuanShi.xml");

        // 3. 创建SqlSessionFactory对象
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
// 4. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 5. 执行SqlSession对象执行查询，获取结果User

//        ?##############################################################################
        // 第一个参数是User.xml的statement的id，第二个参数是执行sql需要的参数；
        Object user = sqlSession.selectOne("user.selectById", "031a5ae5611d49db9d8e88f36fde1643");

//        模糊查询
        List list = sqlSession.selectList("user.selectByIdMoHu", "2");

//        插入(增加)
        Menu menu = new Menu("32", "mybatis", "224", "8", "2018-9", "2019-9");
        int result = sqlSession.insert("user.insertItem", menu);
        // 插入，修改，删除需要进行事务提交
        sqlSession.commit();
//        #################################################################
        // 6. 打印结果
        System.out.println(user);
        System.out.println(list);
        System.out.println(result);
        // 7. 释放资源
        sqlSession.close();
    }
}
