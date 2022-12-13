package eco_service.Eco.email;

public interface MailService {
    void sendMail(String clientMail, String subject, String message);
}
