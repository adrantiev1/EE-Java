package security.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

//@BasicAuthenticationMechanismDefinition(
//		realmName="jaspitest"
//)

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/customLogin.xhtml", 
		errorPage="/security/customLogin.xhtml?error")
)

@LdapIdentityStoreDefinition(
		url = "ldap://metro-ds1.nait.ca:389/",
		callerSearchBase = "dc=nait,dc=ca",
		callerNameAttribute = "SamAccountName",	
		groupSearchBase = "dc=nait,dc=ca",
		bindDn = "CN=DMIT Student1,ou=DMITStudentRestricted,ou=Student,ou=DMIT,ou=SICET,dc=nait,dc=ca",
		bindDnPassword = "Password2015"
)

//@DatabaseIdentityStoreDefinition(
//	dataSourceLookup="java:jboss/datasources/MysqlNorthwindDS",
//	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
//	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
//	hashAlgorithm = Pbkdf2PasswordHash.class,
//	hashAlgorithmParameters = { 
//		"Pbkdf2PasswordHash.Iterations=3072", 
//		"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", 
//		"Pbkdf2PasswordHash.SaltSizeBytes=64" },
//	priority = 10
//)

@ApplicationScoped
public class ApplicationConfig {

}
