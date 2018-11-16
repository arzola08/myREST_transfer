package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(exported = false)

public interface AccountRepository extends JpaRepository<Account, String> {

}