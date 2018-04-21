/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guildmembers;

import abstract_definitions.CraftingItem;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rodgortfactory.GuildBank;
import rodgortfactory.RodgortFactory;

/**
 *
 * @author Bálint
 */
public class GuildMaster2 {
    
    private final String name;
    private final String Id;
    private final List<String> listOfMaterials;
    
    private GuildMaster2(String name) {
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
    
    public static GuildMaster2 getInstance() {
        return GuildMaster2Holder.INSTANCE;
    }
    
    private static class GuildMaster2Holder {

        private static final GuildMaster2 INSTANCE = new GuildMaster2("Matther Mercer");
    }

    @Override
    public String toString() {
        return "GuildMaster2{" + "name=" + name + ", Id=" + Id + ", listOfMaterials=" + listOfMaterials + '}';
    }
    
    public void fillInventory(GuildBank bank){
        
        for(String s : listOfMaterials)
        {
            Class<?> object;
            try {
                object = Class.forName("materials." + s);
                Constructor<?> constructor = object.getConstructor();
                Object shard = constructor.newInstance();
                
                bank.addToBank((CraftingItem) shard);


            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RodgortFactory.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (NoSuchMethodException ex) { 
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(GuildMaster2.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }
}
