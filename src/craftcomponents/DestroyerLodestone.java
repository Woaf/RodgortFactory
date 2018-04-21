/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.BottleOfElonianWine;
import materials.DestroyerCore;
import materials.MysticCrystal;
import materials.PileofCrystallineDust;

/**
 *
 * @author Bálint
 */
public class DestroyerLodestone extends CraftComponent {

    public DestroyerLodestone() {
        super("Destroyer Lodestone");
        super.materials.add(new Pair(new BottleOfElonianWine(), 1));
        super.materials.add(new Pair(new PileofCrystallineDust(), 1));
        super.materials.add(new Pair(new MysticCrystal(), 1));
        super.materials.add(new Pair(new DestroyerCore(), 2));
    }
    
}
