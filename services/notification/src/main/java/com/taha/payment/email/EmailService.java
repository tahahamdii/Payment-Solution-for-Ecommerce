package com.taha.payment.email;

import com.taha.payment.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("hamdi.taha@esprit.tn");
        final String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent successful email to %s", destinationEmail));
        } catch (MessagingException e) {
            log.warn("WARN - Email sent failed", e);
        }
    }


    @Async
    public void sentOrderSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
        messageHelper.setFrom("hamdi.taha@esprit.tn");
        final String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);


        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email sent successful email to %s", destinationEmail));
        } catch (MessagingException e) {
            log.warn("WARN - Email sent failed", e);
        }
    }
}
