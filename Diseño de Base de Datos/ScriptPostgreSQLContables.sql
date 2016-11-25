/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     19/11/2016 11:27:48 p. m.                    */
/*==============================================================*/


drop index RELATIONSHIP_4_FK;

drop index AREA_PK;

drop table AREA;

drop index BASEPRORRATEO_PK;

drop table BASEPRORRATEO;

drop index RELATIONSHIP_6_FK;

drop index CATALOGOCUENTA_PK;

drop table CATALOGOCUENTA;

drop index CENTRODECOSTO_PK;

drop table CENTRODECOSTO;

drop index RELATIONSHIP_17_FK;

drop index RELATIONSHIP_10_FK;

drop index RELATIONSHIP_9_FK;

drop index RELATIONSHIP_8_FK;

drop index CUENTA_PK;

drop table CUENTA;

drop index RELATIONSHIP_12_FK;

drop index RELATIONSHIP_11_FK;

drop index DETALLEDIARIO_PK;

drop table DETALLEDIARIO;

drop index RELATIONSHIP_18_FK;

drop index RELATIONSHIP_13_FK;

drop index DIARIO_PK;

drop table DIARIO;

drop index RELATIONSHIP_2_FK;

drop index RELATIONSHIP_14_FK;

drop index EMPLEADO_PK;

drop table EMPLEADO;

drop index RELATIONSHIP_15_FK;

drop index EMPRESA_PK;

drop table EMPRESA;

drop index PERIODOCONTABLE_PK;

drop table PERIODOCONTABLE;

drop index PRORRATEO_FK;

drop index PRORRATEO2_FK;

drop index PRORRATEO_PK;

drop table PRORRATEO;

drop index RELATIONSHIP_3_FK;

drop index PUESTO_PK;

drop table PUESTO;

drop index RETENCIONIMPUESTO_PK;

drop table RETENCIONIMPUESTO;

drop index ROL_PK;

drop table ROL;

drop index RELATIONSHIP_5_FK;

drop index TECHO_PK;

drop table TECHO;

drop index TIPOCUENTA_PK;

drop table TIPOCUENTA;

drop index RELATIONSHIP_7_FK;

drop index RELATIONSHIP_1_FK;

drop index USUARIO_PK;

drop table USUARIO;

/*==============================================================*/
/* Table: AREA                                                  */
/*==============================================================*/
create table AREA (
   IDAREA               SERIAL               not null,
   IDEMPRESA            INT4                 not null,
   NOMAREA              VARCHAR(50)          not null,
   DESCRIPCIONAREA      VARCHAR(50)          null,
   constraint PK_AREA primary key (IDAREA)
);

/*==============================================================*/
/* Index: AREA_PK                                               */
/*==============================================================*/
create unique index AREA_PK on AREA (
IDAREA
);

/*==============================================================*/
/* Index: RELATIONSHIP_4_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_4_FK on AREA (
IDEMPRESA
);

/*==============================================================*/
/* Table: BASEPRORRATEO                                         */
/*==============================================================*/
create table BASEPRORRATEO (
   IDBASE               SERIAL               not null,
   NOMBASE              VARCHAR(20)          not null,
   TOTALBASE            DECIMAL(8,2)         not null,
   constraint PK_BASEPRORRATEO primary key (IDBASE)
);

/*==============================================================*/
/* Index: BASEPRORRATEO_PK                                      */
/*==============================================================*/
create unique index BASEPRORRATEO_PK on BASEPRORRATEO (
IDBASE
);

/*==============================================================*/
/* Table: CATALOGOCUENTA                                        */
/*==============================================================*/
create table CATALOGOCUENTA (
   IDCATALOGO           SERIAL               not null,
   IDEMPRESA            INT4                 null,
   NOMCATALOGO          VARCHAR(30)          not null,
   constraint PK_CATALOGOCUENTA primary key (IDCATALOGO)
);

/*==============================================================*/
/* Index: CATALOGOCUENTA_PK                                     */
/*==============================================================*/
create unique index CATALOGOCUENTA_PK on CATALOGOCUENTA (
IDCATALOGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_6_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_6_FK on CATALOGOCUENTA (
IDEMPRESA
);

/*==============================================================*/
/* Table: CENTRODECOSTO                                         */
/*==============================================================*/
create table CENTRODECOSTO (
   IDCENTRO             SERIAL               not null,
   NOMCENTRO            VARCHAR(20)          not null,
   TOTALCOSTO           DECIMAL(8,2)         not null,
   constraint PK_CENTRODECOSTO primary key (IDCENTRO)
);

/*==============================================================*/
/* Index: CENTRODECOSTO_PK                                      */
/*==============================================================*/
create unique index CENTRODECOSTO_PK on CENTRODECOSTO (
IDCENTRO
);

/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA (
   CODCUENTA            VARCHAR(9)           not null,
   IDCATALOGO           INT4                 not null,
   IDTIPOCUENTA         VARCHAR(9)           not null,
   CUE_CODCUENTA        VARCHAR(9)           null,
   IDBASE               INT4                 null,
   NOMCUENTA            VARCHAR(50)          not null,
   ESTADOCUENTA         CHAR(1)              not null,
   SALDOCUENTA          DECIMAL(8,2)         not null,
   constraint PK_CUENTA primary key (CODCUENTA)
);

/*==============================================================*/
/* Index: CUENTA_PK                                             */
/*==============================================================*/
create unique index CUENTA_PK on CUENTA (
CODCUENTA
);

/*==============================================================*/
/* Index: RELATIONSHIP_8_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_8_FK on CUENTA (
IDCATALOGO
);

/*==============================================================*/
/* Index: RELATIONSHIP_9_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_9_FK on CUENTA (
IDTIPOCUENTA
);

/*==============================================================*/
/* Index: RELATIONSHIP_10_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_10_FK on CUENTA (
CUE_CODCUENTA
);

/*==============================================================*/
/* Index: RELATIONSHIP_17_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_17_FK on CUENTA (
IDBASE
);

/*==============================================================*/
/* Table: DETALLEDIARIO                                         */
/*==============================================================*/
create table DETALLEDIARIO (
   IDDETALLE            SERIAL               not null,
   CODCUENTA            VARCHAR(9)           not null,
   IDREGISTRO           INT4                 not null,
   DEBE                 DECIMAL(8,2)         null,
   HABER                DECIMAL(8,2)         null,
   constraint PK_DETALLEDIARIO primary key (IDDETALLE)
);

/*==============================================================*/
/* Index: DETALLEDIARIO_PK                                      */
/*==============================================================*/
create unique index DETALLEDIARIO_PK on DETALLEDIARIO (
IDDETALLE
);

/*==============================================================*/
/* Index: RELATIONSHIP_11_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_11_FK on DETALLEDIARIO (
CODCUENTA
);

/*==============================================================*/
/* Index: RELATIONSHIP_12_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_12_FK on DETALLEDIARIO (
IDREGISTRO
);

/*==============================================================*/
/* Table: DIARIO                                                */
/*==============================================================*/
create table DIARIO (
   IDREGISTRO           SERIAL               not null,
   IDPERIODO            INT4                 not null,
   IDUSUARIO            INT4                 null,
   CONCEPTO             VARCHAR(50)          not null,
   FECHA                DATE                 not null,
   ESTADODIARIO         CHAR(1)              not null,
   constraint PK_DIARIO primary key (IDREGISTRO)
);

/*==============================================================*/
/* Index: DIARIO_PK                                             */
/*==============================================================*/
create unique index DIARIO_PK on DIARIO (
IDREGISTRO
);

/*==============================================================*/
/* Index: RELATIONSHIP_13_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_13_FK on DIARIO (
IDPERIODO
);

/*==============================================================*/
/* Index: RELATIONSHIP_18_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_18_FK on DIARIO (
IDUSUARIO
);

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO (
   CODEMPLEADO          SERIAL               not null,
   IDUSUARIO            INT4                 null,
   IDPUESTO             INT4                 not null,
   NOMEMPLEADO          VARCHAR(30)          not null,
   APELLIDO             VARCHAR(30)          not null,
   EMAIL                VARCHAR(20)          null,
   ISSS                 VARCHAR(15)          null,
   AFP                  VARCHAR(15)          null,
   SEXO                 CHAR(1)              not null,
   DIREMPLEADO          VARCHAR(30)          null,
   TELEFONO             CHAR(9)              null,
   DUI                  CHAR(10)             null,
   NITEMPLEADO          VARCHAR(17)          null,
   EDAD                 INT2                 null,
   FECHAN               DATE                 null,
   ESTADOCIVIL          CHAR(1)              null,
   constraint PK_EMPLEADO primary key (CODEMPLEADO)
);

/*==============================================================*/
/* Index: EMPLEADO_PK                                           */
/*==============================================================*/
create unique index EMPLEADO_PK on EMPLEADO (
CODEMPLEADO
);

/*==============================================================*/
/* Index: RELATIONSHIP_14_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_14_FK on EMPLEADO (
IDUSUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on EMPLEADO (
IDPUESTO
);

/*==============================================================*/
/* Table: EMPRESA                                               */
/*==============================================================*/
create table EMPRESA (
   IDEMPRESA            SERIAL               not null,
   IDCATALOGO           INT4                 null,
   NOMEMPRESA           VARCHAR(50)          not null,
   NITEMPRESA           VARCHAR(17)          not null,
   DIREMPRESA           VARCHAR(50)          not null,
   constraint PK_EMPRESA primary key (IDEMPRESA)
);

/*==============================================================*/
/* Index: EMPRESA_PK                                            */
/*==============================================================*/
create unique index EMPRESA_PK on EMPRESA (
IDEMPRESA
);

/*==============================================================*/
/* Index: RELATIONSHIP_15_FK                                    */
/*==============================================================*/
create  index RELATIONSHIP_15_FK on EMPRESA (
IDCATALOGO
);

/*==============================================================*/
/* Table: PERIODOCONTABLE                                       */
/*==============================================================*/
create table PERIODOCONTABLE (
   IDPERIODO            SERIAL               not null,
   FECHAINICIO          DATE                 not null,
   FECHAFIN             DATE                 not null,
   constraint PK_PERIODOCONTABLE primary key (IDPERIODO)
);

/*==============================================================*/
/* Index: PERIODOCONTABLE_PK                                    */
/*==============================================================*/
create unique index PERIODOCONTABLE_PK on PERIODOCONTABLE (
IDPERIODO
);

/*==============================================================*/
/* Table: PRORRATEO                                             */
/*==============================================================*/
create table PRORRATEO (
   IDCENTRO             INT4                 not null,
   IDBASE               INT4                 not null,
   constraint PK_PRORRATEO primary key (IDCENTRO, IDBASE)
);

/*==============================================================*/
/* Index: PRORRATEO_PK                                          */
/*==============================================================*/
create unique index PRORRATEO_PK on PRORRATEO (
IDCENTRO,
IDBASE
);

/*==============================================================*/
/* Index: PRORRATEO2_FK                                         */
/*==============================================================*/
create  index PRORRATEO2_FK on PRORRATEO (
IDBASE
);

/*==============================================================*/
/* Index: PRORRATEO_FK                                          */
/*==============================================================*/
create  index PRORRATEO_FK on PRORRATEO (
IDCENTRO
);

/*==============================================================*/
/* Table: PUESTO                                                */
/*==============================================================*/
create table PUESTO (
   IDPUESTO             SERIAL               not null,
   IDAREA               INT4                 not null,
   NOMPUESTO            VARCHAR(40)          not null,
   DESCRIPCIONPUESTO    VARCHAR(50)          null,
   SALARIO              DECIMAL(6,2)         not null,
   constraint PK_PUESTO primary key (IDPUESTO)
);

/*==============================================================*/
/* Index: PUESTO_PK                                             */
/*==============================================================*/
create unique index PUESTO_PK on PUESTO (
IDPUESTO
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_3_FK on PUESTO (
IDAREA
);

/*==============================================================*/
/* Table: RETENCIONIMPUESTO                                     */
/*==============================================================*/
create table RETENCIONIMPUESTO (
   IDRETIMP             SERIAL               not null,
   NOMRETIMPT           VARCHAR(30)          not null,
   constraint PK_RETENCIONIMPUESTO primary key (IDRETIMP)
);

/*==============================================================*/
/* Index: RETENCIONIMPUESTO_PK                                  */
/*==============================================================*/
create unique index RETENCIONIMPUESTO_PK on RETENCIONIMPUESTO (
IDRETIMP
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL (
   IDROL                SERIAL               not null,
   NOMROL               VARCHAR(30)          not null,
   DESCRIPCIONROL       VARCHAR(50)          null,
   constraint PK_ROL primary key (IDROL)
);

/*==============================================================*/
/* Index: ROL_PK                                                */
/*==============================================================*/
create unique index ROL_PK on ROL (
IDROL
);

/*==============================================================*/
/* Table: TECHO                                                 */
/*==============================================================*/
create table TECHO (
   IDTECHO              SERIAL               not null,
   IDRETIMP             INT4                 null,
   DESDE                DECIMAL(8,2)         null,
   HASTA                DECIMAL(8,2)         null,
   PORCENAPLICAR        DECIMAL(7,6)         null,
   SOBREEXCESO          DECIMAL(8,2)         null,
   CUOTAFIJA            DECIMAL(8,2)         null,
   constraint PK_TECHO primary key (IDTECHO)
);

/*==============================================================*/
/* Index: TECHO_PK                                              */
/*==============================================================*/
create unique index TECHO_PK on TECHO (
IDTECHO
);

/*==============================================================*/
/* Index: RELATIONSHIP_5_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_5_FK on TECHO (
IDRETIMP
);

/*==============================================================*/
/* Table: TIPOCUENTA                                            */
/*==============================================================*/
create table TIPOCUENTA (
   IDTIPOCUENTA         VARCHAR(9)           not null,
   NOMTIPOCUENTA        VARCHAR(30)          not null,
   constraint PK_TIPOCUENTA primary key (IDTIPOCUENTA)
);

/*==============================================================*/
/* Index: TIPOCUENTA_PK                                         */
/*==============================================================*/
create unique index TIPOCUENTA_PK on TIPOCUENTA (
IDTIPOCUENTA
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   IDUSUARIO            SERIAL               not null,
   CODEMPLEADO          INT4                 not null,
   IDROL                INT4                 null,
   NOMUSUARIO           VARCHAR(30)          not null,
   PASSWORD             VARCHAR(20)          not null,
   constraint PK_USUARIO primary key (IDUSUARIO)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
IDUSUARIO
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on USUARIO (
CODEMPLEADO
);

/*==============================================================*/
/* Index: RELATIONSHIP_7_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_7_FK on USUARIO (
IDROL
);

alter table AREA
   add constraint FK_AREA_RELATIONS_EMPRESA foreign key (IDEMPRESA)
      references EMPRESA (IDEMPRESA)
      on delete restrict on update restrict;

alter table CATALOGOCUENTA
   add constraint FK_CATALOGO_RELATIONS_EMPRESA foreign key (IDEMPRESA)
      references EMPRESA (IDEMPRESA)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_RELATIONS_CUENTA foreign key (CUE_CODCUENTA)
      references CUENTA (CODCUENTA)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_RELATIONS_BASEPROR foreign key (IDBASE)
      references BASEPRORRATEO (IDBASE)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_RELATIONS_CATALOGO foreign key (IDCATALOGO)
      references CATALOGOCUENTA (IDCATALOGO)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_RELATIONS_TIPOCUEN foreign key (IDTIPOCUENTA)
      references TIPOCUENTA (IDTIPOCUENTA)
      on delete restrict on update restrict;

alter table DETALLEDIARIO
   add constraint FK_DETALLED_RELATIONS_CUENTA foreign key (CODCUENTA)
      references CUENTA (CODCUENTA)
      on delete restrict on update restrict;

alter table DETALLEDIARIO
   add constraint FK_DETALLED_RELATIONS_DIARIO foreign key (IDREGISTRO)
      references DIARIO (IDREGISTRO)
      on delete restrict on update restrict;

alter table DIARIO
   add constraint FK_DIARIO_RELATIONS_PERIODOC foreign key (IDPERIODO)
      references PERIODOCONTABLE (IDPERIODO)
      on delete restrict on update restrict;

alter table DIARIO
   add constraint FK_DIARIO_RELATIONS_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table EMPLEADO
   add constraint FK_EMPLEADO_RELATIONS_USUARIO foreign key (IDUSUARIO)
      references USUARIO (IDUSUARIO)
      on delete restrict on update restrict;

alter table EMPLEADO
   add constraint FK_EMPLEADO_RELATIONS_PUESTO foreign key (IDPUESTO)
      references PUESTO (IDPUESTO)
      on delete restrict on update restrict;

alter table EMPRESA
   add constraint FK_EMPRESA_RELATIONS_CATALOGO foreign key (IDCATALOGO)
      references CATALOGOCUENTA (IDCATALOGO)
      on delete restrict on update restrict;

alter table PRORRATEO
   add constraint FK_PRORRATE_PRORRATEO_CENTRODE foreign key (IDCENTRO)
      references CENTRODECOSTO (IDCENTRO)
      on delete restrict on update restrict;

alter table PRORRATEO
   add constraint FK_PRORRATE_PRORRATEO_BASEPROR foreign key (IDBASE)
      references BASEPRORRATEO (IDBASE)
      on delete restrict on update restrict;

alter table PUESTO
   add constraint FK_PUESTO_RELATIONS_AREA foreign key (IDAREA)
      references AREA (IDAREA)
      on delete restrict on update restrict;

alter table TECHO
   add constraint FK_TECHO_RELATIONS_RETENCIO foreign key (IDRETIMP)
      references RETENCIONIMPUESTO (IDRETIMP)
      on delete restrict on update restrict;

alter table USUARIO
   add constraint FK_USUARIO_RELATIONS_EMPLEADO foreign key (CODEMPLEADO)
      references EMPLEADO (CODEMPLEADO)
      on delete restrict on update restrict;

alter table USUARIO
   add constraint FK_USUARIO_RELATIONS_ROL foreign key (IDROL)
      references ROL (IDROL)
      on delete restrict on update restrict;

