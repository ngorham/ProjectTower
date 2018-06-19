package net.ngorham.projecttower;

/**
 * Project Tower
 * Player.java
 * POJO
 * Purpose: Provides local storage and access to a Player
 *
 * @author Neil Gorham
 * @version 1.0 06/16/2018
 *
 */

public class Player {
    //Private variables
    private int currentHP;
    private int maxHP;
    private int currentAP;
    private int maxAP;
    private int ATK; //Attack
    private int DEF; //Defense
    private int SPD; //Speed
    private int damage;
    //private ArrayList<Item> items;
    //private ArrayList<Skill> skills;

    //Default Constructor
    public Player(){
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    //Loaded Constructor
    public Player(int currentHP, int maxHP, int currentAP, int maxAP,
                  int ATK, int DEF, int SPD, int damage){
        setCurrentHP(currentHP);
        setMaxHP(maxHP);
        setCurrentAP(currentAP);
        setMaxAP(maxAP);
        setATK(ATK);
        setDEF(DEF);
        setSPD(SPD);
        setDamage(damage);
    }

    //Setter methods
    public void setCurrentHP(int newHP){ currentHP = newHP; }

    public void setMaxHP(int newHP){ maxHP = newHP; }

    public void setCurrentAP(int newAP){ currentAP = newAP; }

    public void setMaxAP(int newAP){ maxAP = newAP; }

    public void setATK(int newATK){ ATK = newATK; }

    public void setDEF(int newDEF){ DEF = newDEF; }

    public void setSPD(int newSPD){ SPD = newSPD; }

    public void setDamage(int newDamage){ damage = newDamage; }

    //Getter methods
    public int getCurrentHP(){ return currentHP; }

    public int getMaxHP(){ return maxHP; }

    public int getCurrentAP(){ return currentAP; }

    public int getMaxAP(){ return maxAP; }

    public int getATK(){ return ATK; }

    public int getDEF(){ return DEF; }

    public int getSPD(){ return SPD; }

    public int getDamage(){ return damage; }

    public String toString(){
        return "currentHP = " + currentHP
                + "\nmaxHP = " + maxHP
                + "\ncurrentAP = " + currentAP
                + "\nmaxAP = " + maxAP
                + "\nATK = " + ATK
                + "\nDEF = " + DEF
                + "\nSPD + " + SPD
                + "\ndamage = " + damage;
    }
}
