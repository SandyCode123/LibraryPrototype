delete from library_books;
delete from library;
delete from books;

insert into Library(ID,BUILDING,CITY,COUNTRY,LANDMARK,PINCODE,STATE,CREATE_DATE_TIME,REGISTRATION_CODE,REGISTRATION_NAME,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Shanti Shopping', 'MUMBAI', 'IN','Indralok','401105','MH',now(),'LIB-1569-456','Joseph Library',now());
insert into Library(ID,BUILDING,CITY,COUNTRY,LANDMARK,PINCODE,STATE,CREATE_DATE_TIME,REGISTRATION_CODE,REGISTRATION_NAME,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Hypercity Shopping', 'Marathali', 'IN','Indralok','896547','KA',now(),'LIB-988-453','Maratha Library',now());
insert into Library(ID,BUILDING,CITY,COUNTRY,LANDMARK,PINCODE,STATE,CREATE_DATE_TIME,REGISTRATION_CODE,REGISTRATION_NAME,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Godegaon Shopping', 'Koramangalam', 'IN','Gurtern','386987','GJ',now(),'LIB-123-965','Gujju Library',now());
insert into Library(ID,BUILDING,CITY,COUNTRY,LANDMARK,PINCODE,STATE,CREATE_DATE_TIME,REGISTRATION_CODE,REGISTRATION_NAME,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Iskon Shopping', 'Bodakdev', 'IN','Quarter Road','896547','GJ',now(),'LIB-654-798','Shankarbhai Library',now());
insert into Library(ID,BUILDING,CITY,COUNTRY,LANDMARK,PINCODE,STATE,CREATE_DATE_TIME,REGISTRATION_CODE,REGISTRATION_NAME,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Orange Shopping', 'Borivali', 'IN','Indralok','401105','MH',now(),'LIB-765-354','SN Goenka Library',now());

insert into BOOKS(ID,AUTHOR_NAME,CREATE_DATE_TIME,LANGUAGE_ACCURACY,PRINT_QUALITY_RATING,STORY_RATING,TITLE,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Eckhart Tolle', now(),4,4,5,'Power Of Now',now());
insert into BOOKS(ID,AUTHOR_NAME,CREATE_DATE_TIME,LANGUAGE_ACCURACY,PRINT_QUALITY_RATING,STORY_RATING,TITLE,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'F. Scott', now(),5,3,5,'The great Gatsby',now());
insert into BOOKS(ID,AUTHOR_NAME,CREATE_DATE_TIME,LANGUAGE_ACCURACY,PRINT_QUALITY_RATING,STORY_RATING,TITLE,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Sally Room', now(),5,3,5,'Normal People',now());
insert into BOOKS(ID,AUTHOR_NAME,CREATE_DATE_TIME,LANGUAGE_ACCURACY,PRINT_QUALITY_RATING,STORY_RATING,TITLE,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'J.R.R Toi', now(),5,3,5,'Lord Of The Ring',now());
insert into BOOKS(ID,AUTHOR_NAME,CREATE_DATE_TIME,LANGUAGE_ACCURACY,PRINT_QUALITY_RATING,STORY_RATING,TITLE,UPDATE_DATE_TIME) values(HIBERNATE_SEQUENCE.nextval,'Viamdir', now(),5,3,5,'Lolita',now());

insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-1569-456',select ID from books where author_name='Eckhart Tolle' and title='Power Of Now');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-1569-456',select ID from books where author_name='Viamdir' and title='Lolita');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-1569-456',select ID from books where author_name='Sally Room' and title='Normal People');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-1569-456',select ID from books where author_name='F. Scott' and title='The great Gatsby');

insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-988-453',select ID from books where author_name='Eckhart Tolle' and title='Power Of Now');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-988-453',select ID from books where author_name='Viamdir' and title='Lolita');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-988-453',select ID from books where author_name='Sally Room' and title='Normal People');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-988-453',select ID from books where author_name='F. Scott' and title='The great Gatsby');

insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-123-965',select ID from books where author_name='Eckhart Tolle' and title='Power Of Now');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-123-965',select ID from books where author_name='Sally Room' and title='Normal People');

insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-765-354',select ID from books where author_name='Eckhart Tolle' and title='Power Of Now');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-765-354',select ID from books where author_name='Viamdir' and title='Lolita');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-765-354',select ID from books where author_name='Sally Room' and title='Normal People');
insert into library_books(library_id,books_id) values(select ID from library where registration_code='LIB-765-354',select ID from books where author_name='F. Scott' and title='The great Gatsby');

