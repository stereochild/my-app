package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoMailCreatorService implements MailTextCreator {

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String createMailMessageText(String message) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Goodbye!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_contact", "@: " + companyConfig.getCompanyMail() + "    mobile: " + companyConfig.getCompanyPhone());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("isList", false);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
