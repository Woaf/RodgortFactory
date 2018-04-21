/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.AncientBone;
import materials.ArmoredScale;
import materials.ViciousClaw;
import materials.ViciousFang;

/**
 *
 * @author Bálint
 */
public class GiftOfMight extends CraftComponent {

    public GiftOfMight() {
        super("Gift of Might");
        super.materials.add(new Pair(new ViciousFang(), 250));
        super.materials.add(new Pair(new ArmoredScale(), 250));
        super.materials.add(new Pair(new ViciousClaw(), 250));
        super.materials.add(new Pair(new AncientBone(), 250));
    }
}
