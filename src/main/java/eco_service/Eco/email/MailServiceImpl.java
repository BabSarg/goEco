package eco_service.Eco.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final MailSender mailSender;

    public MailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendMail(String clientMail, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(clientMail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }
}
