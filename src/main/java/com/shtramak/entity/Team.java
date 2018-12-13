package com.shtramak.entity;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Team {
    private Long id;
    private String name;
    private List<Player> players;
    private Player captain;
}
