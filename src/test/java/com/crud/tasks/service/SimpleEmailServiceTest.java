package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//@RunWith(MockitoJUnitRunner.class)
//public class SimpleEmailServiceTest {
//
//    @InjectMocks
//    private SimpleEmailService simpleEmailService;
//
//    @Mock
//    private JavaMailSender javaMailSender;
//
//    @Test
//    public void shouldSendEmail() {
//        //Given
//        Mail mail = new Mail("test@test.com", "", "Test subject", "Test message");
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//        Optional.ofNullable(mail.getToCc())
//                .ifPresent(c ->mailMessage.setCc(mail.getToCc()));
//
//        //When
//        simpleEmailService.send(mail);
//
//        //Then
//        verify(javaMailSender, times(1)).send(mailMessage);
//    }
//}