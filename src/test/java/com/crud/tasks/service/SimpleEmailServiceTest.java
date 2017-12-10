package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.mailCreator.MailTextCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailTextCreator mailTextCreator;

    @Test
    public void shouldSendEmail() throws MessagingException {
        //Given
        Mail mail = new Mail("test@test.com", "", "Test subject", "Test message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        Optional.ofNullable(mail.getToCc())
                .ifPresent(c ->mailMessage.setCc(mail.getToCc()));

        //When
        simpleEmailService.send(mail, mailTextCreator);

        //Then
        verify(javaMailSender, times(1)).send(Mockito.any(MimeMessagePreparator.class));
    }
}