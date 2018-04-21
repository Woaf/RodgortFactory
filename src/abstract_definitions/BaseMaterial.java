/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstract_definitions;

/**
 *
 * @author Bálint
 */
public abstract class BaseMaterial extends CraftingItem {
    
    protected BaseMaterial(String name, int price)
    {
        super(name);
        this.price = price;
        this.stack_count = 0;
    }

    public void setStack_count(int stack_count) {
        this.stack_count = stack_count;
    }

    @Override
    public String toString() {
        return "Material name: " + name + "\t\t Price: " + price + " bronze coins.\nCurrent stack size: " + stack_count;
    }
    
}
