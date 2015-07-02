/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.exercises;

import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author lukasz
 */
public class TricepsExercises extends ExercisesBase {
    public TricepsExercises() {
        bigPart = false;
        exercises = new ArrayList<>();
        exercises.add(new Attributes("Wycisk wąsko",0,0,5,0,10,0,0,false));
        exercises.add(new Attributes("Atlas nadchwyt",0,0,5,0,10,0,0,true));
        exercises.add(new Attributes("Dipsy",0,0,5,0,10,0,0,true));
        exercises.add(new Attributes("Wyciskanie francuskie",0,0,0,0,10,0,0,false));
	exercises.add(new Attributes("Pompki Tyłem",0,0,2,2,10,0,0,false));
	exercises.add(new Attributes("Pompki wąsko",0,0,4,3,10,0,0,false));
    }
}
