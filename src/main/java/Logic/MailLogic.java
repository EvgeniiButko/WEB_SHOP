package Logic;

import Resourses.MessageManager;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Properties;

/**
 * A logic for the Email.
 */
public final class MailLogic {
    /**
     * Private constructor.
     */
    private MailLogic() {
    }

    /**
     * Email.
     */
    private static final String USERNAME = "butkoevgenii@gmail.com";//illuminataldus@gmail.com
    /**
     * Password.
     */
    private static final String PASSWORD = "kong72820";//aldus5070507aldus
    /**
     * Logger.
     */

    /**
     * Sends the message.
     *
     * @param email email.
     * @param id    id.
     * @param login login.
     * @throws MessagingException    occurs when the message wasn't sent.
     * @throws FileNotFoundException occurs when a file with
     *                               the message is lost.
     */
    public static void sendConfirmationMessage(final String email,
                                               final int id,
                                               final String login,
                                               final String mess)
            throws MessagingException, FileNotFoundException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("Confirmation");
        message.setContent(createConfirmMessage(id, login, mess),
                "text/html");
        Transport.send(message);

    }

    /**
     * Creates message for confirmation.
     *
     * @param id    id.
     * @param login login.
     * @return Message.
     * @throws FileNotFoundException occurs when a file with
     *                               the message is lost.
     */
    private static String createConfirmMessage(final int id,
                                               final String login,
                                               final String mess)
            throws FileNotFoundException {
        Formatter formatter = new Formatter();
        formatter.format(mess, login, id);
        return formatter.toString();
    }

    public static void main(String[] args) {
        try {
            sendConfirmationMessage("butkoevgenii@gmail.com",1,"butkoevgenii@gmail.com","hello");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}