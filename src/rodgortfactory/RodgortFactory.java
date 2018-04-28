/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.GuildMember;
import guildmembers.GuildMaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Bálint
 */
public class RodgortFactory {

    private void readConfig()
    {
        
    }
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
        GuildMember cheraFox = new GuildMember("Chera Fox", gm.bank, CraftPhase.PHASE1);
        GuildMember feron = new GuildMember("Feron Ragemendler", gm.bank, CraftPhase.PHASE2);
        gm.members.add(cheraFox);
        gm.members.add(feron);
        
        List<Thread> threads = new ArrayList<>();
        
        gm.members.forEach((m) -> {
            threads.add(new Thread(m));
        });
        
        threads.forEach((thread) -> {
            thread.start();
        });
        
        
    }   
}
