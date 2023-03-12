## Introduction

This project created by Geza Czimeth to demonstrate Spring Security usage.

#### Available spring profiles

- dev-simple-security
  - security credentials loaded from 'simple-security.properties' file
  - username/password: user/user
- dev 
  - uses [InMemoryUserAuthenticationConfig](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/inmemory/InMemoryUserAuthenticationConfig.java)
  - username/password: admin/admin
- test
  - for testing purposes 
  - uses [NoOpPasswordEncoder](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/jdbc/JdbcBasedUserDetailsManager.java) so testing is easier
  - stores authentication data in 'user' table
  - username/password: userdb/userdb 
- prod-custom-userdetailsservice
  - customized [UserDetailsService](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomUserDetailsService.java), user credentials are loaded up from custom table(customer)
  - for production environment
  - username/password: johndoe@example.com/54321(use postman file to submit the request)
  - uses [BCryptPasswordEncoder](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomSecurityConfig.java)
  - uses JDBC to store/load users

you can set the profile with the 'spring.profiles.active' property

### Mysql Db setup
You need to start up MySQL to run the application and run the schema/table creation script. 

<pre>
docker run --name some-mysql5 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d -p 3306:3306 mysql
</pre>

After that login with
<pre>
using username:root
passowrd: my-secret-pw
</pre>
and run the content of the
<pre>
sql/*.script
</pre>
to create the necessary structure.

### What is being demonstrated

- [Non authenticated endpoint security configuration](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomtSecurityConfig.java)
- Authenticated endpoint security configuration:
  - [The endpoint](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/controller/UserController.java#L16)     
  - [SpringConfig](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/ProjectSecurityConfig.java#L22)

- For Development InMemoryUserDetailsManager usage:
  - you have to use 'spring.profiles.active=dev'
  - [InMemoryUserAuthenticationConfig](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/inmemory/InMemoryUserAuthenticationConfig.java)

- User credentials loaded from database:
  - [JdbcUserDetailsManager Bean](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/jdbc/JdbcBasedUserDetailsManager.java#L15)
  - [configuration of database](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/resources/application.properties#L9)
  - [database script](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/assets/sql/secruity_scheme.sql)

- [AuthoritiesLoggingAfterFilter](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/filter/AuthoritiesLoggingAfterFilter.java#L11) plugged in to log the authenticated user
- [Method level security]() check have been plugged in


### TODO

- add sequence diagrams for the message flows

### Testing

Integration tests: Postman file is provided to test the various spring configurations
Unit tests: There are no business logic in the application only spring security configuration, so unit tests are omitted

### Useful links
https://bcrypt-generator.com/
