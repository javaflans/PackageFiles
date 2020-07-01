package pf01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * This class provide interface to execute command on remote Linux.
 */

public class SSHExecutor {

	private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

	private String ipAddress;

	private String username;

	private String password;

	public static final int DEFAULT_SSH_PORT = 22;

	private Vector<String> stdout;

	public SSHExecutor(final String ipAddress, final String username, final String password) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		stdout = new Vector<String>();
	}

	public int execute(final String[] commands) {
		int returnCode = 0;
		JSch jsch = new JSch();
		MyUserInfo userInfo = new MyUserInfo();

		try {
			Session session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
			session.setPassword(password);
			session.setUserInfo(userInfo);
			session.connect();

			Channel channel = session.openChannel("shell");
			channel.setOutputStream(null);
			PrintStream shellStream = new PrintStream(channel.getOutputStream());

			channel.connect();
			for (String command : commands) {
				log.info("The remote command is: " + command);
				shellStream.println(command);
				shellStream.flush();
			}

			Thread.sleep(5000);

			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnCode;
	}

	public Vector<String> getStandardOutput() {
		return stdout;
	}

}
