import java.util.*;
import entities.*;
import entities.items.*;
import weapons.*;


public class Game {
    // IN PROGRESS
    private Board board;
    private Player player;
    private ArrayList<Enemy> enemies; // probably arraylist later.
    private ArrayList<Item> items;
    private boolean gameOver;

    public Game(){
        Weapon sword = new Weapon("sword", 1, 20);
        Weapon bow = new Weapon("bow", 3, 10);
        Weapon spear = new Weapon("spear", 2, 15);
        this.board = new Board(25, 25);

        // list decs
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();


        this.player = new Player("Hero", sword, 100); // placeholder, should be based on player choice (frontend thing)

        // just test stuff. probably random assignment later? tbd.
        String[] names = {"glorp", "mito", "lee"};
        String[] types = {"Gobin", "Orc", "Elf"};
        Weapon[] wepList = {sword, bow, spear};
        for(int idx = 0; idx < 3; idx++){
            Enemy temp = new Enemy(names[idx], wepList[idx], 150/(idx+1), types[idx]);
            enemies.add(temp);
        }

        // health pack test
        HealthPack pack1 = new HealthPack("health");
        items.add(pack1);
    }

    public void start(){
        // TODO: main menu, call startGame() or quit
    }

    // startGame(): place entities on board, run game loop
    // playerturn(): player pickAction() and resolve choice
    // enemyTurn(): handle automatic enemy movements
    // some logic for checking if the player has won or lost.
    
    public static void main(String[] args){

    }
}
