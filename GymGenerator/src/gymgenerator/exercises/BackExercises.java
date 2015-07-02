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
public class BackExercises extends ExercisesBase {
    
        public BackExercises()
        {
            bigPart = true;
             exercises = new ArrayList<>();
exercises.add(new Attributes("Deadlift",4,10,2,2,0,2,4,false));
             exercises.add(new Attributes("Wiosłowanie",2,10,0,0,0,2,0,false));
              exercises.add(new Attributes("Podciaganie nadchwyt",0,10,0,2,0,10,4,false));
               exercises.add(new Attributes("Wiosłowanie sztangielką",4,10,2,2,0,2,4,false));
	exercises.add(new Attributes("Trójkat góra",0,10,0,0,0,2,0,false));
	exercises.add(new Attributes("trojkat dolem",0,10,0,2,0,2,2,false));
	exercises.add(new Attributes("unoszenie tulowia",0,10,0,0,0,0,0,false));
        exercises.add(new Attributes("Ściąganie atlas",0,10,0,0,3,0,0,true));
        
                
        }
}
