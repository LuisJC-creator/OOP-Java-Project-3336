import java.util.*;
import entities.*;
import entities.items.*;
import weapons.*;
import action.Action;

import java.awt.Point;


public class Game {
    // IN PROGRESS
    private Board board;
    private Player player;
    private ArrayList<Enemy> enemies; // probably arraylist later.
    private ArrayList<Item> items; // TODO: dynamically update this to remove items when they get removed off board (NOT URGENT)
    private boolean gameOver;
    private Random rand;
    private int boardSize = 25;

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
        // main menu
        startGame();
        // quit
    }

    // startGame(): place entities on board, run game loop
    public void startGame(){


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
        int testVal = 5;
        for(int i = 0; i < testVal; i++){
            HealthPack temp = new HealthPack("health");
            items.add(temp);
        }

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


        // MAIN GAME LOOP
        // while(!gameOver){
        //     playerTurn(); // always start with the player's turn.
        //     for(Enemy e : enemies){
        //         if(!e.isDead()){
        //             enemyTurn(e);
        //         }
        //     }
        //     checkWinLose();
    }

    public void executeGuiTurn(int dx, int dy) {
        if (gameOver) return;

        Point p = board.getPosition(player);
        if (p == null) return;

        int newX = p.x + dx;
        int newY = p.y + dy;

        if (board.moveEntity(player, newX, newY)) {
            for (Enemy e : enemies) {
                if (!e.isDead()) {
                    enemyTurn(e);
                }
            }
            checkWinLose();
        }
    }

    //random gen
    private int randBoardNum(int boardSize){
    return rand.nextInt(boardSize); 
    }

    // checks if all enemies are dead
    private boolean enemiesDead(){
        for(Enemy e : enemies){
            if(!e.isDead()){
                return false;
            }
        }
        return true;
    }

    // call to see if the game is over
    private void checkWinLose(){
        if(this.player.isDead() || enemiesDead()) {gameOver = true;}
        else { return; }
    }

    private void playerTurn(){
         // use getEntityAt() for player logic, if player lands on healthpack, heal them by the healhpack value
        // do things based on what this returns.
        Action action = player.pickAction();

        if(action == Action.MOVE){
            // TODO: frontend provides direction (up/down/left/right) <- Aron
            // translate to dx/dy and call board.moveEntity(player, newX, newY) <- Luis or Aron whoever wants to do it.
            // then check getEntityAt() for item interaction
        }
        else if(action == Action.ATTACK){
            // TODO: frontend provides target Entity or target coordinates
            // check distance <= this.player.getWeapon().getRange()
            // if valid, call attack(player, target)
        }      

    }

    private void enemyTurn(Enemy e){
        
        // movement logic, won't move unless player isn't nearby. distance calculations
        Point enemyPos = board.getPosition(e);
        Point playerPos = board.getPosition(player);

        int dx = Integer.signum(playerPos.x - enemyPos.x);
        int dy = Integer.signum(playerPos.y - enemyPos.y);

        Entity targetTile = board.getEntityAt(enemyPos.x + dx, enemyPos.y + dy);
            if(targetTile instanceof Item){
                board.removeEntity(targetTile); 
            }

        board.moveEntity(e, enemyPos.x + dx, enemyPos.y + dy);
        enemyPos = board.getPosition(e); // update the position.

        // check to attack
        int distance = Math.abs(playerPos.x - enemyPos.x) + Math.abs(playerPos.y - enemyPos.y);
        
        if(distance <= e.getWeapon().getRange()){
            attack(e, this.player);
        }

    }

    private void attack(Combatant attacker, Combatant target){
        int damage = attacker.getWeapon().getDmg();
        target.setHp(target.getHp() - damage);
        if(target.isDead()){
            board.removeEntity(target);
        }
    }
 
    public static void main(String[] args){
        // Game game = new Game();
        // game.start();
    }

    // getters
    public Board getBoard() { return this.board; }
    public boolean isGameOver() { return this.gameOver; }
}
