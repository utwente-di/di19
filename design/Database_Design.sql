CREATE TABLE TESTi.Person(
	personID 	INTEGER,
	firstname	TEXT NOT NULL,
	surname		TEXT NOT NULL,
	password	TEXT NOT NULL,	
	PRIMARY KEY	(personID));

CREATE TABLE TESTi.Student(
	studentID	INTEGER,
	PRIMARY KEY	(studentID),
	FOREIGN KEY	(studentID) REFERENCES TESTi.Person(personID));

CREATE TABLE TESTi.Teacher(
	teacherID	INTEGER,
	administrator	BIT NOT NULL,
	PRIMARY KEY	(teacherID),
	FOREIGN KEY	(teacherID) REFERENCES TESTi.Person(personID));

CREATE TABLE TESTi.SuperModule(
	moduleCode	INTEGER,
	name		TEXT NOT NULL,	
	PRIMARY KEY	(ModuleCode));

CREATE TABLE TESTi.Module(
	moduleCode	INTEGER,
	year		INTEGER NOT NULL,
	PRIMARY KEY	(ModuleCode, year), 	
	FOREIGN KEY	(moduleCode) REFERENCES TESTi.SuperModule(moduleCode));

CREATE TABLE TESTi.Teaches(
	personID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	type		TEXT NOT NULL,
	FOREIGN KEY	(personID) REFERENCES TESTi.Person(personID),
	FOREIGN KEY	(moduleCode, year) REFERENCES TESTi.Module(moduleCode, year),
	PRIMARY KEY	(personID, moduleCode, year));
	
CREATE TABLE TESTi.Course(
	courseCode	INTEGER,
	name		TEXT NOT NULL,
	weight		INTEGER NOT NULL,
	year		INTEGER,
	PRIMARY KEY	(courseCode, year));

CREATE TABLE TESTi.hasCourses(
	moduleCode	INTEGER,
	courseCode	INTEGER,
	courseYear	INTEGER,
	PRIMARY KEY	(moduleCode, courseCode, courseYear),
	FOREIGN KEY	(moduleCode) REFERENCES TESTi.SuperModule(moduleCode),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES TESTi.Course(courseCode, year));

CREATE TABLE TESTi.Assignment(
	assignmentID	INTEGER,
	courseCode	INTEGER,
	courseyear	INTEGER,
	name		TEXT NOT NULL,
	isGradedAssignment
			BIT NOT NULL,
	weight		INTEGER NOT NULL,
	minimumResult	DECIMAL(2,1) NOT NULL,
	PRIMARY KEY	(assignmentID),
	FOREIGN KEY	(courseCode, courseYear) REFERENCES TESTi.Course(courseCode, year));

CREATE TABLE TESTi.AssignmentOccasion(
	occasionID	INTEGER,
	assignmentID	INTEGER,
	occasionDate	DATE,
	PRIMARY KEY	(occasionID),
	FOREIGN KEY	(assignmentID) references TESTi.Assignment(assignmentID));

CREATE TABLE TESTi.AssignmentResult(
	occasionID	INTEGER,
	studentID	INTEGER,
	result		DECIMAL(2,1) CHECK (result >=1.0 AND result <=10.0),
	PRIMARY KEY 	(occasionID, studentID),
	FOREIGN KEY	(occasionID) REFERENCES TESTi.AssignmentOccasion(occasionID),
	FOREIGN KEY	(studentID) REFERENCES TESTi.Student(studentID));
	

CREATE TABLE TESTi.ModuleResult(
	studentID	INTEGER,
	moduleCode	INTEGER,
	year		INTEGER,
	result		DECIMAL(2,1) CHECK (result >=1.0 AND result <=10.0),
	PRIMARY KEY	(studentID, moduleCode, year),
	FOREIGN KEY	(studentID) REFERENCES TESTi.Student(studentID),
	FOREIGN KEY	(moduleCode, year) REFERENCES TESTi.Module(moduleCode, year));
	