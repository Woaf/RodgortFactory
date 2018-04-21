/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.GhostPepper;
import materials.GiftOfBattle;

/**
 *
 * @author Bálint
 */
public class VialOfLiquidFlame extends CraftComponent {

    public VialOfLiquidFlame() {
        super("Vial of Liquid Flame");
        super.materials.add(new Pair(new GhostPepper(), 250));
        super.materials.add(new Pair(new GiftOfBattle(), 1));
        super.materials.add(new Pair(new MoltenLodestone(), 100));
        super.materials.add(new Pair(new DestroyerLodestone(), 100));
    }
}
