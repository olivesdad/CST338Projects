/*
 * Andrew Shiraki
 * 2021 - 11 - 09
 * StoreTrooper is an extension of Trooper class
 */
public class StormTrooper extends Trooper{
    private String name;
    private static int soldierCount=0;
//Constructor
    StormTrooper(String unit ,int Number){
        super(unit , Number);
        this.name=unit;
        this.setTrooperKind("StormTrooper");
        setMarchModifier(1.10);
       soldierCount++;
    }

    //Getters+Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getSoldierCount() {
        return soldierCount;
    }

    //goString
    @Override
    public String toString() {
        return "(" +name+ String.valueOf(getNumber()) + ":) a " + getTrooperKind();
    }
    @Override
    public double march(double duration) {
        return marchSpeed * duration * marchModifier;
    }
}
