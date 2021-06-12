INSERT ALL
    INTO PatientAccount VALUES (1000000000, 'John Smith', date '1978-09-23', 'GenericName')
    INTO PatientAccount VALUES (1000000001, 'Karen Kao', date '1940-10-20', 'Manager123')
    INTO PatientAccount VALUES (1000000002, 'Peter Parker', date '2001-04-06', 'Spiderman')
    INTO PatientAccount VALUES (1000000003, 'Dan Ham', date '2000-12-25', 'Breakfast')
    INTO PatientAccount VALUES (1000000004, 'Sam Wilson', date '1978-09-23', 'Falcon')
    INTO PatientAccount VALUES (1000000005, 'Steve Rogers', date '1918-07-04', 'CaptAmerica')
    INTO PatientAccount VALUES (1000000006, 'Tony Stark', date '1970-05-29', 'IronMan')
    INTO PatientAccount VALUES (2000000000, 'Child 1', date '2010-01-01', 'Child1')
    INTO PatientAccount VALUES (2000000001, 'Child 2', date '2010-01-01', 'Child2')
    INTO PatientAccount VALUES (2000000002, 'Child 3', date '2010-01-01', 'Child3')
    INTO PatientAccount VALUES (2000000003, 'Child 4', date '2010-01-01', 'Child4')
    INTO PatientAccount VALUES (2000000004, 'Child 5', date '2010-01-01', 'Child5')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO SideEffect (SideEffectName, Treatment, Duration) VALUES ('Swelling at injection site', 'Apply wet cloth/ice', 2)
INTO SideEffect (SideEffectName, Treatment, Duration) VALUES ('Fever', 'Drink lots of water', 3)
INTO SideEffect (SideEffectName, Treatment, Duration) VALUES ('Fatigue', 'Stay at home and rest', 2)
INTO SideEffect (SideEffectName, Treatment, Duration) VALUES ('Blood clots', 'Visit a doctor', 7)
INTO SideEffect (SideEffectName, Treatment, Duration) VALUES ('Muscle ache', 'Light exercise', 5)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (1001, 'Pfizer', 'mRNA', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (1002, 'AstraZeneca', 'mRNA', 0.4)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (1003, 'Moderna', 'mRNA', 0.5)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (1004, 'Janssen', 'Viral Vector', 0.5)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (1005, 'AntiCovid', 'mRNA', 0.5)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2000, 'Flulaval', 'Quadrivalent', 0.5)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2002, 'FLUAD', 'Quadrivalent', 0.5)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2004, 'Afluria', 'Viral Vector', 0.7)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2006, 'Flucelvax', 'mRNA', 0.7)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2008, 'Flublok', 'Quadrivalent', 0.2)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (2010, 'FluMist', 'Quadrivalent', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3000, 'VariVax', 'mRNA', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3005, 'ProQuad', 'mRNA', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3010, 'Zostavax', 'Viral Vector', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3015, 'Shingrix', 'Quadrivalent', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3020, 'NoChickenPox', 'Attenuated', 0.3)
    INTO Vaccine (VacID, VacName, Type, Dosage) VALUES (3025, 'Varicella', 'Attenuated', 0.7)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO VacDosage (Dosage, Type, Availability) VALUES (0.3, 'mRNA', 'Y')
    INTO VacDosage (Dosage, Type, Availability) VALUES (0.5, 'mRNA', 'Y')
    INTO VacDosage (Dosage, Type, Availability) VALUES (0.5, 'Viral Vector', 'N')
    INTO VacDosage (Dosage, Type, Availability) VALUES (0.5, 'Quadrivalent', 'Y')
    INTO VacDosage (Dosage, Type, Availability) VALUES (0.7, 'Attenuated', 'N')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO VacTypeMOI (Type, MethodOfInjection) VALUES ('mRNA', 'Intramuscular')
    INTO VacTypeMOI (Type, MethodOfInjection) VALUES ('Viral Vector', 'Intramuscular')
    INTO VacTypeMOI (Type, MethodOfInjection) VALUES ('Quadrivalent', 'Intradermal')
    INTO VacTypeMOI (Type, MethodOfInjection) VALUES ('Attenuated', 'Subcutaneous')
    INTO VacTypeMOI (Type, MethodOfInjection) VALUES ('Toxoid', 'Intramuscular')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Nurse (NurseID, NurseName) VALUES (12345, 'Alice')
    INTO Nurse (NurseID, NurseName) VALUES (24682, 'Betty')
    INTO Nurse (NurseID, NurseName) VALUES (34567, 'Carl')
    INTO Nurse (NurseID, NurseName) VALUES (48484, 'Davis')
    INTO Nurse (NurseID, NurseName) VALUES (51235, 'Erin')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Facility(FacilityID, FacilityName, Address) VALUES (101, 'FacilityA','2405 Wesbrook Mall, Vancouver, BC V6T 1Z3')
    INTO Facility(FacilityID, FacilityName, Address) VALUES (105, 'FacilityB','6308 Thunderbird Blvd, Vancouver, BC V6T 1Z4')
    INTO Facility(FacilityID, FacilityName, Address) VALUES (300, 'FacilityC','1055 Canada Pl, Vancouver, BC V6C 0C3')
    INTO Facility(FacilityID, FacilityName, Address) VALUES (480, 'FacilityD','1127 Sumas way Abbotsford, Vancouver BC')
    INTO Facility(FacilityID, FacilityName, Address) VALUES (744, 'FacilityE','Dentistry Bldg, 5940 University Blvd, Vancouver, BC V6T 1Z3')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Distributor(DistributorName, DistributorPhoneNumber, DistributorEmail) VALUES ('A', '800-822-2463', 'www.sanofipasteur.us/contact')
    INTO Distributor(DistributorName, DistributorPhoneNumber, DistributorEmail) VALUES ('B','855-358-8966','cs.flu@seqirus.com')
    INTO Distributor(DistributorName, DistributorPhoneNumber, DistributorEmail) VALUES ('C','404-639-3670','drugservice@cdc.gov')
    INTO Distributor(DistributorName, DistributorPhoneNumber, DistributorEmail) VALUES ('D','800-637-2590',NULL)
    INTO Distributor(DistributorName, DistributorPhoneNumber, DistributorEmail) VALUES ('E','877-246-8472','productsafety@ebsi.com')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO AdministeredVaccineGivenToPatient (EventID, NurseID, CareCardNumber, VacDate) VALUES (123456, 12345, 1000000000, date '2021-05-30')
    INTO AdministeredVaccineGivenToPatient (EventID, NurseID, CareCardNumber, VacDate) VALUES (858541, 24682, 1000000000, date '2021-03-22')
    INTO AdministeredVaccineGivenToPatient (EventID, NurseID, CareCardNumber, VacDate) VALUES (711128, 34567, 1000000004, date '2015-02-28')
    INTO AdministeredVaccineGivenToPatient (EventID, NurseID, CareCardNumber, VacDate) VALUES (468997, 48484, 2000000002, date '2009-05-07')
    INTO AdministeredVaccineGivenToPatient (EventID, NurseID, CareCardNumber, VacDate) VALUES (010228, 51235, 2000000002, date '2021-08-17')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO VaccineRecord (CareCardNumber, ID, EventID) VALUES (1000000000, 1, 123456)
    INTO VaccineRecord (CareCardNumber, ID, EventID) VALUES (1000000000, 2, 858541)
    INTO VaccineRecord (CareCardNumber, ID, EventID) VALUES (1000000004, 3, 711128)
    INTO VaccineRecord (CareCardNumber, ID, EventID) VALUES (2000000002, 4, 468997)
    INTO VaccineRecord (CareCardNumber, ID, EventID) VALUES (2000000002, 5, 010228)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO PreExistingCondition (CareCardNumber, Condition) VALUES (1000000006, 'Heart Disease')
    INTO PreExistingCondition (CareCardNumber, Condition) VALUES (2000000004, 'High Colestoral')
    INTO PreExistingCondition (CareCardNumber, Condition) VALUES (2000000000, 'Lung Cancer')
    INTO PreExistingCondition (CareCardNumber, Condition) VALUES (2000000003, 'High Blood Pressure')
    INTO PreExistingCondition (CareCardNumber, Condition) VALUES (1000000001, 'High Colestoral')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO AgeBracketLookup VALUES (date '1978-09-23', '30-44')
    INTO AgeBracketLookup VALUES (date '2001-04-06', '18-29')
    INTO AgeBracketLookup VALUES (date '2000-12-25', '18-29')
    INTO AgeBracketLookup VALUES (date '1970-05-29', '45-59')
    INTO AgeBracketLookup VALUES (date '1940-10-20', '60+')
    INTO AgeBracketLookup VALUES (date '1918-07-04', '60+')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Minor VALUES (1000000001, 2000000000)
    INTO Minor VALUES (1000000001, 2000000001)
    INTO Minor VALUES (1000000001, 2000000002)
    INTO Minor VALUES (1000000006, 2000000003)
    INTO Minor VALUES (1000000006, 2000000004)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO LoginInfo(Username, UserPassword) VALUES ('GenericName', 'password123')
    INTO LoginInfo(Username, UserPassword) VALUES ('Manager123', 'password123')
    INTO LoginInfo(Username, UserPassword) VALUES ('Spiderman', 'Password123')
    INTO LoginInfo(Username, UserPassword) VALUES ('Breakfast', '123Password')
    INTO LoginInfo(Username, UserPassword) VALUES ('Falcon', '123password')
    INTO LoginInfo(Username, UserPassword) VALUES ('CaptAmerica', '123@Password')
    INTO LoginInfo(Username, UserPassword) VALUES ('IronMan', 'Ppassword123')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Couriers(Courier, DistributorName) VALUES ('UPS', 'A')
    INTO Couriers(Courier, DistributorName) VALUES ('Fedex', 'B')
    INTO Couriers(Courier, DistributorName) VALUES ('Canada Post', 'C')
    INTO Couriers(Courier, DistributorName) VALUES ('Independent', 'D')
    INTO Couriers(Courier, DistributorName) VALUES ('Purolator', 'E')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO HappensIn (EventID, CareCardNumber, FacilityID) VALUES (123456, 1000000000, 101)
    INTO HappensIn (EventID, CareCardNumber, FacilityID) VALUES (858541, 1000000000, 105)
    INTO HappensIn (EventID, CareCardNumber, FacilityID) VALUES (711128, 1000000004, 300)
    INTO HappensIn (EventID, CareCardNumber, FacilityID) VALUES (468997, 2000000002, 480)
    INTO HappensIn (EventID, CareCardNumber, FacilityID) VALUES (010228, 2000000002, 744)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Delivers(DistributorName, FacilityID, OrderID, Quantity, TimeOfDelivery) VALUES ('A',101, 100, 1000, date '2021-05-31')
INTO Delivers(DistributorName, FacilityID, OrderID, Quantity, TimeOfDelivery) VALUES ('B',105, 300, 1000, date '2021-04-23')
INTO Delivers(DistributorName, FacilityID, OrderID, Quantity, TimeOfDelivery) VALUES ('C',300, 300, 1000, date '2021-03-08')
INTO Delivers(DistributorName, FacilityID, OrderID, Quantity, TimeOfDelivery) VALUES ('D',480,400, 1000, date '2021-05-31')
INTO Delivers(DistributorName, FacilityID, OrderID, Quantity, TimeOfDelivery) VALUES ('E',744, 500,1000, date '2021-05-30')
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Include (EventID, CareCardNumber, VacID) VALUES (123456, 1000000000, 1001)
INTO Include (EventID, CareCardNumber, VacID) VALUES (858541, 1000000000, 1002)
INTO Include (EventID, CareCardNumber, VacID) VALUES (711128, 1000000004, 1003)
INTO Include (EventID, CareCardNumber, VacID) VALUES (468997, 2000000002, 1004)
INTO Include (EventID, CareCardNumber, VacID) VALUES (010228, 2000000002, 3025)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO RequiredBooster (BoosterName, VacID, Dosage) VALUES ('Pfizer', 1001, 0.3)
    INTO RequiredBooster (BoosterName, VacID, Dosage) VALUES ('AstraZenca', 1002, 0.5)
    INTO RequiredBooster (BoosterName, VacID, Dosage) VALUES ('Moderna', 1003, 0.5)
    INTO RequiredBooster (BoosterName, VacID, Dosage) VALUES ('Varicella', 3025, 0.7)
    INTO RequiredBooster (BoosterName, VacID, Dosage) VALUES ('Flulaval', 2000, 0.3)
SELECT 1 FROM DUAL;



INSERT ALL
    INTO VaccineHasSideEffect (SideEffectName, VacID) VALUES ('Swelling at injection site', 1001)
    INTO VaccineHasSideEffect (SideEffectName, VacID) VALUES ('Fever', 1003)
    INTO VaccineHasSideEffect (SideEffectName, VacID) VALUES ('Fatigue', 2000)
    INTO VaccineHasSideEffect (SideEffectName, VacID) VALUES ('Blood clots', 1002)
    INTO VaccineHasSideEffect (SideEffectName, VacID) VALUES ('Muscle ache', 3025)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO ChickenPox (PreviousShinglesVacRequired, VacID) VALUES ('Y', 3000)
INTO ChickenPox (PreviousShinglesVacRequired, VacID) VALUES ('Y', 3005)
INTO ChickenPox (PreviousShinglesVacRequired, VacID) VALUES ('N', 3010)
INTO ChickenPox (PreviousShinglesVacRequired, VacID) VALUES ('N', 3015)
INTO ChickenPox (PreviousShinglesVacRequired, VacID) VALUES ('Y', 3020)
SELECT 1 FROM DUAL;


INSERT ALL
    INTO Flu (MinAge, VacID) VALUES (65, 2002)
INTO Flu (MinAge, VacID) VALUES (18, 2004)
INTO Flu (MinAge, VacID) VALUES (4, 2006)
INTO Flu (MinAge, VacID) VALUES (18, 2008)
INTO Flu (MinAge, VacID) VALUES (2, 2010)
SELECT 1 FROM DUAL;

INSERT ALL
    INTO Covid19 (WaitTimeUntilBooster, VacID) VALUES (10, 1002)
INTO Covid19 (WaitTimeUntilBooster, VacID) VALUES (NULL, 1004)
INTO Covid19 (WaitTimeUntilBooster, VacID) VALUES (4, 1003)
INTO Covid19 (WaitTimeUntilBooster, VacID) VALUES (3, 1001)
INTO Covid19 (WaitTimeUntilBooster, VacID) VALUES (5, 1005)
SELECT 1 FROM DUAL;