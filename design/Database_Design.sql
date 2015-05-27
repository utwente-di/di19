CREATE TABLE "TESTi"."Person"(
	personID 	INTEGER,
	firstname	TEXT NOT NULL,
	surname		TEXT NOT NULL,
	username	TEXT NOT NULL,
	password	TEXT NOT NULL,	
	PRIMARY KEY	(personID),
	UNIQUE		(username));


CREATE TABLE "TESTi"."Student"(
	studentID	INTEGER,
	PRIMARY KEY	(studentID),
	FOREIGN KEY	(studentID) REFERENCES "TESTi"."Person"(personID));

CREATE TABLE "TESTi"."Docent"(
	docentID	INTEGER,
	PRIMARY KEY	(docentID),
	FOREIGN KEY	(docentID) REFERENCES "TESTi"."Person"(personID));

CREATE TABLE "TESTi"."SuperModule"(
	moduleCode	INTEGER,
	name		TEXT NOT NULL,	
	PRIMARY KEY	(ModuleCode));

CREATE TABLE "TESTi"."Module"(
	moduleCode	INTEGER,
	year		INTEGER NOT NULL,
	PRIMARY KEY	(ModuleCode, year), 	
	FOREIGN KEY	(moduleCode) REFERENCES "TESTi"."SuperModule"(moduleCode));

CREATE TABLE "TESTi"."Teaches"(
	personID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	type		TEXT NOT NULL,
	FOREIGN KEY	(personID) REFERENCES "TESTi"."Person"(personID),
	FOREIGN KEY	(moduleCode, year) REFERENCES "TESTi"."Module"(moduleCode, year),
	PRIMARY KEY	(personID, moduleCode, year));
	
CREATE TABLE "TESTi"."Course"(
	courseCode	INTEGER,
	name		TEXT NOT NULL,
	weight		INTEGER NOT NULL,
	year		INTEGER,
	PRIMARY KEY	(courseCode, year));

CREATE TABLE "TESTi"."hasCourses"(
	moduleCode	INTEGER,
	courseCode	INTEGER,
	courseYear	INTEGER,
	PRIMARY KEY	(moduleCode, courseCode, courseYear),
	FOREIGN KEY	(moduleCode) REFERENCES "TESTi"."SuperModule"(moduleCode),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES "TESTi"."Course"(courseCode, year));

CREATE TABLE "TESTi"."Assignment"(
	assignmentID	INTEGER,
	courseCode	INTEGER,
	courseyear	INTEGER,
	name		TEXT NOT NULL,
	isGradedAssignment
			BIT NOT NULL,
	weight		INTEGER NOT NULL,
	minimunResult	INTEGER NOT NULL,
	PRIMARY KEY	(assignmentID, courseCode, courseYear),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES "TESTi"."Course"(courseCode, year),
	UNIQUE		(assignmentID));

CREATE TABLE "TESTi"."AssignmentOccasion"(
	assignmentID	INTEGER,
	occasionDate	DATE,
	PRIMARY KEY	(assignmentID, occasionDate),
	FOREIGN KEY	(assignmentID) references "TESTi"."Assignment"(assignmentID));

CREATE TABLE "TESTi"."AssignmentResult"(
	assignmentID	INTEGER,
	occasionDate	DATE,
	studentID	INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY 	(assignmentID, occasionDate, studentID),
	FOREIGN KEY	(assignmentID, occasionDate) REFERENCES "TESTi"."AssignmentOccasion"(assignmentID, occasionDate),
	FOREIGN KEY	(studentID) REFERENCES "TESTi"."Student"(studentID));

CREATE TABLE "TESTi"."ModuleResult"(
	studentID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	result		DECIMAL(2,1),
	PRIMARY KEY	(studentID, moduleCode, year),
	FOREIGN KEY	(studentID) REFERENCES "TESTi"."Student"(studentID),
	FOREIGN KEY	(moduleCode, year) REFERENCES "TESTi"."Module"(moduleCode, year));


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


	



		