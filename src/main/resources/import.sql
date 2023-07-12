


INSERT INTO tb_user(id, username, email, password) VALUES ('8e0b352f-9adf-4157-96bb-ea0ab3362774','Ana', 'ana@gmail.com', '$2a$10$a6ianqQkC5HGz8iptGUyqOVqe4Cltu3z14zq5sqwhoK/sflaQ7ZUe');
INSERT INTO tb_user(id, username, email, password) VALUES ('df474f9d-3228-45bc-8ce9-1d5191fb2c2f','João', 'joao@gmail.com', '$2a$10$GPTOFymybykXvfoJ.USJuOmQhAIE6UK0kC7dYUkfWaT5wRwD9GG0y');

INSERT INTO tb_admin(username, email, password) VALUES ('ADM', 'ADM@gmail.com', '$2a$10$eH9.IsksiehkvZcWk2zgfuzVA29wPastZJ9.mbrgn.NwRkx4Bk.au');
-- INSERT INTO tb_attendant(username, email, password) VALUES ('Atendente1', 'att1@gmail.com', '$2a$10$UGkXHacQ2AlxLfC8pFP7jOay0rvqTdg/r/qdtBgkSSgrtQ7M2Mkqi');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dr. Ricardo Santiago', 'Cardiologia', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dra. Ana Valle', 'Dermatologia','https://images.unsplash.com/photo-1559839734-2b71ea197ec2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80');
INSERT INTO tb_doctor(name, specialty, photoURL) VALUES ('Dr. Bruno Rodrigues', 'Fisioterapia','https://images.unsplash.com/photo-1622253694242-abeb37a33e97?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=765&q=80');
-- INSERT INTO tb_query(doctor_id, user_id, date, is_Confirmed) VALUES (1, '8e0b352f-9adf-4157-96bb-ea0ab3362774', '2023-06-08 08:00:00.0000000', False)
