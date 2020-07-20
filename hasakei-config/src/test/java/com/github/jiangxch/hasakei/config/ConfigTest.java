package com.github.jiangxch.hasakei.config;

import com.github.jiangxch.hasakei.config.dao.UsersMapper;
import com.github.jiangxch.hasakei.common.util.FileUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 默认使用 application.properties 配置
 * @author: jiangxch
 * @date: 2020/7/17 18:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void createDb() throws SQLException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();

        String sql = FileUtil.readFile("db/config.sql");
//        System.out.println(sql);
        Statement state = connection.createStatement();

        String[] sqls = sql.split(";");
        for (String s : sqls) {
            if (!"".equals(s)) {
                state.execute(s);
            }
        }

    }

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void  curdTest(){
        System.out.println(usersMapper.findAll());
        System.out.println(usersMapper.aaa());
    }

}
