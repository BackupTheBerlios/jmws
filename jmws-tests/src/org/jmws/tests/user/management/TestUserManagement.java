package org.jmws.tests.user.management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.jmws.session.user.management.UserManagement;
import org.jmws.session.user.management.UserManagementHome;
import org.jmws.session.user.management.UserManagementRemote;

/**
 * TestUserManagement
 * 
 * @author Mikael Barbeaux
 */
public class TestUserManagement {

	/**
	 * Main program. Test for the management of a User.
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		//try {
			// JNDI naming context
			InitialContext context  = new InitialContext();
			
			// Get UserCreationHome interface
			UserManagementHome home;
			Object obj = context.lookup(UserManagement.JNDI_NAME);
			home = (UserManagementHome) 
				PortableRemoteObject.narrow(obj, 
						UserManagementHome.class);
			
			// Get UserCreationRemote interface
			UserManagementRemote remote = home.create();
			
			// Displaying all inactive users
			System.out.println("1");
			Collection users = remote.getUsersToActive();
			System.out.println("2");
			Iterator it = users.iterator();
			System.out.println("3");
			System.out.println("Inactive Users:");
			while(it.hasNext()) {
				String user = (String) it.next();
				System.out.println("4");
				System.out.println(" " + user);
			}
			System.out.println();			
			
			// Asking for Activator
			BufferedReader br = new BufferedReader(
					new InputStreamReader(System.in));
			
			System.out.print("Activator Login: ");
			String theActivator = br.readLine();
			System.out.print("User to active: ");
			String user = br.readLine();
			
			// Create the new User
			remote.activeUser(user, theActivator);
			System.out.println("-==== USER ACTIVATED ====-");
					
			
	/*	}
		catch(Exception e) {
			System.out.println("Exception thrown...");
			System.out.println("Exception ClassName: " + 
				e.getClass().getName());
			System.out.println("Message: " + e.getMessage());	
		}*/
	}
}
