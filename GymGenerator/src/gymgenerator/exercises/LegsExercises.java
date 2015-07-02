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
public class LegsExercises extends ExercisesBase{
        public LegsExercises()
        {
            bigPart = true;
             exercises = new ArrayList<>();
             exercises.add(new Attributes("Przysiad",10,0,0,0,0,0,0,false));
              exercises.add(new Attributes("Przysiad przód",10,0,0,0,0,0,0,false));
               exercises.add(new Attributes("Maszyna",10,0,0,0,0,0,0,true));
                exercises.add(new Attributes("Wykroki sztanga",10,0,0,0,0,0,0,false));
		exercises.add(new Attributes("Maszyna leżąc przodem",10,0,0,0,0,0,0,true));
                exercises.add(new Attributes("Wykroki hantle",10,0,0,0,0,0,0,false));
        }
}
