/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.CraftingItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bálint
 */
public class GuildBank {
    
    private List<CraftingItem> bank;

    public GuildBank() {
        this.bank = new ArrayList<>();
    }

    public void addToBank(CraftingItem item)
    {
        bank.add(item);
        System.out.println("Item added: " + item.toString());
    }
    
}
