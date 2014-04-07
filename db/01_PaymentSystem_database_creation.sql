CREATE SCHEMA IF NOT EXISTS PaymentSystem DEFAULT CHARACTER SET utf8 ;
USE PaymentSystem ;

-- -----------------------------------------------------
-- Table country
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS country (
  code VARCHAR(3) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (code));

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  role ENUM('ROLE_ANONYMOUS_USER','ROLE_EXTERNAL_USER', 'ROLE_INTERNAL_USER') NOT NULL,
  country_code VARCHAR(3),
  city VARCHAR(100),
  zip_code VARCHAR(20),
  address VARCHAR(200),
  phone_number VARCHAR(20),
  active TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  INDEX fk_user_country_idx (country_code),
  UNIQUE INDEX unique_email_idx (email),
  CONSTRAINT fk_user_country
    FOREIGN KEY (country_code)
    REFERENCES country (code));

-- -----------------------------------------------------
-- Table permission
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permission (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table user_has_permission
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_has_permission (
  user_id INT NOT NULL,
  permission_id INT NOT NULL,
  PRIMARY KEY (user_id, permission_id),
  INDEX fk_user_has_permission_permission_idx (permission_id),
  INDEX fk_user_has_permission_user_idx (user_id),
  CONSTRAINT fk_user_has_permission_user
    FOREIGN KEY (user_id)
    REFERENCES user (id),
  CONSTRAINT fk_user_has_permission_permission
    FOREIGN KEY (permission_id)
    REFERENCES permission (id));

-- -----------------------------------------------------
-- Table bank
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bank (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  active TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id));

-- -----------------------------------------------------
-- Table mechanism
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS mechanism (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  our_commission DECIMAL(5,2) NOT NULL DEFAULT 0,
  fee DECIMAL(5,2) NOT NULL DEFAULT 0,
  bank_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_mechanism_bank_idx (bank_id),
  CONSTRAINT fk_mechanism_bank
    FOREIGN KEY (bank_id)
    REFERENCES bank (id));

-- -----------------------------------------------------
-- Table company
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS company (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  mechanism_id INT NOT NULL,
  country_code VARCHAR(3) NOT NULL,
  city VARCHAR(100) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone_number VARCHAR(20),
  active TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id),
  INDEX fk_company_country_idx (country_code),
  INDEX fk_company_mechanism_idx (mechanism_id),
  CONSTRAINT fk_company_country
    FOREIGN KEY (country_code)
    REFERENCES country (code),
  CONSTRAINT fk_company_mechanism
    FOREIGN KEY (mechanism_id)
    REFERENCES mechanism (id));

-- -----------------------------------------------------
-- Table currency
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS currency (
  code INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (code));

-- -----------------------------------------------------
-- Table bank_proposal
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bank_proposal (
  id INT NOT NULL AUTO_INCREMENT,
  bank_id INT NOT NULL,
  currency_code INT NOT NULL,
  commission DECIMAL(5,2) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  INDEX fk_bank_proposal_bank_idx (bank_id),
  INDEX fk_bank_proposal_currency_idx (currency_code),
  CONSTRAINT fk_bank_proposal_bank
    FOREIGN KEY (bank_id)
    REFERENCES bank (id),
  CONSTRAINT fk_bank_proposal_currency
    FOREIGN KEY (currency_code)
    REFERENCES currency (code));

-- -----------------------------------------------------
-- Table bank_account
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bank_account (
  number INT NOT NULL,
  bank_proposal_id INT NOT NULL,
  active TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (number),
  INDEX fk_account_bank_proposal_idx (bank_proposal_id),
  CONSTRAINT fk_account_bank_proposal
    FOREIGN KEY (bank_proposal_id)
    REFERENCES bank_proposal (id));

-- -----------------------------------------------------
-- Table user_in_company
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_in_company (
  user_id INT NOT NULL,
  company_id INT NOT NULL,
  PRIMARY KEY (user_id, company_id),
  INDEX fk_user_in_company_company_idx (company_id),
  INDEX fk_user_in_company_user_idx (user_id),
  CONSTRAINT fk_user_in_company_user
    FOREIGN KEY (user_id)
    REFERENCES user (id),
  CONSTRAINT fk_user_in_company_company
    FOREIGN KEY (company_id)
    REFERENCES company (id));

-- -----------------------------------------------------
-- Table completed_payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS completed_payment (
  id INT NOT NULL AUTO_INCREMENT,
  account_number_from INT NOT NULL,
  user_created INT NOT NULL,
  date_created TIMESTAMP NOT NULL,
  amount_from DECIMAL(19,4) NOT NULL,
  account_number_to INT NOT NULL,
  user_accepted INT NOT NULL,
  date_accepted TIMESTAMP NOT NULL,
  amount_to DECIMAL(19,4) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_completed_payment_account_from_idx (account_number_from),
  INDEX fk_completed_payment_account_to_idx (account_number_to),
  INDEX fk_completed_payment_user_created_idx (user_created),
  INDEX fk_completed_payment_user_accepted_idx (user_accepted),
  CONSTRAINT fk_completed_payment_account_from
    FOREIGN KEY (account_number_from)
    REFERENCES bank_account (number),
  CONSTRAINT fk_completed_payment_account_to
    FOREIGN KEY (account_number_to)
    REFERENCES bank_account (number),
  CONSTRAINT fk_completed_payment_user_created
    FOREIGN KEY (user_created)
    REFERENCES user (id),
  CONSTRAINT fk_completed_payment_user_accepted
    FOREIGN KEY (user_accepted)
    REFERENCES user (id));

-- -----------------------------------------------------
-- Table binded_payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS binded_payment (
  main_payment_id INT NOT NULL,
  binded_payment_id INT NOT NULL,
  type VARCHAR(50) NOT NULL,
  PRIMARY KEY (main_payment_id, binded_payment_id),
  INDEX fk_binded_payment_payment_binded_idx (binded_payment_id),
  CONSTRAINT fk_binded_payment_payment_main
    FOREIGN KEY (main_payment_id)
    REFERENCES completed_payment (id),
  CONSTRAINT fk_binded_payment_payment_binded
    FOREIGN KEY (binded_payment_id)
    REFERENCES completed_payment (id));

-- -----------------------------------------------------
-- Table active_payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS active_payment (
  id INT NOT NULL AUTO_INCREMENT,
  account_number_from INT NOT NULL,
  user_created INT NOT NULL,
  date_created TIMESTAMP NOT NULL,
  amount_from DECIMAL(19,4) NOT NULL,
  company_to INT NOT NULL,
  amount_to DECIMAL(19,4) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_active_payment_account_from_idx (account_number_from),
  INDEX fk_active_payment_user_created_idx (user_created),
  INDEX fk_active_payment_company_to_idx (company_to),
  CONSTRAINT fk_active_payment_account_from
    FOREIGN KEY (account_number_from)
    REFERENCES bank_account (number),
  CONSTRAINT fk_active_payment_user_created
    FOREIGN KEY (user_created)
    REFERENCES user (id),
  CONSTRAINT fk_active_payment_company_to
    FOREIGN KEY (company_to)
    REFERENCES company (id));

-- -----------------------------------------------------
-- Table cancelled_payment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cancelled_payment (
  id INT NOT NULL AUTO_INCREMENT,
  account_number_from INT NOT NULL,
  user_created INT NOT NULL,
  date_created TIMESTAMP NOT NULL,
  amount_from DECIMAL(19,4) NOT NULL,
  user_cancelled INT NOT NULL,
  date_cancelled TIMESTAMP NOT NULL,
  company_to INT NOT NULL,
  amount_to DECIMAL(19,4) NOT NULL,
  reason TEXT NULL,
  PRIMARY KEY (id),
  INDEX fk_cancelled_payment_account_from_idx (account_number_from),
  INDEX fk_cancelled_payment_user_created_idx (user_created),
  INDEX fk_cancelled_payment_user_cancelled_idx (user_cancelled),
  INDEX fk_cancelled_cancelled_payment_company_to_idx (company_to),
  CONSTRAINT fk_cancelled_payment_account_from
    FOREIGN KEY (account_number_from)
    REFERENCES bank_account (number),
  CONSTRAINT fk_cancelled_payment_user_created
    FOREIGN KEY (user_created)
    REFERENCES user (id),
  CONSTRAINT fk_cancelled_payment_user_cancelled
    FOREIGN KEY (user_cancelled)
    REFERENCES user (id),
  CONSTRAINT fk_cancelled_payment_company_to
    FOREIGN KEY (company_to)
    REFERENCES company (id));

-- -----------------------------------------------------
-- Table bank_account_of_company
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bank_account_of_company (
  company_id INT NOT NULL,
  bank_account_number INT NOT NULL,
  INDEX fk_bank_account_of_company_company_idx (company_id),
  INDEX fk_bank_account_of_company_bank_account_idx (bank_account_number),
  PRIMARY KEY (company_id, bank_account_number),
  CONSTRAINT fk_bank_account_of_company_company
    FOREIGN KEY (company_id)
    REFERENCES company (id),
  CONSTRAINT fk_bank_account_of_company_bank_account
    FOREIGN KEY (bank_account_number)
    REFERENCES bank_account (number));

-- -----------------------------------------------------
-- Table bank_account_of_bank
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bank_account_of_bank (
  bank_id INT NOT NULL,
  bank_account_number INT NOT NULL,
  PRIMARY KEY (bank_id, bank_account_number),
  INDEX fk_bank_account_of_bank_bank_account_idx (bank_account_number),
  CONSTRAINT fk_bank_account_of_bank_bank
    FOREIGN KEY (bank_id)
    REFERENCES bank (id),
  CONSTRAINT fk_bank_account_of_bank_bank_account
    FOREIGN KEY (bank_account_number)
    REFERENCES bank_account (number));