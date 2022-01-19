-- Datos ya existentes. No dinámicos.

SET FOREIGN_KEY_CHECKS=0;

INSERT INTO provincia (nombre_provincia) VALUES
	 ('Albacete'),
	 ('Alicante'),
	 ('A Coruña'),
	 ('Álava'),
	 ('Almería'),
	 ('Asturias'),
	 ('Ávila'),
	 ('Badajoz'),
	 ('Barcelona'),
	 ('Bizkaia');
INSERT INTO provincia (nombre_provincia) VALUES
	 ('Burgos'),
	 ('Cáceres'),
	 ('Cádiz'),
	 ('Cantabria'),
	 ('Castelló'),
	 ('Ceuta'),
	 ('Ciudad Real'),
	 ('Córdoba'),
	 ('Cuenca'),
	 ('Gipuzkoa');
INSERT INTO provincia (nombre_provincia) VALUES
	 ('Girona'),
	 ('Granada'),
	 ('Guadalajara'),
	 ('Huelva'),
	 ('Huesca'),
	 ('Illes Balears'),
	 ('Jaén'),
	 ('La Rioja'),
	 ('Las Palmas'),
	 ('León');
INSERT INTO provincia (nombre_provincia) VALUES
	 ('Lleida'),
	 ('Lugo'),
	 ('Madrid'),
	 ('Málaga'),
	 ('Melilla'),
	 ('Murcia'),
	 ('Nafarroa'),
	 ('Ourense'),
	 ('Palencia'),
	 ('Pontevedra');
INSERT INTO provincia (nombre_provincia) VALUES
	 ('Salamanca'),
	 ('Segovia'),
	 ('Sevilla'),
	 ('Soria'),
	 ('Sta. Cruz de Tenerife'),
	 ('Tarragona'),
	 ('Teruel'),
	 ('Toledo'),
	 ('Valéncia'),
	 ('Valladolid');
INSERT INTO provincia (nombre_provincia) VALUES
	 ('Zamora'),
	 ('Zaragoza');

INSERT INTO rol (nombre_rol) VALUES
	 ('ROLE_CANDIDATO'),
	 ('ROLE_EMPRESA');

INSERT INTO sector_laboral (nombre_sector_laboral) VALUES
	 ('Actividades deportivas'),
	 ('Administración'),
	 ('Agroalimentario'),
	 ('Artes gráficas'),
	 ('Construcción'),
	 ('Energía'),
	 ('Imagen personal'),
	 ('Imagen y sonido'),
	 ('Industrial'),
	 ('Informática');
INSERT INTO sector_laboral (nombre_sector_laboral) VALUES
	 ('Logística,transporte y comercio'),
	 ('Mantenimiento'),
	 ('Medio ambiente'),
	 ('Químico'),
	 ('Salud y servicios a la comunidad'),
	 ('Servicios turísticos y hosteleros'),
	 ('Textil');

-- Datos dinámicos, pueden haber o no.

INSERT INTO candidato (apellidos_candidato,fecha_nacimiento_candidato,nombre_candidato,telefono_candidato,credencial_id_credencial,provincia_candidato) VALUES
	 ('Pérez García','3920-02-20','Pepe','666555444',1,NULL),
	 ('Muñoz Sanchez','3921-03-10','Maria','654321987',2,NULL),
	 ('Mios','2021-12-27','Security','+34999999999',20,NULL);

INSERT INTO credencial_roles (credencial_id_credencial,roles_id_rol) VALUES
	 (1,1),
	 (2,1),
	 (3,2);

INSERT INTO empresa (cif_empresa,logo_empresa,nombre_empresa,nombre_juridico_empresa,sector_empresa,empleados_empresa,provincia_empresa,provincia_empresa_e,localidad_empresa,direccion_empresa,telefono_empresa,whatsapp_empresa,credencial_id_credencial) VALUES
	 ('J20698098',NULL,'Nombre empresa 1','Nombre empresa 1',1,12,9,'1','Locallidad empresa','C/ de la empresa','698745123','',NULL),
	 ('222',NULL,'Segunda empresa','2222',2,20,2,'2','2222','2222','22222','22222',NULL),
	 ('3333',NULL,'3333','3333',3,333,3,'3','3333','333','3333','3333',NULL),
	 ('4444',NULL,'Empresa 4','4444',4,444,4,'48','4444','444','4444','4444',NULL),
	 ('5555',NULL,'Nombre empresa','nombre kuridico',5,5555,13,'5','Locallidad empresa','C/ de la empresa','698745123','',NULL),
	 ('666666',NULL,'Nombre empresa','nombre kuridico',7,6666,13,'6','Locallidad empresa','C/ de la empresa','698745123','',NULL),
	 ('7777777',NULL,'Septima Empresa','Septima Empresa S.A.',6,4000,11,'7','Chipiona','dfasdfasfasf','78906543','987654321',NULL),
	 ('88888',NULL,'88888','88888',13,88888,12,'8','888888888','88888888','888888888','88888888',NULL),
	 ('99999',NULL,'99999','Empresa 9',14,99999,35,'9','999999999','99999999','999999999','99999999',NULL),
	 ('S4280108D',NULL,'Pruebas Validation','asdfasdf',1,10000,2,NULL,'La mia propia','En un lugar de la Mancha','+34-687654321','+34-687654321',NULL);
INSERT INTO empresa (cif_empresa,logo_empresa,nombre_empresa,nombre_juridico_empresa,sector_empresa,empleados_empresa,provincia_empresa,provincia_empresa_e,localidad_empresa,direccion_empresa,telefono_empresa,whatsapp_empresa,credencial_id_credencial) VALUES
	 ('987456132',NULL,'imprenta beltran sl','beltransl',4,14,21,NULL,'sna juan del puerto','poligono indutec nave 2','959356454','685416128',NULL),
	 ('1254255',NULL,'Isi el guapo SL','Isi el guapo sociedad limitada',10,5,41,NULL,'Fuentes de andalucia','calle granada nº9','684279534','',NULL),
	 ('333',NULL,'Perfido Doofenshmirtz SL','Perry el Ornitorrinco',5,1,38,NULL,'Area de los Tres Estados','En el medio, idk','123456','123456',NULL);

INSERT INTO habilidad (nombre_habilidad,descripcion_habilidad,categoria_habilidad) VALUES
	 ('Nombre 1','Descripcion 1',0),
	 ('Nombre 3','Descripcion 3',0),
	 ('Habilida terta','asdg',1),
	 ('Bladding','Use of ...',1);

INSERT INTO oferta_empleo (titulo_oferta,descripcion_oferta,sector_oferta,salario_oferta,numero_vacantes_oferta,provincia_oferta,localidad_oferta,fecha_inicio_oferta,fecha_fin_oferta,id_empresa_oferta) VALUES
	 ('Catador estilo Agustin','Programador Kotlin en sevilla.',10,1231,23,41,'Los corrales','2022-01-23','2022-01-30',1),
	 ('Programador Java','Programador java en sevilla.',1,1231,2,2,'Los corrales','2022-01-10','2022-01-19',1),
	 ('Programador Java','Programador java en sevilla.',1,3123,2,2,'Los corrales','2022-01-17','2022-01-18',1),
	 ('Limpiador','Limpiador en sevilla',1,950,2,8,'Los corrales','2022-01-17','2022-01-27',1),
	 ('Programador Php','follador',1,1231,2,33,'Los corrales','2022-01-10','2022-01-27',2),
	 ('Programador Junior','programador junior en sevilla.',10,1231,2,2,'Los corrales','2022-01-10','2022-01-27',2),
	 ('programador fullstack','trabajaras para george Inc',2,12321,1,15,'wqerwq','2022-03-12','2023-03-12',1),
	 ('troquelador','usuario de maquinaria troqueladora',4,27000,3,21,'san juan del puerto','2022-01-01','2022-04-28',1),
	 ('Programador','Se busca programadors Java y php',2,10,2,41,'LA Algaba','2022-01-01','2023-02-17',1),
	 ('Poned el UTF-8','No furulan las tildes o poner &"letra"acute',10,1,1,41,'Sevilla','2022-01-17','2022-01-28',1);
INSERT INTO oferta_empleo (titulo_oferta,descripcion_oferta,sector_oferta,salario_oferta,numero_vacantes_oferta,provincia_oferta,localidad_oferta,fecha_inicio_oferta,fecha_fin_oferta,id_empresa_oferta) VALUES
	 ('Programador Java','Solo gente del curso de fullstack',10,25555,25,41,'Fuentes de andalucia','2022-01-17','2022-01-24',1);


SET FOREIGN_KEY_CHECKS=1;

