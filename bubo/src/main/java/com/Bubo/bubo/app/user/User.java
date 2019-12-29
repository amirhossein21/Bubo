package com.Bubo.bubo.app.user;

import com.Bubo.bubo.app.message.Message;
import com.Bubo.bubo.app.messageParent.MessageParent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is the user class in which
 * these attributes are defined
 * ID , uuid , name , phoneNumber and messageParents
 *
 * @author Farzan & amirhossein
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql ="update User set deleted = true where id=? ")
@Where(clause = " deleted = false")
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private UUID uuid;
    private String name;
    private String phoneNumber;

    //constructor
    public User(long id,String name){
        this.id=id;
        this.name=name;
    }

    //constructor
    public User(String uuid,String name,String phoneNumber){
        this.uuid=UUID.fromString(uuid);
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    @JsonIgnore
    private Boolean deleted=false;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MessageParent> messageparents=new HashSet<>();

    //test
    public User(String uuid){
        this.uuid=UUID.fromString(uuid);
    }
}
