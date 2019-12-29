package com.Bubo.bubo.app.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    /**
     * finding a customer by its uuid
     * @param uuid
     * @return customer inforrmation
     */
    @Query("select c from Customer c where c.uuid=?1")
    Optional<Customer> findByUUID(UUID uuid);

}
