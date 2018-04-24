/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rodgortfactory;

import abstract_definitions.CraftingItem;
import guildmembers.GuildMaster;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bálint
 */
public class GuildBank {
    
    private final List<CraftingItem> bank;
    private final String name;

    private GuildBank(String name) {
        this.name = name;
        this.bank = new ArrayList<>();
    }
    
    private static GuildBank instance = null;
    
    public static GuildBank getInstance() {
        if(instance == null)
        {
            instance = new GuildBank("Guild master");
        }
        return instance;
    }

    public void addToBank(CraftingItem item)
    {
        bank.add(item);
        System.out.println("Item added: " + item.toString());
    }
    
    public int getSize()
    {
        return bank.size();
    }

    public List<CraftingItem> getBank() {
        return bank;
    }

    @Override
    public String toString() {
        StringBuilder bank_status = new StringBuilder("Bank contents:\n");
        bank.forEach((material) -> {
            bank_status.append(material.getName()).append(": ").append(material.getStack_count()).append("\n");
        });
        return bank_status.toString();
    }
    
}
