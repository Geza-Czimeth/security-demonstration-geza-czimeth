## Introduction

This project created by Geza Czimeth to demonstrate Spring Security usage.


#### Available spring profiles

- dev-simple-security --> simpliest spring security, username/password loaded from 'simple-security.properties' file
- dev --> InMemoryUserAuthenticationConfig used for web security
- prod-default-userdetailsservice --> johndoe@example.com/54321
- prod-custom-userdetailsservice

you can set the profile with the 'spring.profiles.active' property

### Useful links
https://bcrypt-generator.com/

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



### Mysql Db setup

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

### TODO

- add sequence diagrams for the message flows
