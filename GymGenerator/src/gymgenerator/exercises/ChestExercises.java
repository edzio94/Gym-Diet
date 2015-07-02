/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lukasz
 */
// ID, Legs,Back,Chest,Shoulders, tric, bic traps
public class ChestExercises extends ExercisesBase{

     public ChestExercises() {
         bigPart = true;
        exercises = new ArrayList<>();
        exercises.add(new Attributes("Wycisk sztangi",0,0,10,4,4,0,0,false));
         exercises.add(new Attributes("Wycisk sztangi skos góra",0,0,10,6,3,0,0,false));
          exercises.add(new Attributes("Wycisk sztangi skos w dół",0,0,10,3,5,0,0,false));
           exercises.add(new Attributes("Rozpietki",0,0,10,2,3,0,0,false));
            exercises.add(new Attributes("Za glowe",0,0,10,0,4,0,0,false));
	exercises.add(new Attributes("Pompki",0,0,10,0,4,0,0,false));
        
    }
    
}
