package org.jmws.tests.user.creation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.jmws.session.user.creation.UserCreation;
import org.jmws.session.user.creation.UserCreationHome;
import org.jmws.session.user.creation.UserCreationRemote;

/**
 * TestUserCreation
 * 
 * @author Mikael Barbeaux
 */
public class TestUserCreation {

	/**
	 * Main program. Test for the creation of a User.
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			// JNDI naming context
			InitialContext context  = new InitialContext();
			
			// Get UserCreationHome interface
			UserCreationHome home;
			Object obj = context.lookup(UserCreation.JNDI_NAME);
			home = (UserCreationHome) 
				PortableRemoteObject.narrow(obj, 
					UserCreationHome.class);
			
			// Get UserCreationRemote interface
			UserCreationRemote remote = home.create();
			
			// Asking for new User's login and password
			BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
			
			System.out.print("Login: ");
			String login = br.readLine();
			System.out.print("Password: ");
			String password = br.readLine();
			System.out.print("E-mail: ");
			String email = br.readLine();
			
			// Create the new User
			remote.addUser(login, password, email, Boolean.FALSE);
			System.out.println("-==== USER CREATED ====-");				
			
		}
		catch(Exception e) {
			System.out.println("Exception thrown...");
			System.out.println("Exception ClassName: " + 
				e.getClass().getName());
			System.out.println("Message: " + e.getMessage());	
		}
	}
}
