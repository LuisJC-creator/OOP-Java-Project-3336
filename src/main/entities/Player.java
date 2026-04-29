package entities;
import weapons.Weapon;

public class Player extends Combatant {
    
    
    public Player(String s, Weapon w, int hp){
        super(s, w, hp);
        
    }

    public void pickAction(){
        // TODO: display action menu, wait for player input
        // options: move, attack.
    }

    public boolean isDead(){
        if(this.getHp() <= 0) { return true; }
        else { return false; }
    }
}
