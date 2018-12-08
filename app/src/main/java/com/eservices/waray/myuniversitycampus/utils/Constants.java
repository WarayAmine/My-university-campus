package com.eservices.waray.myuniversitycampus.utils;

import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Constants {
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.eservices.waray.myuniversitycampus";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
    public static final String LOCATION_LAT = PACKAGE_NAME +
            ".LOCATION_LAT";
    public static final String LOCATION_LNG = PACKAGE_NAME +
            ".LOCATION_LNG";

    public static List<Problem> staticProblems = new ArrayList<Problem>() {
        {
            add( new Problem("Avenue Carl Gauss, 59650 Villeneuve-d'Ascq","Arbre à tailler en face de l'entrée principale de SUP-SUAIO",50.610339,3.137678, Problem.ProblemType.ARBRE_TAILLER,false, new Date(), new Date()));
            add( new Problem("59650 Villeneuve-d'Ascq","Enlevez cet arbre svp",50.610339,3.137678, Problem.ProblemType.ARBRE_ABATTRE,false, new Date(), new Date()));
            add( new Problem("Hall Vallin Avenue Paul Langevin, 59650 Villeneuve-d'Ascq","URGENT svp, ça bloque le chemin",50.609829,3.135982, Problem.ProblemType.DETRITUS,false, new Date(), new Date()));
            add( new Problem("RU Barrois Avenue Paul Langevin, 59650 Villeneuve-d'Ascq","", 50.611875, 3.143274 , Problem.ProblemType.HAIE_TAILLER,false, new Date(), new Date()));
            add( new Problem("le chemin qui mène à P1, 59650 Villeneuve-d'Ascq","Des mauvaises herbes à enlever", 50.610905, 3.141709, Problem.ProblemType.MAUVAISE_HERBE,false, new Date(), new Date()));
            add( new Problem("Parking du bâtiment A3 59650 Villeneuve-d'Ascq","AUTRES",50.608873, 3.141000, Problem.ProblemType.AUTRE,false, new Date(), new Date()));
            add( new Problem("Unnamed Road, 59650 Villeneuve-d'Ascq","Arbre à tailler le plus tôt possible",50.610327, 3.139323, Problem.ProblemType.ARBRE_TAILLER,false, new Date(), new Date()));
            add( new Problem("Parking Polytech 59650 Villeneuve-d'Ascq","c'est urgent",50.607463, 3.137898, Problem.ProblemType.HAIE_TAILLER,false, new Date(), new Date()));
            add( new Problem("Avenue Paul Langevin, 59650 Villeneuve-d'Ascq","parking labo de mécanique",50.608805, 3.135252, Problem.ProblemType.DETRITUS,false, new Date(), new Date()));
            add( new Problem("Bureau d'accueil des étudiants internationaux 2 Avenue Jean Perrin, 59650 Villeneuve-d'Ascq","Des mauvaises herbes à enlever s'il vous plaît",50.611187, 3.141915, Problem.ProblemType.MAUVAISE_HERBE,false, new Date(), new Date()));
        }
    };

}
