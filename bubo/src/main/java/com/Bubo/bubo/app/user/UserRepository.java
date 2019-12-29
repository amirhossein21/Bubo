package com.Bubo.bubo.app.user;

import com.Bubo.bubo.app.messageParent.MessageParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.uuid=?1")
    Optional<User> findByUUID(UUID uuid);
}
