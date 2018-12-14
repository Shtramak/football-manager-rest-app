package com.shtramak.repository;

import java.util.Optional;

import com.shtramak.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select t from Team t left join fetch t.players where t.id=:teamId")
    Optional<Team> getTeamFetchPlayers(@Param("teamId") Long teamId);
}
