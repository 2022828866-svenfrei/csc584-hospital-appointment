CREATE TABLE Expertise (
    expertiseId INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL
);

CREATE TABLE Account (
    accountId INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    expertiseIdFK INT,
    email VARCHAR(100) UNIQUE NOT NULL,
    fullName VARCHAR(100) NOT NULL,
    birthdate DATE NOT NULL,
    password VARCHAR(100) NOT NULL,
    isDoctor BOOLEAN WITH DEFAULT FALSE,
    CONSTRAINT FK_AccountExpertse FOREIGN KEY (expertiseIdFK)
    REFERENCES Expertise(expertiseId)
);

CREATE TABLE Absence (
    absenceId INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    accountIdFK INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE,
    CONSTRAINT FK_AbsenceAccount FOREIGN KEY (accountIdFK)
    REFERENCES Account(accountId)
);

CREATE TABLE Appointment (
    appointmentId INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    accountDoctorIdFK INT NOT NULL,
    accountPatientIdFK INT NOT NULL,
    date DATE NOT NULL,
    startTime TIME NOT NULL,
    duration TIME NOT NULL,
    CONSTRAINT FK_AppointmentDoctor FOREIGN KEY (accountDoctorIdFK)
    REFERENCES Account(accountId),
    CONSTRAINT FK_AppointmentPatient FOREIGN KEY (accountPatientIdFK)
    REFERENCES Account(accountId)
);