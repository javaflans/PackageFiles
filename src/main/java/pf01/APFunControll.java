package pf01;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APFunControll implements ActionListener {

	private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Object> config = PF0101.config;

		for (Checkbox chAP : PF0101.chAPs) {
			if (chAP.getState()) {
				@SuppressWarnings("unchecked")
				Map<String, Object> data = (Map<String, Object>) config.get(chAP.getLabel());
				log.info("======================================");
				log.info("AP: " + chAP.getLabel());
				log.info("======================================");
				log.info("Execute Shell Command: ");
				SSHExecutor sshExecutor = new SSHExecutor(data.get("url").toString(), data.get("id").toString(),
						data.get("pwd").toString());
				int status = 0;
				if (chAP.getLabel().equals("landAE")) {
					String[] cmds = { "sh apstop" };
					status = sshExecutor.execute(cmds);
					String[] cmds2 = { "sh apstart" };
					status = sshExecutor.execute(cmds2);
				} else {
					String[] cmds = { "cd /opt/jboss/bin", "./apstop.sh", "./rm_tmpDir.sh" };
					status = sshExecutor.execute(cmds);
					String[] cmds2 = { "cd /opt/jboss/bin", "./apstart.sh" };
					status = sshExecutor.execute(cmds2);
				}

				// Vector<String> stdout = sshExecutor.getStandardOutput();
				// log.info("Execute Shell Output: "+status);
				// for (String str : stdout) {
				// 	log.info(str);
				// }
				System.out.println("");
			}
		}
	}

}
