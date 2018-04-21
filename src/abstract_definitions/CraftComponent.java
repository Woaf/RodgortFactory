/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Bálint
 */
public abstract class CraftComponent extends CraftingItem {
    
    protected ArrayList<Pair<CraftingItem, Integer>> materials;
    
    protected CraftComponent(String name)
    {
        super(name);
        this.price = 0;
        this.stack_count = 0;
        this.materials = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Craft Item name: " + name;
    }
}
