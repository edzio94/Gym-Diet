/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author lukasz
 */
public class Attributes {
    // Every excercise have from 0 to 10 point. higher, the better for muscle.
    // map for excercise how it's good for part of muscle
    public  Map muscles;
    // Checking if excersice can be made on gym or with home equipment.
    public  boolean gym;
    public  String info;
    public boolean replaced;
    public boolean bigPart;
    public String ID;
    public boolean isUsed;
    
    public  String getInfo()
    {
        return info;
    }
    
    public Attributes(String nazwa,int legs,int back, int chest, int shoulder,
            int triceps, int biceps, int traps,boolean onlyGym){
        isUsed = false;
        ID = nazwa;
        gym = onlyGym;
        muscles = new HashMap();
        muscles.put("Legs", legs);
        muscles.put("Back", back);
        muscles.put("Chest", chest);
        muscles.put("Shoulders", shoulder);
        muscles.put("Triceps", triceps);
        muscles.put("Biceps", biceps);
        muscles.put("Traps", traps);
        replaced = false;
        
        
    }
    
}
