/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import java.util.ArrayList;
import java.util.List;
import static rodgortfactory.CraftPhase.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import materials.BottleOfElonianWine;
import materials.Moltencore;
import materials.MysticCrystal;
import materials.PileofCrystallineDust;
import rodgortfactory.CraftPhase;
import rodgortfactory.GuildBank;

/**
 *
 * @author Bálint
 */
public class GuildMember implements Runnable {

    private final String name;
    private final String playerId;
    private CraftPhase phase;
    public GuildBank guildBank;
    private List<CraftingItem> ownInventory;

    public GuildMember(String name, GuildBank guildBank) {
        this.name = name;
        this.playerId = name.replaceAll("\\s", "").toLowerCase() + ".#" + randomIntGenerator(8999, 1000);
        this.phase = PHASE1;
        this.ownInventory = new ArrayList<>();
        this.guildBank = guildBank;
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPhase(CraftPhase phase) {
        this.phase = phase;
    }
    
    @Override
    public String toString() {
        return "Player name: " + name + "\t\t User ID: " + playerId;
    }
    
    private void grabMaterials(CraftingItem item, int count){
        guildBank.getBank().forEach((material) -> {
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
    
    private int randomIntGenerator(int maxAmount, int addConst)
    {
        Random rnd = new Random();
        return rnd.nextInt(maxAmount) + addConst;   
    }
    
    public void createDestroyerLodestone(int phase, GuildBank bank)
    {
        grabMaterials(new BottleOfElonianWine(), randomIntGenerator(1, 0));
        grabMaterials(new PileofCrystallineDust(), randomIntGenerator(1, 0));
        grabMaterials(new MysticCrystal(), randomIntGenerator(1, 0));
        grabMaterials(new Moltencore(), randomIntGenerator(2, 0));
        
        
        
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
                System.err.println("Error in the [" + this.getClass().getName() + "] class Run method!");
                Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
