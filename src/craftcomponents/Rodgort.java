/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import rodgortfactory.materials.RodgortsFlame;

/**
 *
 * @author Bálint
 */
public class Rodgort extends CraftComponent{

    public Rodgort() {
        super("Rodgort");
        super.materials.add(new Pair(new RodgortsFlame(), 1));
        super.materials.add(new Pair(new GiftOfRodgort(), 1));
        super.materials.add(new Pair(new GiftOfMastery(), 1));
        super.materials.add(new Pair(new GiftOfFortune(), 1));
    }
}
