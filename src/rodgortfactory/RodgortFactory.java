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
        
        GuildMaster gm = GuildMaster.getInstance();
        gm.fillBank();
        
        Random rand = new Random();
        gm.getListOfBaseMaterials().forEach((material) -> {
            int inc = rand.nextInt(20) + 1;
            gm.addToBank(material, inc);
        });
        
        
        Thread gmRunner = new Thread(gm);
        gmRunner.start();
        
        GuildMember robot = new GuildMember("Chera Fox", gm.bank);
        Thread robotRunner = new Thread(robot);
        
        robotRunner.start();
        System.out.println(robot.toString());
        
        //GuildMember member = new GuildMember("WoafTheWolf", bank);
        //member.run();
        
        
        
        
    }   
}
