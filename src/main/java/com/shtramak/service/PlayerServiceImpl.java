package com.shtramak.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.shtramak.entity.Player;
import com.shtramak.entity.Team;
import com.shtramak.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository repository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Player> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Player> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Player player) {
        repository.save(player);
    }

    @Override
    public void removeById(Long id) {
        Optional<Player> optPlayer = getById(id);
        Player player = optPlayer.orElseThrow();
        Team team = player.getTeam();
        if (team != null) {
            if (Objects.equals(player.getId(), team.getCaptainId())) {
                team.setCaptainId(null);
            }
            team.removePlayer(player);
        } else {
            repository.deleteById(id);
        }
    }

    @Override
    public void addPlayerToTeam(Long playerId, Long teamId) {
        Optional<Player> optPlayer = getById(playerId);
        Player player = optPlayer.orElseThrow();
        Team playerTeam = player.getTeam();
        if (playerTeam != null) {
            throw new IllegalArgumentException("Player already plays for a team with id=" + playerTeam.getId());
        }

        repository.addPlayerToTeam(playerId, teamId);
    }
}

//todo what about removeById?
