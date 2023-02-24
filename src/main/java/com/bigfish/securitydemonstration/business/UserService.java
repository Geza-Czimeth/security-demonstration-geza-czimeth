package com.bigfish.securitydemonstration.business;

import com.bigfish.securitydemonstration.model.Customer;

public interface UserService {

    boolean registerUser(Customer customer);
}
