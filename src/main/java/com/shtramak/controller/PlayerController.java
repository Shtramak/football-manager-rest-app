package com.shtramak.controller;

import java.util.List;

import com.shtramak.entity.Player;
import com.shtramak.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id){
        return service.getById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Player player) {
        service.save(player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @PostMapping("/{id}/team/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPlayerToTeam(@PathVariable("id") Long playerId, @PathVariable("teamId") Long teamId) {
        service.addPlayerToTeam(playerId, teamId);
    }

}

//todo is there a way to use redirects or smth to not duplicate operations (Add player to team or set a team to Player)