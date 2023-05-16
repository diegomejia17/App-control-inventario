/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     29/4/2023 18:32:41                           */
/*==============================================================*/


drop table if exists AUTOR;

drop table if exists CATALOGO_UBICACION;

drop table if exists CATEGORIALIBRO;

drop table if exists DESCARGO;

drop table if exists DOCENTE;

drop table if exists EDITORIAL;

drop table if exists EQUIPO;

drop table if exists ESCUELA;

drop table if exists ESTUDIANTE;

drop table if exists FACULTAD;

drop table if exists IDIOMA;

drop table if exists LIBRO;

drop table if exists MARCA;

drop table if exists MATERIA;

drop table if exists MOVIMIENTO;

drop table if exists PRESTAMO;

drop table if exists RELATIONSHIP_1;

drop table if exists RELATIONSHIP_10;

drop table if exists RELATIONSHIP_11;

drop table if exists RELATIONSHIP_4;

drop table if exists RELATIONSHIP_7;

drop table if exists RELATIONSHIP_9;

drop table if exists SECRETARIA;

drop table if exists TESIS;

/*==============================================================*/
/* Table: AUTOR                                                 */
/*==============================================================*/
create table AUTOR
(
   IDAUTOR              int not null,
   FECHACREACIONAUTOR   datetime not null,
   NOMBREAUTOR          varchar(100) not null,
   primary key (IDAUTOR)
);

/*==============================================================*/
/* Table: CATALOGO_UBICACION                                    */
/*==============================================================*/
create table CATALOGO_UBICACION
(
   IDCATALOGOUBICACION  int not null,
   IDESCUELA            int,
   FECHACREACIONCATALOGOUBICACION datetime not null,
   DESCRIPCIONCATALOGODESCRIPCION varchar(256),
   primary key (IDCATALOGOUBICACION)
);

/*==============================================================*/
/* Table: CATEGORIALIBRO                                        */
/*==============================================================*/
create table CATEGORIALIBRO
(
   IDCATEGORIALIBRO     int not null,
   FECHACREACIONCATEGORIALIBRO datetime not null,
   DESCRIPCIONCATEGORIALIBRO varchar(256) not null,
   primary key (IDCATEGORIALIBRO)
);

/*==============================================================*/
/* Table: DESCARGO                                              */
/*==============================================================*/
create table DESCARGO
(
   IDDESCARGO           int not null,
   IDMOVIMIENTO         int,
   FECHACREACIONDESCARGO datetime not null,
   primary key (IDDESCARGO)
);

/*==============================================================*/
/* Table: DOCENTE                                               */
/*==============================================================*/
create table DOCENTE
(
   IDDOCENTE            int not null,
   FECHACREACIONDOCENTE datetime not null,
   NOMBREDOCENTE        varchar(100) not null,
   primary key (IDDOCENTE)
);

/*==============================================================*/
/* Table: EDITORIAL                                             */
/*==============================================================*/
create table EDITORIAL
(
   IDEDITORIAL          int not null,
   FECHACREACIONEDITORIAL datetime not null,
   DESCRIPCIONEDITORIAL varchar(256) not null,
   primary key (IDEDITORIAL)
);

/*==============================================================*/
/* Table: EQUIPO                                                */
/*==============================================================*/
create table EQUIPO
(
   IDEQUIPO             int not null,
   IDMARCA              int,
   FECHACREACIONEQUIPO  datetime not null,
   DESCRIPCIONEQUIPO    varchar(256) not null,
   NUMEROSERIEEQUIPO    varchar(256) not null,
   UBICACIONEQUIPO      varchar(256) not null,
   MODELOEQUIPO         varchar(256),
   primary key (IDEQUIPO)
);

/*==============================================================*/
/* Table: ESCUELA                                               */
/*==============================================================*/
create table ESCUELA
(
   IDESCUELA            int not null,
   IDFACULTAD           int,
   FECHACREACIONESCUELA datetime not null,
   NOMBREESCUELA        varchar(50) not null,
   primary key (IDESCUELA)
);

/*==============================================================*/
/* Table: ESTUDIANTE                                            */
/*==============================================================*/
create table ESTUDIANTE
(
   IDESTUDIANTE         int not null,
   FECHACREACIONESTUDIANTE datetime not null,
   DUEESTUDIANTE        int not null,
   NOMBREESTUDIANTE     varchar(100) not null,
   primary key (IDESTUDIANTE)
);

/*==============================================================*/
/* Table: FACULTAD                                              */
/*==============================================================*/
create table FACULTAD
(
   IDFACULTAD           int not null,
   FECHACREACIONFACULTAD datetime not null,
   NOMBREFACULTAD       varchar(100) not null,
   primary key (IDFACULTAD)
);

/*==============================================================*/
/* Table: IDIOMA                                                */
/*==============================================================*/
create table IDIOMA
(
   IDIDIOMA             int not null,
   FECHACREACIONIDIOMA  datetime not null,
   DESCRIPCIONIDIOMA    varchar(100) not null,
   primary key (IDIDIOMA)
);

/*==============================================================*/
/* Table: LIBRO                                                 */
/*==============================================================*/
create table LIBRO
(
   IDLIBRO              int not null,
   IDCATEGORIALIBRO     int,
   IDEDITORIAL          int,
   IDMATERIA            int,
   FECHACREACIONLIBRO   datetime not null,
   DESCRIPCIONLIBRO     varchar(256) not null,
   ISBNLIBRO            int,
   FECHAPUBLICACIONLIBRO date not null,
   EDICIONLIBRO         int,
   TOMOLIBRO            int,
   primary key (IDLIBRO)
);

/*==============================================================*/
/* Table: MARCA                                                 */
/*==============================================================*/
create table MARCA
(
   IDMARCA              int not null,
   FECHACREACIONMARCA   datetime not null,
   DESCRIPCIONMARCA     varchar(255) not null,
   primary key (IDMARCA)
);

/*==============================================================*/
/* Table: MATERIA                                               */
/*==============================================================*/
create table MATERIA
(
   IDMATERIA            int not null,
   FECHACREACIONMATERIA datetime not null,
   DESCRIPCIONMATERIA   varchar(100) not null,
   primary key (IDMATERIA)
);

/*==============================================================*/
/* Table: MOVIMIENTO                                            */
/*==============================================================*/
create table MOVIMIENTO
(
   IDMOVIMIENTO         int not null,
   IDFACULTAD           int,
   IDDESCARGO           int,
   IDPRESTAMO           int,
   FECHACREACIONMOVIMIENTO datetime not null,
   DESCRIPCIONMOVIMIENTO varchar(255) not null,
   CANTIDADTOTALMOVIMIENTO int not null,
   primary key (IDMOVIMIENTO)
);

/*==============================================================*/
/* Table: PRESTAMO                                              */
/*==============================================================*/
create table PRESTAMO
(
   IDPRESTAMO           int not null,
   IDMOVIMIENTO         int,
   IDSECRETARIA         int,
   IDDOCENTE            int,
   IDESTUDIANTE         int,
   FECHACREACIONPRESTAMO datetime not null,
   ESTADOPRESTAMO       varchar(50) not null,
   FECHAINICIOPRESTAMO  datetime not null,
   FECHAFINPRESTAMO     datetime not null,
   FECHAENTREGAPRESTAMO datetime not null,
   primary key (IDPRESTAMO)
);

/*==============================================================*/
/* Table: RELATIONSHIP_1                                        */
/*==============================================================*/
create table RELATIONSHIP_1
(
   IDLIBRO              int not null,
   IDAUTOR              int not null,
   primary key (IDLIBRO, IDAUTOR)
);

/*==============================================================*/
/* Table: RELATIONSHIP_10                                       */
/*==============================================================*/
create table RELATIONSHIP_10
(
   IDMOVIMIENTO         int not null,
   IDLIBRO              int not null,
   primary key (IDMOVIMIENTO, IDLIBRO)
);

/*==============================================================*/
/* Table: RELATIONSHIP_11                                       */
/*==============================================================*/
create table RELATIONSHIP_11
(
   IDMOVIMIENTO         int not null,
   IDTESIS              int not null,
   primary key (IDMOVIMIENTO, IDTESIS)
);

/*==============================================================*/
/* Table: RELATIONSHIP_4                                        */
/*==============================================================*/
create table RELATIONSHIP_4
(
   IDIDIOMA             int not null,
   IDLIBRO              int not null,
   primary key (IDIDIOMA, IDLIBRO)
);

/*==============================================================*/
/* Table: RELATIONSHIP_7                                        */
/*==============================================================*/
create table RELATIONSHIP_7
(
   IDESTUDIANTE         int not null,
   IDTESIS              int not null,
   primary key (IDESTUDIANTE, IDTESIS)
);

/*==============================================================*/
/* Table: RELATIONSHIP_9                                        */
/*==============================================================*/
create table RELATIONSHIP_9
(
   IDMOVIMIENTO         int not null,
   IDEQUIPO             int not null,
   primary key (IDMOVIMIENTO, IDEQUIPO)
);

/*==============================================================*/
/* Table: SECRETARIA                                            */
/*==============================================================*/
create table SECRETARIA
(
   IDSECRETARIA         int not null,
   FECHACREACIONSECRETARIA varchar(100) not null,
   NOMBRESECRETARIA     varchar(100) not null,
   primary key (IDSECRETARIA)
);

/*==============================================================*/
/* Table: TESIS                                                 */
/*==============================================================*/
create table TESIS
(
   IDTESIS              int not null,
   FECHACREACIONTESIS   datetime not null,
   TITULOTESIS          varchar(256),
   DESCRIPCIONTESIS     varchar(256),
   primary key (IDTESIS)
);

alter table CATALOGO_UBICACION add constraint FK_RELATIONSHIP_20 foreign key (IDESCUELA)
      references ESCUELA (IDESCUELA) on delete restrict on update restrict;

alter table DESCARGO add constraint FK_RELATIONSHIP_25 foreign key (IDMOVIMIENTO)
      references MOVIMIENTO (IDMOVIMIENTO) on delete restrict on update restrict;

alter table EQUIPO add constraint FK_RELATIONSHIP_6 foreign key (IDMARCA)
      references MARCA (IDMARCA) on delete restrict on update restrict;

alter table ESCUELA add constraint FK_RELATIONSHIP_19 foreign key (IDFACULTAD)
      references FACULTAD (IDFACULTAD) on delete restrict on update restrict;

alter table LIBRO add constraint FK_RELATIONSHIP_2 foreign key (IDCATEGORIALIBRO)
      references CATEGORIALIBRO (IDCATEGORIALIBRO) on delete restrict on update restrict;

alter table LIBRO add constraint FK_RELATIONSHIP_3 foreign key (IDEDITORIAL)
      references EDITORIAL (IDEDITORIAL) on delete restrict on update restrict;

alter table LIBRO add constraint FK_RELATIONSHIP_5 foreign key (IDMATERIA)
      references MATERIA (IDMATERIA) on delete restrict on update restrict;

alter table MOVIMIENTO add constraint FK_RELATIONSHIP_12 foreign key (IDFACULTAD)
      references FACULTAD (IDFACULTAD) on delete restrict on update restrict;

alter table MOVIMIENTO add constraint FK_RELATIONSHIP_13 foreign key (IDDESCARGO)
      references DESCARGO (IDDESCARGO) on delete restrict on update restrict;

alter table MOVIMIENTO add constraint FK_RELATIONSHIP_16 foreign key (IDPRESTAMO)
      references PRESTAMO (IDPRESTAMO) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_RELATIONSHIP_15 foreign key (IDSECRETARIA)
      references SECRETARIA (IDSECRETARIA) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_RELATIONSHIP_17 foreign key (IDDOCENTE)
      references DOCENTE (IDDOCENTE) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_RELATIONSHIP_18 foreign key (IDESTUDIANTE)
      references ESTUDIANTE (IDESTUDIANTE) on delete restrict on update restrict;

alter table PRESTAMO add constraint FK_RELATIONSHIP_26 foreign key (IDMOVIMIENTO)
      references MOVIMIENTO (IDMOVIMIENTO) on delete restrict on update restrict;

alter table RELATIONSHIP_1 add constraint FK_RELATIONSHIP_1 foreign key (IDLIBRO)
      references LIBRO (IDLIBRO) on delete restrict on update restrict;

alter table RELATIONSHIP_1 add constraint FK_RELATIONSHIP_8 foreign key (IDAUTOR)
      references AUTOR (IDAUTOR) on delete restrict on update restrict;

alter table RELATIONSHIP_10 add constraint FK_RELATIONSHIP_10 foreign key (IDMOVIMIENTO)
      references MOVIMIENTO (IDMOVIMIENTO) on delete restrict on update restrict;

alter table RELATIONSHIP_10 add constraint FK_RELATIONSHIP_23 foreign key (IDLIBRO)
      references LIBRO (IDLIBRO) on delete restrict on update restrict;

alter table RELATIONSHIP_11 add constraint FK_RELATIONSHIP_11 foreign key (IDMOVIMIENTO)
      references MOVIMIENTO (IDMOVIMIENTO) on delete restrict on update restrict;

alter table RELATIONSHIP_11 add constraint FK_RELATIONSHIP_24 foreign key (IDTESIS)
      references TESIS (IDTESIS) on delete restrict on update restrict;

alter table RELATIONSHIP_4 add constraint FK_RELATIONSHIP_14 foreign key (IDLIBRO)
      references LIBRO (IDLIBRO) on delete restrict on update restrict;

alter table RELATIONSHIP_4 add constraint FK_RELATIONSHIP_4 foreign key (IDIDIOMA)
      references IDIOMA (IDIDIOMA) on delete restrict on update restrict;

alter table RELATIONSHIP_7 add constraint FK_RELATIONSHIP_21 foreign key (IDTESIS)
      references TESIS (IDTESIS) on delete restrict on update restrict;

alter table RELATIONSHIP_7 add constraint FK_RELATIONSHIP_7 foreign key (IDESTUDIANTE)
      references ESTUDIANTE (IDESTUDIANTE) on delete restrict on update restrict;

alter table RELATIONSHIP_9 add constraint FK_RELATIONSHIP_22 foreign key (IDEQUIPO)
      references EQUIPO (IDEQUIPO) on delete restrict on update restrict;

alter table RELATIONSHIP_9 add constraint FK_RELATIONSHIP_9 foreign key (IDMOVIMIENTO)
      references MOVIMIENTO (IDMOVIMIENTO) on delete restrict on update restrict;

