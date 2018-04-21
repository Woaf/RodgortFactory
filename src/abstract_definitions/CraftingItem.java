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
public abstract class CraftingItem {
    
    protected String name;
    protected int price;
    protected int stack_count;
    
    protected CraftingItem(String name){
        this.name = name;
        this.stack_count = 0;
        //System.out.println("One " + this.name + " created.");
    }
    
    public int getStack_count() {
        return stack_count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    
    public void increment()
    {
        //int oldSize = stack_count;
        this.stack_count++;
    }
    
    public void decrement()
    {
        //int oldSize = stack_count;
        this.stack_count--;
    }
    
}
