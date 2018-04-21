/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import java.util.Random;

/**
 *
 * @author Bálint
 */
public abstract class GuildMember {

    protected final String name;
    protected final String playerId;

    public GuildMember(String name) {
        this.name = name;
        this.playerId = name.toLowerCase() + ".#" + generateRandomSequence();
    }

    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }

    @Override
    public String toString() {
        return "Player name: " + name + "\t\t User ID: " + playerId;
    }
    
}
