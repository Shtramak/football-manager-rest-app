package com.shtramak.entity;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Player {
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    private LocalDate birthday;
    private Team team;
}
