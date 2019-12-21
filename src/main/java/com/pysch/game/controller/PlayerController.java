package com.pysch.game.controller;

import com.pysch.game.model.Players;
import com.pysch.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players")
    public List<Players> getAllPlayers(){
        return playerRepository.findAll();
    }

    @PostMapping("/players")
    public Players createPlayer(@Valid @RequestBody Players player){
        return playerRepository.save(player);
    }

    @GetMapping("/players/{id}")
    public Players getPlayerById(@PathVariable(value = "id") Long id) throws Exception{
        return playerRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/players/{id}")
    public Players updatePlayerById(@PathVariable(value = "id") Long id, @Valid @RequestBody Players player) throws Exception{
        Players p = playerRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        p.setName(player.getName());
        return playerRepository.save(p);
    }

    @DeleteMapping("/players/{id}")
    public Players deletePlayer(@PathVariable(value = "id") Long id) throws Exception{
        Players p = playerRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        playerRepository.delete(p);
        return p;
    }
}
