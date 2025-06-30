package com.bling.consumer.controller;

import com.bling.common.model.Game;
import com.bling.common.service.GameService;
import com.bling.rpc.springboot.starter.annotation.RpcReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GameController {
    @RpcReference(address = "localhost:9009",interfaceClass = GameService.class)
    private GameService gameService;

    @GetMapping("/get/{n}/{v}")
    public Game get(@PathVariable("n") String n,@PathVariable("v") String v){
        return gameService.generateGame(v,n);
    }
}
