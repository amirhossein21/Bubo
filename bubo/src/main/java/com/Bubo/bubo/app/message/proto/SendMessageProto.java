package com.Bubo.bubo.app.message.proto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * This is the sendMessageProto class in which
 * these attributes are defined
 * text, ID and phoneNumber
 *
 * @author Farzan & amirhosein
 */
@Setter
@Getter
@NoArgsConstructor
public class SendMessageProto implements Serializable {
    UUID id;
    String text;
    String phoneNumber;

}
