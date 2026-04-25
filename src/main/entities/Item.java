package entities;

public abstract class Item extends Entity {
    
    public Item(String s){
        super(s);
    }

    public abstract void onPickup(Combatant c);

}
