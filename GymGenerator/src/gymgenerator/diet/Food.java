/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.diet;

/**
 *
 * @author lukasz
 */
public class Food {
    public String Name;
    public float b;
    public float t;
    public float w;
    public float kcal;
    public final int ID;
    public Food(int id,String name, float b, float t, float w, float kcal)
    {
        ID = id;
        Name = name;
        this.b = b;
        this.t = t;
        this.w = w;
        this.kcal = kcal;
        
    }
}
