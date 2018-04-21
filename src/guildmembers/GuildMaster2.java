/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guildmembers;

import abstract_definitions.BaseMaterial;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import materials.AncientBone;
import materials.AncientWoodPlank;
import materials.ArmoredScale;
import materials.BloodstoneShard;
import materials.BottleOfElonianWine;
import materials.DestroyerCore;
import materials.ElaborateTotem;
import materials.ElderWoodLog;
import materials.GhostPepper;
import materials.GiftOfBaelfire;
import materials.GiftOfBattle;
import materials.GiftOfExploration;
import materials.GlobOfEctoplasm;
import materials.HardWoodLog;
import materials.IcyRunestone;
import materials.Moltencore;
import materials.MysticCoin;
import materials.MysticCrystal;
import materials.ObsidianShard;
import materials.PhilosophersStone;
import materials.PileofCrystallineDust;
import materials.PowerfullVenomSac;
import materials.RodgortsFlame;
import materials.SeasonedWoodLog;
import materials.SuperiorSigilOfFire;
import materials.VialOfPowerfullBlood;
import materials.ViciousClaw;
import materials.ViciousFang;

/**
 *
 * @author Bálint
 */
public class GuildMaster2 {
    
    private final String name;
    private final String Id;
    private final List<BaseMaterial> listOfMaterials;
    
    private GuildMaster2(String name) {
        this.name = name;
        this.Id = name.replaceAll("\\s", "").toLowerCase() + ".#" + generateRandomSequence();
        listOfMaterials = new ArrayList<>();
        listOfMaterials.add(new AncientBone());
        listOfMaterials.add(new AncientWoodPlank());
        listOfMaterials.add(new ArmoredScale());
        listOfMaterials.add(new BloodstoneShard());
        listOfMaterials.add(new BottleOfElonianWine());
        listOfMaterials.add(new DestroyerCore());
        listOfMaterials.add(new ElaborateTotem());
        listOfMaterials.add(new ElderWoodLog());
        listOfMaterials.add(new GhostPepper());
        listOfMaterials.add(new GiftOfBaelfire());
        listOfMaterials.add(new GiftOfBattle());
        listOfMaterials.add(new GiftOfExploration());
        listOfMaterials.add(new GlobOfEctoplasm());
        listOfMaterials.add(new HardWoodLog());
        listOfMaterials.add(new IcyRunestone());
        listOfMaterials.add(new Moltencore());
        listOfMaterials.add(new MysticCoin());
        listOfMaterials.add(new MysticCrystal());
        listOfMaterials.add(new ObsidianShard());
        listOfMaterials.add(new PhilosophersStone());
        listOfMaterials.add(new PileofCrystallineDust());
        listOfMaterials.add(new PowerfullVenomSac());
        listOfMaterials.add(new RodgortsFlame());
        listOfMaterials.add(new SeasonedWoodLog());
        listOfMaterials.add(new SuperiorSigilOfFire());
        listOfMaterials.add(new VialOfPowerfullBlood());
        listOfMaterials.add(new ViciousClaw());
        listOfMaterials.add(new ViciousFang());
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
    
    
    
}
