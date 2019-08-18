package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminConfigTest {
    @Autowired
    AdminConfig adminConfig;

    @Test
    public void adminConfigTest() {
        //Given & When
        String adminEmail = adminConfig.getAdminMail();
        String adminName = adminConfig.getAdminName();
        String companyName = adminConfig.getCompanyName();
        String companyGoal = adminConfig.getCompanyGoal();

        //Then
        assertEquals("kodilla.mpastusiak@gmail.com", adminEmail);
        assertEquals("Mateusz", adminName);
        assertEquals("TaskCrudAppCreator", companyName);
        assertEquals("Our goal is to make the world a better place!", companyGoal);
    }
}
