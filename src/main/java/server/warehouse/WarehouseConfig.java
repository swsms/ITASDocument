package server.warehouse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

final class WarehouseConfig {
	private static final String PROPS_NAME = "warehouse.properties";
	private static WarehouseConfig instance;

	private final Properties props;

	private WarehouseConfig() throws IOException {
		props = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				PROPS_NAME);
		if (is != null) {
			props.load(is);
		} else {
			throw new FileNotFoundException(
					"Warehouse config not found in the classpath");
		}
	}

	public String apiRoot() {
		return props.getProperty("api_root");
	}

	public String apiRoot(String pathParam) {
		return apiRoot() + "/" + pathParam;
	}

	public String apiSearchPath(String searchContent) {
		return props.getProperty("search_path") + "/" + searchContent;
	}

	public static WarehouseConfig get() throws IOException {
		if (instance == null)
			instance = new WarehouseConfig();
		return instance;
	}
}
