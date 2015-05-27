CREATE TABLE Person(
	personID 	INTEGER,
	firstname	TEXT NOT NULL,
	surname		TEXT NOT NULL,
	username	TEXT NOT NULL,
	password	TEXT NOT NULL,	
	PRIMARY KEY	(personID),
	UNIQUE		(username));


CREATE TABLE Student(
	studentID	INTEGER,
	PRIMARY KEY	(studentID),
	FOREIGN KEY	(studentID) REFERENCES Person(personID));

CREATE TABLE Docent(
	docentID	INTEGER,
	PRIMARY KEY	(docentID),
	FOREIGN KEY	(docentID) REFERENCES Person(personID));

CREATE TABLE SuperModule(
	moduleCode	INTEGER,
	name		TEXT NOT NULL,	
	PRIMARY KEY	(ModuleCode));

CREATE TABLE Module(
	moduleCode	INTEGER,
	year		INTEGER NOT NULL,
	PRIMARY KEY	(ModuleCode, year), 	
	FOREIGN KEY	(moduleCode) REFERENCES SuperModule(moduleCode));

CREATE TABLE Teaches(
	personID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	type		TEXT NOT NULL,
	FOREIGN KEY	(personID) REFERENCES Person(personID),
	FOREIGN KEY	(moduleCode, year) REFERENCES Module(moduleCode, year),
	PRIMARY KEY	(personID, moduleCode, year));
	
CREATE TABLE Course(
	courseCode	INTEGER,
	name		TEXT NOT NULL,
	weight		INTEGER NOT NULL,
	year		INTEGER,
	PRIMARY KEY	(courseCode, year));

CREATE TABLE hasCourses(
	moduleCode	INTEGER,
	courseCode	INTEGER,
	courseYear	INTEGER,
	PRIMARY KEY	(moduleCode, courseCode, courseYear),
	FOREIGN KEY	(moduleCode) REFERENCES SuperModule(moduleCode),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES Course(courseCode, year));

CREATE TABLE Assignment(
	assignmentID	INTEGER,
	courseCode	INTEGER,
	courseyear	INTEGER,
	name		TEXT NOT NULL,
	isGradedAssignment
			BIT NOT NULL,
	weight		INTEGER NOT NULL,
	PRIMARY KEY	(assignmentID, courseCode, courseYear),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES Course(courseCode, year),
	UNIQUE		(assignmentID));

CREATE TABLE AssignmentOccasion(
	assignmentID	INTEGER,
	occasionDate	DATE,
	PRIMARY KEY	(assignmentID, occasionDate),
	FOREIGN KEY	(assignmentID) references Assignment(assignmentID));

CREATE TABLE AssignmentResult(
	assignmentID	INTEGER,
	occasionDate	DATE,
	studentID	INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY 	(assignmentID, occasionDate, studentID),
	FOREIGN KEY	(assignmentID, occasionDate) REFERENCES AssignmentOccasion(assignmentID, occasionDate),
	FOREIGN KEY	(studentID) REFERENCES Student(studentID));

CREATE TABLE ModuleResult(
	studentID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY	(studentID, moduleCode, year),
	FOREIGN KEY	(studentID) REFERENCES Student(studentID),
	FOREIGN KEY	(moduleCode, year) REFERENCES Module(moduleCode, year));


/*
CREATE TABLE RequiredResult(
	requiredResultID
			INTEGER,
	minimumResult	DECIMAL(2,1),
	PRIMARY KEY	(requiredResultID));

CREATE TABLE ModuleParticipation(
	studentID	INTEGER,
	moduleID	INTEGER,
	PRIMARY KEY	(studentID, moduleID),
	FOREIGN KEY	(studentID) REFERENCES Student(studentID),
	FOREIGN KEY	(moduleID) REFERENCES Module(moduleID));

CREATE TABLE AssignmentResult(
	assignmentResultID	
			INTEGER,
	result		TEXT,
	PRIMARY KEY	(assignmentResultID),
	FOREIGN KEY	(assignmentResultID) REFERENCES ModuleParticipation (studentID, moduleID));

CREATE TABLE CourseResult(
	courseResultID	INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY	(courseResultID),
	FOREIGN KEY	(courseResultID) REFERENCES ModuleParticipation (studentID, moduleID));

CREATE TABLE ModuleResult(
	moduleResultID	INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY	(moduleResultID),
	FOREIGN KEY	(moduleResultID) REFERENCES ModuleParticipation (studentID, moduleID));


CREATE TABLE CourseRequiredResult(
	courseRequiredResultID
			INTEGER,
	superModuleID	INTEGER NOT NULL,
	PRIMARY KEY	(courseRequiredResultID),
	FOREIGN KEY	(courseRequiredResultID) REFERENCES RequiredResult(requiredResultID),
	FOREIGN KEY	(superModuleID) REFERENCES SuperModule(superModuleID));


	
CREATE TABLE AssignmentRequiredResult(
	assignmentRequiredResultID
			INTEGER,
	courseID	INTEGER NOT NULL,
	PRIMARY KEY	(assignmentRequiredResultID),
	FOREIGN KEY	(assignmentRequiredResultID) REFERENCES RequiredResult(requiredResultID),
	FOREIGN KEY	(courseID) REFERENCES Course(courseID));

*/


	



		