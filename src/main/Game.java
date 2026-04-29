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
    private Random rand;

    public Game(){
        this.board = null;
        this.player = null;
        this.enemies = new ArrayList<Enemy>();
        this.items = new ArrayList<Item>();
        this.gameOver = false;
        rand = new Random();
    }

    public void start(){
        // TODO: main menu, call startGame() or quit
    }

    // startGame(): place entities on board, run game loop
    public void startGame(){
        
        // constant board size.
        int boardSize = 25;

        Weapon sword = new Weapon("sword", 1, 20);
        Weapon bow = new Weapon("bow", 3, 10);
        Weapon spear = new Weapon("spear", 2, 15);
        this.board = new Board(boardSize, boardSize);

        // list decs
        enemies = new ArrayList<Enemy>();
        items = new ArrayList<Item>();


        this.player = new Player("Hero", sword, 100); // placeholder, should be based on player choice (frontend thing), negotiable depending on scope

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

        // make placing a functin probably.
        
        // place enemies.
        for(int idx = 0; idx < enemies.size(); idx++){
            // random!
            int x;
            int y;
            do {
                x = randBoardNum(boardSize);
                y = randBoardNum(boardSize);
                
            } while(!this.board.placeEntity(enemies.get(idx), x, y));
        }

        // place items (only 1 item right now lol but we'll fix later) 
         for(int idx = 0; idx < items.size(); idx++){
            // random!
            int x;
            int y;
            do {
                x = randBoardNum(boardSize);
                y = randBoardNum(boardSize);
                
            } while(!this.board.placeEntity(items.get(idx), x, y));
        }

        // place player
        int x;
        int y;
        do {
            x = randBoardNum(boardSize);
            y = randBoardNum(boardSize);
        } while(!this.board.placeEntity(player, x ,y));


    }
    // TODO: playerturn(): player pickAction() and resolve choice
    // TODO: enemyTurn(): handle automatic enemy movements
    // TODO: handle attacking (within turn most likely.)
    // TODO: some logic for checking if the player has won or lost.

    //random gen
    private int randBoardNum(int boardSize){
    return rand.nextInt(boardSize); 
    }
    
    public static void main(String[] args){
        
    }
}
