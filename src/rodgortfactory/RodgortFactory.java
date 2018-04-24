/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import guildmembers.GuildMaster;

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
        gm.fillInventory();
        
        //Random rand = new Random();
        //gm.getListOfBaseMaterials().forEach((material) -> {
        //    int inc = rand.nextInt(20) + 1;
        //    gm.addToInventory(material, inc);
        //});
        
        //GuildMember robot = new GuildMember("Chera Fox", gm.bank);
        //System.out.println(robot.toString());
        
        //GuildMember member = new GuildMember("WoafTheWolf", bank);
        //member.run();
        
        
        
        
    }   
}
