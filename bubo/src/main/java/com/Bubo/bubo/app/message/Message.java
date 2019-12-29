package com.Bubo.bubo.app.message;

import com.Bubo.bubo.app.user.User;
import com.Bubo.bubo.app.messageParent.MessageParent;
import com.Bubo.bubo.app.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * This is the message class in which
 * these attributes are defined
 * text, ID , uuid , date , sent , customer ID ,
 * user ID  and message parent
 *
 * @author Farzan & amirhossein
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql ="update Message set deleted = true where id=? ")
@Where(clause = " deleted = false")
public class Message {

    @Id @GeneratedValue @JsonIgnore
    private Long id;
    private String text;
    private Date date;

    private Boolean sent=false;

    @JsonIgnore
    private Boolean deleted=false;
    private UUID uuid;

    //with customer
    private Long customerID;

    //with user
    private Long userID;



    //with MessageParent
    @ManyToOne
    @JoinColumn(name = "messageParentFk")
    private MessageParent messageParent;

}
