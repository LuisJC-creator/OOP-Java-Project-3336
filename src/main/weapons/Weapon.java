package weapons;

public class Weapon {
    private String name;
    private int range;
    private int dmg;
    
    public Weapon(String name, int range, int dmg){
        this.name = name;
        this.range = range;
        this.dmg = dmg;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public int getDmg(){
        return dmg;
    }

}
