package com.shtramak.repository;

import com.shtramak.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Modifying
    @Query("UPDATE Player p SET p.team.id=:teamId where p.id=:playerId")
    void addPlayerToTeam(@Param("playerId") Long playerId, @Param("teamId") Long teamId);
}

//todo method doesn't work without @Modifying annotation... Why?