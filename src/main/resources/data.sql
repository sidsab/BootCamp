INSERT INTO DEPARTMENT (ID, NAME, READ_ONLY, MANDATORY) VALUES (nextval('DEPARTMENT_SEQ'),'Organisation', true, true),
                                                            (nextval('DEPARTMENT_SEQ'),'IT', false, false),
                                                            (nextval('DEPARTMENT_SEQ'),'Sales', false, false),
                                                            (nextval('DEPARTMENT_SEQ'),'HR', false, false);


INSERT INTO EMPLOYEE (ID, NAME) VALUES (nextval('EMPLOYEE_SEQ'),'Siddharth'),
                                    (nextval('EMPLOYEE_SEQ'),'Akshay'),
                                    (nextval('EMPLOYEE_SEQ'),'Aditya'),
                                    (nextval('EMPLOYEE_SEQ'),'Anirudh');

INSERT INTO MAP_EMPLOYEE_DEPARTMENT(ID_EMPLOYEE, ID_DEPARTMENT) VALUES ((SELECT ID FROM EMPLOYEE WHERE NAME = 'Siddharth'),(SELECT ID FROM DEPARTMENT WHERE NAME='Organisation')),
                                                                        ((SELECT ID FROM EMPLOYEE WHERE NAME = 'Akshay'),(SELECT ID FROM DEPARTMENT WHERE NAME='Organisation')),
                                                                        ((SELECT ID FROM EMPLOYEE WHERE NAME = 'Aditya'),(SELECT ID FROM DEPARTMENT WHERE NAME='Organisation')),
                                                                         ((SELECT ID FROM EMPLOYEE WHERE NAME = 'Anirudh'),(SELECT ID FROM DEPARTMENT WHERE NAME='Organisation'));

