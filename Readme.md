## Introduction

This project created by Geza Czimeth to demonstrate Spring Security usage.

#### Available spring profiles

- dev-simple-security
  - security credentials loaded from 'simple-security.properties' file
  - username/password: user/user
- dev 
  - uses [InMemoryUserAuthenticationConfig]()
  - username/password: admin/admin
- test
  - for testing purposes 
  - uses [NoOpPasswordEncoder]() so testing is easier
  - stores authentication data in 'user' table
  - username/password: userdb/userdb 
- prod-custom-userdetailsservice
  - for productions environment
  - customized user loading from 'customer' table
  - username/password: johndoe@example.com/secret....johndoe@example.com/54321
  - uses [BCryptPasswordEncoder]()
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

- [Non authenticated endpoint security configuration](linkurl)
- Authenticated endpoint security configuration:
  - [The endpoint](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/b010887bc9b19214c3adb48405b220b216f8b3ca/src/main/java/com/bigfish/securitydemonstration/controller/OrderController.java#L13)     
  - [SpringConfig](https://github.com/Geza-Czimeth/security-demonstration-geza-czimeth/blob/b010887bc9b19214c3adb48405b220b216f8b3ca/src/main/java/com/bigfish/securitydemonstration/config/ProjectSecurityConfig.java#L14)

- For Development InMemoryUserDetailsManager usage:
  - you have to use 'spring.profiles.active=dev'
  - [InMemoryUserAuthenticationConfig]()
  - [configuration]()


- Passwords are loaded up from JDBC:
  - [JdbcUserDetailsManager Bean]()
  - [configuration of database]()
  - [database script]()

- [AuthoritiesLoggingAfterFilter]() plugged in to log the authenticated user
- [Method level security]() check have been plugged in


### TODO

- add sequence diagrams for the message flows

### Testing

Integration tests: Postman file is provided to test the various spring configurations
Unit tests: There are no business logic in the application only spring security configuration, so unit tests are omitted

### Useful links
https://bcrypt-generator.com/
