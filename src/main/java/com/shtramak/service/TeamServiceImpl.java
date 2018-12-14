package com.shtramak.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import com.shtramak.entity.Player;
import com.shtramak.entity.Team;
import com.shtramak.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;

    @Autowired
    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> getPlayers(Long teamId) {
        Team team = getTeamWithPlayersOrThrowNoSuchElementException(teamId);
        return team.getPlayers();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Player> getCaptain(Long teamId) {
        Team team = getTeamWithPlayersOrThrowNoSuchElementException(teamId);
        List<Player> players = team.getPlayers();
        Long captainId = team.getCaptainId();
        return players.stream()
                .filter(player -> Objects.equals(captainId,player.getId()))
                .findAny();
    }

    @Override
    public void addPlayer(Long teamId, Player player) {
        Team team = getTeamWithPlayersOrThrowNoSuchElementException(teamId);
        Team playerTeam = player.getTeam();
        if (playerTeam != null) {
            throw new IllegalArgumentException("Player already plays for a team with id=" + playerTeam.getId());
        }
        team.addPlayer(player);
    }

    @Override
    public void setCaptain(Long teamId, Long captainId) {
        Team team = getTeamWithPlayersOrThrowNoSuchElementException(teamId);
        team.setCaptainId(captainId);
    }

    @Override
    public void removePlayer(Long teamId, Long playerId) {
        Team team = getTeamWithPlayersOrThrowNoSuchElementException(teamId);
        List<Player> players = team.getPlayers();
        Optional<Player> player = players.stream()
                .filter(p -> p.getId().equals(playerId))
                .findFirst();
        team.removePlayer(player.orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Team> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Team team) {
        repository.save(team);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    private Team getTeamWithPlayersOrThrowNoSuchElementException(Long teamId) {
        Optional<Team> teamFetchPlayers = repository.getTeamFetchPlayers(teamId);
        return teamFetchPlayers.
                orElseThrow(() -> new NoSuchElementException("There's no Team with id=" + teamId));
    }
}

// todo How to throw Exception, Where to handle? All in Global ExceptionHandler?

// todo can't use captainId in Json to update Team... Is it because of the setter?