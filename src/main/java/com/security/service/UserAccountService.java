package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by CAPTAN GHOURI on 01/04/2020.
 */

@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountDao userAccountDao;

    @Autowired
    public UserAccountService(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userAccountDao.findUserByUserName(s).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
