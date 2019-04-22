package dataProviders;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertyfilepath = "configs//Configuration.properties";
	
	
	
	public ConfigFileReader(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(propertyfilepath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyfilepath);
		}
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverpath");
		if(driverPath!= null) 
			return driverPath;
		else throw new RuntimeException("driverpath not specified in the Configuration.properties file");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitwait");
		if(implicitlyWait != null) 
			return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitwait not specified in the Configuration.properties file");		
	}
	
	public long getTimeout() {		
		String timeout = properties.getProperty("timeout");
		if(timeout != null) 
			return Long.parseLong(timeout);
		else throw new RuntimeException("timeout not specified in the Configuration.properties file");		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) 
			return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file");
	}
	
	public String getBrowserName() {
		String browsername = properties.getProperty("browser");
		if(browsername != null) 
			return browsername;
		else throw new RuntimeException("browser not specified in the Configuration.properties file");
	}
	

	public String getValueFromProperty(String key) {
		String value = properties.getProperty(key);
		return value;
		
	}

}
