package com.lateroad.buber.entity;

import lombok.*;

/**
 * Created by LateRoad on 13.03.2018.
 */

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Card implements Entity {
    private Long id;
    private String hashNumber;
    private String login;
}