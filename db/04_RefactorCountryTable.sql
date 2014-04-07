ALTER TABLE bank DROP FOREIGN KEY fk_bank_country;
ALTER TABLE company DROP FOREIGN KEY fk_company_country;
ALTER TABLE user DROP FOREIGN KEY fk_user_country;
DROP TABLE country;
CREATE TABLE  country (
code VARCHAR(3) NOT NULL,
name VARCHAR(100) NOT NULL,
code2 VARCHAR(2) NOT NULL,
PRIMARY KEY (code));
INSERT INTO paymentsystem.country (code, name, code2)
SELECT code, name, code2 FROM world.country;
ALTER TABLE bank ADD CONSTRAINT fk_bank_country
FOREIGN KEY (country_code)
REFERENCES country(code);
ALTER TABLE company ADD CONSTRAINT fk_company_country
FOREIGN KEY (country_code)
REFERENCES country(code);
ALTER TABLE user ADD CONSTRAINT fk_user_country
FOREIGN KEY (country_code)
REFERENCES country(code);