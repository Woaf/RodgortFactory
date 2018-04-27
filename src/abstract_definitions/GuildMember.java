/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import craftcomponents.DestroyerLodestone;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static rodgortfactory.CraftPhase.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import materials.BottleOfElonianWine;
import materials.Moltencore;
import materials.MysticCrystal;
import materials.PileOfCrystallineDust;
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

    private void grabMaterials(CraftingItem item, int count) {
        guildBank.getBank().forEach((material) -> {
            if (material.getClass().getSimpleName().equals(item.getClass().getSimpleName())) {
                if (checkMaterialStack(count, material.stack_count)) {
                    try {
                        //System.out.println("Removing [" + count + "] pieces of: " + material.getName());
                        for (int i = 0; i < count; i++) {
                        ownInventory.add(material.getClass().newInstance());
                        material.decrement();
                    }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        //System.out.println(ownInventory.toString());
    }

    private boolean checkMaterialStack(int needs, int contains) {
        System.out.println("Enough materials in bank: " + (needs <= contains));
        return needs <= contains;
    }

    public void addItemToBank(GuildBank bank, CraftingItem item) {
        boolean itemInstanceFound = false;
        for (CraftingItem material : bank.getBank()) {
            if (material.getName().equals(item.getName())) {
                itemInstanceFound = true;
            }
        }

        if (!itemInstanceFound) {
            bank.getBank().add(item);
        }

        System.out.println("[" + item.getName() + "] added to the bank.");
        bank.getBank().forEach((material) -> {
            if (material.getClass().getSimpleName().equals(item.getClass().getSimpleName())) {
                material.increment();
            }
        });
    }

    private int randomIntGenerator(int maxAmount, int addConst) {
        Random rnd = new Random();
        return rnd.nextInt(maxAmount) + addConst;
    }

    public void createDestroyerLodestone(int phase, GuildBank bank) {
        grabMaterials(new BottleOfElonianWine(), randomIntGenerator(1, 1));
        grabMaterials(new PileOfCrystallineDust(), randomIntGenerator(1, 1));
        grabMaterials(new MysticCrystal(), randomIntGenerator(1, 1));
        grabMaterials(new Moltencore(), randomIntGenerator(2, 1));

        if (hasAdequateNumberOfMaterials(new BottleOfElonianWine(), 1)
                && hasAdequateNumberOfMaterials(new PileOfCrystallineDust(), 1)
                && hasAdequateNumberOfMaterials(new MysticCrystal(), 1)
                && hasAdequateNumberOfMaterials(new Moltencore(), 2)) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
            removeItem(new BottleOfElonianWine());
            removeItem(new PileOfCrystallineDust());
            removeItem(new MysticCrystal());
            removeItem(new Moltencore());
            removeItem(new Moltencore());
            addItemToBank(bank, new DestroyerLodestone());
            System.out.println(bank.toString());
        }

    }

    private boolean hasAdequateNumberOfMaterials(CraftingItem item, int amount) {
        int counter = 0;
        counter = ownInventory.stream().filter((material)
                -> (material.getClass().getSimpleName().equals(item.getClass().getSimpleName())))
                .map((_item) -> 1)
                .reduce(counter, Integer::sum);

        return counter == amount;
    }

    private void removeItem(CraftingItem item) {
        
        Iterator<CraftingItem> inventoryIterator = ownInventory.iterator();
        while(inventoryIterator.hasNext())
        {
            CraftingItem o = inventoryIterator.next();
            System.out.println("asdfa");
            if(o.getName().equals(item.getName()))
            {
                System.out.println("Item removed: " + item.getName());
                ownInventory.remove(o);
                break;
            }
        }
        
    }

    @Override
    public void run() {
        System.out.println(getPlayerId());
        int temp = 0;
        while (true) {
            System.out.println(temp);
            try {
                createDestroyerLodestone(1, guildBank);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("Error in the [" + this.getClass().getName() + "] class Run method!");
                Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
