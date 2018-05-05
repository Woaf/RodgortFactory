/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import craftcomponents.DestroyerLodestone;
import craftcomponents.ElderWoodPlank;
import craftcomponents.GiftOfFortune;
import craftcomponents.GiftOfMagic;
import craftcomponents.GiftOfMastery;
import craftcomponents.GiftOfMight;
import craftcomponents.GiftOfRodgort;
import craftcomponents.GiftOfWood;
import craftcomponents.HardWoodPlank;
import craftcomponents.MoltenLodestone;
import craftcomponents.MysticClover;
import craftcomponents.Rodgort;
import craftcomponents.SeasonedWoodPlank;
import craftcomponents.VialOfLiquidFlame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
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
    
    private final int sleepTime;
    
    private static int getSleepTime()
    {
        File config = new File("src/resources/config.txt");
        int ret = 0;
        try {
            Scanner sc = new Scanner(config);
            for(int i = 0; i < 3; i++)
            {
                ret = sc.nextInt();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Config file cannot be found!\n[" + ex.getMessage() + ']');
        }

        System.out.format("Guild member sleep time: %d\n", ret);
        return ret;
    }

    public GuildMember(String name, GuildBank guildBank, CraftPhase phase) {
        this.name = name;
        this.playerId = name.replaceAll("\\s", "").toLowerCase() + ".#" + randomIntGenerator(8999, 1000);
        this.phase = phase;
        this.ownInventory = new ArrayList<>();
        this.guildBank = guildBank;
        this.sleepTime = getSleepTime();
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }

    public CraftPhase getPhase() {
        return phase;
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

    private void craft(CraftComponent itemToCraft) {
        synchronized (guildBank) {
            boolean hasAllMaterials = true;
            for (Pair<CraftingItem, Integer> item : itemToCraft.materials) {
                if (!hasAdequateNumberOfMaterials(item.getKey(), item.getValue())) {
                    hasAllMaterials = false;
                }
            }

            if (hasAllMaterials) {
                itemToCraft.materials.forEach((item) -> {
                    for (int i = 0; i < item.getValue(); i++) {
                        try {
                            removeItem(item.getKey().getClass().newInstance());
                        } catch (InstantiationException | IllegalAccessException ex) {
                            Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                addItemToBank(guildBank, itemToCraft);
            } else {
                itemToCraft.materials.forEach((item) -> {
                    grabMaterials(item.getKey(), randomIntGenerator(item.getValue(), 1));
                });
            }
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
        System.out.println(getPlayerId());
        while (this.phase.getPhaseNumber() != 7) {
            System.out.printf("[%s] is working on %s\n", name, phase.getPhaseName());
            try {
                synchronized (this) {
                    switch (phase.getPhaseNumber()) {
                        case 1:
                            craft(new DestroyerLodestone());
                            craft(new MoltenLodestone());
                            craft(new ElderWoodPlank());
                            craft(new HardWoodPlank());
                            craft(new SeasonedWoodPlank());
                            craft(new MysticClover());
                            break;
                        case 2:
                            craft(new GiftOfMagic());
                            craft(new GiftOfMight());
                            craft(new MysticClover());
                            craft(new ElderWoodPlank());
                            craft(new HardWoodPlank());
                            craft(new SeasonedWoodPlank());
                            break;
                        case 3:
                            craft(new GiftOfWood());
                            craft(new VialOfLiquidFlame());
                            craft(new DestroyerLodestone());
                            craft(new MoltenLodestone());
                            break;
                        case 4:
                            craft(new DestroyerLodestone());
                            craft(new MoltenLodestone());
                            craft(new GiftOfFortune());
                            craft(new GiftOfMastery());
                            break;
                        case 5:
                            craft(new GiftOfRodgort());
                            break;
                        case 6:
                            craft(new Rodgort());
                            break;
                        default:
                            break;
                    }
                }

                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                System.err.println("Error in the [" + this.getClass().getName() + "] class Run method!");
                Logger.getLogger(GuildMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
