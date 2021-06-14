# VacBase: An Oracle Database for Vaccine Tracking
![Logo for VacBase on a Banner.](data/Logo2.png?raw=true "VacBase Banner")

- Note 1
- Note 2
- Note 3

# Running the Program

# What does VacBase do? 

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
"SELECT DISTINCT ? FROM Vaccine, VacDosage WHERE Availability = 'Y'"
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
"SELECT AV.CareCardNumber AS CCN, COUNT(*) AS COUNT 
 FROM AdministeredVaccineGivenToPatient AV 
 GROUP BY AV.CareCardNumber HAVING COUNT(*) > 
 (SELECT AVG(COUNT(CareCardNumber)) 
  FROM VaccineRecord GROUP BY CareCardNumber)";

```

Division:
``` sql
"SELECT p.FullName, p.CareCardNumber FROM PatientAccount p 
 WHERE NOT EXISTS (SELECT * from Vaccine v 
 WHERE NOT EXISTS (SELECT i.VacID FROM Include i 
 WHERE p.CareCardNumber=i.CareCardNumber AND v.VacID=i.VacID))";

```

# ER/D Modelling
![Entity Relationship Diagram](data/ERDiagram.png?raw=true "Entity Relationship Diagram")
This image is is best viewed in another tab.

# About us!
| Team Member   | Github        |
| ------------- | ------------- |
| Alice         | [https://github.com/alicekanng](https://github.com/alicekanng "link title")|
| Jonathan      | [https://github.com/JonAndYu](https://github.com/JonAndYu "link title")    |
| Davis         | [https://github.com/d-x-s](https://github.com/d-x-s "link title")          |