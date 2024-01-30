-- By default, data.sql gets executed -> tables get created, before entities are processed
-- We 'prevent' the default by configuring spring.jpa.defer-datasource-initialization=true in 
-- application.properties file

-- Multiple insert rows syntax is from MySQL

INSERT INTO todo(ID, USERNAME, DESCRIPTION, TARGET_DATE, DONE)
VALUES
	(10001, 'in28minutes', 'GET AWS Certified', CURRENT_DATE(), false),
	(10002, 'in28minutes', 'Learn Dev Ops', CURRENT_DATE(), false),
	(10003, 'in28minutes', 'Learn Full Stack Development', CURRENT_DATE(), false),
	(10004, 'in28minutes', 'Learn Oracle SQL', CURRENT_DATE(), false);