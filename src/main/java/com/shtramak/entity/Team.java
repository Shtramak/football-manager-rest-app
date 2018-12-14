package com.shtramak.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Player> players = new ArrayList<>();

    @Column(name = "captain_id")
    @Setter(AccessLevel.NONE)
    private Long captainId;

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

    public void setCaptainId(Long captainId) {
        boolean isValidId = players.stream()
                .map(Player::getId)
                .anyMatch(i -> i.equals(captainId) || captainId == null);
        if (!isValidId) {
            throw new IllegalArgumentException("Captain must be one of the team player!");
        }
        this.captainId = captainId;
    }
}
