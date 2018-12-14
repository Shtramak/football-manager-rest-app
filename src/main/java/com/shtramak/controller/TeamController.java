package com.shtramak.controller;

import java.util.List;
import java.util.Objects;

import com.shtramak.entity.Player;
import com.shtramak.entity.Team;
import com.shtramak.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping
    public List<Team> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable Long id){
        return service.getById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Team team) {
        service.save(team);
    }

    @GetMapping("/{id}/player")
    public List<Player> getPlayers(@PathVariable Long id) {
        return service.getPlayers(id);
    }

    @GetMapping("/{id}/captain")
    public Player getCaptain(@PathVariable Long id) {
        return service.getCaptain(id)
                .orElseThrow();
    }

    @PostMapping("/{id}/player")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayer(@PathVariable("id") Long teamId, @RequestBody Player player) {
        service.addPlayer(teamId, player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        service.removeById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable Long id, @RequestBody Team team) {
        if (!Objects.equals(id, team.getId())) {
            throw new IllegalArgumentException();
        }
        service.save(team);
    }

    @PutMapping("/{id}/captain/{captainId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setCaptain(@PathVariable("id") Long teamId, @PathVariable("captainId") Long captainId) {
        service.setCaptain(teamId, captainId);
    }

    @DeleteMapping("/{id}/player/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePlayerFromTeam(@PathVariable("id") Long teamId, @PathVariable("playerId") Long playerId) {
        service.removePlayer(teamId, playerId);
    }

}
