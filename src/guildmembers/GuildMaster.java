/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guildmembers;

import abstract_definitions.CraftingItem;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rodgortfactory.GuildBank;

/**
 *
 * @author Bálint
 */
public class GuildMaster {
    
    private final String name;
    private final String Id;
    private final List<String> listOfMaterials;
    
    private GuildMaster(String name) {
        this.name = name;
        this.Id = name.replaceAll("\\s", "").toLowerCase() + ".#" + generateRandomSequence();
        listOfMaterials = new ArrayList<>();
        listOfMaterials.add("AncientBone");
        listOfMaterials.add("AncientWoodPlank");
        listOfMaterials.add("ArmoredScale");
        listOfMaterials.add("BloodstoneShard");
        listOfMaterials.add("BottleOfElonianWine");
        listOfMaterials.add("DestroyerCore");
        listOfMaterials.add("ElaborateTotem");
        listOfMaterials.add("ElderWoodLog");
        listOfMaterials.add("GhostPepper");
        listOfMaterials.add("GiftOfBaelfire");
        listOfMaterials.add("GiftOfBattle");
        listOfMaterials.add("GiftOfExploration");
        listOfMaterials.add("GlobOfEctoplasm");
        listOfMaterials.add("HardWoodLog");
        listOfMaterials.add("IcyRunestone");
        listOfMaterials.add("Moltencore");
        listOfMaterials.add("MysticCoin");
        listOfMaterials.add("MysticCrystal");
        listOfMaterials.add("ObsidianShard");
        listOfMaterials.add("PhilosophersStone");
        listOfMaterials.add("PileofCrystallineDust");
        listOfMaterials.add("PowerfulVenomSac");
        listOfMaterials.add("RodgortsFlame");
        listOfMaterials.add("SeasonedWoodLog");
        listOfMaterials.add("SuperiorSigilOfFire");
        listOfMaterials.add("VialOfPowerfulBlood");
        listOfMaterials.add("ViciousClaw");
        listOfMaterials.add("ViciousFang");
    }
    
    private static int generateRandomSequence() {
        Random random = new Random();
        return random.nextInt(8999) + 1000;
    }
    
    public static GuildMaster getInstance() {
        return GuildMaster2Holder.INSTANCE;
    }
    
    private static class GuildMaster2Holder {

        private static final GuildMaster INSTANCE = new GuildMaster("Matthew Mercer");
    }

    @Override
    public String toString() {
        return "Guild Master: " + name + "\t\t\t Player ID: " + Id + "\n List of materials: " + listOfMaterials;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return Id;
    }

    public List<String> getListOfMaterials() {
        return listOfMaterials;
    }
    
    public void fillInventory(GuildBank bank){
        
        listOfMaterials.forEach((material) -> {
            Class<?> object;
            try {
                object = Class.forName("materials." + material);
                Constructor<?> constructor = object.getConstructor();
                Object item = constructor.newInstance();
                
                bank.addToBank((CraftingItem) item);
                
                
            } catch (Exception ex) {
                System.err.println("Error during Object instantiation from string: " + ex.getMessage());
            }
        });
    }
    
    public void addToInventory(GuildBank bank, String itemName, int number)
    {
        bank.getBank().forEach((material) -> {
            if(material.getClass().getSimpleName().equals(itemName))
            {
                System.out.println("Adding [" + number + "] of " + material.getName() + " to the bank.");
                for(int i = 0; i < number; i++)
                {
                    material.increment();
                }
            }
        });
    }
    
}
