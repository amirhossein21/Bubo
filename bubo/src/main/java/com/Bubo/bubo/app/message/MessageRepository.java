package com.Bubo.bubo.app.message;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message,Long> {

    /**
     * finding message by its uuid
     * @param uuid
     * @return message information
     */
    @Query("select m from Message m where m.uuid=?1")
    Optional<Message> findByUUID(UUID uuid);

    /**
     * findind messages with unsend status
     * @return list of unsend messages
     */
    @Query("select m from Message m where m.sent=false")
    Optional <List<Message>> findUnSendMessages();

    /**
     * updating sent status of message by its id
     * @param id
     */
    @Transactional
    @Modifying
    @Query("update Message m set m.sent =true where m.id=?1")
    void updateSentStatus(Long id);


}
