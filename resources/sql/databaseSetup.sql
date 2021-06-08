DROP TABLE VaccineHasSideEffect;
DROP TABLE RequiredBooster;
DROP TABLE Covid19;
DROP TABLE Flu;
DROP TABLE ChickenPox;
DROP TABLE LoginInfo;
DROP TABLE Minor;
DROP TABLE AgeBracketLookup;
DROP TABLE PreExistingCondition;
DROP TABLE VaccineRecord;
DROP TABLE Couriers;
DROP TABLE Delivers;
DROP TABLE HappensIn;
DROP TABLE Distributor;
DROP TABLE Facility;
DROP TABLE VacTypeMOI;
DROP TABLE VacDosage;
DROP TABLE SideEffect;
DROP TABLE Include;
DROP TABLE Vaccine;
DROP TABLE AdministeredVaccineGivenToPatient;
DROP TABLE PatientAccount;
DROP TABLE Nurse;

CREATE TABLE Branch (
                        branch_id integer not null PRIMARY KEY,
                        branch_name varchar2(20) not null,
                        branch_addr varchar2(50),
                        branch_city varchar2(20) not null,
                        branch_phone integer

);



CREATE TABLE PatientAccount (
                                CareCardNumber INTEGER PRIMARY KEY,
                                FullName CHAR(20),
                                DOB DATE,
                                Username CHAR(20) UNIQUE NOT NULL
);

CREATE TABLE Nurse (
                       NurseID CHAR(20) PRIMARY KEY,
                       NurseName CHAR(20) NOT NULL
);


CREATE TABLE Vaccine (
                         VacName CHAR(50) PRIMARY KEY,
                         Type CHAR(50) NOT NULL,
                         Dosage REAL NOT NULL
);


CREATE TABLE VacTypeMOI (
                            Type CHAR(50) PRIMARY KEY,
                            MethodOfInjection CHAR(50) NOT NULL
);


CREATE TABLE VacDosage (
                           Dosage REAL,
                           Type CHAR(50),
                           Availability CHAR(1) NOT NULL,
                           PRIMARY KEY (Dosage, Type)
);


CREATE TABLE AdministeredVaccineGivenToPatient (
                                                   EventID CHAR(20) NOT NULL,
                                                   NurseID CHAR(20) NOT NULL,
                                                   CareCardNumber INTEGER,
                                                   VacDate DATE,
                                                   PRIMARY KEY (EventID, CareCardNumber),
                                                   FOREIGN KEY (NurseID) REFERENCES Nurse (NurseID)
                                                       ON DELETE CASCADE,
                                                   FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount (CareCardNumber)
                                                       ON DELETE CASCADE
);



CREATE TABLE Include (
                         EventID CHAR(20),
                         CareCardNumber INTEGER,
                         VacName CHAR(50),
                         PRIMARY KEY (EventID, CareCardNumber, VacName),
                         FOREIGN KEY (EventID, CareCardNumber) REFERENCES AdministeredVaccineGivenToPatient (EventID, CareCardNumber)
                             ON DELETE CASCADE,
                         FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                             ON DELETE SET NULL
);




CREATE TABLE SideEffect (
                            SideEffectName CHAR(100) PRIMARY KEY,
                            Treatment CHAR(200) NOT NULL,
                            Duration INTEGER
);


CREATE TABLE VaccineHasSideEffect (
                                      SideEffectName CHAR(100),
                                      VacName CHAR(50),
                                      PRIMARY KEY (SideEffectName, VacName),
                                      FOREIGN KEY (SideEffectName) REFERENCES SideEffect(SideEffectName)
                                          ON DELETE CASCADE,
                                      FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                                          ON DELETE SET NULL
);


CREATE TABLE RequiredBooster (
                                 BoosterName CHAR(20),
                                 VacName CHAR(50),
                                 Dosage REAL NOT NULL,
                                 PRIMARY KEY (BoosterName, VacName),
                                 FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                                     ON DELETE CASCADE
);


CREATE TABLE Covid19 (
                         VacName CHAR(50) PRIMARY KEY,
                         WaitTimeUntilBooster INTEGER,
                         FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                             ON DELETE CASCADE
);


CREATE TABLE Flu (
                     VacName CHAR(50) PRIMARY KEY,
                     MinAge INTEGER,
                     FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                         ON DELETE CASCADE
);


CREATE TABLE ChickenPox (
                            VacName CHAR(50) PRIMARY KEY,
                            PreviousShinglesVacRequired CHAR(1) NOT NULL,
                            FOREIGN KEY (VacName) REFERENCES Vaccine (VacName)
                                ON DELETE CASCADE
);


CREATE TABLE LoginInfo (
                           Username CHAR(20) PRIMARY KEY,
                           UserPassword CHAR(60),
                           FOREIGN KEY (Username) REFERENCES PatientAccount (Username)
);


CREATE TABLE AgeBracketLookup (
                                  DOB DATE PRIMARY KEY,
                                  AgeBracket CHAR(20)
);


CREATE TABLE Minor (
                       CareCardNumber INTEGER,
                       MinorCardNumber INTEGER PRIMARY KEY,
                       FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount
                           ON DELETE CASCADE
);



CREATE TABLE PreExistingCondition (
                                      CareCardNumber INTEGER,
                                      Condition CHAR(60),
                                      PRIMARY KEY(CareCardNumber, Condition),
                                      FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount
                                          ON DELETE CASCADE
);


CREATE TABLE VaccineRecord (
                               CareCardNumber INTEGER,
                               ID INTEGER,
                               EventID CHAR(20),
                               PRIMARY KEY (CareCardNumber, ID),
                               FOREIGN KEY (CareCardNumber,EventID) REFERENCES AdministeredVaccineGivenToPatient(CareCardNumber, EventID)
                                   ON DELETE CASCADE
);


CREATE TABLE Facility (
                          FacilityName CHAR(200) PRIMARY KEY,
                          Address CHAR(100) UNIQUE
);


CREATE TABLE Distributor(
                            DistributorName CHAR(50) PRIMARY KEY,
                            DistributorPhoneNumber CHAR(50),
                            DistributorEmail CHAR(50)
);

CREATE TABLE Couriers(
                         Courier CHAR(50) PRIMARY KEY,
                         DistributorName CHAR(50),
                         FOREIGN KEY (DistributorName) REFERENCES Distributor(DistributorName)
                             ON DELETE SET NULL
);

CREATE TABLE Delivers (
                          DistributorName CHAR(50),
                          FacilityName CHAR(200),
                          OrderID INTEGER,
                          Quantity INTEGER,
                          TimeOfDelivery DATE,
                          PRIMARY KEY (DistributorName, FacilityName),
                          FOREIGN KEY (DistributorName) REFERENCES Distributor(DistributorName)
                              ON DELETE CASCADE,
                          FOREIGN KEY (FacilityName) REFERENCES Facility(FacilityName)
                              ON DELETE CASCADE
);




CREATE TABLE HappensIn (
                           EventID CHAR(20),
                           CareCardNumber INTEGER,
                           FacilityName CHAR(200),
                           PRIMARY KEY (EventID, CareCardNumber, FacilityName),
                           FOREIGN KEY (EventID, CareCardNumber) REFERENCES AdministeredVaccineGivenToPatient(EventID, CareCardNumber)
                               ON DELETE CASCADE,
                           FOREIGN KEY (FacilityName) REFERENCES Facility (FacilityName)
                               ON DELETE CASCADE
);




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

