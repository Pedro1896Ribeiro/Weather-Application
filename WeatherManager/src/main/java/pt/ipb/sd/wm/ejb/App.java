package pt.ipb.sd.wm.ejb;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;

public class App {

    public static void main(String[] args) throws IOException {
	Properties p = new Properties();
	p.load(App.class.getResourceAsStream("/jndi.properties"));

	Configuration configuration = new Configuration();
	configuration.setProperties(p);
	configuration.setWithEjbRemote(true);
	try (
		Container container = new Container(configuration);
		Scanner scanner = new Scanner(System.in);
		) {

	    container.deployClasspathAsWebApp("/", new File("src/main/webapp"));
	    System.out.println("Started on http://localhost:"
		    + container.getConfiguration().getHttpPort());
	    
            while (scanner.hasNextLine()) {
        	String l = scanner.nextLine();
        	System.out.println("Linha: " + l);
                switch (l.trim()) {
                    case "quit":
                    case "exit":
                        return;
                    default:
                        System.out.println("Unknown command '" + l + "', supported commands: 'quit', 'exist'");
                }
            }
	    container.await();
	}
    }
}
