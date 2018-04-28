/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import materials.SeasonedWoodLog;

/**
 *
 * @author Bálint
 */
public class SeasonedWoodPlank extends CraftComponent {

    public SeasonedWoodPlank() {
        super("Seasoned Wood Plank");
        super.materials.add(new Pair(new SeasonedWoodLog(), 3));
    }
}
