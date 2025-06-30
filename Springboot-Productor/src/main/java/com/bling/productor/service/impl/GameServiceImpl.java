package com.bling.productor.service.impl;

import com.bling.common.model.Game;
import com.bling.common.service.GameService;
import com.bling.rpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class GameServiceImpl implements GameService {

    @Override
    public Game generateGame(String version, String name) {
        Game game = new Game();
        game.setName(name);
        game.setVersion(version);
        return game;
    }

    @Override
    public void start() {
        System.out.println("game starting ...");
    }
}
