/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import materials.ElderWoodLog;

/**
 *
 * @author Bálint
 */
public class ElderWoodPlank extends CraftComponent {

    public ElderWoodPlank() {
        super("Elder Wood Plank");
        super.materials.add(new Pair(new ElderWoodLog(), 750));
    }
}
