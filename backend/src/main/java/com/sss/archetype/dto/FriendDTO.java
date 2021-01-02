package com.sss.archetype.dto;

import lombok.*;

@Data
public class FriendDTO extends BaseDTO<java.lang.String> {
    /**
     * 用户id
     */
    private String myUserId;

    /**
     * 用户的好友id
     */
    private String myFriendUserId;

}