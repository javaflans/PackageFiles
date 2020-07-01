package pf01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.UserInfo;

/**
 * This class provide interface to feedback information to the user.
 */
public class MyUserInfo implements UserInfo {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
    private String password;

    private String passphrase;

    @Override
    public String getPassphrase() {
    	log.info("MyUserInfo.getPassphrase()");
        return null;
    }

    @Override
    public String getPassword() {
    	log.info("MyUserInfo.getPassword()");
        return null;
    }

    @Override
    public boolean promptPassphrase(final String arg0) {
    	log.info("MyUserInfo.promptPassphrase()");
        log.info(arg0);
        return false;
    }

    @Override
    public boolean promptPassword(final String arg0) {
        log.info("MyUserInfo.promptPassword()");
        log.info(arg0);
        return false;
    }

    @Override
    public boolean promptYesNo(final String arg0) {
        log.info("MyUserInfo.promptYesNo()");
        log.info(arg0);
        if (arg0.contains("The authenticity of host")) {
            return true;
        }
        return false;
    }

    @Override
    public void showMessage(final String arg0) {
        log.info("MyUserInfo.showMessage()");
    }
}
