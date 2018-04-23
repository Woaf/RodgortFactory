/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import craftcomponents.DestroyerLodestone;
import java.util.ArrayList;
import java.util.List;
import static rodgortfactory.CraftPhase.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rodgortfactory.CraftPhase;
import rodgortfactory.GuildBank;

/**
 *
 * @author Bálint
 */
public class GuildMember implements Runnable {

   
    protected final String name;
    protected final String playerId;
    protected CraftPhase phase;
    protected List<CraftingItem> ownInventory;

    public GuildMember(String name) {
        this.name = name;
        this.playerId = name.replaceAll("\\s", "").toLowerCase() + ".#" + generateRandomSequence();
        this.phase = PHASE1;
        this.ownInventory = new ArrayList<>();
    }

    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }

    @Override
    public String toString() {
        return "Player name: " + name + "\t\t User ID: " + playerId;
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }
    
    public void grabMaterials(GuildBank bank, CraftingItem item, int count){
        bank.getBank().forEach((material) -> {
            if(material.getClass().getSimpleName().equals(item.getClass().getSimpleName()))
            {
                if(checkMaterialStack(count, material.stack_count))
                {
                    System.out.println("Removing [" + count + "] pieces of: " + material.getName());
                    for(int i = 0; i < count; i++)
                    {
                        ownInventory.add(material);
                        material.decrement();
                    }
                }
            }
        });
    }
    
    private boolean checkMaterialStack(int needs, int contains)
    {
        System.out.println("Enough materials in bank: " + (needs <= contains));
        return needs <= contains;   
    }
    
    public void addItemToBank(GuildBank bank, CraftingItem item)
    {
        System.out.println("[" + item.getName() + "] added to the bank.");
        bank.getBank().forEach((material) -> {
            if(material.getClass().getSimpleName().equals(item.getClass().getSimpleName()))
            {
                material.increment();
            }
        });
    }
    
    public void createDestroyerLodestone(GuildBank bank)
    {
        DestroyerLodestone stone = new DestroyerLodestone();
        
    }

    @Override
    public void run() {
        System.out.println(getPlayerId());
        int temp = 0;
        while(true)
        {
            System.out.println(temp);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
