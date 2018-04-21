/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.IcyRunestone;
import materials.SuperiorSigilOfFire;

/**
 *
 * @author Bálint
 */
public class GiftOfRodgort extends CraftComponent {

    public GiftOfRodgort() {
        super("Gift of Rodgort");
        super.materials.add(new Pair(new IcyRunestone(), 100));
        super.materials.add(new Pair(new SuperiorSigilOfFire(), 1));
        super.materials.add(new Pair(new GiftOfWood(), 1));
        super.materials.add(new Pair(new VialOfLiquidFlame(), 1));
    }
}
