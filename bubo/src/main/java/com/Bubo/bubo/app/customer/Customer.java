package com.Bubo.bubo.app.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * This class is a customer whose properties
 * (name, ID , uuid  and phoneNumber)
 * are defined in this class
 *
 * @author Farzan & amirhosein
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@SQLDelete(sql ="update Customer set deleted = true where id=? ")
@Where(clause = " deleted = false")
public class Customer {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    //for soft delete
    @JsonIgnore
    private Boolean deleted=false;
    private UUID uuid;
    private String name;
    private String phoneNumber;

}
