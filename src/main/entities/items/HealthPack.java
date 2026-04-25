package entities.items;

import entities.Combatant;

public class HealthPack extends Item {
    public HealthPack(String s){
        super(s);
    }

    public void onPickup(Combatant c){
        c.setHp(c.getHp() + 10);
    }
}