package com.crud.tasks.service.mailCreator;

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

        List<String> tasks = new ArrayList<>();
        tasks.add("Do task");
        tasks.add("Do task");
        tasks.add("Do task");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("preview_message", "Currently in database...");
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Goodbye!");
        context.setVariable("company_name", companyConfig.getCompanyName());
        context.setVariable("company_contact", "@: " + companyConfig.getCompanyMail() + "    mobile: " + companyConfig.getCompanyPhone());
        context.setVariable("company_goal", companyConfig.getCompanyGoal());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks", tasks);
        return templateEngine.process("mail/info-mail", context);
    }
}
