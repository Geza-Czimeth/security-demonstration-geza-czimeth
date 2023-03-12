package com.bigfish.securitydemonstration.config.security.custom;

import com.bigfish.securitydemonstration.model.Customer;
import com.bigfish.securitydemonstration.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Profile("prod-custom-userdetailsservice")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> grantedAuthorities = null;
        List<Customer> customers = customerRepository.findByEmail(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException(String.format("User details not found for username -> %", username));
        }else if(customers.size() > 1){
            throw new IllegalStateException(String.format("Multiple users found with the same username/email -> %s", username));
        }
        else {
            final Customer customer = customers.get(0);
            userName = customer.getEmail();
            password = customer.getPwd();
            grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(customer.getAuthority()));
        }
        return new User(userName, password, grantedAuthorities);
    }
}
