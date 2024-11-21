package com.petgroomer.petgroomer.services;

import com.petgroomer.petgroomer.models.Cita;
import com.petgroomer.petgroomer.models.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;


    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(Email email, Cita cita) throws MessagingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(email.getAddress());
            mimeMessageHelper.setSubject(email.getSubject());
            //Creo context para agregar al email con thymeleaf
            Context context = new Context();
            context.setVariable("message", email.getBody());
            context.setVariable("cliente", cita.getCliente());
            context.setVariable("cita", cita);
            context.setVariable("empleado", cita.getEmpleado());
            context.setVariable("servicios", cita.getServicios());

            //Proceso la plantilla en html
            String html = templateEngine.process("email", context);
            mimeMessageHelper.setText(html, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MessagingException("Error al enviar el email");
        }
    }
}
