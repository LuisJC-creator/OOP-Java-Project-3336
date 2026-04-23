public class Board {
    
    private int width;
    private int length;

    // the "board"
    private Entity board[][];

    public Board(int w, int l){
        this.width = w;
        this.length = l;
        this.board = new Entity[length][width];
    }


    // so game can call this on initialization, easy peasy.
    public void placeEntities(Entity e, int x, int y){

    }

    // redundancy?
    public void moveEntity(Entity e, int x, int y){

    }

    // check if position is within board bounds.
    public boolean isValidPosition(int x, int y){

    }
}
