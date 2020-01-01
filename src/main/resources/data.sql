DROP TABLE IF EXISTS collaborator;
DROP TABLE IF EXISTS interview;

CREATE TABLE collaborator (
  mail_adresse VARCHAR(250) PRIMARY KEY
);

INSERT INTO collaborator (mail_adresse) VALUES
  ('adangote@sqli.com'),
  ('aelouardi@sqli.com'),
  ('ielouardi@sqli.com'),
  ('yelouardi@sqli.com');

CREATE TABLE interview (
  interview_id INT AUTO_INCREMENT  PRIMARY KEY,
  interview_title VARCHAR(250) NOT NULL,
  interview_description VARCHAR(250) NOT NULL,
  interview_date DATE DEFAULT current_date,
  collaborator_mail_adresse VARCHAR(250) NOT NULL
);

INSERT INTO interview (interview_title, interview_description, interview_description, interview_date, collaborator_mail_adresse) VALUES
  ('interview-01','interview-01','2020-01-01','adangote@sqli.com'),
  ('interview-02','interview-02','2020-01-02','aelouardi@sqli.com'),
  ('interview-03','interview-03','2020-01-03','ielouardi@sqli.com'),
  ('interview-04','interview-04','2020-01-04','yelouardi@sqli.com');