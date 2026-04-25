package entities;

public abstract class Entity {
    private String name;

    public Entity(String s){
        this.name = s;
    }
    
    public String getName(){
        return name;
    }

    public void setName(String s){
        this.name = s;
    }
}
