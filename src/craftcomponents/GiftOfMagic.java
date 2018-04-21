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
import materials.PowerfullVenomSac;
import materials.VialOfPowerfullBlood;

/**
 *
 * @author Bálint
 */
public class GiftOfMagic extends CraftComponent {

    public GiftOfMagic() {
        super("Gift of Magic");
        super.materials.add(new Pair(new VialOfPowerfullBlood(), 250));
        super.materials.add(new Pair(new PowerfullVenomSac(), 250));
        super.materials.add(new Pair(new ElaborateTotem(), 250));
        super.materials.add(new Pair(new PileofCrystallineDust(), 250));
    }
}
