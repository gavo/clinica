CREATE DATABASE `clinica`; 

USE `clinica`; 

CREATE TABLE `clinica`.`persona`( 
	`ci` VARCHAR(15) NOT NULL COMMENT 'Carnet de Identidad', 
	`nombres` VARCHAR(60) NOT NULL COMMENT 'Nombres de la Persona', 
	`apellidos` VARCHAR(60) NOT NULL COMMENT 'Apellidos de la Persona', 
	`direccion` VARCHAR(150) COMMENT 'Direccion donde vive la persona', 
	`telefono` VARCHAR(15) COMMENT 'Telefono que usa la persona', 
	PRIMARY KEY (`ci`) 
); 

CREATE TABLE `clinica`.`rango`( 
	`id_ra` INT(4) NOT NULL AUTO_INCREMENT COMMENT 'identificador de rango', 
	`nombre` VARCHAR(50) NOT NULL COMMENT 'nombre del rango', 
	PRIMARY KEY (`id_ra`) 
); 

CREATE TABLE `clinica`.`usuario`(  
	`id_us` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador de usuario',
	`user` VARCHAR(30) NOT NULL COMMENT 'usuario que se utilizara para loguear la cuenta',
	`pass` VARCHAR(40) NOT NULL COMMENT 'password que se utilizara para autentificar la cuenta',
	`ci` VARCHAR(15) NOT NULL COMMENT 'Ci de persona ligada a la cuenta',
	PRIMARY KEY (`id_us`),
	CONSTRAINT `rel_usuario_persona` 
	FOREIGN KEY (`ci`) REFERENCES `clinica`.`persona`(`ci`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE `clinica`.`rango_usuario`(  
	`id_ra` INT(4) NOT NULL COMMENT 'identificador de rango de usuario',
	`id_us` INT(11) NOT NULL COMMENT 'identificador de usuario',
	PRIMARY KEY (`id_ra`, `id_us`),
	CONSTRAINT `rel_rango_rango_usuario` 
	FOREIGN KEY (`id_ra`) REFERENCES `clinica`.`rango`(`id_ra`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `rel_usuario_rango_usuario` FOREIGN KEY (`id_us`) 
	REFERENCES `clinica`.`usuario`(`id_us`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE `clinica`.`consulta`( 
	`id_con` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador de consulta', 
	`registrado` DATETIME NOT NULL COMMENT 'fecha en que se regitroo la consulta', 
	`atencion` DATETIME NOT NULL COMMENT 'fecha en que se realizo la atencion', 
	`costo` FLOAT(11) NOT NULL DEFAULT 0 COMMENT 'costo de la consulta', 
	`detalle` TEXT COMMENT 'detalle de lo diagnosticado y visto en la consulta', 
	`ci` VARCHAR(15) NOT NULL COMMENT 'carnet de identidad del cliente', 
	`id_us_r` INT(11) NOT NULL COMMENT 'identificador de usuario que registro la consulta', 
	`id_us_a` INT(11) NOT NULL COMMENT 'identificador de usuario que realizo la consulta', 
	`estado` int(1) DEFAULT '0' COMMENT 'Flag para determinar si la consulta fue o no atendida',
	PRIMARY KEY (`id_con`), 
	CONSTRAINT `rel_consulta_persona` FOREIGN KEY (`ci`) 
	REFERENCES `clinica`.`persona`(`ci`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT, 	
	CONSTRAINT `rel_consulta_usuario_1` FOREIGN KEY (`id_us_r`) 
	REFERENCES `clinica`.`usuario`(`id_us`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT, 	
	CONSTRAINT `rel_consulta_usuario_2` FOREIGN KEY (`id_us_a`) 
	REFERENCES `clinica`.`usuario`(`id_us`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT
); 

CREATE TABLE `clinica`.`especialidad`(  
	`id_es` INT(11) NOT NULL COMMENT 'identificador del usuario que atendera',
	`nombre` VARCHAR(50) COMMENT 'especialidad en la que se atendera',
	`costo` FLOAT(11) NOT NULL DEFAULT 0 COMMENT 'costo de la consulta',
	PRIMARY KEY (`id_es`)
);

CREATE TABLE `clinica`.`esp_us`(  
	`id_es` INT(11) NOT NULL COMMENT 'identificador de especialidad',
	`id_us` INT(11) NOT NULL COMMENT 'identificador de usuario',
	PRIMARY KEY (`id_es`, `id_us`),
	CONSTRAINT `esp_us_especialidad` FOREIGN KEY (`id_es`) 
	REFERENCES `clinica`.`especialidad`(`id_es`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `esp_us_usuario` FOREIGN KEY (`id_us`) 
	REFERENCES `clinica`.`usuario`(`id_us`) 
	ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE `backup` (
	`id_ba` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador de modificacion a la base de datos',
	`sql` text NOT NULL COMMENT 'comando sql ejecutado en la base de datos por el sistema',
	PRIMARY KEY (`id_ba`)
);
