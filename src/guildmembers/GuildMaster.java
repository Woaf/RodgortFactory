/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guildmembers;

import abstract_definitions.BaseMaterial;
import abstract_definitions.CraftingItem;
import abstract_definitions.GuildMember;
import craftcomponents.DestroyerLodestone;
import craftcomponents.ElderWoodPlank;
import craftcomponents.GiftOfFortune;
import craftcomponents.GiftOfMagic;
import craftcomponents.GiftOfMastery;
import craftcomponents.GiftOfMight;
import craftcomponents.GiftOfRodgort;
import craftcomponents.GiftOfWood;
import craftcomponents.MoltenLodestone;
import craftcomponents.MysticClover;
import craftcomponents.Rodgort;
import craftcomponents.VialOfLiquidFlame;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rodgortfactory.CraftPhase;
import rodgortfactory.GuildBank;

/**
 *
 * @author Bálint
 */
public class GuildMaster implements Runnable {

    private final String name;
    private final String Id;
    private final List<String> listOfBaseMaterials;
    public GuildBank bank;

    private final int amountToCraft;
    private final int sleepTime;

    public List<GuildMember> members;

    private GuildMaster(String name, int amount, int sleepTime) {
        this.name = name;
        this.Id = name.replaceAll("\\s", "").toLowerCase() + ".#" + generateRandomSequence();
        listOfBaseMaterials = new ArrayList<>();
        listOfBaseMaterials.add("AncientBone");
        listOfBaseMaterials.add("AncientWoodPlank");
        listOfBaseMaterials.add("ArmoredScale");
        listOfBaseMaterials.add("BloodstoneShard");
        listOfBaseMaterials.add("BottleOfElonianWine");
        listOfBaseMaterials.add("DestroyerCore");
        listOfBaseMaterials.add("ElaborateTotem");
        listOfBaseMaterials.add("ElderWoodLog");
        listOfBaseMaterials.add("GhostPepper");
        listOfBaseMaterials.add("GiftOfBaelfire");
        listOfBaseMaterials.add("GiftOfBattle");
        listOfBaseMaterials.add("GiftOfExploration");
        listOfBaseMaterials.add("GlobOfEctoplasm");
        listOfBaseMaterials.add("HardWoodLog");
        listOfBaseMaterials.add("IcyRunestone");
        listOfBaseMaterials.add("Moltencore");
        listOfBaseMaterials.add("MysticCoin");
        listOfBaseMaterials.add("MysticCrystal");
        listOfBaseMaterials.add("ObsidianShard");
        listOfBaseMaterials.add("PhilosophersStone");
        listOfBaseMaterials.add("PileofCrystallineDust");
        listOfBaseMaterials.add("PowerfulVenomSac");
        listOfBaseMaterials.add("RodgortsFlame");
        listOfBaseMaterials.add("SeasonedWoodLog");
        listOfBaseMaterials.add("SuperiorSigilOfFire");
        listOfBaseMaterials.add("VialOfPowerfulBlood");
        listOfBaseMaterials.add("ViciousClaw");
        listOfBaseMaterials.add("ViciousFang");

        bank = GuildBank.getInstance();

        this.amountToCraft = amount;
        this.sleepTime = sleepTime;

        this.members = new ArrayList<>();
    }

    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }

    private static int getAmountToCraft() {
        File config = new File("src/resources/config.txt");
        int ret = 0;
        try {
            Scanner sc = new Scanner(config);
            while (sc.hasNextLine()) {
                ret = sc.nextInt();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Config file cannot be found!\n[" + ex.getMessage() + ']');
        }

        System.out.format("Amount of Rodgort(s) to be crafted: %d\n", ret);
        return ret;
    }
    
    private static int getSleepTime()
    {
        File config = new File("src/resources/config.txt");
        int ret = 0;
        try {
            Scanner sc = new Scanner(config);
            for(int i = 0; i < 2; i++)
            {
                ret = sc.nextInt();
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Config file cannot be found!\n[" + ex.getMessage() + ']');
        }

        System.out.format("Guild master sleep time: %d\n", ret);
        return ret;
    }

    private static GuildMaster instance = null;

    public static GuildMaster getInstance() {
        if (instance == null) {
            instance = new GuildMaster("Guild Master", getAmountToCraft(), getSleepTime());
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Guild Master: " + name + "\t\t\t Player ID: " + Id + "\n List of materials: " + listOfBaseMaterials;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public List<String> getListOfBaseMaterials() {
        return listOfBaseMaterials;
    }

    public List<GuildMember> getMembers() {
        return members;
    }

    public void fillBank() {
        listOfBaseMaterials.forEach((materialName) -> {
            Class<?> object;
            try {
                object = Class.forName("materials." + materialName);
                Constructor<?> constructor = object.getConstructor();
                Object item = constructor.newInstance();

                bank.addToBank((CraftingItem) item);

            } catch (Exception ex) {
                System.err.println("Error during Object instantiation from string: " + ex.getMessage());
            }
        });
    }

    public void addToBank(String itemName, int number) {
        bank.getBank().forEach((material) -> {
            if (material.getClass().getSimpleName().equals(itemName)) {
                System.out.println("Adding [" + number + "] of " + material.getName() + " to the bank.");
                for (int i = 0; i < number; i++) {
                    material.increment();
                }
            }
        });
    }

    private boolean hasEnoughCraftItem(CraftingItem item, int necessaryAmount) {
        return bank.getBank().stream()
                .filter((material)
                        -> (material.getName()
                        .equals(item.getName())))
                .anyMatch((material)
                        -> (material.getStack_count() >= necessaryAmount));
    }

    private boolean isFinished() {
        return members.stream().noneMatch((member) -> (member.getPhase().getPhaseNumber() != 7));
    }

    @Override
    public void run() {

        Random rnd = new Random();
        int amount;
        while (!isFinished()) {
            try {
                Thread.sleep(sleepTime);
                synchronized (bank) {
                    for (CraftingItem material : bank.getBank()) {
                        amount = rnd.nextInt(15*members.size()) + 1;
                        if (material instanceof BaseMaterial) {
                            for (int i = 0; i < amount; i++) {
                                material.increment();
                            }
                        }
                    }
                }

                members.forEach((member) -> {
                    switch (member.getPhase().getPhaseNumber()) {

                        case 1:
                            if (hasEnoughCraftItem(new ElderWoodPlank(), amountToCraft * 250 + 250)
                                    && hasEnoughCraftItem(new MoltenLodestone(), amountToCraft * 100 + 100)
                                    && hasEnoughCraftItem(new DestroyerLodestone(), amountToCraft * 100 + 100)) {
                                member.setPhase(CraftPhase.PHASE3);
                            }
                            break;
                        case 2:
                            if (hasEnoughCraftItem(new MysticClover(), amountToCraft * 77 + 77)
                                    && hasEnoughCraftItem(new GiftOfMagic(), amountToCraft * 1 + 1)
                                    && hasEnoughCraftItem(new GiftOfMight(), amountToCraft * 1 + 1)) {
                                member.setPhase(CraftPhase.PHASE4);
                            }
                            break;
                        case 3:
                            if (hasEnoughCraftItem(new GiftOfWood(), amountToCraft * 1)
                                    && hasEnoughCraftItem(new VialOfLiquidFlame(), amountToCraft * 1)) {
                                member.setPhase(CraftPhase.PHASE5);
                            }
                            break;
                        case 4:
                            if (hasEnoughCraftItem(new GiftOfFortune(), amountToCraft * 1)
                                    && hasEnoughCraftItem(new GiftOfMastery(), amountToCraft * 1)) {
                                member.setPhase(CraftPhase.PHASE6);
                            }
                            break;
                        case 5:
                            if (hasEnoughCraftItem(new GiftOfRodgort(), amountToCraft * 1)) {
                                member.setPhase(CraftPhase.PHASE7);
                            }
                            break;
                        case 6:
                            if (hasEnoughCraftItem(new Rodgort(), amountToCraft)) {
                                member.setPhase(CraftPhase.PHASE7);
                            }
                            break;
                        default:
                    }
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(GuildMaster.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(bank.toString());
        }
    }

}
