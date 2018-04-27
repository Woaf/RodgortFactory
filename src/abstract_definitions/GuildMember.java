/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import craftcomponents.DestroyerLodestone;
import craftcomponents.ElderWoodPlank;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static rodgortfactory.CraftPhase.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import materials.BottleOfElonianWine;
import materials.ElderWoodLog;
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

    private int randomIntGenerator(int maxAmount, int addConst) {
        Random rnd = new Random();
        return rnd.nextInt(maxAmount) + addConst;
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
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        //System.out.println(ownInventory.toString());
    }

    private boolean checkMaterialStack(int needs, int contains) {
        //System.out.println("Enough materials in bank: " + (needs <= contains));
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

        System.out.printf("%s created something!!!\n", name);
        System.out.println("[" + item.getName() + "] added to the bank.");
        bank.getBank().forEach((material) -> {
            if (material.getClass().getSimpleName().equals(item.getClass().getSimpleName())) {
                material.increment();
            }
        });
    }

    private void createDestroyerLodestone(GuildBank bank) {

        if (hasAdequateNumberOfMaterials(new BottleOfElonianWine(), 1)
                && hasAdequateNumberOfMaterials(new PileOfCrystallineDust(), 1)
                && hasAdequateNumberOfMaterials(new MysticCrystal(), 1)
                && hasAdequateNumberOfMaterials(new Moltencore(), 2)) {
            removeItem(new BottleOfElonianWine());
            removeItem(new PileOfCrystallineDust());
            removeItem(new MysticCrystal());
            removeItem(new Moltencore());
            removeItem(new Moltencore());
            addItemToBank(bank, new DestroyerLodestone());
        } else {
            grabMaterials(new BottleOfElonianWine(), randomIntGenerator(1, 1));
            grabMaterials(new PileOfCrystallineDust(), randomIntGenerator(1, 1));
            grabMaterials(new MysticCrystal(), randomIntGenerator(1, 1));
            grabMaterials(new Moltencore(), randomIntGenerator(2, 1));
        }
    }
    
    private void createElderWoodPlank(GuildBank bank) {

        if (hasAdequateNumberOfMaterials(new ElderWoodLog(), 3)) {
            removeItem(new ElderWoodLog());
            removeItem(new ElderWoodLog());
            removeItem(new ElderWoodLog());
            addItemToBank(bank, new ElderWoodPlank());
        } else {
            grabMaterials(new ElderWoodLog(), randomIntGenerator(3, 1));
        }
    }

    private boolean hasAdequateNumberOfMaterials(CraftingItem item, int amount) {
        int counter = 0;
        counter = ownInventory.stream().filter((material)
                -> (material.getClass().getSimpleName().equals(item.getClass().getSimpleName())))
                .map((_item) -> 1)
                .reduce(counter, Integer::sum);

        return counter >= amount;
    }

    private void removeItem(CraftingItem item) {
        Iterator<CraftingItem> inventoryIterator = ownInventory.iterator();
        while (inventoryIterator.hasNext()) {
            CraftingItem o = inventoryIterator.next();
            if (o.getName().equals(item.getName())) {
                System.out.println("Item removed: " + item.getName());
                ownInventory.remove(o);
                break;
            }
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(getPlayerId());
            while (true) {
                System.out.printf("[%s] is working on %s\n", name, phase.getPhaseName());
                try {
                    switch(phase.getPhaseNumber())
                    {
                        case 1: createDestroyerLodestone(guildBank); 
                                createElderWoodPlank(guildBank);
                        break;
                        case 2: break;
                        case 3: break;
                        case 4: break;
                        default: break;
                    }
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.err.println("Error in the [" + this.getClass().getName() + "] class Run method!");
                    Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
