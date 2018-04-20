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
    
    protected String name;
    protected ArrayList<Pair<CraftingItem, Integer>> materials;
    
    protected CraftComponent(String name)
    {
        this.materials = new ArrayList<>();
        this.name = name;
        System.out.println("One " + this.name + " created.");
    }

    @Override
    public String toString() {
        return "Craft Item name: " + name;
    }
}
