/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import rodgortfactory.materials.BottleOfElonianWine;
import rodgortfactory.materials.Moltencore;
import rodgortfactory.materials.MysticCrystal;
import rodgortfactory.materials.PileofCrystallineDust;

/**
 *
 * @author Bálint
 */
public class MoltenLodestone extends CraftComponent {

    public MoltenLodestone() {
        super("Molten Lodestone");
        super.materials.add(new Pair(new BottleOfElonianWine(), 100));
        super.materials.add(new Pair(new PileofCrystallineDust(), 100));
        super.materials.add(new Pair(new MysticCrystal(), 100));
        super.materials.add(new Pair(new Moltencore(), 200));
    }
    
}
