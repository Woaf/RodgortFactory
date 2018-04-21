/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package craftcomponents;

import abstract_definitions.CraftComponent;
import javafx.util.Pair;
import materials.BloodstoneShard;
import materials.GiftOfBattle;
import materials.GiftOfExploration;
import materials.ObsidianShard;

/**
 *
 * @author Bálint
 */
public class GiftOfMastery extends CraftComponent {

    public GiftOfMastery() {
        super("Gift of Mastery");
        super.materials.add(new Pair(new GiftOfBattle(), 1));
        super.materials.add(new Pair(new GiftOfExploration(), 1));
        super.materials.add(new Pair(new ObsidianShard(), 250));
        super.materials.add(new Pair(new BloodstoneShard(), 1));
    }
}
