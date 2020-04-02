package com.security.service;

import com.security.Models.UserAccounts;

import java.util.Optional;

/**
 * Created by CAPTAN GHOURI on 01/04/2020.
 */
public interface UserAccountDao {

    public Optional<UserAccounts> findUserByUserName(String userName);
}
