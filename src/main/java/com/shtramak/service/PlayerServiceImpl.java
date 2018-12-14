package com.shtramak.service;

import java.util.List;
import java.util.Optional;

import com.shtramak.entity.Player;
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
        repository.deleteById(id);
    }
}
