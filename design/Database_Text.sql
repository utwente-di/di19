INSERT INTO TESTi.Person
VALUES	('00001', 'Tim', 'de Klerk', 'wachtwoord'),
	('00002', 'Frank', 'Groeneveld', '1234'),
	('00003', 'Kevin', 'Booijink', 'lala'),
	('69426', 'Mark', '`t Hart', 'mark'),
	('22222', 'Leon', 'de Vries', 'vroemvroem'),
	('09876', 'Guus', 'Kuiper', 'jala'),
	('12345', 'Klaas', 'Sikkel', 'sikkel');

INSERT INTO TESTi.Student 
VALUES	('00001'),
	('00002'),
	('00003'),
	('69426');

INSERT INTO TESTi.Teacher
VALUES	('22222', '1'),
	('09876', '0'),
	('12345', '1');

INSERT INTO TESTi.SuperModule
VALUES	('1234567890', 'Data & Informatie'),
	('0987654321', 'Getallenmanagement');

INSERT INTO TESTi.Module
VALUES	('1234567890', '2014'),
	('1234567890', '2015'),
	('1234567890', '2001'),
	('0987654321', '2014');

INSERT INTO TESTi.Teaches
VALUES	('22222', '1234567890', '2014', 'Studentassistent'),
	('22222', '0987654321', '2014', 'Hulpmedewerker'),
	('12345', '1234567890', '2015', 'Docent'),
	('12345', '1234567890', '2014', 'Docent'),
	('09876', '0987654321', '2014', 'Studentassistent');

INSERT INTO TESTi.Course
VALUES	('55656', 'Web Application Programming', '1', '2014'),
	('55656', 'Web Application Programming', '1', '2015'),
	('99999', 'Epibreren kun je leren', '8', '2018');

INSERT INTO TESTi.hasCourses
VALUES 	('1234567890', '55656', '2014'),
	('1234567890', '99999', '2018'),
	('0987654321', '99999', '2018'),
	('0987654321', '55656', '2015');

INSERT INTO TESTi.Assignment
VALUES	('1', '55656', '2014', 'Toest 1', '1', '3', '6.2'),
	('4435', '55656', '2014', 'Herkansing 1','1', '3', '6.9'),
	('8898', '99999', '2018', 'Opdracht', '0', '1', '5.5'),
	('3', '55656', '2015', 'Tentamen', '1', '2', '4.5'),
	('2', '55656', '2014', 'Extra herkansing', '1', '3', '1.3');

INSERT INTO TESTi.AssignmentOccasion
VALUES 	('1', '1', '03-03-14'),
	('2', '1', '04-03-14'),
	('3', '4435', '08-03-14'),
	('4', '8898', '12-06-18'),
	('5', '3', '2015-08-08'),
	('38', '3', '2015-12-08');

INSERT INTO TESTi.AssignmentResult
VALUES	('1', '00001', '9.8'),
	('1', '00002', '8.8'),
	('1', '00003', '1.8'),
	('2', '00003', '8.8'),
	('5', '69426', '9.4'),
	('5', '00001', '9.9'),
	('38', '00002', '5.5'),
	('4', '00002', '7.5'),
	('3', '00003', '8.5');

INSERT INTO TESTi.ModuleResult
VALUES	('00001', '1234567890', '2014', '5.7'),
	('00001', '1234567890', '2015', '6.7'),
	('00002', '1234567890', '2015', '8.9'),
	('69426', '1234567890', '2015', '6.1'),
	('00003', '1234567890', '2015', '5.5'),
	('00003', '1234567890', '2001', '9.0'),
	('69426', '1234567890', '2014', '8.2'),
	('00001', '0987654321', '2014', '3.3'),
	('69426', '0987654321', '2014', '4.3');
	

	


	