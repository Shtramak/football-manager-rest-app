package com.shtramak.service;

import java.util.List;
import java.util.Optional;

import com.shtramak.entity.Player;
import com.shtramak.entity.Team;

public interface TeamService extends CrudService<Team> {
    List<Player> getPlayers(Long teamId);

    Optional<Player> getCaptain(Long teamId);

    void addPlayer(Long teamId, Player player);
}
