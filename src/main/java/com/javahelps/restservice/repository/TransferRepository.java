package com.javahelps.restservice.repository;

import com.javahelps.restservice.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="/transfers",exported = false)

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}