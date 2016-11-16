package common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 验证类
 *
 * @author Administrator
 *
 */
public class MyAuthenticator extends Authenticator {
    private String userName = null;
    private String password = null;

    public MyAuthenticator() {

    }

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
