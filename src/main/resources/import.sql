INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(1,'2015200643','Sebastian','Navarro','1999-01-13','M','',false,'78204120','$2a$10$UrUiDr80wQf7/GinKUj60uV1cpkIgyeIIhLblXWOx67rDO/ny6kGa',true,'christiam_navarro@usmp.pe')
INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(2,'2016201028','Santiago','Airaldi','1998-01-25','M','',false,'12345678','$2a$10$yk50zhQMYKbz/eVYrSLKl.aDREWLpZnuLm6XRR71kxp5rKrX/FQKy',true,'santiago_airaldi@usmp.pe')
INSERT INTO public.alumnos(id,codigo,nombres,apellidos,fecha_nacimiento,sexo,alumno_imagen,estado,username,password,enabled,email) values(3,'2020201510','Mario','Castillo','1997-05-13','M','',false,'87654321','$2a$10$Eb1P0E96m.2/7It2WKfCr.hqChcoNadkuf0FuVKl9MD4F3E34hK1m',true,'mario_castillo@usmp.pe')

INSERT INTO public.roles(nombre) VALUES ('ROLE_USER')
INSERT INTO public.roles(nombre) VALUES ('ROLE_ADMIN')

INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(1,1)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(1,2)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(2,1)
INSERT INTO public.alumnos_roles(alumnos_id, roles_id) values(3,1)