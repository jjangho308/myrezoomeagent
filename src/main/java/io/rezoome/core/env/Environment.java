package io.rezoome.core.env;

import java.util.Map;

/**
 * Environment property class. <br />
 * 
 * @since 1.0.0
 * @author TACKSU
 *
 */
public class Environment {
	
	
	static{
	      
  
	}
	
	// Hide constructor. <br />
	private Environment(){
	  Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
        System.out.format("%s=%s%n",
                          envName,
                          env.get(envName));
    }
    
	}
}
