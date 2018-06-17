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
    private int id;
    private String name;
    private String createdOn;
    private String lastModified;
    private int currentHP;
    private int maxHP;
    private int currentAP;
    private int maxAP;
    private int level;
    private int EXP; //Experience
    private int ATK; //Attack
    private int DEF; //Defense
    private int SPD; //Speed
    private int damage;
    private String location;
    private int clock;

    //Default Constructor
    public Player(){
        this(0, "Name", "createdOn", "lastModified",
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, "location", 0);
    }

    //Loaded Constructor
    public Player(int id, String name, String createdOn, String lastModified,
                  int currentHP, int maxHP, int currentAP, int maxAP, int level, int EXP,
                  int ATK, int DEF, int SPD, int damage, String location, int clock){
        setId(id);
        setName(name);
        setCreatedOn(createdOn);
        setLastModified(lastModified);
        setCurrentHP(currentHP);
        setMaxHP(maxHP);
        setCurrentAP(currentAP);
        setMaxAP(maxAP);
        setLevel(level);
        setEXP(EXP);
        setATK(ATK);
        setDEF(DEF);
        setSPD(SPD);
        setDamage(damage);
        setLocation(location);
        setClock(clock);
    }

    //Setter methods
    public void setId(int newId){ id = newId; }

    public void setName(String newName){ name = newName; }

    public void setCreatedOn(String newCreatedOn){ createdOn = newCreatedOn; }

    public void setLastModified(String newLastModified){ lastModified = newLastModified; }

    public void setCurrentHP(int newHP){ currentHP = newHP; }

    public void setMaxHP(int newHP){ maxHP = newHP; }

    public void setCurrentAP(int newAP){ currentAP = newAP; }

    public void setMaxAP(int newAP){ maxAP = newAP; }

    public void setLevel(int newLevel){ level = newLevel; }

    public void setEXP(int newEXP){ EXP = newEXP; }

    public void setATK(int newATK){ ATK = newATK; }

    public void setDEF(int newDEF){ DEF = newDEF; }

    public void setSPD(int newSPD){ SPD = newSPD; }

    public void setDamage(int newDamage){ damage = newDamage; }

    public void setLocation(String newLocation){ location = newLocation; }

    public void setClock(int newClock){ clock = newClock; }

    //Getter methods
    public int getId(){return id; }

    public String getName(){ return name; }

    public String getCreatedOn(){ return createdOn; }

    public String getLastModified(){ return lastModified; }

    public int getCurrentHP(){ return currentHP; }

    public int getMaxHP(){ return maxHP; }

    public int getCurrentAP(){ return currentAP; }

    public int getMaxAP(){ return maxAP; }

    public int getLevel(){ return level; }

    public int getEXP(){ return EXP; }

    public int getATK(){ return ATK; }

    public int getDEF(){ return DEF; }

    public int getSPD(){ return SPD; }

    public int getDamage(){ return damage; }

    public String getLocation(){ return location; }

    public int getClock(){ return clock; }

    public String toString(){
        return "id = " + id
                + "\nname = " + name
                + "\ncreatedOn = " + createdOn
                + "\nlastModified = " + lastModified
                + "\ncurrentHP = " + currentHP
                + "\nmaxHP = " + maxHP
                + "\ncurrentAP = " + currentAP
                + "\nmaxAP = " + maxAP
                + "\nlevel = " + level
                + "\nEXP = " + EXP
                + "\nATK = " + ATK
                + "\nDEF = " + DEF
                + "\nSPD + " + SPD
                + "\ndamage = " + damage
                + "\nlocation = " + location
                + "\nclock = " + clock;
    }
}
