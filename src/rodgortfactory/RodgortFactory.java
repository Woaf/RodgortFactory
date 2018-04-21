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
        GuildMaster2 a = GuildMaster2.getInstance();
        GuildMaster2 b = GuildMaster2.getInstance();
        
        System.out.println(b.equals(a));
    }   
}
