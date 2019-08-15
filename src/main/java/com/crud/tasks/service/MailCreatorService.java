package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskController taskController;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public Context createUniversalTasksEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Best regards");
        return context;
    }

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello account");
        functionality.add("Application allows sending task to Trello");

        Context context = createUniversalTasksEmail(message);
        context.setVariable("heading", "Successful!");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheaduledTasksEmail(String message) {
        List<TaskDto> taskDtoList = taskController.getTasks();
        List<String> tasksTitlesList = taskDtoList.stream()
                .map(object -> object.getTitle())
                .collect(Collectors.toList());

        Context context = createUniversalTasksEmail(message);
        context.setVariable("heading", "Raport!");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("is_list", true);
        context.setVariable("list_header", "Tasks titles:");
        context.setVariable("list_content", tasksTitlesList);
        return templateEngine.process("mail/universal-mail", context);
    }

}
