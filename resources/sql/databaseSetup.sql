CREATE TABLE ActivityLog
(
    Username CHAR(30),
    Activity CHAR(30),
    EventDate DATE
);

CREATE TABLE PatientAccount
(
    CareCardNumber INTEGER PRIMARY KEY,
    FullName       CHAR(20),
    DOB            DATE,
    Username       CHAR(20) UNIQUE NOT NULL
);

CREATE TABLE Nurse
(
    NurseID   INTEGER PRIMARY KEY,
    NurseName CHAR(20) NOT NULL
);


CREATE TABLE Vaccine
(
    VacID   INTEGER PRIMARY KEY,
    VacName CHAR(30) UNIQUE NOT NULL,
    Type    CHAR(50) NOT NULL,
    Dosage  REAL     NOT NULL
);


CREATE TABLE VacTypeMOI
(
    Type              CHAR(50) PRIMARY KEY,
    MethodOfInjection CHAR(50) NOT NULL
);


CREATE TABLE VacDosage
(
    Dosage       REAL,
    Type         CHAR(50),
    Availability CHAR(1) NOT NULL,
    PRIMARY KEY (Dosage, Type)
);


CREATE TABLE AdministeredVaccineGivenToPatient
(
    EventID        INTEGER NOT NULL,
    NurseID        INTEGER NOT NULL,
    CareCardNumber INTEGER,
    VacDate        DATE,
    PRIMARY KEY (EventID, CareCardNumber),
    FOREIGN KEY (NurseID) REFERENCES Nurse (NurseID)
        ON DELETE CASCADE,
    FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount (CareCardNumber)
        ON DELETE CASCADE
);

CREATE TABLE Include
(
    EventID        INTEGER,
    CareCardNumber INTEGER,
    VacID        INTEGER,
    PRIMARY KEY (EventID, CareCardNumber),
    FOREIGN KEY (EventID, CareCardNumber) REFERENCES AdministeredVaccineGivenToPatient (EventID, CareCardNumber)
        ON DELETE CASCADE,
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE CASCADE
);


CREATE TABLE SideEffect
(
    SideEffectName CHAR(100) PRIMARY KEY,
    Treatment      CHAR(200) NOT NULL,
    Duration       INTEGER
);

CREATE TABLE VaccineHasSideEffect
(
    SideEffectName CHAR(100),
    VacID        INTEGER,
    PRIMARY KEY (SideEffectName, VacID),
    FOREIGN KEY (SideEffectName) REFERENCES SideEffect (SideEffectName)
        ON DELETE CASCADE,
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE SET NULL
);

CREATE TABLE RequiredBooster
(
    BoosterName CHAR(20),
    VacID     INTEGER,
    Dosage      REAL NOT NULL,
    PRIMARY KEY (BoosterName, VacID),
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE CASCADE
);

CREATE TABLE Covid19
(
    VacID              INTEGER PRIMARY KEY,
    WaitTimeUntilBooster INTEGER,
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE CASCADE
);

CREATE TABLE Flu
(
    VacID INTEGER PRIMARY KEY,
    MinAge  INTEGER,
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE CASCADE
);

CREATE TABLE ChickenPox
(
    VacID                     INTEGER PRIMARY KEY,
    PreviousShinglesVacRequired CHAR(1) NOT NULL,
    FOREIGN KEY (VacID) REFERENCES Vaccine (VacID)
        ON DELETE CASCADE
);


CREATE TABLE LoginInfo
(
    Username     CHAR(20) PRIMARY KEY,
    UserPassword CHAR(60),
    FOREIGN KEY (Username) REFERENCES PatientAccount (Username)
        ON DELETE CASCADE
);


CREATE TABLE AgeBracketLookup
(
    DOB        DATE PRIMARY KEY,
    AgeBracket CHAR(20)
);

CREATE TABLE Minor
(
    CareCardNumber  INTEGER,
    MinorCardNumber INTEGER PRIMARY KEY,
    FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount
        ON DELETE CASCADE
);


CREATE TABLE PreExistingCondition
(
    CareCardNumber INTEGER,
    Condition      CHAR(60),
    PRIMARY KEY (CareCardNumber, Condition),
    FOREIGN KEY (CareCardNumber) REFERENCES PatientAccount
        ON DELETE CASCADE
);

CREATE TABLE VaccineRecord
(
    CareCardNumber INTEGER,
    ID             INTEGER,
    EventID        INTEGER,
    PRIMARY KEY (ID),
    FOREIGN KEY (CareCardNumber, EventID) REFERENCES AdministeredVaccineGivenToPatient (CareCardNumber, EventID)
        ON DELETE CASCADE
);

CREATE TABLE Facility
(
    FacilityID   INTEGER PRIMARY KEY,
    FacilityName CHAR(50),
    Address      CHAR(100) UNIQUE
);


CREATE TABLE Distributor
(
    DistributorName        CHAR(50) PRIMARY KEY,
    DistributorPhoneNumber CHAR(50),
    DistributorEmail       CHAR(50)
);

CREATE TABLE Couriers
(
    Courier         CHAR(50) PRIMARY KEY,
    DistributorName CHAR(50),
    FOREIGN KEY (DistributorName) REFERENCES Distributor (DistributorName)
        ON DELETE SET NULL
);

CREATE TABLE Delivers
(
    DistributorName CHAR(50),
    FacilityID    INTEGER,
    OrderID         INTEGER,
    Quantity        INTEGER,
    TimeOfDelivery  DATE,
    PRIMARY KEY (DistributorName, FacilityID),
    FOREIGN KEY (DistributorName) REFERENCES Distributor (DistributorName)
        ON DELETE CASCADE,
    FOREIGN KEY (FacilityID) REFERENCES Facility (FacilityID)
        ON DELETE CASCADE
);


CREATE TABLE HappensIn
(
    EventID        INTEGER,
    CareCardNumber INTEGER,
    FacilityID   INTEGER,
    PRIMARY KEY (EventID, CareCardNumber, FacilityID),
    FOREIGN KEY (EventID, CareCardNumber) REFERENCES AdministeredVaccineGivenToPatient (EventID, CareCardNumber)
        ON DELETE CASCADE,
    FOREIGN KEY (FacilityID) REFERENCES Facility (FacilityID)
        ON DELETE CASCADE
);