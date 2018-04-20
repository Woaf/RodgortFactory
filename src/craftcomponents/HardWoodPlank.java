/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import rodgortfactory.materials.HardWoodLog;

/**
 *
 * @author Bálint
 */
public class HardWoodPlank extends CraftComponent {

    public HardWoodPlank() {
        super("Hard Wood Plank");
        super.materials.add(new Pair(new HardWoodLog(), 750));
    }
    
}
