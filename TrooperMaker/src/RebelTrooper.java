/*
 *                __
    .-.__      \ .-.  ___  __|_|
'--.-.-(   \/\;;\_\.-._______.-.
    (-)___     \ \ .-\ \;;\(   \       \ \
     Y    '---._\_((Q)) \;;\\ .-\     __(_)
     I           __'-' / .--.((Q))---'    \,
     I     ___.-:    \|  |   \'-'_          \
     A  .-'      \ .-.\   \   \ \ '--.__     '\
     |  |____.----((Q))\   \__|--\_      \     '
        ( )        '-'  \_  :  \-' '--.___\
         Y                \  \  \       \(_)
         I                 \  \  \         \,
         I                  \  \  \          \
         A                   \  \  \          '\
         |              snd   \  \__|           '
                               \_:.  \
                                 \ \  \
                                  \ \  \
                                   \_\_|

 *
 *
 * Andrew Shiraki
 * 2021 - 11 - 09
 * RebelTrooper.java extends the Trooper class and can be instantiated in AStarWar! >o<
 */
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
