package com.lateroad.buber.entity;

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
public class Location implements Entity{
    private Long id;
    private String latitude;
    private String longitude;
}