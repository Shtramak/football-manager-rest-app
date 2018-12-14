package com.shtramak.service;

import com.shtramak.entity.Player;

public interface PlayerService extends CrudService<Player> {
    public void addPlayerToTeam(Long playerId, Long teamId);
}
