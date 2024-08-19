package com.example.gymbro.Utils;


import com.example.gymbro.Data.Workout;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.Models.ExerciseSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<String> getExercisesName() {
        ArrayList<ExerciseInfo> exerciseInfoArrayList = getExercisesInfo();
        ArrayList<String> exerciseNamesArrayList = new ArrayList<>();
        for(int i=0;i<exerciseInfoArrayList.size();i++)
        {
            exerciseNamesArrayList.add(exerciseInfoArrayList.get(i).getExerciseName());
        }
        return exerciseNamesArrayList;
    }

    //Upon creating a new workout user will receive 4 workout templates to test out
    public static ArrayList<Workout> getTemplateWorkouts(){

        ArrayList<Workout> templateWorkouts = new ArrayList<>();

        Workout chestTricepsWorkout = new Workout();
        chestTricepsWorkout.setWorkoutName("Chest Triceps Workout");
        chestTricepsWorkout.setSaved(true);
        Exercise benchPress = new Exercise("Bench Press");
        Exercise chestDips = new Exercise("Chest Dips");
        Exercise ropePushdown = new Exercise("Rope Pushdown");
        Exercise skullCrushers = new Exercise("Skull Crushers");
        ArrayList<Exercise> WorkoutChestTricepExercises = new ArrayList<>();
        WorkoutChestTricepExercises.add(benchPress);
        WorkoutChestTricepExercises.add(chestDips);
        WorkoutChestTricepExercises.add(ropePushdown);
        WorkoutChestTricepExercises.add(skullCrushers);
        chestTricepsWorkout.setExercises(WorkoutChestTricepExercises);

        Workout backBicepsWorkout = new Workout();
        backBicepsWorkout.setSaved(true);
        backBicepsWorkout.setWorkoutName("Back Biceps Workout");
        Exercise pullUps = new Exercise("Pullups");
        Exercise dumbbellRows = new Exercise("Dumbbell Rows");
        Exercise dummbbellCurl = new Exercise("Dumbbell Curl");
        Exercise hammerCurl = new Exercise("Hammer Curl");
        ArrayList<Exercise> WorkoutBackBicepsExercises = new ArrayList<>();
        WorkoutBackBicepsExercises.add(pullUps);
        WorkoutBackBicepsExercises.add(dumbbellRows);
        WorkoutBackBicepsExercises.add(dummbbellCurl);
        WorkoutBackBicepsExercises.add(hammerCurl);
        backBicepsWorkout.setExercises(WorkoutBackBicepsExercises);

        Workout legsShoulderAbsWorkout = new Workout();
        backBicepsWorkout.setSaved(true);
        legsShoulderAbsWorkout.setWorkoutName("Legs Shoulder Abs Workout");
        Exercise squats = new Exercise("Barbell Squats");
        Exercise lunges = new Exercise("Dumbbell Lunge");
        Exercise shoulderPress = new Exercise("Dumbbell Shoulder Press");
        Exercise lateralRaise = new Exercise("Dumbbell Lateral Raise");
        Exercise crunch = new Exercise("Crunch");
        Exercise bicycleCrunch = new Exercise("Bicycle Crunch");
        ArrayList<Exercise> legsShoulderAbsWorkoutExercises = new ArrayList<>();
        legsShoulderAbsWorkoutExercises.add(squats);
        legsShoulderAbsWorkoutExercises.add(lunges);
        legsShoulderAbsWorkoutExercises.add(shoulderPress);
        legsShoulderAbsWorkoutExercises.add(lateralRaise);
        legsShoulderAbsWorkoutExercises.add(crunch);
        legsShoulderAbsWorkoutExercises.add(bicycleCrunch);
        legsShoulderAbsWorkout.setExercises(legsShoulderAbsWorkoutExercises);

        templateWorkouts.add(chestTricepsWorkout);
        templateWorkouts.add(backBicepsWorkout);
        templateWorkouts.add(legsShoulderAbsWorkout);
        return templateWorkouts;
    }


    public static ArrayList<ExerciseInfo> getExercisesInfo() {
        ArrayList<ExerciseInfo> exerciseInfoArrayList = new ArrayList<>();
        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Bench Press")
                .setExerciseMuscleGroup("Chest")
                .setExerciseDescription("1. Setup:\n- Lie on a flat bench with your feet flat on the floor.\n- Grip the barbell slightly wider than shoulder-width apart.\n\n2. Execution:\n- Lift the barbell off the rack and hold it above your chest with arms fully extended.\n- Lower the barbell slowly until it touches your chest, keeping your elbows at around a 45-degree angle to your body.\n- Press the barbell back up to the starting position, exhaling as you push.")
                .setExerciseImage("https://i.pinimg.com/originals/08/60/37/08603700cb6365ab40466f4dd9d49e23.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Chest Dips")
                .setExerciseMuscleGroup("Chest")
                .setExerciseDescription("1. Setup:\n- Use parallel bars and position your hands shoulder-width apart.\n- Lift yourself up so that your arms are straight, and lean slightly forward.\n\n2. Execution:\n- Lower your body by bending your elbows, keeping them close to your body.\n- Continue lowering until your shoulders are slightly below your elbows.\n- Push yourself back up to the starting position, exhaling as you rise.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/06/Chest-Dips.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Push-Ups")
                .setExerciseMuscleGroup("Chest")
                .setExerciseDescription("1. Setup:\n- Start in a plank position with your hands placed slightly wider than shoulder-width apart.\n- Keep your body in a straight line from head to heels.\n\n2. Execution:\n- Lower your body until your chest almost touches the floor, keeping your elbows at a 45-degree angle.\n- Push through your hands to lift your body back to the starting position, exhaling as you push.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Push-Up.gif")
        );


        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Pullups")
                .setExerciseMuscleGroup("Back")
                .setExerciseDescription("1. Setup: Hang from a pull-up bar with an overhand grip, hands slightly wider than shoulder-width apart.\n2. Execution: Pull your body up until your chin is above the bar.\nLower yourself back down until your arms are fully extended.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Pull-up.gif")

        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Rows")
                .setExerciseMuscleGroup("Back")
                .setExerciseDescription("1. Setup:\n- Place one knee and hand on a bench, with the opposite foot on the floor and a dumbbell in the opposite hand.\n- Keep your back straight and parallel to the floor.\n\n2. Execution:\n- Pull the dumbbell towards your hip, squeezing your shoulder blade at the top of the movement.\n- Lower the dumbbell back down to the starting position, inhaling as you lower.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Dumbbell-Row.gif")
        );



        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Close-Grip Cable Row")
                .setExerciseMuscleGroup("Back")
                .setExerciseDescription("1. Setup:\n- Sit on the cable row machine with your feet firmly on the platform and knees slightly bent.\n- Grab the close-grip handle with both hands, keeping your back straight.\n\n2. Execution:\n- Pull the handle towards your abdomen, squeezing your shoulder blades together.\n- Slowly return the handle to the starting position, controlling the weight as you extend your arms.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/06/close-grip-cable-row.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Barbell Squats")
                .setExerciseMuscleGroup("Legs")
                .setExerciseDescription("1. Setup: Stand with feet shoulder-width apart and the barbell resting on your upper back.\n2. Execution: Lower your body by bending your knees and hips, keeping your back straight.\nDescend until your thighs are at least parallel to the floor.\nPush through your heels to return to the starting position.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/BARBELL-SQUAT.gif")
        );


        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Lunge")
                .setExerciseMuscleGroup("Legs")
                .setExerciseDescription("1. Setup:\n- Stand upright with a dumbbell in each hand, arms hanging by your sides.\n- Step forward with one leg, keeping your torso upright.\n\n2. Execution:\n- Lower your hips until both knees are bent at about 90 degrees, making sure your front knee does not go past your toes.\n- Push through the heel of your front foot to return to the starting position, exhaling as you rise.\n- Alternate legs with each repetition.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2023/09/dumbbell-lunges.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Goblet Squat")
                .setExerciseMuscleGroup("Legs")
                .setExerciseDescription("1. Setup:\n- Stand with your feet shoulder-width apart, holding a dumbbell vertically in front of your chest with both hands.\n- Keep your elbows close to your body.\n\n2. Execution:\n- Lower your body into a squat by bending your knees and pushing your hips back, keeping the dumbbell close to your chest.\n- Continue lowering until your thighs are parallel to the floor.\n- Push through your heels to return to the starting position, exhaling as you rise.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2023/01/Dumbbell-Goblet-Squat.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Barbell Military Press")
                .setExerciseMuscleGroup("Shoulders")
                .setExerciseDescription("1. Setup: Stand with feet shoulder-width apart and hold the barbell at shoulder height with an overhand grip.\n2. Execution: Press the barbell overhead until your arms are fully extended.\nLower the barbell back to shoulder height.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/07/Barbell-Standing-Military-Press.gif")
        );

        // Dumbbell Shoulder Press
        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Shoulder Press")
                .setExerciseMuscleGroup("Shoulders")
                .setExerciseDescription("1. Setup:\n- Sit on a bench with back support and hold a dumbbell in each hand at shoulder height, palms facing forward.\n- Keep your back straight and feet flat on the floor.\n\n2. Execution:\n- Press the dumbbells upward until your arms are fully extended, without locking your elbows.\n- Lower the dumbbells back to the starting position with control, inhaling as you lower.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Dumbbell-Shoulder-Press.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Lateral Raise")
                .setExerciseMuscleGroup("Shoulders")
                .setExerciseDescription("1. Setup:\n- Stand upright with a dumbbell in each hand, arms hanging by your sides, and palms facing inward.\n\n2. Execution:\n- Raise your arms out to the sides until they are parallel to the floor, keeping a slight bend in your elbows.\n- Lower the dumbbells back to the starting position with control, inhaling as you lower.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Dumbbell-Lateral-Raise.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Dumbbell Curl")
                .setExerciseMuscleGroup("Biceps")
                .setExerciseDescription("1. Setup:\n- Stand upright with a dumbbell in each hand, arms fully extended, and palms facing forward.\n\n2. Execution:\n- Curl the dumbbells up towards your shoulders, keeping your elbows close to your torso.\n- Lower the dumbbells back to the starting position with control, exhaling as you lower.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Dumbbell-Curl.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Hammer Curl")
                .setExerciseMuscleGroup("Biceps")
                .setExerciseDescription("1. Setup:\n- Stand upright with a dumbbell in each hand, arms fully extended, and palms facing inward.\n\n2. Execution:\n- Curl the dumbbells up towards your shoulders while keeping your palms facing each other.\n- Lower the dumbbells back to the starting position with control, exhaling as you lower.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Hammer-Curl.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Zottman Curl")
                .setExerciseMuscleGroup("Biceps")
                .setExerciseDescription("1. Setup:\n- Stand upright with a dumbbell in each hand, arms fully extended, and palms facing forward.\n\n2. Execution:\n- Curl the dumbbells up towards your shoulders. At the top, rotate your wrists so your palms face downward.\n- Slowly lower the dumbbells back to the starting position with your palms facing down, and then rotate your wrists back to the starting position.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/04/zottman-curl.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Seated Dumbbell Triceps Extension")
                .setExerciseMuscleGroup("Triceps")
                .setExerciseDescription("1. Setup:\n- Sit on a bench with back support, holding a dumbbell with both hands overhead.\n- Keep your elbows close to your ears and your arms fully extended.\n\n2. Execution:\n- Lower the dumbbell behind your head by bending your elbows, keeping them stationary.\n- Extend your arms back to the starting position, exhaling as you straighten your arms.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/06/Seated-Dumbbell-Triceps-Extension.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Rope Pushdown")
                .setExerciseMuscleGroup("Triceps")
                .setExerciseDescription("1. Setup:\n- Stand facing the cable machine with the rope attachment set at the highest position.\n- Grab the rope with both hands, palms facing each other, and pull it slightly towards your body.\n\n2. Execution:\n- Push the rope down and out, separating the ends of the rope as you extend your arms fully.\n- Slowly return to the starting position, keeping control of the weight.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/06/Rope-Pushdown.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Skull Crushers")
                .setExerciseMuscleGroup("Triceps")
                .setExerciseDescription("1. Setup:\n- Lie on a decline bench with a barbell held in a close grip.\n- Start with the barbell above your chest, arms fully extended.\n\n2. Execution:\n- Lower the barbell towards your forehead by bending your elbows, keeping them close to your body.\n- Extend your arms back to the starting position, exhaling as you press up.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2022/01/Decline-Close-Grip-Bench-To-Skull-Crusher.gif")
        );


        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Crunch")
                .setExerciseMuscleGroup("Abs")
                .setExerciseDescription("1. Setup:\n- Lie on your back with your knees bent and feet flat on the floor.\n- Place your hands behind your head or across your chest.\n\n2. Execution:\n- Lift your shoulders off the floor by contracting your abs, keeping your lower back pressed into the floor.\n- Lower back down with control, exhaling as you crunch.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2015/11/Crunch.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Bicycle Crunch")
                .setExerciseMuscleGroup("Abs")
                .setExerciseDescription("1. Setup:\n- Lie on your back with your hands behind your head and legs lifted, knees bent.\n- Start with your left elbow pointing towards your right knee.\n\n2. Execution:\n- Extend your left leg while bringing your right knee towards your chest, rotating your torso to touch your left elbow to your right knee.\n- Switch sides, alternating in a pedaling motion, exhaling as you twist.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Bicycle-Crunch.gif")
        );


        return exerciseInfoArrayList;
    }

    public static ArrayList<ExerciseInfo> getExercisesInfoByMuscleGroup(String muscleGroup){
        ArrayList<ExerciseInfo> exerciseInfoArrayList = getExercisesInfo();
        ArrayList<ExerciseInfo> muscleGroupExercises = new ArrayList<>();
        for(ExerciseInfo e:exerciseInfoArrayList){
            if(e.getExerciseMuscleGroup().equals(muscleGroup)){
                muscleGroupExercises.add(e);
            }
        }
        return muscleGroupExercises;
    }

    public static String getImageByExerciseName(String ExerciseName){
        ArrayList<ExerciseInfo> exerciseInfoArrayList = getExercisesInfo();
        for(ExerciseInfo e:exerciseInfoArrayList){
            if(e.getExerciseName().equals(ExerciseName)){
                return e.getExerciseImage();
            }
        }
        return null;
    }


}
