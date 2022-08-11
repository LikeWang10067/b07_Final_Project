# b07_Final_Project

This is a project that committed by 5 people as a group to develop an Android application that allows customers to schedule sports events online.
There are 2 types of Users, customers and admin.


> **API required: API must be 33.**
>
> **Please change API path inside build.gradle match your own environment**
>
> **For best viewing experience, please select virtual device that have screen size 5.0" or more.**
>
> **Daily Scrum Meeting detail is in each standup File.**

## Preset data in the database:

### Admin Account:
    
    Username:     admin    
    Passoword:    admin
    
    There is only one admin account defult in the database.
    User cannot SignUp as an admin account, instead only regular user account.

### User Account:
    
    Username:   user1 
    Password:   user1 
    
    Sample event that already joined by this user:      
        Havn't joint any event yet.
        
    username:   user2
    password:   user2
    
    Sample event that already joined by this user:      
        NBA event in New York

### Venue already in database:
    
    New York
    Toronto
    Vancouver

### Events already in database:
    
    NBA 
    in New York(Which is already joined by user1)
    
    Football
    in New York(No one joins at this moment)
    
    Basketball
    in Toronto(No one joins at this moment)

    We will auto delete the events that passes the start time.


## Back-end Contributions: 
```
Back-end, is handled by Caleb Zhang and Runyu Yue, we finished all the operations that communicate with Realtime Database.
We commit our code to Master branch.

Caleb Zhang Github username is: kz4ever
Runyu Yue Github username is: Yry01

In general, we split the whole back-end work into 50:50, half. 
Ie, we implemented all the methods in the do.java and make sure all the methods work as we expected.

Caleb Zhang and Runyu Yue finish the back-end portion work together and all of the debugging.
We had meetings everday and we discussed the logic and problems together.   

We carefully find out all of the unexpected behaviour, bugs through massive testing with front-end developers and Scrum master. 
Caleb Zhang and Runyu Yue thoroughly debugged all the bugs. 


```


## Front-end Contributions: 
```
Front-end, is handled by Felix Lee and Like Wang, we finished implementing login, signup, user, and admin activity pages for user stories.

Felix Lee Github username is: FelixLYY
Like Wang Github username is: LikeWang10067

In general, Like Wang writes delete/add venue, join/leave event, and Signup Activity while Felix writes all of the rest.

During the process, we communicate with Steven (who design the UI), Runyu, and Caleb (who is in charge of the back-end) to 
consistently write codes and fix bugs that are found during the testing stage. Felix is in charge of the majority of the work 
in the front-end while Like assists Felix while he is implementing the Activity pages.

We spent 2 days carefully finding out all of the unexpected behaviour, and bugs through massive testing with back-end developers and Scrum master. 
Felix Lee and Like Wang thoroughly debugged all the bugs in the front-end. 
```
## UI and Scrum Master Contributions:
```
Ruichen Sun's Github username: Ruichen-Sun

As the scrum master and UI designer for this project. I monitored and engage this project from start to finish. 

For the UI part, I did all the xml page design by myself. And successfully implement various components 
for Android page view to display. And I code AllEventActivity.java and co-code with Felix and Ricky for 
all other frond-end Activities in order to accomplish the interface. I also co-code with Caleb and Runyu 
about couple major method from the back-end code such as DeleteVenuce method and DeleteEvent method in order 
for the program to successfully running.

For the Scrum part, I organize daily project meetings and take minutes of the main meetings. 
During the meetings I communicate with each group member about their progress. And coordinate the parts 
that the group members need from other group members in order to complete the day's tasks.
Because we divided the project into Front-end and Back-end, I'm able to master the code of both parts of the team. 
And be the bridge between my team member.

Overall, I was deeply involved in the entire production process of this project. 
My code tied the whole project together and made this final version.
```
