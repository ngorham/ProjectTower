package net.ngorham.projecttower;

public class BagItem {
    //private variables
    private int id;
    //private String id;
    private String content;
    private String name;
    private int quantity;
    private int icon;
    private int type; //0 => HP, 1 => AP, 2 => State
    private double percentHP; //percent
    private double percentAP; //percent

    public BagItem(int id, String content, String name, int qty, int icon,
                   int type, double HP, double AP){
        this.id = id;
        this.content = content;
        this.name = name;
        this.quantity = qty;
        this.icon = icon;
        this.type = type;
        this.percentHP = HP;
        this.percentAP = AP;
    }

    public void setQuantity(int qty){ quantity = qty; }

    public int getId(){ return id; }

    public String getContent(){ return content; }

    public String getName(){ return name; }

    public int getQuantity(){ return quantity; }

    public int getIcon(){ return icon; }

    public int getType(){ return type; }

    public double getPercentHP(){ return percentHP; }

    public double getPercentAP(){ return percentAP; }
}
