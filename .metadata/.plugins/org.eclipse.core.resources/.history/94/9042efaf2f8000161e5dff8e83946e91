package com.ecomindo.common.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

import com.ecomindo.common.Settings;
import com.ecomindo.common.exception.DAOException;

public class ActiveDirectoryUtil {

	private static Logger logger = Logger.getLogger(ActiveDirectoryUtil.class);

	public static void ldapHandler(String login, String password) {
		// Boolean result = false;
		try {

			// hold user name for ldap authenticating
			String userName = "";

			userName = login + Settings.getPropertiesByName("ldapDomain");

			Properties environment = new Properties();

			environment.put(LdapContext.CONTROL_FACTORIES, "com.sun.jndi.ldap.ControlFactory");
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, Settings.getPropertiesByName("ldapUrl"));
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			environment.put(Context.SECURITY_PRINCIPAL, userName);
			environment.put(Context.SECURITY_CREDENTIALS, password);
			environment.put(Context.STATE_FACTORIES, "PersonStateFactory");
			environment.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");

			// connect to LDAP
			DirContext ctx = new InitialDirContext(environment);

			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctx.search("", "(objectclass=person)", controls);

		} catch (Exception e) {
			throw new DAOException(logger, "LDAP tidak ditemukan", e);

		}
	}

	public static String findLdapUser(String login) {
		String result = "";
		try {
			Properties environment = new Properties();

			environment.put(LdapContext.CONTROL_FACTORIES, "com.sun.jndi.ldap.ControlFactory");
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, Settings.getPropertiesByName("ldapUrl"));
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			environment.put(Context.SECURITY_PRINCIPAL, Settings.getPropertiesByName("ldapUserId"));
			environment.put(Context.SECURITY_CREDENTIALS, Settings.getPropertiesByName("ldapPassword"));

			// connect to LDAP
			DirContext ctx = new InitialDirContext(environment);

			// Specify the search filter
			String FILTER = "(&(objectClass=User) ((sAMAccountName=" + login + ")))";

			// limit returned attributes to those we care about
			String[] attrIDs = { "CN" };

			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			ctls.setReturningAttributes(attrIDs);

			// Search for objects using filter and controls
			NamingEnumeration<SearchResult> answer = ctx.search("", FILTER, ctls);

			SearchResult sr = answer.next();
			Attributes attrs = sr.getAttributes();
			result = attrs.get("CN").toString();

			result = result.substring(4);
		} catch (Exception e) {
			throw new DAOException(logger, "LDAP tidak ditemukan", e);
		}
		return result;
	}
}
