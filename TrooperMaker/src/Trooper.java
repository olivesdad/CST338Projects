
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Trooper {
    private String unit;
    private int number;

    protected String trooperKind;
    protected double marchSpeed, marchModifier;

    //constructors
    Trooper() {
        this("AA", 0);
    }

    ;

    Trooper(String unit, int number) {
        this.unit = unit;
        this.number = number;
        marchSpeed = 5;
    }

    //Setters+Getters

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTrooperKind() {
        return trooperKind;
    }

    public void setTrooperKind(String trooperKind) {
        this.trooperKind = trooperKind;
    }

    public double getMarchSpeed() {
        return marchSpeed;
    }

    public void setMarchSpeed(double marchSpeed) {
        this.marchSpeed = marchSpeed;
    }

    public double getMarchModifier() {
        return marchModifier;
    }

    public void setMarchModifier(double marchModifier) {
        this.marchModifier = marchModifier;
    }

    //toString+equals+hash

    @Override
    public String toString() {
        return unit + " : " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trooper trooper = (Trooper) o;
        return getNumber() == trooper.getNumber() && Double.compare(trooper.getMarchSpeed(), getMarchSpeed()) == 0 && Double.compare(trooper.getMarchModifier(), getMarchModifier()) == 0 && getUnit().equals(trooper.getUnit()) && getTrooperKind().equals(trooper.getTrooperKind());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnit(), getNumber(), getTrooperKind(), getMarchSpeed(), getMarchModifier());
    }

    //thonkers
    public static void addToUnit(HashMap<String, List<Trooper>> units, Trooper t) {
        if (Objects.isNull(t)) return;
        else if (!units.containsKey(t.getUnit())) { //if the unit type is a key
            units.put(t.getUnit(), new ArrayList<>()); //at the entry with a new array list
        }
        units.get(t.getUnit()).add(t); //add trooper to the list keyed by Unit
    }

    public abstract double march(double duration);

    public boolean attack(Trooper target, int roll) {
        System.out.println(this + " is attacking " + target);
        if (this == target || roll == 1) {
            System.out.println(target + " is targeting itself!");
            System.out.println(target + " rolled a " + roll + " and hurt itself in the confusion");
            return true;

        } else if (this instanceof StormTrooper) {
            if (target instanceof RebelTrooper) {
                System.out.println( "rolled " + roll + " against the rebel scum.");
                if (roll > 10 && roll % 2 == 0) return true;
            } else if (target instanceof StormTrooper) {
                System.out.println("No treason in the ranks!");
                return false;
            } else {
                System.out.println("Acceptable Collateral Damage!");
                if (roll > 10 || roll % 2 == 0) return true;
            }
        } else {
            if (target instanceof StormTrooper) {
                System.out.println("rolled " + roll + " against the imperial scum.");
                if (roll > 5 && roll % 2 != 0) return true;
            } else if (this instanceof RebelTrooper) {
                System.out.println("Imperial Spy!");
                return false;
            } else {
                System.out.println("Rebels target an innocent bystander");
                if (roll >= 18 || roll % 2 == 0) return true;
            }
        }
        return false;
    }
}
