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
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import materials.AncientWoodPlank;
import materials.DestroyerCore;
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

    public List<GuildMember> members;

    private GuildMaster(String name) {
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
        listOfBaseMaterials.add("PileOfCrystallineDust");
        listOfBaseMaterials.add("PowerfulVenomSac");
        listOfBaseMaterials.add("RodgortsFlame");
        listOfBaseMaterials.add("SeasonedWoodLog");
        listOfBaseMaterials.add("SuperiorSigilOfFire");
        listOfBaseMaterials.add("VialOfPowerfulBlood");
        listOfBaseMaterials.add("ViciousClaw");
        listOfBaseMaterials.add("ViciousFang");

        bank = GuildBank.getInstance();

        this.members = new ArrayList<>();
    }

    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }

    private static GuildMaster instance = null;

    public static GuildMaster getInstance() {
        if (instance == null) {
            instance = new GuildMaster("Matthew Mercer");
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
        for (GuildMember member : members) {
            if (member.getPhase().getPhaseNumber() == 7) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {

        Random rnd = new Random();
        int amount;
        while (!isFinished()) {
            try {
                Thread.sleep(1000);
                for (CraftingItem material : bank.getBank()) {
                    amount = rnd.nextInt(20) + 1;
                    if (material instanceof BaseMaterial) {
                        for (int i = 0; i < amount; i++) {
                            material.increment();
                        }
                    }
                }

                members.forEach((member) -> {
                    switch (member.getPhase().getPhaseNumber()) {

                        case 1:
                            if (hasEnoughCraftItem(new ElderWoodPlank(), 250)
                                    && hasEnoughCraftItem(new MoltenLodestone(), 100)
                                    && hasEnoughCraftItem(new DestroyerLodestone(), 100)) {
                                member.setPhase(CraftPhase.PHASE3);
                            }
                            break;
                        case 2:
                            if (hasEnoughCraftItem(new MysticClover(), 77)
                                    && hasEnoughCraftItem(new GiftOfMagic(), 1)
                                    && hasEnoughCraftItem(new GiftOfMight(), 1)) {
                                member.setPhase(CraftPhase.PHASE4);
                            }
                            break;
                        case 3:
                            if (hasEnoughCraftItem(new GiftOfWood(), 1)
                                    && hasEnoughCraftItem(new VialOfLiquidFlame(), 1)) {
                                member.setPhase(CraftPhase.PHASE5);
                            }
                            break;
                        case 4:
                            if (hasEnoughCraftItem(new GiftOfFortune(), 1)
                                    && hasEnoughCraftItem(new GiftOfMastery(), 1)) {
                                member.setPhase(CraftPhase.PHASE6);
                            }
                            break;
                        case 5:
                            if (hasEnoughCraftItem(new GiftOfRodgort(), 1)) {
                                member.setPhase(CraftPhase.PHASE7);
                            }
                            break;
                        case 6:
                            if (hasEnoughCraftItem(new Rodgort(), 1)) {
                                member.setPhase(CraftPhase.PHASE7);
                            }
                            break;
                        default:
                    }
                });

            } catch (InterruptedException ex) {
                Logger.getLogger(GuildMaster.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Members: " + members.toString());

            System.out.println(bank.toString());
        }
    }

}
