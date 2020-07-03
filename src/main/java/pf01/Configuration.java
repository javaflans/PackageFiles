package pf01;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Configuration {
	public Map<String, Object> loadConfig() {
		Yaml yml = new Yaml();
		InputStream is = null;
		is = this.getClass().getClassLoader().getResourceAsStream("config.yml");
		Map<String, Object> map = null;
		if (is != null)
			map = yml.load(is);
		return map;
	}
}
