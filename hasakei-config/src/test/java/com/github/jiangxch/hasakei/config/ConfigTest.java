package com.github.jiangxch.hasakei.config;

import com.github.jiangxch.hassakei.common.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;

/**
 * @author: jiangxch
 * @date: 2020/7/17 18:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest extends BaseTest{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void createDb() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();

        String sql = FileUtil.readFile("db/config.sql");
        System.out.println(sql);
    }


}
