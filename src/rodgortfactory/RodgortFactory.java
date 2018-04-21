/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import guildmembers.GuildMaster2;

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
        GuildMaster2 gm = GuildMaster2.getInstance();
        
        gm.fillInventory(bank);
        
    }   
}
