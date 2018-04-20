/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import javafx.util.Pair;
import abstract_definitions.CraftComponent;
import rodgortfactory.materials.GlobOfEctoplasm;
import rodgortfactory.materials.MysticCoin;
import rodgortfactory.materials.ObsidianShard;

/**
 *
 * @author Bálint
 */
public class MysticClover extends CraftComponent {
    
    public MysticClover() {
        super("Mystic Clover");
        super.materials.add(new Pair(new ObsidianShard(), 249));
        super.materials.add(new Pair(new MysticCoin(), 249));
        super.materials.add(new Pair(new GlobOfEctoplasm(), 249));
    }
    
}
