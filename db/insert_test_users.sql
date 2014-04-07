USE PaymentSystem ;

INSERT INTO user (email, password, name, surname, role)
VALUES ('tor@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'Torwald', 'Olafsen', 'ROLE_INTERNAL_USER'),
  ('venia@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'Veniamin', 'Nilva', 'ROLE_INTERNAL_USER'),
  ('girl_on_fire@gmail.com', '25d55ad283aa400af464c76d713c07ad', 'Joan', 'of Arc', 'ROLE_EXTERNAL_USER');

INSERT INTO permission (name)
VALUES ('create_user'),
  ('add_bank');

INSERT INTO user_has_permission (user_id, permission_id)
VALUES ((SELECT id FROM user WHERE email = 'tor@gmail.com') , (SELECT id FROM permission WHERE name = 'create_user')),
  ((SELECT id FROM user where email = 'tor@gmail.com'), (SELECT id FROM permission WHERE name = 'add_bank'));