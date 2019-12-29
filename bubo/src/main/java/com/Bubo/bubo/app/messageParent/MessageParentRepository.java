package com.Bubo.bubo.app.messageParent;

import com.Bubo.bubo.app.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MessageParentRepository extends JpaRepository<MessageParent,Long> {
    /**
     * finding messageParent by its uuid
     * @param uuid
     * @return messageParent information
     */
    @Query("select m from MessageParent m where m.uuid=?1")
    Optional<MessageParent> findByUUID(UUID uuid);
}
