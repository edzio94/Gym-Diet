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
public class ShoulderExercises extends ExercisesBase {
     //public List<Attributes> exercises;
     //final boolean bigPart = false;
    
    public ShoulderExercises() {
        bigPart = false;
        exercises = new ArrayList<>();
  exercises.add(new Attributes("Wycisk sztangielek",0,0,0,10,2,0,2,false));
        exercises.add(new Attributes("Żołnierskie",0,0,0,10,4,0,0,false));
        exercises.add(new Attributes("Na boki",0,0,10,10,4,0,0,false));
        exercises.add(new Attributes("Przed siebie",0,0,4,10,4,0,2,false));
	exercises.add(new Attributes("Za siebie",0,0,2,10,2,0,4,false));
	exercises.add(new Attributes("sztanga w opadzie za dupa",0,0,2,10,4,0,2,false));
        
    
    }
}
