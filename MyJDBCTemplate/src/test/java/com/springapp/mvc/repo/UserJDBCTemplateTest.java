package com.springapp.mvc.repo;

import com.springapp.mvc.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class UserJDBCTemplateTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserJDBCTemplate userJDBCTemplate;

    @Before
    public void setUp() throws Exception {
        userJDBCTemplate = new UserJDBCTemplate();
        userJDBCTemplate.setDataSource(dataSource);
    }

    @Test
    public void testCreate() throws Exception {
        assertEquals(1, userJDBCTemplate.create("Lola"));
    }
}
