ALTER TABLE bank DROP iban;
ALTER TABLE bank ADD
country_code VARCHAR(3) NOT NULL;
ALTER TABLE bank ADD CONSTRAINT fk_bank_country
FOREIGN KEY (country_code)
REFERENCES country(code);