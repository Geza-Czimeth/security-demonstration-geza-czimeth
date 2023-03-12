## Introduction

This project created by Geza Czimeth to demonstrate Spring Security usage.
In the application several spring profile is available. Each profile demonstrates a different spring security configuration.

#### Available spring profiles

- dev-simple-security
  - security credentials loaded from 'simple-security.properties' file
  - username/password: user/user
- dev 
  - uses [InMemoryUserAuthenticationConfig](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/inmemory/InMemoryUserAuthenticationConfig.java)
  - username/password: admin/admin
- test
  - for testing purposes 
  - uses [NoOpPasswordEncoder](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/a652b225a0f11805442647180878c517ffc6383c/src/main/java/com/bigfish/securitydemonstration/config/security/jdbc/JdbcBasedUserDetailsManager.java#L25) so testing is easier
  - stores authentication data in 'user' table
  - username/password: userdb/userdb 
- prod
  - customized [UserDetailsService](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomUserDetailsService.java#L22), user credentials are loaded up from custom table(customer)
  - for production environment
  - username/password: johndoe@example.com/54321(use postman file to submit the request)
  - uses [BCryptPasswordEncoder](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomSecurityConfig.java#L16)
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

- [Non authenticated endpoint security configuration](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/custom/CustomtSecurityConfig.java#L22)
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

- [AuthoritiesLoggingAfterFilter](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/java/com/bigfish/securitydemonstration/config/security/ProjectSecurityConfig.java#L27) plugged in to log the authenticated user
- [Method level security](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/29659021a145a18d9a1eedb60c8851c4146cd4be/src/main/java/com/bigfish/securitydemonstration/controller/UserControllerWithAuthorityCheck.java#L19) check have been plugged in


### Postman tests

File available at '/assets/postman/Spring Security Demonstration Geza Czimeth.postman_collection.json' <br/><br/>
![Postman tests](/assets/images/postman-test.png)

Each test directory related to a spring profile, so they only work if you are using that profile.
In detail
<br/>

1.1 Loads the users from in memory after authentication is successful<br/>
    - for authentication the user credentials are loaded up from property file [simple-security.properties](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/4a7a0d6ed8357a0c70244ac941db58678f20b514/src/main/resources/simple-security.properties)
<br/><br/>
2.1 Loads the users from in memory after authentication is successful<br/>
    - for authentication the user credentials are loaded from in memory 'storage'<br/>
<br/>
3.1 Loads the users from in memory after authentication is successful<br/>
    - for authentication the user credentials are loaded from SQL database

3.2. Loads the users but uses a path(url) on which authentication is not necessary<br/>

<br/>
4.1 Creates a user with email and password(stored hashed in sql database)<br/>
4.2. Loads users if the requestor has 'view' authority<br/>

![Sequence diagram for 4,2](/assets/images/sequence-4.2.png)

4.3. Demonstrates that authenticated user can not load the users if has no 'view' authority<br/>

### Testing

Integration tests: Postman file is provided to test the various spring configurations</br>
Unit tests: There are no business logic in the application only spring security configuration, so unit tests are omitted

### Build and run
<pre>
mvn clean install
java -Dspring.profiles.active=__INSERT_PROFILE_HERE__ -jar target/security-demonstration-geza-czimeth-0.0.1-SNAPSHOT.jar
</pre>

### Useful links
https://bcrypt-generator.com/
