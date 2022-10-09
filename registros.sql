/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 8.0.29 : Database - universidad
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`universidad` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `universidad`;

/*Data for the table `alumno` */

INSERT  INTO `alumno`(`id`,`activo`,`apellido`,`dni`,`fecha_nacimiento`,`nombre`,`id_usuario`) VALUES 
(1,'','Rodriguez','65434565','1993-01-01','Roberto',4),
(2,'','Arevalo','24212345','1995-02-02','Jose',5),
(3,'','Guerrero','22222212','1992-02-02','Norberto',6),
(4,'','Valdiviezo','7867888','1990-02-02','Pablo',7),
(5,'','Ramirez','5675555','1989-02-02','Carlos',8);

/*Data for the table `alumno_seccion` */

INSERT  INTO `alumno_seccion`(`id`,`id_alumno`,`id_seccion`) VALUES 
(1,1,1),
(2,2,1),
(7,3,1),
(8,4,2),
(9,5,2),
(11,1,3),
(12,2,3),
(13,3,3),
(14,4,3);

/*Data for the table `ciclo` */

INSERT  INTO `ciclo`(`id`,`anio`,`fecha_fin`,`fecha_inicio`,`numero_ciclo`) VALUES 
(1,2022,'2022-01-01','2022-03-01','I'),
(2,2022,'2022-03-02','2022-06-01','II'),
(3,2022,'2022-06-01','2022-09-02','III');

/*Data for the table `curso` */

INSERT  INTO `curso`(`id`,`descripcion`) VALUES 
(1,'Base de datos'),
(2,'Programacion Java'),
(3,'Angular'),
(4,'.NET'),
(5,'Data Science');


/*Data for the table `profesor` */

INSERT  INTO `profesor`(`id`,`activo`,`apellido`,`dni`,`nombre`,`id_usuario`) VALUES 
(1,'','Gomez','45432345','Gonzalo',2),
(2,'','Lopez','87654345','Franco',3);

/*Data for the table `profesor_curso` */

/*Data for the table `rol` */

INSERT  INTO `rol`(`id`,`descripcion`) VALUES 
(1,'Administrador'),
(2,'Profesor'),
(3,'Alumno');

/*Data for the table `seccion` */

INSERT  INTO `seccion`(`id`,`id_ciclo`,`id_curso`,`id_profesor`) VALUES 
(1,1,1,1),
(2,1,2,2),
(3,1,3,1),
(4,2,2,1),
(5,2,3,1),
(6,3,1,1),
(7,3,3,2);

/*Data for the table `tipo_evaluacion` */

INSERT  INTO `tipo_evaluacion`(`id`,`descripcion`) VALUES 
(1,'Práctica Calificada I'),
(2,'Práctica Calificada II'),
(3,'Exámen Parcial'),
(4,'Exámen final');

/*Data for the table `usuario` */

INSERT  INTO `usuario`(`id`,`contrasenia`,`nombre`,`id_rol`) VALUES 
(1,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','admin',1),
(2,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','ggomez',2),
(3,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','flopez',2),
(4,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','rrodriguez',3),
(5,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','jarevalo',3),
(6,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','nguerrero',3),
(7,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','pvaldiviezo',3),
(8,'$2a$10$z4C7APMhlYI92tX0gMWJMOMXuJti3FHzmbjuUIh.dx9mBsAjh0oWO','cramirez',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
