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

    protected int stack_count;
    protected String name;
    protected int price;
    
    protected BaseMaterial(String name, int price)
    {
        this.stack_count = 0;
        this.name = name;
        this.price = price;
        System.out.println("One " + this.name + " created.");
    }
    
    public void increment()
    {
        int oldSize = stack_count;
        this.stack_count++;
        System.out.println("Stack incremented. Old size: " + oldSize + ". New size: " + stack_count);
    }

    @Override
    public String toString() {
        return "Material name: " + name + "\t\t Price: " + price + " bronze coins.\nCurrent stack size: " + stack_count;
    }
    
}
