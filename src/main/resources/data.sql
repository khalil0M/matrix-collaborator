DROP TABLE IF EXISTS collaborator;
DROP TABLE IF EXISTS profile;

CREATE TABLE profile (
  profile_id INT AUTO_INCREMENT  PRIMARY KEY,
  profile_title VARCHAR(250) NOT NULL,
  profile_description VARCHAR(250) NOT NULL
);

INSERT INTO profile (profile_title, profile_description) VALUES
  ('Developpeur Junior', 'Developpeur Junior 3ans'),
  ('Developpeur Sénior', 'Developpeur Sénior 5ans'),
  ('Business Analyste Junior', 'Business Analyste Junior 1an(s)'),
  ('Business Analyste Sénior', 'Business Analyste Sénior 5ans');

  CREATE TABLE collaborator (
  mail_adresse VARCHAR(250) PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  birth_date DATE DEFAULT NULL,
  profile_id INT NOT NULL
);

INSERT INTO collaborator (mail_adresse,first_name, last_name, birth_date,profile_id) VALUES
  ('adangote@sqli.com','Aliko','Dangote','1973-11-17',1),
  ('aelouardi@sqli.com','Amira','El ouardi','2015-09-26',1),
  ('ielouardi@sqli.com','Ibrahim', 'El ouardi','2016-10-23',2),
  ('yelouardi@sqli.com','Yassine', 'El ouardi','1986-09-14',2);