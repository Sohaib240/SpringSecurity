package com.security.service;

import com.security.Models.UserAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by CAPTAN GHOURI on 01/04/2020.
 */
@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    @Autowired
    private PasswordEncoder passwordEncryptor;

    @Override
    public Optional<UserAccounts> findUserByUserName(String userName) {
        return getUserAccounts().stream().filter(userAccounts ->
                userName.equals(userAccounts.getUsername())).
                findFirst();
    }

    private List<UserAccounts> getUserAccounts() {
        List<UserAccounts> user = new ArrayList<>();
        user.add(new UserAccounts(Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "sohaib", passwordEncryptor.encode("password"), true, true, true, true));
        user.add(new UserAccounts(Arrays.asList(new SimpleGrantedAuthority("STUDENT")), "ali", passwordEncryptor.encode("password"), true, true, true, true));
        user.add(new UserAccounts(Arrays.asList(new SimpleGrantedAuthority("UNKNOWN")), "unknown", passwordEncryptor.encode("password"), true, true, true, true));

        return user;

    }
}
