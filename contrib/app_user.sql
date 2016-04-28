drop TABLE app_user_user_profile;
drop TABLE app_user;

drop TABLE user_profile;

CREATE TABLE app_user
(
   id int IDENTITY (1 , 1),
   sso_id varchar(30) NOT NULL,
   password varchar(100) NOT NULL,
   attempts int default 0 not null,
   last_access datetime,
   last_Change_psw datetime,
   state varchar(30) NOT NULL default 'ACTIVE',
   utenti_id smallint,
   CODINTERNODONAT varchar(50)
);
CREATE UNIQUE INDEX PK_app_user ON app_user(id);
CREATE UNIQUE INDEX sso_id ON app_user(sso_id);
CREATE UNIQUE INDEX pk ON app_user(id);
CREATE INDEX utenti_id ON app_user(utenti_id);
CREATE INDEX CODINTERNODONAT ON app_user(CODINTERNODONAT);

CREATE TABLE user_profile
(
   id int PRIMARY KEY NOT NULL,
   type varchar(30) NOT NULL
);

CREATE UNIQUE INDEX PK_user_pro_3213E83F24D8399D ON user_profile(id);
CREATE UNIQUE INDEX type ON user_profile(type);
CREATE UNIQUE INDEX pk ON user_profile(id);

CREATE TABLE app_user_user_profile
(
   user_id int NOT NULL,
   user_profile_id int NOT NULL,
   CONSTRAINT PK_app_user PRIMARY KEY (user_id, user_profile_id)
);

ALTER TABLE app_user_user_profile
ADD CONSTRAINT FK_USER_PROFILE
FOREIGN KEY (user_profile_id)
REFERENCES user_profile(id)

CREATE UNIQUE INDEX PK_app_user_48EFE4898B63BE82 ON app_user_user_profile
(
  user_id,
  user_profile_id
)

CREATE INDEX FK_USER_PROFILE ON app_user_user_profile(user_profile_id)

INSERT INTO user_profile (id,type) VALUES (1,'ADMIN');
INSERT INTO user_profile (id,type) VALUES (2,'AVIS');
INSERT INTO user_profile (id,type) VALUES (3,'DONA');
INSERT INTO user_profile (id,type) VALUES (4,'OPERA');

insert into app_user (sso_id, password) values ('mauro', '$2a$10$onFQ.gJZ/XTslQo//KEYvumgWLuLQbwdO4ziKBnEo0S8.FCNgSs9C')

SELECT * FROM user_profile order by id

SELECT * FROM app_user_user_profile

insert into app_user_user_profile
SELECT
u.id,
up.id
FROM user_profile up
,app_user u
where u.sso_id = 'mauro'

SELECT * 
FROM app_user
where sso_id = 'mauro'

update app_user set password = '$2a$10$vdAPUkR1gtmH/bpVMGpKnOM.V0N6qZoowNrh5H9fRRZjlWvIMMqnm' where sso_id = 'mauro' 


SELECT * FROM utenti


   select
        this_.id as id1_0_1_,
        this_.ATTEMPTS as ATTEMPTS2_0_1_,
        this_.CODINTERNODONAT as CODINTER3_0_1_,
        this_.LAST_ACCESS as LAST_ACC4_0_1_,
        this_.LAST_CHANGE_PSW as LAST_CHA5_0_1_,
        this_.PASSWORD as PASSWORD6_0_1_,
        this_.SSO_ID as SSO_ID7_0_1_,
        this_.STATE as STATE8_0_1_,
        this_.UTENTI_ID as UTENTI_I9_0_1_,
        userprofil2_.USER_ID as USER_ID1_0_3_,
        userprofil3_.id as USER_PRO2_1_3_,
        userprofil3_.id as id1_4_0_,
        userprofil3_.TYPE as TYPE2_4_0_ 
    from
        APP_USER this_ 
    left outer join APP_USER_USER_PROFILE userprofil2_ on this_.id=userprofil2_.USER_ID 
    left outer join USER_PROFILE userprofil3_  on userprofil2_.USER_PROFILE_ID=userprofil3_.id 
    where
        this_.SSO_ID='mauro'



select
   this_.id as id1_4_0_,
   this_.TYPE as TYPE2_4_0_ 
from
   USER_PROFILE this_ 
order by
   this_.TYPE asc

