public class RebelTrooper extends Trooper{
    private String name;
    private static int soldierCount=0;
    //Constructor
    RebelTrooper( String unit, int number ,String name){
        super(unit , number);
        this.name=name;
        this.setTrooperKind("pilot");
        setMarchModifier(0.75);
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
        return name + "(" +getUnit()+ String.valueOf(getNumber()) + ": ) a " + getTrooperKind();
    }
    @Override
    public double march(double duration) {
        return marchSpeed * duration * marchModifier;
    }

}
