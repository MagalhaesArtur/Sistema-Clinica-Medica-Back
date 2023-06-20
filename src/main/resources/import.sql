-- INSERT INTO tb_department(name) VALUES ('Gestão');
-- INSERT INTO tb_department(name) VALUES ('Informática');

-- INSERT INTO tb_user(username, email, password) VALUES ('Maria', 'maria@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Bob', 'bob@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Alex', 'alex@gmail.com', '1234');
-- INSERT INTO tb_user(username, email, password) VALUES ('Ana', 'ana@gmail.com', '1234');



INSERT INTO tb_user(id, username, email, password) VALUES ('8e0b352f-9adf-4157-96bb-ea0ab3362774','Ana', 'ana@gmail.com', '$2a$10$zXAWTuEBdjorolAHahQV8eF19hYnqKGkh71jhJn7aGyDHW2MVSzn6');
INSERT INTO tb_admin(username, email, password) VALUES ('ADM', 'ADM@gmail.com', '$2a$10$eH9.IsksiehkvZcWk2zgfuzVA29wPastZJ9.mbrgn.NwRkx4Bk.au');
INSERT INTO tb_attendant(username, email, password) VALUES ('Atendente1', 'att1@gmail.com', '$2a$10$UGkXHacQ2AlxLfC8pFP7jOay0rvqTdg/r/qdtBgkSSgrtQ7M2Mkqi');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dr. Ricardo Santiago', 'Cardiologia', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dra. Ana Valle', 'Dermatologia','https://images.unsplash.com/photo-1559839734-2b71ea197ec2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dr. Bruno Rodrigues', 'Fisioterapia','https://images.unsplash.com/photo-1622253694242-abeb37a33e97?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=765&q=80');
INSERT INTO tb_query(doctor_id, user_id, date) VALUES (1, '8e0b352f-9adf-4157-96bb-ea0ab3362774', '2023-06-08 08:00:00.0000000')
