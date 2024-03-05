# Emergency-Service
Emergency Service (server, app for clients, app for officers)

UI patterns
- MVP
- MVVM

## Exercise 1. Develop a server application
Develop a Ktor server app for our new emergency service. This server will handle requests from both clients and store data about zones and incidents

**Project:**  
- Create a Ktor project

**Model and Storage:**  
- Add Exposed + H2 Database to the project
- Add entities describing a Zone, an Incident.
- Create in H2 corresponding tables, fill them with data

**API:**
- Add endpoint allows you get a list of zones
- Add endpoint allows you get a list of incidents
- Add endpoint allows you to get incidents by zone
- Add endpoint allows you to create and update an incident

**Logic:**
- Connect network and data layers with a domain layer
- Add the logic from the Day01, which will calculate where (to which zone) each new incident belongs depending on its coordinates. It must determine in which zone an incident has happened or a nearest zone to it

## Exercise 2. Develop a client app for citizens
Using our experience of the Day07, create an application for citizens with the MVVM architecture. The app will show a user list of zones and allow to declare an incident.

**Project:**
- Create an android project

**Model:**
- Add entities for different layers when needed

**Screens:**
- Add the main screen and its View Model. There should be a tab with list of incidents and a tab with list of zones. The tabs should be implemented with Fragments and have their corresponding ViewModels.
- On the "Zones" tab there is a list of zones. A list element shows us a name of a zone and has a color associated with a level that represents a probability of an incident
- If you click on a zone, you'll be navigated to a screen "Zone Details" (Show details of zone there). It's also a Fragment with a ViewModel
- On the "Incident" tab there is a list of incidents and a "Declare an Incident" button. A list element shows us a type of an incident and its status.
- If you click on an Incident, you'll be shown a dialog with incident details. 
- If you click on a "Declare an Incident" button, you'll be shown a dialog with fields to create a new incident. Reuse the dialog from the previous point. It should have different behavior depending on content: 
  - when showing details, the dialog must have immutable fields and a button "Close"
  - when declaring an incident, the dialog must have mutable fields and buttons "Declare"/"Cancel". 
- The empty incident is initialized with a NEW status
- Besides input fields for details, there are two fields for coordinates of this incident
- After creation a new incident is sent to the backend (and is added to a list on client)

**API**
- Create repositories for zones and incidents
- Add requests to get zones, get and create incidents

## Exercise 3. Develop a client app for officers
Create an application for officers with the MVP architecture. There should also be two tabs: Zones and Incidents. The tabs should be implemented with Fragments and have their corresponding Presenters.

**Project:**
- Create an android project

**Model:**
- Add entities for different layers when needed

**Screens:**
Add the main screen and its Presenter. There should be a tab with list of incidents and a tab with list of zones. The tabs should be implemented with Fragments and have their corresponding Presenters.
- On the "Zones" tab there is a list of zones. A list element shows us a name of a zone and has a color associated with a level that represents a probability of an incident
- If you click on a zone, you'll be navigated to a screen "Zone Details" (Show details of zone there). It's also a Fragment with a Presenter. Also, there is a list of incidents in this zone. Clicking on an incident shows you a dialog with incident details (see the tab with a list of incidents)
- On the "Incident" tab there is a list of incidents. A list element shows us a type of an incident and its status.
- If you click on an Incident, you'll be shown a dialog with incident details. Also, there is an ability to change an incident status (maybe a dropdown) 

**API:**
- Create repositories for zones and incidents
- Add requests to get list of zones, get incidents by a zone, get all incidents, change an incident status
