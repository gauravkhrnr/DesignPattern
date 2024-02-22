package com.structural.pattern;
/*
  Proxy :
  The Proxy design pattern provides a placeholder for another object in order to control access to it. 
  The main purpose of this pattern is to add an additional level of interface to the target object, allowing us to perform operations 
  before or after the target object is accessed, without changing its interface.
 
 The Proxy pattern solves several problems :
  1) Controlling access to an object: The Proxy pattern provides a way to control access to an object by encapsulating it and providing a 
     surrogate object to the client. This allows us to restrict or grant access to the object based on specific conditions.
  2) Reducing resource usage: The Proxy pattern can be used to reduce the resource usage of an application by creating lightweight 
     proxy objects that consume fewer resources than the actual objects they represent. This is useful when we have a large number of 
     expensive objects that we want to limit the number of. 
  3) Enhancing functionality: The Proxy pattern allows us to enhance the functionality of an object by adding additional features 
     such as caching, logging, or lazy initialization without modifying its original implementation.
  
  When to use : 
  1) Proxy pattern can be useful in cases where sessions are provided to an application, by allowing the use of a proxy session 
     in place of a real session.
     For example: consider a web application that allows users to login and access their personal account information. 
     When a user logs in, a session is created that contains information about the user, such as their name, email address, and 
     preferences. This session is stored on the server and is accessed by the application whenever the user interacts with the system.
     In this scenario, we can use a Proxy pattern to provide a proxy session object that can be used in place of the real session object. 
     The proxy session object would contain a reference to the real session object, and would be responsible for controlling access to it.
  
  2) One use case for a proxy session object would be to limit the amount of access that a user has to their account information. 
     For example: if a user logs in from a public computer or a device that they don't own, the application might want to restrict access 
     to certain parts of their account information, such as their billing information or their purchase history.
     The proxy session object could be designed to provide this functionality by checking the user's IP address or device type, and 
     only allowing access to certain parts of their account information based on those factors. The proxy session object could also be designed 
     to log out the user automatically after a certain period of inactivity, or to limit the number of login attempts that are allowed.
     Using a proxy session object in this way allows the application to provide a more secure and flexible user experience, 
     while still maintaining control over the user's access to their account information.
 */
/*
+----------------------------------------+
|                Session                 |
+----------------------------------------+
| -sessionId: int                        |
+----------------------------------------+
| +open(): void                          |
| +close(): void                         |
+----------------------------------------+
                  |
      +---------------------+
      |                     |
+----------------+  +--------------------------+
|  RealSession   |  |    ProxySession          |
+----------------+  +--------------------------+
| -sessionId: int|  | -sessionId: int          |
+----------------+  | -realSession: RealSession|
| +open(): void  |  | +open(): void            |
| +close(): void |  | +close(): void           |
+----------------+  +--------------------------+
		      |           |  
		      |           |
		      |           |                       
		      |           |
		      |           |
		   +------------------+
		   |                  |
		   |    WebApp        |
		   |                  |
		   +------------------+
		   | -session: Session|
		   +------------------+
		   | +execute(): void |
		   +------------------+   

  Advatages :
  1) Controlled access to the target object: The Proxy allows us to control access to the target object, by adding additional functionality 
     before or after the target object is accessed.
  2) Reduced memory usage: The Proxy can delay the creation of the target object until it is actually needed, reducing memory usage in 
     situations where the target object is expensive to create.
  3) Improved performance: The Proxy can cache the results of expensive operations, improving performance in situations where the same 
     operation is called repeatedly.

  Disadvantages :
  1) Additional complexity: Introducing a proxy layer adds an additional layer of complexity to the system. This additional complexity 
     can be a burden on the system, especially if the proxy object is handling multiple tasks.
  2) Performance overhead: Proxy design pattern can add additional overheads to the system in terms of performance. The extra processing 
     required by the proxy can slow down the system.
  3) Maintenance overhead: The proxy design pattern can add to the maintenance overhead of the system. Any changes made to the underlying 
     real object must also be reflected in the proxy, which can be a challenging task.
  4) Limited functionality: Proxies can be limited in terms of the functionality they offer compared to the real object. This can be 
     a problem if the proxy does not support all of the features required by the system.
  5) Security risks: The use of a proxy can introduce additional security risks, as it adds another point of access to the system. 
     If not designed and implemented properly, a proxy can be exploited by malicious users.
*/

interface Session {
	public boolean login(String username,String password);
    public void logout();
    public void sendData(String data) throws Exception;
}

class RealSession implements Session {
	public boolean login(String username,String password) {
		 System.out.println("Logging in with username: "+username +" and password: "+ password);
	     // Do actual login work here
	     return true;
	}
	
    public void logout() {
    	System.out.println("Logging out");
    }
    
    public void sendData(String data) throws Exception {
    	System.out.println("Sending data: " + data);
    }
}

class ProxySession implements Session {
	private Session realSession;
	private boolean isLoggedIn = false;
	 
	public ProxySession(Session realSession) {
		this.realSession = realSession;
	}
	
    public boolean login(String username,String password) {
        if (realSession.login(username, password)) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() { 
        isLoggedIn = false;
        realSession.logout();
    }

    public void sendData(String data) throws Exception {
        if (isLoggedIn) {
            realSession.sendData(data);
        } else {
            throw new Exception("Cannot send data without logging in first.");
        }
    }
}

class WebApp {
	private Session session;
	public WebApp(Session session) {
		this.session = session;
	}
	
	public boolean login(String username,String password) {
        return session.login(username, password);
    }

	public void logout() { 
        session.logout();
    }

	public void sendData(String data) throws Exception {
        session.sendData(data);
    }
}

public class ProxyPattern {

	public static void main(String[] args) {
		Session realSession = new RealSession();
	    Session proxySession = new ProxySession(realSession);

	    try {
	    	//With session
			WebApp webApp = new WebApp(realSession);
			webApp.login("user123", "password123");
		    webApp.sendData("Hello world!");
		    webApp.logout();
		    
		    //With proxy session
		    WebApp webAppWithProxy = new WebApp(proxySession);
			webAppWithProxy.login("user456", "password456");
		    webAppWithProxy.sendData("Hello proxy!");
		    webAppWithProxy.logout();
	    }catch (Exception ex) {
			System.out.println("Login failed "+ ex);
		}

	}

}
