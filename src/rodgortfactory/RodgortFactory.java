/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.GuildMember;
import craftcomponents.GiftOfWood;
import guildmembers.Aslaeya;
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
        
        System.out.println(bank.getSize());
        System.out.println(bank.toString());
        
        
        gm.getListOfMaterials().forEach((material) -> {
            Random rand = new Random();
            int inc = rand.nextInt(20) + 1;
            gm.addToInventory(bank, material, inc);
        });
        
        System.out.println(bank.toString());
        
        GuildMember member = new Aslaeya();
        member.grabMaterials(bank, new GiftOfWood(), 4);
        
    }   
}
