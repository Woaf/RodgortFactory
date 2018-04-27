/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.GuildMember;
import guildmembers.GuildMaster;
import java.util.Random;

/**
 *
 * @author Bálint
 */
public class RodgortFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Initialize guildmaster
        GuildMaster gm = GuildMaster.getInstance();
        gm.fillBank();
            // Initilize guild bank
        Random rand = new Random();
        gm.getListOfBaseMaterials().forEach((material) -> {
            int inc = rand.nextInt(20) + 1;
            gm.addToBank(material, inc);
        });
        // Run guildmaster
        Thread gmRunner = new Thread(gm);
        gmRunner.start();
        
        //Initilize guild members
        GuildMember cheraFox = new GuildMember("Chera Fox", gm.bank);
        GuildMember feron = new GuildMember("Feron Ragemendler", gm.bank);
        
        Thread t_cherafox = new Thread(cheraFox);
        Thread t_feron = new Thread(feron);
        
        t_cherafox.start();
        t_feron.start();
        
        
    }   
}
