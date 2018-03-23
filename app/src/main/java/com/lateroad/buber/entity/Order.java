package com.lateroad.buber.entity;

import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by LateRoad on 12.03.2018.
 */

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Order implements Entity {
    private Long id;
    private String clientLogin;
    private String driverLogin;
    private String money;
    private OrderStatus status;
    private Date date;
    private Location origin;
    private Location destination;

    public enum OrderStatus {
        DONE,
        ACCEPTED,
        UNDONE,
        CANCELLED;
    }
}
