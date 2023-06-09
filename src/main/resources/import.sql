-- INSERT INTO tb_department(name) VALUES ('Gestão');
-- INSERT INTO tb_department(name) VALUES ('Informática');

-- INSERT INTO tb_user(username, email, password) VALUES ('Maria', 'maria@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Bob', 'bob@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Alex', 'alex@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Ana', 'ana@gmail.com', '1234');



INSERT INTO tb_user(id, username, email, password) VALUES ('8e0b352f-9adf-4157-96bb-ea0ab3362774','Ana', 'ana@gmail.com', '$2a$10$zXAWTuEBdjorolAHahQV8eF19hYnqKGkh71jhJn7aGyDHW2MVSzn6');
INSERT INTO tb_admin(username, email, password) VALUES ('ADM', 'ADM@gmail.com', '$2a$10$eH9.IsksiehkvZcWk2zgfuzVA29wPastZJ9.mbrgn.NwRkx4Bk.au');
INSERT INTO tb_attendant(username, email, password) VALUES ('Atendente1', 'att1@gmail.com', '$2a$10$UGkXHacQ2AlxLfC8pFP7jOay0rvqTdg/r/qdtBgkSSgrtQ7M2Mkqi');
INSERT INTO tb_doctor(name, specialty) VALUES ('Ricardo', 'Cardiologia');
INSERT INTO tb_doctor(name, specialty) VALUES ('Ana', 'Dermatologia');
INSERT INTO tb_doctor(name, specialty) VALUES ('José', 'Oftamologia');
INSERT INTO tb_query(doctor_id, user_id, date) VALUES (1, '8e0b352f-9adf-4157-96bb-ea0ab3362774', '2023-06-08 08:00:00.0000000')
