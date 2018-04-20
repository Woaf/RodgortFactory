/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.CraftComponent;
import abstract_definitions.CraftingItem;
import craftcomponents.MysticClover;
import craftcomponents.Rodgort;

/**
 *
 * @author Bálint
 */
public class RodgortFactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CraftComponent rodgort = new Rodgort();
        System.out.println(rodgort.toString());   
    }   
}
