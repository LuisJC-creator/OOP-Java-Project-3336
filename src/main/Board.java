import java.util.*; // useful data structures.

import entities.Entity;

import java.awt.Point;

public class Board {
    
    private int x;
    private int y;

    // the "board"
    private Entity board[][];

    // Hashmap for entity locations
    private HashMap<Entity, Point> entityPos;

    public Board(int x, int y){
        this.x = x;
        this.y = y;
        this.board = new Entity[x][y];
        this.entityPos = new HashMap<>();
    }


    // so game can call this on initialization, easy peasy.
    public boolean placeEntity(Entity e, int x, int y){
        if(!isOccupied(x, y) && isValidPosition(x, y)){
            board[x][y] = e;
            Point temp = new Point(x,y); // this kind of sucks maybe I can streamline this. but might be more lines to do this.
            entityPos.put(e, temp);
            return true;
        }
        else {
            // send some signal to game to pick a different spot?
            return false;
        }
    }

    // for turn moves
    public boolean moveEntity(Entity e, int x, int y){
        // just in case is called on a nonexistent entity also check if valid, add error handling at some point.
        if(!entityPos.containsKey(e)) return false;
        if(!isValidPosition(x, y)) return false;

        // find entity's current pos.
        Point curr = entityPos.get(e);

        // delete the old position.
        board[curr.x][curr.y] = null;

        // 
        Point temp = new Point(x, y);
        entityPos.put(e, temp);

        // move it to x, y.
        board[temp.x][temp.y] = e;

        return true;
    }

    // check if position is within board bounds.
    private boolean isValidPosition(int x, int y){
        if(x > this.x - 1 || y > this.y - 1) return false;
        else if(x < 0 || y < 0) return false;
        else return true;
    }

    // gonna need this a lot, might as well have a fn for readability.
    private boolean isOccupied(int x, int y){
        if(board[x][y] == null) return false;
        else return true;
    }

    public Point getPosition(Entity e){
        return entityPos.get(e);
    }

    public void removeEntity(Entity e){
        Point temp = entityPos.get(e);
        board[temp.x][temp.y] = null;
        entityPos.remove(e);
    }

    public Entity getEntityAt(int x, int y){
        return board[x][y];
    }

    public int getRows() { return x; }
    public int getCols() { return y; }
}
