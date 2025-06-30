package com.bling.common.service;

import com.bling.common.model.Game;

public interface GameService {
    Game generateGame(String version,String name);
    void start();
}
