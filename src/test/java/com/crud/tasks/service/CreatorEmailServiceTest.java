package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreatorEmailServiceTest {
    @Autowired
    private MailCreatorService mailCreatorService;

    @Test
    public void changeMessageTest() {
        //Given
        Mail mail = new Mail("test@test.com", "Tasks: New Trello card", "Test Message", null);

        //When
        String messageFromBuildTrelloCardEmail = mailCreatorService.buildTrelloCardEmail(mail.getMessage());
        String messageFromBuildScheaduledTasksEmail = mailCreatorService.buildScheaduledTasksEmail(mail.getMessage());

        assertNotEquals(messageFromBuildTrelloCardEmail, mail.getMessage());
        assertNotEquals(messageFromBuildScheaduledTasksEmail, mail.getMessage());
        assertNotEquals(messageFromBuildScheaduledTasksEmail, messageFromBuildTrelloCardEmail);
    }
}
