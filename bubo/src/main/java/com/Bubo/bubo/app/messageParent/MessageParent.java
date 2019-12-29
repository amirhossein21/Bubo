package com.Bubo.bubo.app.messageParent;

import com.Bubo.bubo.app.message.Message;
import com.Bubo.bubo.app.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is the messageParent class in which
 * these attributes are defined
 * text, ID , uuid , user  and messages
 *
 * @author Farzan & amirhossein
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql ="update MessageParent set deleted = true where id=? ")
@Where(clause = " deleted = false")
public class MessageParent {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String text;

    @JsonIgnore
    private Boolean deleted=false;  //for soft delete
    private UUID uuid;

    //with User
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userFK")
    private User user;

    //with Message
    @JsonIgnore
    @OneToMany(mappedBy = "messageParent",cascade = CascadeType.ALL)
    private Set<Message> messages=new HashSet<>();


}
