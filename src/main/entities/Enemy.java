package entities;
import weapons.Weapon;

public class Enemy extends Combatant {
    private String type;
    
    public Enemy(String s, Weapon w, int hp, String type){
        super(s, w, hp);
        this.type = type;
    }

    public String getType(){
        return type;
    }
    
}