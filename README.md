# VacBase: An Oracle Database for Vaccine Tracking
![Logo for VacBase on a Banner.](data/Logo2.png?raw=true "VacBase Banner")


# Running the Program
Our database instance is hosted on the UBC servers. If you are a student, you can connect to UBC's Oracle service with:  

- Username: ORA_[*Your CWL*]
- Password: a[*Your Student Number*]

If you are using this method, you also need to be tunneled into the server via SSH. You can use XShell for this purpose,
or any other tunneling method.
You can simply start the program by running the 'Main' class in the project.ui folder. 

# What does VacBase do? 
VacBase was created as a solution for monitoring the entire Vaccination supply chain. 
From the distributor who ships the order, to the nurse that administers the Vaccine, 
VacBase presents a simple way to store data on every aspect of a Vaccine’s life cycle. 
Backend functionality was accomplished with JDBC and Oracle Database. 
The frontend was created with the JavaFX GUI toolkit.  

VacBase allows for both admin access and patient access, which is fantastic for streamlining the Vaccination process. 
An admin (like a nurse) is free to update information about Patients and store data about each Vaccination 
that takes place. 
Valuable data like Care Card numbers are hidden away from Patients who access the database, 
who are only able to view their own accounts and update only their personal information. 
Furthermore, patients can add details about any pre-existing conditions they may have, 
which is important for a nurse to know beforehand. 
VacBase was inspired by the recent COVID-19 crisis and was implemented more as a thought experiment 
than a serious application for real use.

# Queries
Insertion:
``` sql
TODO
```

Deletion:
``` sql
TODO
```

Update:
``` sql
TODO
```

Selection:
``` sql
SELECT * FROM PatientAccount, AgeBracketLookup 
WHERE PatientAccount.DOB = ageBracketLookup.DOB
```

Projection:
``` sql
SELECT DISTINCT ? FROM Vaccine, VacDosage WHERE Availability = 'Y'
The user can choose which attribute they wish to display. 
```

Join:
``` sql
“SELECT 
   VaccineRecord.CareCardNumber, 
   VaccineRecord.ID, 
   VaccineRecord.EventID, 
   AdministeredVaccineGivenToPatient.NurseID, 
   AdministeredVaccineGivenToPatient.VacDate, 
   Include.VacID, 
   Vaccine.VacName, 
   HappensIn.FacilityID, 
   Facility.FacilityName, 
   Nurse.NurseName FROM VaccineRecord 
INNER JOIN AdministeredVaccineGivenToPatient ON 
   VaccineRecord.EventID = AdministeredVaccineGivenToPatient.EventID and VaccineRecord.CareCardNumber
   = AdministeredVaccineGivenToPatient.CareCardNumber 
INNER JOIN Include ON 
   AdministeredVaccineGivenToPatient.EventID = Include.EventID 
INNER JOIN HappensIn ON 
   AdministeredVaccineGivenToPatient.EventID = HappensIn.EventID 
INNER JOIN NURSE ON 
   AdministeredVaccineGivenToPatient.NurseID = Nurse.NurseID 
INNER JOIN FACILITY ON 
   HappensIn.FacilityID = Facility.FacilityID 
INNER JOIN VACCINE ON 
   Include.VacID = Vaccine.VacID;”

```

Aggregation:
``` sql
“SELECT COUNT(*) FROM VaccineRecord;”
```

Nested Aggregation with Group-By:
``` sql
 SELECT AV.CareCardNumber AS CCN, COUNT(*) AS COUNT 
 FROM AdministeredVaccineGivenToPatient AV 
 GROUP BY AV.CareCardNumber HAVING COUNT(*) > 
 (SELECT AVG(COUNT(CareCardNumber)) 
  FROM VaccineRecord GROUP BY CareCardNumber);
```

Division:
``` sql
 SELECT p.FullName, p.CareCardNumber FROM PatientAccount p 
 WHERE NOT EXISTS (SELECT * from Vaccine v 
 WHERE NOT EXISTS (SELECT i.VacID FROM Include i 
 WHERE p.CareCardNumber=i.CareCardNumber AND v.VacID=i.VacID));
```

# ER/D Modelling
![Entity Relationship Diagram](data/ERDiagram.png?raw=true "Entity Relationship Diagram")
This image is is best viewed in another tab.

# What we could improve or add:
1) Scalability. Database instances on UBC servers are locked to a maximum of 2 users simultaneously. 
   If we were to seriously implement this project and use it for a real-world applications, we would need to find
   a paid database hosting service that allows multiple connections. 

# About us!
| Team Member   | Github        |
| ------------- | ------------- |
| Alice         | [https://github.com/alicekanng](https://github.com/alicekanng "link title")|
| Jonathan      | [https://github.com/JonAndYu](https://github.com/JonAndYu "link title")    |
| Davis         | [https://github.com/d-x-s](https://github.com/d-x-s "link title")          |

