INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(1,'2015200643','Sebastian','Navarro','1999-01-13','M','',false,'78204120','$2a$10$DAp9B3cCpe3uR6sYQ8nNPuc3aglL4sl/31NSmBivWdhj2ZxGCKsee',true,'christiam_navarro@usmp.pe')
INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(2,'2016201028','Santiago','Airaldi','1998-01-25','M','',false,'12345678','$2a$10$9O2o65uCpBP2Nt75pn6J1uDv5S69Z3k1KFnl6WyOZ3sW5mW.I9ns6',true,'santiago_airaldi@usmp.pe')
INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(3,'2020201510','Mario','Castillo','1997-05-13','M','',false,'87654321','$2a$10$Zs6bVSD.OeUk2XoXSo8wk.4Eprcl5.BzcoCTISiChHLr.Lh1AhppS',true,'mario_castillo@usmp.pe')

INSERT INTO public.roles(nombre) VALUES ('ROLE_USER')
INSERT INTO public.roles(nombre) VALUES ('ROLE_ADMIN')

INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(1,1)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(1,2)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(2,1)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(3,1)