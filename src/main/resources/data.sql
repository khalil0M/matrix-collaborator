DROP TABLE IF EXISTS collaborator;

  CREATE TABLE collaborator (
  mail_adresse VARCHAR(250) PRIMARY KEY
);

INSERT INTO collaborator (mail_adresse) VALUES
  ('adangote@sqli.com'),
  ('aelouardi@sqli.com'),
  ('ielouardi@sqli.com'),
  ('yelouardi@sqli.com');