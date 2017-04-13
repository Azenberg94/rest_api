package com.example.controller;

import com.example.config.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Az on 01/03/2017.
 */
@RestController
@RequestMapping("/properties")
public class ControllerRest {
    private final ServerProperties serverProperties;

    @Autowired
    public ControllerRest(ServerProperties serverProperties){
        this.serverProperties = serverProperties;
    }

    @GetMapping
    public ServerProperties getProperties(){
        return serverProperties;
    }
}
