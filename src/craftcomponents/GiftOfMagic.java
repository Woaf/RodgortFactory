/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.ElaborateTotem;
import materials.PileofCrystallineDust;
import materials.PowerfulVenomSac;
import materials.VialOfPowerfulBlood;

/**
 *
 * @author Bálint
 */
public class GiftOfMagic extends CraftComponent {

    public GiftOfMagic() {
        super("Gift of Magic");
        super.materials.add(new Pair(new VialOfPowerfulBlood(), 250));
        super.materials.add(new Pair(new PowerfulVenomSac(), 250));
        super.materials.add(new Pair(new ElaborateTotem(), 250));
        super.materials.add(new Pair(new PileofCrystallineDust(), 250));
    }
}
