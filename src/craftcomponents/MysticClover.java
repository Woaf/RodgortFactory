/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import materials.GlobOfEctoplasm;
import materials.MysticCoin;
import materials.ObsidianShard;
import materials.PhilosophersStone;

/**
 *
 * @author Bálint
 */
public class MysticClover extends CraftComponent {
    
    public MysticClover() {
        super("Mystic Clover");
        super.materials.add(new Pair(new ObsidianShard(), 4));
        super.materials.add(new Pair(new MysticCoin(), 4));
        super.materials.add(new Pair(new GlobOfEctoplasm(), 4));
        super.materials.add(new Pair(new PhilosophersStone(), 24));   
    }
}
