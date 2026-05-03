package entities;
import weapons.Weapon;
import action.Action;

public class Player extends Combatant {
    
    
    public Player(String s, Weapon w, int hp){
        super(s, w, hp);
        
    }

    public Action pickAction(){

        // TODO: display action menu, wait for player input
        // options: move, attack.
        // maybe have this return something so it can act on logic within game?? idk you can do that with front end stuff prob.
        // RETURN AN ACTION
        return Action.MOVE;
    }
}
