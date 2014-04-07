ALTER TABLE bank_account_of_bank DROP FOREIGN KEY fk_bank_account_of_bank_bank_account;
ALTER TABLE bank_account_of_bank CHANGE bank_account_number bank_account_id INT;

ALTER TABLE bank_account_of_company DROP FOREIGN KEY fk_bank_account_of_company_bank_account;
ALTER TABLE bank_account_of_company CHANGE bank_account_number bank_account_id INT;

ALTER TABLE active_payment DROP FOREIGN KEY fk_active_payment_account_from;
ALTER TABLE active_payment CHANGE account_number_from account_id_from INT;

ALTER TABLE cancelled_payment DROP FOREIGN KEY fk_cancelled_payment_account_from;
ALTER TABLE cancelled_payment CHANGE account_number_from account_id_from INT;

ALTER TABLE completed_payment DROP FOREIGN KEY fk_completed_payment_account_from;
ALTER TABLE completed_payment CHANGE account_number_from account_id_from INT;
ALTER TABLE completed_payment DROP FOREIGN KEY fk_completed_payment_account_to;
ALTER TABLE completed_payment CHANGE account_number_to account_id_to INT;

ALTER TABLE bank_account CHANGE number id INT AUTO_INCREMENT;
ALTER TABLE bank_account ADD
iban VARCHAR(34) NOT NULL ;

ALTER TABLE bank_account_of_bank ADD
CONSTRAINT fk_bank_account_of_bank_bank_account
FOREIGN KEY (bank_account_id)
REFERENCES bank_account (id);

ALTER TABLE bank_account_of_company ADD
CONSTRAINT fk_bank_account_of_company_bank_account
FOREIGN KEY (bank_account_id)
REFERENCES bank_account (id);

ALTER TABLE active_payment ADD
CONSTRAINT fk_active_payment_account_from
FOREIGN KEY (account_id_from)
REFERENCES bank_account (id);

ALTER TABLE cancelled_payment ADD
CONSTRAINT fk_cancelled_payment_account_from
FOREIGN KEY (account_id_from)
REFERENCES bank_account (id);

ALTER TABLE completed_payment ADD
CONSTRAINT fk_completed_payment_account_from
FOREIGN KEY (account_id_from)
REFERENCES bank_account (id);
ALTER TABLE completed_payment ADD
CONSTRAINT fk_completed_payment_account_to
FOREIGN KEY (account_id_to)
REFERENCES bank_account (id);

