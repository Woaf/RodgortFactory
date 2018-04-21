/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import materials.AncientWoodPlank;

/**
 *
 * @author Bálint
 */
public class GiftOfWood extends CraftComponent {

    public GiftOfWood() {
        super("Gift of Wood");
        super.materials.add(new Pair(new AncientWoodPlank(), 250));
        super.materials.add(new Pair(new ElderWoodPlank(), 250));
        super.materials.add(new Pair(new HardWoodPlank(), 250));
        super.materials.add(new Pair(new SeasonedWoodPlank(), 250));
    }
    
}
