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
        
        GuildBank bank = new GuildBank();
        GuildMaster gm = GuildMaster.getInstance();
        gm.fillInventory(bank);
        
        System.out.printf("\nNumber of types of materialis in the bank: %d\n", bank.getSize());
        System.out.println(bank.toString());
        
        
        gm.getListOfBaseMaterials().forEach((material) -> {
            Random rand = new Random();
            int inc = rand.nextInt(20) + 1;
            gm.addToInventory(bank, material, inc);
        });
        
        System.out.println(bank.toString());
        
        GuildMember member = new GuildMember("WoafTheWolf");
        member.run();
        
        
    }   
}
