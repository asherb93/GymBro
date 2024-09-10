
## 🚀 About Me
Background: My name is Asher Bashian I am a Bsc in Computer Science on my last Year I made this app as my Final project in an Android development course.



# GymBro - Workout tracker (Android)

GymBro is an Workout app for tracking and saving workouts built in an Android native enviroment.


# Features:
- Start a new workout or start a saved workout
- Can save an existing workout as new or Overrwrite that same workout
- Personal rest timer which can be set to a default time
- 3 Different Timer sounds 
- 22 Exercises to choose from (Could add more but this for educational porpuse only)
- Stats screen to see personal records and more
- Workouts screen to save your workouts
- Exercises screen to see all available exercises on the app 
- Settings screen to handle user data (not much right now)
- Stats screen to view Personal records,time spent working out,favourite exercises and more
- User can choose to start an existing workout or Create a new one by pressing the Red button
- Upon finishing a workout user will be presented by the Workout summary which includes how many personal records he broke,total weight lifted,total time worked out and the uption to save the work out
- Upon completing a set a rest timer will begin (if timer was not set to 00:00) which will allow the user to prolong,shorten or skip the rest.


Some Features I didn't implement:
-
- User weight loss: altough this it is in general an fitness it would be too small of a feature to have. most users find MyFitnessPal or similliar apps to update their weights on I decided not to include weight in my app.
- Favourite Workout/Exercise we all have favourite exercises(Me personally really have to do Pull-ups and Bench presses) altought it would be nice to have once a user creates a workout with his favourite exercises He wouldn't probably create a new one with the same exercise.

Things that don't work properly
-
- Backend: Altough Uploading and Downloading data there might be a duplication on the Workouts screen after deleting a workout although I update the adapter about the Challenges
-I don't think its a bug but rather the recycler view gets updated with a new set or deleted and when that set isn't saved it will zerorize the whole sets except the ones who are not checked

# Footage
https://youtu.be/VVJf8AmdZC4

# Libararies I used
- Shared prefrences to save user prefrences
- Google Firebase for User authentication and Backend
- Material Alpha03 for the navbar
- Glide for image loading



## Feedback

If you have any feedback, please reach out to us at asherba93@gmail.com

