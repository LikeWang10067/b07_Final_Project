# b07_Final_Project

This is a project that committed by 5 people as a group to develop an Android application that allows customers to schedule sports events online.
There are 2 types of Users, customers and admin.


**API required: API must be 33.**

**There is one admin account preset in the database.**

**User cannot SignUp as an admin account, but only regular user account.**

## Preset data in the database:

### Admin Account:
    
    Username:   admin                          
    Passoword:  admin

### User Account:
    
    Username:   user1
    Password:   user1
    
    Sample event that already joined by user1:      NBA event in New York

### Venue already in database:
    
    New York
    Toronto

### Events already in database:
    
    NBA 
    in New York(Which is already joined by user1)
    
    Basketball Game
    in Toronto(No one joins at this moment)

    We will auto delete the events that passes the start time.


### Back-end Contributions: 
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


### Front-end Contributions: 
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
