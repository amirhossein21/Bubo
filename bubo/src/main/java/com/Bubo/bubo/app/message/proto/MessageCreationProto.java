package com.Bubo.bubo.app.message.proto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * This is the messageCreationProto class in which
 * these attributes are defined
 * ID , params , customer ID ,user ID  and message parent ID
 *
 * @author Farzan & amirhosein
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreationProto {

    private UUID id;
    private UUID messageParentID;
    private UUID customerID;
    private UUID userID;
    private List<String> params;



}

