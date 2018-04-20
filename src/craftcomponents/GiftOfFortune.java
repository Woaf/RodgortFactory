/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import rodgortfactory.materials.GlobOfEctoplasm;

/**
 *
 * @author Bálint
 */
public class GiftOfFortune extends CraftComponent {

    public GiftOfFortune() {
        super("Gift of Fortune");
        super.materials.add(new Pair(new GlobOfEctoplasm(), 250));
        super.materials.add(new Pair(new GiftOfMagic(), 1));
        super.materials.add(new Pair(new GiftOfMight(), 1));
        super.materials.add(new Pair(new MysticClover(), 77));
    }   
}
