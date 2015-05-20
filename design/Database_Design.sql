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

CREATE TABLE RequiredResult(
	requiredResultID
			INTEGER,
	minimumResult	DECIMAL(2,1),
	PRIMARY KEY	(requiredResultID));

CREATE TABLE SuperModule(
	superModuleID	INTEGER,
	name		TEXT NOT NULL,
	moduleCode	TEXT NOT NULL,
	requiredResultID
			INTEGER,
	PRIMARY KEY	(superModuleID),
	FOREIGN KEY	(requiredResultID) REFERENCES RequiredResult(requiredResultID));

CREATE TABLE Module(
	moduleID	INTEGER,
	superModuleID	INTEGER NOT NULL,
	year		INTEGER NOT NULL,
	PRIMARY KEY	(moduleID), 	
	FOREIGN KEY	(superModuleID) REFERENCES SuperModule(superModuleID));
	

CREATE TABLE ModuleAttendant(
	personID	INTEGER,
	moduleID	INTEGER,
	type		TEXT NOT NULL,
	PRIMARY KEY	(personID, moduleID),
	FOREIGN KEY	(personID) REFERENCES Person(personID),
	FOREIGN KEY	(moduleID) REFERENCES Module(moduleID));

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

CREATE TABLE Course(
	courseID	INTEGER,
	name		TEXT NOT NULL,
	weight		INTEGER NOT NULL,
	courseRequiredResultID
			INTEGER NOT NULL,
	PRIMARY KEY	(courseID),
	FOREIGN KEY	(courseID) REFERENCES CourseRequiredResult(courseRequiredResultID));
	
CREATE TABLE AssignmentRequiredResult(
	assignmentRequiredResultID
			INTEGER,
	courseID	INTEGER NOT NULL,
	PRIMARY KEY	(assignmentRequiredResultID),
	FOREIGN KEY	(assignmentRequiredResultID) REFERENCES RequiredResult(requiredResultID),
	FOREIGN KEY	(courseID) REFERENCES Course(courseID));

CREATE TABLE Assignment(
	assignmentID	INTEGER,
	name		TEXT NOT NULL,
	assignmentDate	DATE NOT NULL,
	assignmentRequiredResultID
			INTEGER NOT NULL,
	isGradedAssignment
			BIT NOT NULL,
	weight		INTEGER NOT NULL,
	PRIMARY KEY	(assignmentID),
	FOREIGN KEY	(assignmentID) REFERENCES AssignmentRequiredResult(assignmentRequiredResultID));
	




	



		