package entities;
import weapons.Weapon;

public abstract class Combatant extends Entity {
    private Weapon wep;
    private int hp;
    
    public Combatant(String s, Weapon w, int hp){
        super(s);
        this.wep = w;
        this.hp = hp;
    }
    
    public int getHp(){
        return hp;
    }
    
    public void setHp(int hp){
        this.hp = hp;
    }

    public Weapon getWeapon(){
        return wep;
    }

}
