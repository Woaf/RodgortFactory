/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.GuildMember;
import guildmembers.Aslaeya;
import guildmembers.CheraFox;
import guildmembers.EvomDeathspur;
import guildmembers.FeronDeathspur;

/**
 *
 * @author Bálint
 */
public class RodgortFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GuildMember gwenn = new CheraFox();
        GuildMember woaf  = new Aslaeya();
        GuildMember woaf2 = new EvomDeathspur();
        GuildMember woaf3 = new FeronDeathspur();
        System.out.println(gwenn.toString());
        System.out.println(woaf.toString());
        System.out.println(woaf2.toString());
        System.out.println(woaf3.toString());
    }   
}
