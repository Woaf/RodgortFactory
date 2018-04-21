/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guildmembers;

import abstract_definitions.BaseMaterial;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rodgortfactory.GuildBank;

/**
 *
 * @author Bálint
 */
public class GuildMaster {
    
    private final String name;
    private final String playerId;
    private final List<BaseMaterial> listOfMaterials;

    public GuildMaster(String name) {
        this.name = name;
        this.playerId = name.replaceAll("\\s", "").toLowerCase() + ".#" + generateRandomSequence();
        this.listOfMaterials = new ArrayList<>();
    }
    
    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }

    
    protected void fillInventory(GuildBank sharedBank)
    {
        
    }
    
}
