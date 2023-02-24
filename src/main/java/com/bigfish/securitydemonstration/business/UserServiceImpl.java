package com.bigfish.securitydemonstration.business;

import com.bigfish.securitydemonstration.model.Customer;
import com.bigfish.securitydemonstration.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(Customer customer) {
        boolean rv;
        try {
            String hashedPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPwd);
            Customer savedCustomer = customerRepository.save(customer);
            rv = savedCustomer.getId() > 0;
        } catch (Exception ex) {
            log.error("Exception while saving user!", ex.getStackTrace());
            rv = false;
        }
        return rv;
    }
}
