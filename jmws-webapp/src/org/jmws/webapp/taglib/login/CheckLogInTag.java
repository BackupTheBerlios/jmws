package org.jmws.webapp.taglib.login;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.jmws.webapp.Constants;

/**
 * CheckLogInTag
 * 
 * Checks that the current user is correctly logged in.
 * You can specify a Privilege to check too, and optionally
 * a page where to forward to in case User is not logged in.
 * 
 * @author Mikael Barbeaux
 */
public class CheckLogInTag extends TagSupport implements Tag {


	// Page to forward to if not logged in.
	private String page = "/index.jsp";
	
	// User's Privilege to check
	private String privilege = null;
	
	// Logged in ?
	private boolean logged;

	// The page context
	private PageContext pageContext;
	
	
	/**
	 * Default constructor
	 *
	 */
	public CheckLogInTag() {
		super();
	}
	
	
	/**
	 * Returns the page where to forward to in case
	 * User isn't logged in.
	 * 
	 * @return
	 */
	public String getPage() {
		return this.page;
	}
	
	/**
	 * Set the page where to forward to in case
	 * User isn't logged in.
	 * 
	 * @param page
	 */
	public void setPage(String page) {
		this.page = page;
	}
	
	
	/**
	 * Returns the User's privilege to check if any.
	 * 
	 * @return
	 */
	public String getPrivilege() {
		return this.privilege;
	}
	
	/**
	 * Set the User's privilege to check.
	 * 
	 * @param privilege
	 */
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	
	/**
	 * Code to process when the CheckLogIn start tag 
	 * is reached.
	 * 
	 * @return
	 * @throws JspTagException
	 */
	public int doStartTag() throws JspTagException {
		logged = false;
		
		// Check if User is logged in
		HttpSession session = pageContext.getSession();
		if((session != null) && 
				(session.getAttribute(Constants.USER_KEY) != null))
			logged = true;
			
		/* If connected and no privilege to check, 
		 * then all is ok, skiping tag's body
		 */ 
		if((logged) && (this.privilege == null))
			return SKIP_BODY;
			
		
		/* If there is a privilege to check...
		 */	
		else if(logged) {
			// Privilege list
			List priv = Arrays.asList(Constants.PRIVILEGE_LIST);
			
			// If privilege is correctly defined...			
			if(priv.contains(this.privilege)) {
				// Checks that User has this privilege
				String privi = (String) 	
					session.getAttribute(Constants.USERPRIVILEGE_KEY);
				
				// Privilege is ok ? skipping body...
				if((privi != null) && (privi.equals(this.privilege)))
					return SKIP_BODY;
				// Else eval tag's body
				else {
					logged = false;
					return EVAL_BODY_INCLUDE;
				} 
			}
			else return SKIP_BODY;
		}
		
		
		/* Use isn't logged in, then
		 * forward to the given page.
		 */		
		try {
			// Get configuration for context path
			ModuleConfig config = (ModuleConfig)
				pageContext.getServletContext().getAttribute(
					Globals.MODULE_KEY);
			
			// forward using context path
			pageContext.forward(config.getPrefix() + page);
			
			// Skip current page
			return SKIP_PAGE;
		}
		catch(IOException ioe) {
			throw new JspTagException("IOException: " + 
				ioe.getMessage());
		}
		catch(ServletException se) {
			throw new JspTagException("ServletException: " +
				se.getMessage());				
		}
	}
	
	
	/**
	 * Code to process when the CheckLogIn end tag 
	 * is reached.
	 * 
	 * @return
	 * @throws JspTagException
	 */
	public int doEndTag() throws JspTagException {
		// If logged, goes on loading page
		if(logged)
			return EVAL_PAGE;
		// Else, stop loading
		else return SKIP_PAGE;
	}
	
	
	/**
	 * Release tag
	 */
	public void release() {
	}
	
	/**
	 * Set page context.
	 * 
	 * @param pageContext
	 */
	public void setPageContext(final PageContext pageContext) {
		this.pageContext = pageContext;	
	}
	

}
