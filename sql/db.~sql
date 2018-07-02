/*==============================================================*/
/* Content:          车辆管理系统初始化代码（一键执行）         */
/* Creater:          Roy                                        */
/* Created Time:     2016/8/1 16:56:37                          */
/*==============================================================*/

/*==============================================================*/
/* Sequences: all                                               */
/*==============================================================*/

--延迟段创建，解决11G R2中第一次序列自动跳过1的问题，须有权限
--ALTER SYSTEM SET deferred_segment_creation=FALSE;

CREATE SEQUENCE SEQ_QUSERS_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_QROLE_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_ACCIDENT_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_CAR_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_CAR_PIC_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_CURRENT_UNIT_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_DICTIONARY_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_DRIVER_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_DRIVING_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_FEES_MANAGER_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_INSPECTION_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_INSURANCE_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_MAINTAIN_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_PECCANCY_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_REFUELING_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SEQ_REPAIR_RECORD_ID
MINVALUE 1
MAXVALUE 9999999999999999999999999999
START WITH 1
INCREMENT BY 1;

/*==============================================================*/
/* Table: Q_MENU                                                */
/*==============================================================*/
create table Q_MENU  (
   ID                   VARCHAR2(50)                    not null,
   PID                  VARCHAR2(50),
   TEXT                 VARCHAR2(100),
   URL                  VARCHAR2(100),
   constraint PK_Q_MENU primary key (ID)
);

comment on table Q_MENU is
'菜单表';

comment on column Q_MENU.PID is
'父级ID';

comment on column Q_MENU.TEXT is
'内容';

comment on column Q_MENU.URL is
'对应地址';

/*==============================================================*/
/* Table: Q_ROLE                                                */
/*==============================================================*/
create table Q_ROLE  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   constraint PK_Q_ROLE primary key (ID)
);

comment on table Q_ROLE is
'角色表';

comment on column Q_ROLE.NAME is
'角色名称';

/*==============================================================*/
/* Table: Q_ROLE_MENU                                           */
/*==============================================================*/
create table Q_ROLE_MENU  (
   RID                  NUMBER,
   MID                  VARCHAR2(50)
);

comment on table Q_ROLE_MENU is
'角色菜单中间表';

comment on column Q_ROLE_MENU.RID is
'角色ID';

comment on column Q_ROLE_MENU.MID is
'菜单ID';

/*==============================================================*/
/* Table: Q_USERS                                               */
/*==============================================================*/
create table Q_USERS  (
   ID                   NUMBER                          not null,
   LNAME                VARCHAR2(50),
   LPASS                VARCHAR2(32),
   RNAME                VARCHAR2(50),
   AGE                  NUMBER,
   GENDER               VARCHAR2(1),
   ADDRESS              VARCHAR2(100),
   PHONE                VARCHAR2(11),
   DEPT_ID              NUMBER,
   POST_ID              NUMBER,
   BIRTHDAY             DATE,
   CARD                 VARCHAR2(18),
   ENTRYTIME            DATE,
   ISDISABLE            NUMBER DEFAULT(1),
   constraint PK_Q_USERS primary key (ID)
);

comment on table Q_USERS is
'用户表也叫职员表';

comment on column Q_USERS.LNAME is
'登录名';

comment on column Q_USERS.LPASS is
'密码，MD5加密';

comment on column Q_USERS.RNAME is
'真实姓名';

comment on column Q_USERS.AGE is
'年龄';

comment on column Q_USERS.GENDER is
'性别，M/F';

comment on column Q_USERS.ADDRESS is
'地址';

comment on column Q_USERS.PHONE is
'手机';

comment on column Q_USERS.DEPT_ID is
'部门ID';

comment on column Q_USERS.POST_ID is
'职位ID';

comment on column Q_USERS.BIRTHDAY is
'生日';

comment on column Q_USERS.CARD is
'身份证';

comment on column Q_USERS.ENTRYTIME is
'入职时间';

comment on column Q_USERS.ISDISABLE is
'是否停用，1、0';

/*==============================================================*/
/* Table: Q_USERS_ROLE                                          */
/*==============================================================*/
create table Q_USERS_ROLE  (
   USID                 NUMBER,
   RID                  NUMBER
);

comment on table Q_USERS_ROLE is
'用户角色中间表';

comment on column Q_USERS_ROLE.USID is
'用户ID';

comment on column Q_USERS_ROLE.RID is
'角色ID';

/*==============================================================*/
/* Table: ACCIDENT_RECORD                                       */
/*==============================================================*/
create table ACCIDENT_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   DRIVER_ID            NUMBER,
   ACC_DATE             TIMESTAMP,
   ACC_PLACE            VARCHAR2(100),
   ACC_EXPLAIN          VARCHAR2(200),
   WE_SITUATION         VARCHAR2(200),
   RESULT               VARCHAR2(200),
   OTHER_SITUATION      VARCHAR2(200),
   WE_AMOUNT            NUMBER(10,2),
   OTHER_AMOUNT         NUMBER(10,2),
   INS_AMOUNT           NUMBER(10,2),
   REMARKS              VARCHAR2(100),
   constraint PK_ACCIDENT_RECORD primary key (ID)
);

comment on table ACCIDENT_RECORD is
'事故记录';

comment on column ACCIDENT_RECORD.CAR_ID is
'车辆ID';

comment on column ACCIDENT_RECORD.DRIVER_ID is
'驾驶员ID';

comment on column ACCIDENT_RECORD.ACC_DATE is
'事故日期';

comment on column ACCIDENT_RECORD.ACC_PLACE is
'事故地点';

comment on column ACCIDENT_RECORD.ACC_EXPLAIN is
'事故说明';

comment on column ACCIDENT_RECORD.WE_SITUATION is
'我方情况';

comment on column ACCIDENT_RECORD.RESULT is
'处理结果';

comment on column ACCIDENT_RECORD.OTHER_SITUATION is
'对方情况';

comment on column ACCIDENT_RECORD.WE_AMOUNT is
'我方承担金额';

comment on column ACCIDENT_RECORD.OTHER_AMOUNT is
'对方承担金额';

comment on column ACCIDENT_RECORD.INS_AMOUNT is
'保险承担金额';

comment on column ACCIDENT_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: CAR                                                   */
/*==============================================================*/
create table CAR  (
   ID                   NUMBER                          not null,
   CAR_NO               VARCHAR2(8),
   CAR_BRAND            NUMBER,
   CAR_MODEL            NUMBER,
   CAR_COLOR            NUMBER,
   CAR_LOAD             NUMBER(4,2),
   CAR_SEATS            NUMBER,
   OIL_WEAR             NUMBER(4,2),
   INIT_MIL             NUMBER,
   MAINTAIN_MIL         NUMBER,
   MAINTAIN_PERIOD      NUMBER,
   ENGINE_NUM           VARCHAR2(20),
   FRAME_NUM            VARCHAR(20),
   SUP_ID               NUMBER,
   PURCHASE_PRICE       NUMBER(10,2),
   PURCHASE_DATE        DATE,
   DEPT_ID              NUMBER,
   CAR_STATE            NUMBER,
   REMARKS              VARCHAR2(100),
   ISDISABLE            NUMBER DEFAULT(1),
   constraint PK_CAR primary key (ID)
);

comment on table CAR is
'车辆档案';

comment on column CAR.CAR_NO is
'车牌';

comment on column CAR.CAR_BRAND is
'车辆品牌';

comment on column CAR.CAR_MODEL is
'车辆型号';

comment on column CAR.CAR_COLOR is
'车辆颜色';

comment on column CAR.CAR_LOAD is
'载重';

comment on column CAR.CAR_SEATS is
'座位数';

comment on column CAR.OIL_WEAR is
'油耗';

comment on column CAR.INIT_MIL is
'初始里程';

comment on column CAR.MAINTAIN_MIL is
'保养里程';

comment on column CAR.MAINTAIN_PERIOD is
'保养周期';

comment on column CAR.ENGINE_NUM is
'发动机号';

comment on column CAR.FRAME_NUM is
'车架号';

comment on column CAR.SUP_ID is
'供应商ID';

comment on column CAR.PURCHASE_PRICE is
'购买价格';

comment on column CAR.PURCHASE_DATE is
'购买日期';

comment on column CAR.DEPT_ID is
'部门ID';

comment on column CAR.CAR_STATE is
'状态';

comment on column CAR.REMARKS is
'备注';

comment on column CAR.ISDISABLE is
'是否停用，1、0';

/*==============================================================*/
/* Table: CAR_PIC                                               */
/*==============================================================*/
create table CAR_PIC  (
   ID                   NUMBER                          not null,
   CID                  NUMBER,
   IMG_PATH             VARCHAR2(100),
   DES                  VARCHAR2(36),
   constraint PK_CAR_PIC primary key (ID)
);

comment on table CAR_PIC is
'车辆图片';

comment on column CAR_PIC.ID is
'主键';

comment on column CAR_PIC.CID is
'车辆档案主键';

comment on column CAR_PIC.IMG_PATH is
'图片路径';

comment on column CAR_PIC.DES is
'描述';

/*==============================================================*/
/* Table: CURRENT_UNIT                                          */
/*==============================================================*/
create table CURRENT_UNIT  (
   ID                   NUMBER                          not null,
   UNIT_NAME            VARCHAR2(50),
   UNIT_TYPE            NUMBER,
   ADDRESS              VARCHAR2(100),
   TEL                  VARCHAR2(12),
   CONTACTS             VARCHAR2(50),
   REMARKS              VARCHAR2(100),
   ISDISABLE            NUMBER DEFAULT(1),
   constraint PK_CURRENT_UNIT primary key (ID)
);

comment on table CURRENT_UNIT is
'往来单位';

comment on column CURRENT_UNIT.UNIT_NAME is
'单位名称';

comment on column CURRENT_UNIT.UNIT_TYPE is
'单位类型ID';

comment on column CURRENT_UNIT.ADDRESS is
'地址';

comment on column CURRENT_UNIT.TEL is
'电话';

comment on column CURRENT_UNIT.CONTACTS is
'联系人';

comment on column CURRENT_UNIT.REMARKS is
'备注';

/*==============================================================*/
/* Table: DICTIONARY                                            */
/*==============================================================*/
create table DICTIONARY  (
   ID                   NUMBER                          not null,
   PID                  NUMBER,
   TEXT                 VARCHAR2(20),
   LEV                  NUMBER,
   ISDISABLE            NUMBER DEFAULT(1),
   constraint PK_DICTIONARY primary key (ID)
);

comment on table DICTIONARY is
'字典表';

comment on column DICTIONARY.ID is
'主键';

comment on column DICTIONARY.PID is
'父级ID';

comment on column DICTIONARY.TEXT is
'描述';

comment on column DICTIONARY.ISDISABLE is
'是否停用1,0';

/*==============================================================*/
/* Table: DRIVER                                                */
/*==============================================================*/
create table DRIVER  (
   ID                   NUMBER                          not null,
   NAME                 VARCHAR2(50),
   DEPT_ID              NUMBER,
   GENDER               VARCHAR2(1),
   BIRTHDAY             DATE,
   CARD                 VARCHAR2(18),
   PHONE                VARCHAR2(11),
   ENTRYTIME            DATE,
   ADDRESS              VARCHAR2(100),
   DRIVER_NO            VARCHAR2(18),
   DRIVER_TYPE          NUMBER,
   REMARKS              VARCHAR2(100),
   ISDISABLE            NUMBER DEFAULT(1),
   constraint PK_DRIVER primary key (ID)
);

comment on table DRIVER is
'驾驶员表';

comment on column DRIVER.NAME is
'姓名';
comment on column DRIVER.DEPT_ID is
'部门ID';

comment on column DRIVER.GENDER is
'性别';

comment on column DRIVER.BIRTHDAY is
'生日
';

comment on column DRIVER.CARD is
'身份证';

comment on column DRIVER.PHONE is
'手机';

comment on column DRIVER.ENTRYTIME is
'入职时间';

comment on column DRIVER.ADDRESS is
'住址';

comment on column DRIVER.DRIVER_NO is
'驾照号码';

comment on column DRIVER.DRIVER_TYPE is
'准驾类型ID';

comment on column DRIVER.REMARKS is
'备注';

comment on column DRIVER.ISDISABLE is
'是否启用，1、0';

/*==============================================================*/
/* Table: DRIVING_RECORD                                        */
/*==============================================================*/
create table DRIVING_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   DEPT_ID              NUMBER,
   BEGIN_TIME           TIMESTAMP,
   PRE_BACKTIME         TIMESTAMP,
   BACKTIME             TIMESTAMP,
   PERSONNEL            VARCHAR2(50),
   DRIVER_ID            NUMBER,
   DESTINATION          VARCHAR2(100),
   REASON               VARCHAR2(100),
   START_MIL            NUMBER(10,2),
   RETURN_MIL           NUMBER(10,2),
   THIS_MIL             NUMBER,
   PARK_PLACE           VARCHAR2(100),
   REMARKS              VARCHAR2(100),
   CREATE_UID           NUMBER,
   constraint PK_DRIVING_RECORD primary key (ID)
);

comment on table DRIVING_RECORD is
'出车记录表';

comment on column DRIVING_RECORD.CAR_ID is
'车辆ID';

comment on column DRIVING_RECORD.DEPT_ID is
'部门ID';

comment on column DRIVING_RECORD.BEGIN_TIME is
'出行时间';

comment on column DRIVING_RECORD.PRE_BACKTIME is
'预计回车时间';

comment on column DRIVING_RECORD.BACKTIME is
'回车时间';

comment on column DRIVING_RECORD.PERSONNEL is
'用车人';

comment on column DRIVING_RECORD.DRIVER_ID is
'驾驶员ID';

comment on column DRIVING_RECORD.DESTINATION is
'目的地';

comment on column DRIVING_RECORD.REASON is
'用车原因';

comment on column DRIVING_RECORD.START_MIL is
'出发里程';

comment on column DRIVING_RECORD.RETURN_MIL is
'回车里程';

comment on column DRIVING_RECORD.THIS_MIL is
'本次里程';

comment on column DRIVING_RECORD.PARK_PLACE is
'停放位置';

comment on column DRIVING_RECORD.REMARKS is
'回车备注';

comment on column DRIVING_RECORD.CREATE_UID is
'创建人';

/*==============================================================*/
/* Table: FEES_MANAGER                                          */
/*==============================================================*/
create table FEES_MANAGER  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   FEES_NAME            VARCHAR2(50),
   FEES_DATE            TIMESTAMP,
   FEES_AMOUNT          NUMBER(10,2),
   FEES_UNIT            NUMBER,
   OPERATOR             NUMBER,
   REMARKS              VARCHAR2(100),
   constraint PK_FEES_MANAGER primary key (ID)
);

comment on table FEES_MANAGER is
'规费记录表';

comment on column FEES_MANAGER.CAR_ID is
'车辆ID';

comment on column FEES_MANAGER.FEES_NAME is
'费用名称';

comment on column FEES_MANAGER.FEES_DATE is
'缴费日期';

comment on column FEES_MANAGER.FEES_AMOUNT is
'缴费金额';

comment on column FEES_MANAGER.FEES_UNIT is
'收费单位';

comment on column FEES_MANAGER.OPERATOR is
'经办人';

comment on column FEES_MANAGER.REMARKS is
'备注';

/*==============================================================*/
/* Table: INSPECTION_RECORD                                     */
/*==============================================================*/
create table INSPECTION_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   INS_NO               VARCHAR2(50),
   INS_DATE             DATE,
   INS_AMOUNT           NUMBER(10,2),
   VAO_ID               NUMBER,
   EXPIRE_DATE          DATE,
   OPERATOR             NUMBER,
   REMARKS              VARCHAR2(100),
   constraint PK_INSPECTION_RECORD primary key (ID)
);

comment on table INSPECTION_RECORD is
'年检记录表';

comment on column INSPECTION_RECORD.CAR_ID is
'车辆ID';

comment on column INSPECTION_RECORD.INS_NO is
'年检标志号';

comment on column INSPECTION_RECORD.INS_DATE is
'年检日期';

comment on column INSPECTION_RECORD.INS_AMOUNT is
'年检费用';

comment on column INSPECTION_RECORD.VAO_ID is
'车管所ID';

comment on column INSPECTION_RECORD.EXPIRE_DATE is
'到期日期';

comment on column INSPECTION_RECORD.OPERATOR is
'经办人';

comment on column INSPECTION_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: INSURANCE_RECORD                                      */
/*==============================================================*/
create table INSURANCE_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   INS_NO               VARCHAR2(50),
   INS_DATE             DATE,
   INS_TYPE             NUMBER,
   INS_AMOUNT           NUMBER(10,2),
   INS_UNIT             NUMBER,
   EXPIRE_DATE          DATE,
   OPERATOR             NUMBER,
   REMARKS              VARCHAR2(100),
   constraint PK_INSURANCE_RECORD primary key (ID)
);

comment on table INSURANCE_RECORD is
'保险记录';

comment on column INSURANCE_RECORD.CAR_ID is
'车辆ID';

comment on column INSURANCE_RECORD.INS_NO is
'保单号';

comment on column INSURANCE_RECORD.INS_DATE is
'保单日期';

comment on column INSURANCE_RECORD.INS_TYPE is
'保险种类';

comment on column INSURANCE_RECORD.INS_AMOUNT is
'保险金额';

comment on column INSURANCE_RECORD.INS_UNIT is
'保险公司';

comment on column INSURANCE_RECORD.EXPIRE_DATE is
'到期日期';

comment on column INSURANCE_RECORD.OPERATOR is
'经办人';

comment on column INSURANCE_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: MAINTAIN_RECORD                                       */
/*==============================================================*/
create table MAINTAIN_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER                          not null,
   MAIN_TYPE            NUMBER,
   MAIN_DATE            DATE,
   MAIN_AMOUNT          NUMBER(10,2),
   MAIN_UNIT            NUMBER,
   MAIN_MIL             NUMBER(10,2),
   MAIN_CONTENT         VARCHAR2(200),
   NEXT_DATE            DATE,
   NEXT_MIL             NUMBER(10,2),
   OPERATOR             NUMBER,
   REMARKS              VARCHAR2(100),
   constraint PK_MAINTAIN_RECORD primary key (ID, CAR_ID)
);

comment on table MAINTAIN_RECORD is
'保养记录表';

comment on column MAINTAIN_RECORD.CAR_ID is
'车辆ID';

comment on column MAINTAIN_RECORD.MAIN_TYPE is
'保养类型ID';

comment on column MAINTAIN_RECORD.MAIN_DATE is
'保养日期';

comment on column MAINTAIN_RECORD.MAIN_AMOUNT is
'保养金额';

comment on column MAINTAIN_RECORD.MAIN_UNIT is
'保养单位ID';

comment on column MAINTAIN_RECORD.MAIN_MIL is
'保养里程';

comment on column MAINTAIN_RECORD.MAIN_CONTENT is
'保养内容';

comment on column MAINTAIN_RECORD.NEXT_DATE is
'下次保养日期';

comment on column MAINTAIN_RECORD.NEXT_MIL is
'下次保养里程';

comment on column MAINTAIN_RECORD.OPERATOR is
'经办人';

comment on column MAINTAIN_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: PECANCY_RECCORD                                        */
/*==============================================================*/
create table PECCANCY_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER                          not null,
   PEC_OPTION           NUMBER,
   PEC_DATE             TIMESTAMP,
   FINE                 NUMBER(10,2),
   POINTS               NUMBER,
   DRIVER_ID            NUMBER,
   PEC_PLACE            VARCHAR2(100),
   REMARKS              VARCHAR2(100),
   constraint PK_PECCANCY_RECORD primary key (ID)
);

comment on table PECCANCY_RECORD is
'违章记录表';

comment on column PECCANCY_RECORD.CAR_ID is
'车辆ID';

comment on column PECCANCY_RECORD.PEC_OPTION is
'违章项目ID';

comment on column PECCANCY_RECORD.PEC_DATE is
'违章时间';

comment on column PECCANCY_RECORD.FINE is
'罚款';

comment on column PECCANCY_RECORD.POINTS is
'扣分';

comment on column PECCANCY_RECORD.DRIVER_ID is
'驾驶员';

comment on column PECCANCY_RECORD.PEC_PLACE is
'违章地点';

comment on column PECCANCY_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: REFUELING_RECORD                                      */
/*==============================================================*/
create table REFUELING_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   OIL_STATION_ID       NUMBER,
   OIL_DATE             TIMESTAMP,
   OIL_LABEL            NUMBER,
   UNIT_PRICE           NUMBER(5,2),
   TOTAL_AMOUNT         NUMBER(5,2),
   THIS_MIL             NUMBER,
   LAST_MIL             NUMBER,
   THIS_GAS_VOLUME      NUMBER(5,2),
   LAST_GAS_VOLUME      NUMBER(5,2),
   OPERATOR             NUMBER,
   REMARKS              VARCHAR2(100),
   constraint PK_REFUELING_RECORD primary key (ID)
);

comment on table REFUELING_RECORD is
'加油记录表';

comment on column REFUELING_RECORD.CAR_ID is
'车辆ID';

comment on column REFUELING_RECORD.OIL_STATION_ID is
'油气站ID';

comment on column REFUELING_RECORD.OIL_DATE is
'加油日期';

comment on column REFUELING_RECORD.OIL_LABEL is
'油料标号ID';

comment on column REFUELING_RECORD.UNIT_PRICE is
'单价';

comment on column REFUELING_RECORD.TOTAL_AMOUNT is
'总金额';

comment on column REFUELING_RECORD.THIS_MIL is
'本次里程';

comment on column REFUELING_RECORD.LAST_MIL is
'上次里程';

comment on column REFUELING_RECORD.THIS_GAS_VOLUME is
'本次油量';

comment on column REFUELING_RECORD.LAST_GAS_VOLUME is
'上次油量';

comment on column REFUELING_RECORD.OPERATOR is
'经办人';

comment on column REFUELING_RECORD.REMARKS is
'备注';

/*==============================================================*/
/* Table: REPAIR_RECORD                                         */
/*==============================================================*/
create table REPAIR_RECORD  (
   ID                   NUMBER                          not null,
   CAR_ID               NUMBER,
   REPAIR_DEPOT_ID      NUMBER,
   SEND_TIME            TIMESTAMP,
   ESTIMATE_TIME        TIMESTAMP,
   SEND_REASON          VARCHAR2(200),
   SEND_REMARKS         VARCHAR2(200),
   OPERATOR             NUMBER,
   REPAIR_TYPE          NUMBER,
   GET_TIME             TIMESTAMP,
   REPAIR_COST          NUMBER(10,2),
   REPAIR_OPTION        VARCHAR2(200),
   USE_STUFF            VARCHAR2(200),
   GET_REMARKS          VARCHAR2(200),
   constraint PK_REPAIR_RECORD primary key (ID)
);

comment on table REPAIR_RECORD is
'维修记录表';

comment on column REPAIR_RECORD.ID is
'主键';

comment on column REPAIR_RECORD.CAR_ID is
'车辆ID';

comment on column REPAIR_RECORD.REPAIR_DEPOT_ID is
'修理厂ID';

comment on column REPAIR_RECORD.SEND_TIME is
'送修日期';

comment on column REPAIR_RECORD.ESTIMATE_TIME is
'预计取车日期';

comment on column REPAIR_RECORD.SEND_REASON is
'送修原因';

comment on column REPAIR_RECORD.SEND_REMARKS is
'送修备注';

comment on column REPAIR_RECORD.OPERATOR is
'经办人ID';

comment on column REPAIR_RECORD.REPAIR_TYPE is
'维修类别ID';

comment on column REPAIR_RECORD.GET_TIME is
'取车日期';

comment on column REPAIR_RECORD.REPAIR_COST is
'维修费用';

comment on column REPAIR_RECORD.REPAIR_OPTION is
'维修项目';

comment on column REPAIR_RECORD.USE_STUFF is
'使用材料';

comment on column REPAIR_RECORD.GET_REMARKS is
'取车备注';

alter table ACCIDENT_RECORD
   add constraint FK_ACCIDENT_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table ACCIDENT_RECORD
   add constraint FK_ACCIDENT_DRIVER foreign key (DRIVER_ID)
      references DRIVER (ID);

alter table CAR
   add constraint FK_CAR_MODEL_DIC foreign key (CAR_MODEL)
      references DICTIONARY (ID);

alter table CAR
   add constraint FK_CAR_COLOR_DIC foreign key (CAR_COLOR)
      references DICTIONARY (ID);

alter table CAR
   add constraint FK_CAR_DEPTID_DIC foreign key (DEPT_ID)
      references DICTIONARY (ID);

alter table CAR
   add constraint FK_CAR_SUP_CURRENT foreign key (SUP_ID)
      references CURRENT_UNIT (ID);

alter table CAR
   add constraint FK_CAR_BRAND_DIC foreign key (CAR_BRAND)
      references DICTIONARY (ID);

alter table CAR_PIC
   add constraint FK_CAR_PIC_CAR foreign key (CID)
      references CAR (ID);

alter table CURRENT_UNIT
   add constraint FK_CUR_UNITTYPE_DIC foreign key (UNIT_TYPE)
      references DICTIONARY (ID);

alter table DRIVER
   add constraint FK_DEPT_DIC foreign key (DEPT_ID)
      references DICTIONARY (ID);

alter table DRIVER
   add constraint FK_DRIVER_TYPE_DIC foreign key (DRIVER_TYPE)
      references DICTIONARY (ID);

alter table DRIVING_RECORD
   add constraint FK_DRIVING_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table DRIVING_RECORD
   add constraint FK_DRIVING_DIC foreign key (DEPT_ID)
      references DICTIONARY (ID);
      
alter table DRIVING_RECORD
   add constraint FK_DRIVING_DRIVER foreign key (DRIVER_ID)
      references DRIVER (ID);

alter table DRIVING_RECORD
   add constraint FK_DRIVING_CREATEUSERS foreign key (CREATE_UID)
      references Q_USERS (ID);

alter table FEES_MANAGER
   add constraint FK_FEESMAN_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table FEES_MANAGER
   add constraint FK_FEES_MAN_UNIT foreign key (FEES_UNIT)
      references CURRENT_UNIT (ID);

alter table FEES_MANAGER
   add constraint FK_FEES_MAN_USERS foreign key (OPERATOR)
      references DRIVER (ID);

alter table INSPECTION_RECORD
   add constraint FK_INSPECTI_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table INSPECTION_RECORD
   add constraint FK_INSPECTI_UNIT foreign key (VAO_ID)
      references CURRENT_UNIT (ID);

alter table INSURANCE_RECORD
   add constraint FK_INSURANC_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table INSURANCE_RECORD
   add constraint FK_INSURANC_CURRENT_ foreign key (INS_UNIT)
      references CURRENT_UNIT (ID);

alter table INSURANCE_RECORD
   add constraint FK_INSURANC_USERS foreign key (OPERATOR)
      references Q_USERS (ID);

alter table MAINTAIN_RECORD
   add constraint FK_MAINTAIN_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table MAINTAIN_RECORD
   add constraint FK_MAINTAIN_UNIT foreign key (MAIN_UNIT)
      references CURRENT_UNIT (ID);

alter table MAINTAIN_RECORD
   add constraint FK_MAINTAIN_USERS foreign key (OPERATOR)
      references DRIVER (ID);

alter table PECCANCY_RECORD
   add constraint FK_PECCANCY_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table PECCANCY_RECORD
   add constraint FK_PECCANCY__DIC foreign key (PEC_OPTION)
      references DICTIONARY (ID);

alter table PECCANCY_RECORD
   add constraint FK_PECCANCY_DRIVER foreign key (DRIVER_ID)
      references DRIVER (ID);

alter table Q_ROLE_MENU
   add constraint FK_RM_ROLE foreign key (RID)
      references Q_ROLE (ID);

alter table Q_ROLE_MENU
   add constraint FK_RM_MENU foreign key (MID)
      references Q_MENU (ID);

alter table Q_USERS
   add constraint FK_USERS_DIC foreign key (DEPT_ID)
      references DICTIONARY (ID);

alter table Q_USERS_ROLE
   add constraint FK_UR_USERS foreign key (USID)
      references Q_USERS (ID);

alter table Q_USERS_ROLE
   add constraint FK_UR_ROLE foreign key (RID)
      references Q_ROLE (ID);

alter table REFUELING_RECORD
   add constraint FK_CAR_ID_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table REFUELING_RECORD
   add constraint FK_OSI_CURRENTUNIT foreign key (OIL_STATION_ID)
      references CURRENT_UNIT (ID);
      
alter table REFUELING_RECORD
   add constraint FK_OSI_DICTIONARY foreign key (OIL_LABEL)
      references DICTIONARY (ID);
      
alter table REFUELING_RECORD
   add constraint FK_OSI_DRIVER foreign key (OPERATOR)
      references DRIVER (ID);

alter table REPAIR_RECORD
   add constraint FK_REPAIR_CAR foreign key (CAR_ID)
      references CAR (ID);

alter table REPAIR_RECORD
   add constraint FK_REPAIR_UNIT foreign key (REPAIR_DEPOT_ID)
      references CURRENT_UNIT (ID);

alter table REPAIR_RECORD
   add constraint FK_REPAIRTYPE_DIC foreign key (REPAIR_TYPE)
      references DICTIONARY (ID);

------------------------------测试数据-------------------------------------
--添加部门一级信息
INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'部门',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'车辆颜色',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'车辆品牌',1);
COMMIT;

/*
INSERT INTO DICTIONARY(ID,TEXT)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'车辆型号',1);
COMMIT;
*/

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'单位类型',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'维修类别',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'保养类别',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'违章项目',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'费用名称',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'保险种类',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'职务',1);
COMMIT;

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'驾照类型',1);
COMMIT;

/*INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'车辆状态',1);
COMMIT;*/

INSERT INTO DICTIONARY(ID,TEXT,LEV)
VALUES(SEQ_DICTIONARY_ID.NEXTVAL,'油料标号',1);
COMMIT;


--添加用户测试数据
INSERT INTO Q_USERS
VALUES(SEQ_QUSERS_ID.nextval,
'admin',
'21232f297a57a5a743894a0e4a801fc3',
'超级管理员',20,'M','西安','12345678900',1,1,
SYSDATE,'610111199001012222',SYSDATE,DEFAULT);
COMMIT;

INSERT INTO Q_USERS
VALUES(SEQ_QUSERS_ID.nextval,'roy',
'd4c285227493531d0577140a1ed03964',
'郝敏一',30,'M','西安','12345678900',1,1,
SYSDATE,'610111199001022222',SYSDATE,DEFAULT);
COMMIT;

INSERT INTO Q_USERS
VALUES(SEQ_QUSERS_ID.nextval,'lily',
'89f288757f4d0693c99b007855fc075e','花花',20,'F','西安','12345678900',1,1,
SYSDATE,'610111199001032222',SYSDATE,DEFAULT);
COMMIT;

--添加菜单测试数据

--根目录
INSERT into Q_MENU
VALUES('L1',null,'企业车辆管理系统',null);
COMMIT;

--一级菜单
INSERT INTO Q_MENU
VALUES('L101','L1','日常处理',null);
INSERT into Q_MENU
VALUES('L102','L1','车辆状态',null);
INSERT INTO Q_MENU
VALUES('L103','L1','到期提醒',null);
INSERT into Q_MENU
VALUES('L104','L1','报表中心',null);
INSERT into Q_MENU
VALUES('L105','L1','基础设置',null);
INSERT into Q_MENU
VALUES('L106','L1','系统设置',null);
COMMIT;

--二级菜单
INSERT INTO Q_MENU
VALUES('L10101','L101','出车记录','DailyHandle/CarOut_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10102','L101','加油记录','DailyHandle/Gas_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10103','L101','维修记录','DailyHandle/Repair_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10104','L101','规费记录','DailyHandle/Fees_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10105','L101','保养记录','DailyHandle/Maintain_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10106','L101','违章记录','DailyHandle/Peccancy_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10107','L101','事故记录','DailyHandle/Accident_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10108','L101','年检记录','DailyHandle/Inspection_Record.jsp');
INSERT INTO Q_MENU
VALUES('L10109','L101','保险记录','DailyHandle/Insurance_Record.jsp');
COMMIT;

INSERT INTO Q_MENU
VALUES('L10201','L102','车辆状态','CarState/CarState.jsp');
COMMIT;

INSERT INTO Q_MENU
VALUES('L10301','L103','到期提醒','ExpirationReminder/Reminder.jsp');
COMMIT;

INSERT INTO Q_MENU
VALUES('L10401','L104','车辆费用对比','Report/Report_1.jsp');
INSERT INTO Q_MENU
VALUES('L10402','L104','车辆费用分布','Report/Report_2.jsp');
COMMIT;

INSERT INTO Q_MENU
VALUES('L10501','L105','系统字典','BasicSet/Sys_Dictionary.jsp');
INSERT INTO Q_MENU
VALUES('L10502','L105','驾驶员档案','BasicSet/Driver_INFO.jsp');
INSERT INTO Q_MENU
VALUES('L10503','L105','往来单位','BasicSet/Current_Unit.jsp');
INSERT INTO Q_MENU
VALUES('L10504','L105','车辆档案','BasicSet/Vehicle_File.jsp');
/*INSERT INTO Q_MENU
VALUES('L10505','L105','职员信息','BasicSet/Employees_INFO.jsp');*/
COMMIT;

INSERT INTO Q_MENU
VALUES('L10601','L106','角色管理','SysSet/Role_Management.jsp');
INSERT INTO Q_MENU
VALUES('L10602','L106','用户管理','SysSet/User_Management.jsp');
INSERT INTO Q_MENU
VALUES('L10603','L106','修改密码','SysSet/Modify_Password.jsp');
COMMIT;

--添加角色测试数据
INSERT INTO Q_ROLE VALUES(SEQ_QROLE_ID.Nextval,'管理员');
INSERT INTO Q_ROLE VALUES(SEQ_QROLE_ID.Nextval,'员工');
INSERT INTO Q_ROLE VALUES(SEQ_QROLE_ID.Nextval,'销售主管');
INSERT INTO Q_ROLE VALUES(SEQ_QROLE_ID.Nextval,'总经理');
COMMIT;

--添加角色菜单测试数据

INSERT INTO Q_ROLE_MENU VALUES(2,'L1');
INSERT INTO Q_ROLE_MENU VALUES(2,'L101');
INSERT INTO Q_ROLE_MENU VALUES(2,'L10101');

INSERT INTO Q_ROLE_MENU VALUES(4,'L1');
INSERT INTO Q_ROLE_MENU VALUES(4,'L104');
INSERT INTO Q_ROLE_MENU VALUES(4,'L10401');

--添加用户角色测试数据
INSERT INTO Q_USERS_ROLE VALUES(2,4);
INSERT INTO Q_USERS_ROLE VALUES(2,2);
COMMIT;

--添加驾驶员信息
INSERT INTO DRIVER(ID,NAME,DEPT_ID,GENDER,BIRTHDAY,
CARD,PHONE,ENTRYTIME,ADDRESS,DRIVER_NO,
DRIVER_TYPE,REMARKS,ISDISABLE)
VALUES(SEQ_DRIVER_ID.NEXTVAL,'舒马赫',NULL,'M',SYSDATE,'610111198307062251',
'13759951277',SYSDATE,'徐家庄','610111198307062251',NULL,'弯道小王子',DEFAULT);
COMMIT;

INSERT INTO DRIVER(ID,NAME,DEPT_ID,GENDER,BIRTHDAY,
CARD,PHONE,ENTRYTIME,ADDRESS,DRIVER_NO,
DRIVER_TYPE,REMARKS,ISDISABLE)
VALUES(SEQ_DRIVER_ID.NEXTVAL,'舒骡赫',NULL,'M',SYSDATE,'610111198307072251',
'13759951277',SYSDATE,'沙井村','610111198307072251',NULL,'烧胎太保',DEFAULT);
COMMIT;

INSERT INTO DRIVER(ID,NAME,DEPT_ID,GENDER,BIRTHDAY,
CARD,PHONE,ENTRYTIME,ADDRESS,DRIVER_NO,
DRIVER_TYPE,REMARKS,ISDISABLE)
VALUES(SEQ_DRIVER_ID.NEXTVAL,'舒兔赫',NULL,'F',SYSDATE,'610111198307082251',
'13759951277',SYSDATE,'八里村','610111198307082251',NULL,'直线太妹',DEFAULT);
COMMIT;

INSERT INTO DRIVER(ID,NAME,DEPT_ID,GENDER,BIRTHDAY,
CARD,PHONE,ENTRYTIME,ADDRESS,DRIVER_NO,
DRIVER_TYPE,REMARKS,ISDISABLE)
VALUES(SEQ_DRIVER_ID.NEXTVAL,'舒蛇赫',NULL,'F',SYSDATE,'610111198307092251',
'13759951277',SYSDATE,'蒋家寨','610111198307092251',NULL,'甩尾一姐',DEFAULT);
COMMIT;

INSERT INTO DRIVER(ID,NAME,DEPT_ID,GENDER,BIRTHDAY,
CARD,PHONE,ENTRYTIME,ADDRESS,DRIVER_NO,
DRIVER_TYPE,REMARKS,ISDISABLE)
VALUES(SEQ_DRIVER_ID.NEXTVAL,'舒虎赫',NULL,'M',SYSDATE,'610111198307012251',
'13759951277',SYSDATE,'黄埔庄','610111198307012251',NULL,'狂飙小霸王',DEFAULT);
COMMIT;

--添加往来单位信息
INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'延长壳牌大明宫加油站',NULL,'半坡路','029-82603321','刘经理','周末休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'延长壳牌太白路加油站',NULL,'太白路立交','029-82634621','范经理','周日休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'广本白云4S店',NULL,'西影路','029-82603322','师总','周六休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'浐灞大众4S店',NULL,'通源路','029-85603323','刘总','周六休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'高新奥迪4S店',NULL,'高新路','029-89603342','李总','周六休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'东郊车管所',NULL,'长乐路','029-82603323','张所长','周末休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'平安保险',NULL,'科创路','029-82603324','童顾问','周末休息',DEFAULT);
COMMIT;

INSERT INTO CURRENT_UNIT(ID,UNIT_NAME,UNIT_TYPE,ADDRESS,TEL,CONTACTS,REMARKS,ISDISABLE)
VALUES(SEQ_CURRENT_UNIT_ID.NEXTVAL,'纺织城高速收费站',NULL,'咸宁东路','029-82603325','马广','不休息',DEFAULT);
COMMIT;

--添加车辆信息
INSERT INTO CAR
VALUES(SEQ_CAR_ID.NEXTVAL,'陕A88888',NULL,NULL,1,
1.5,5,6.5,100,7500,6,'2200480','LHRP20160102001',
NULL,240000.12,SYSDATE,NULL,1,'备注',DEFAULT);
COMMIT;

INSERT INTO CAR
VALUES(SEQ_CAR_ID.NEXTVAL,'陕A66666',NULL,NULL,1,
2.0,5,11.0,50,10000,6,'5500661','QHRP20150102002',
NULL,550000.00,SYSDATE,NULL,1,'备注',DEFAULT);
COMMIT;

INSERT INTO CAR
VALUES(SEQ_CAR_ID.NEXTVAL,'陕A00001',NULL,NULL,1,
1.6,5,10.0,20,10000,6,'6600661','UYAQP20150102002',
NULL,1050000.00,SYSDATE,NULL,1,'备注',DEFAULT);
COMMIT;

---------添加字典子项
DECLARE
V_ID DICTIONARY.ID%TYPE;
V1 DICTIONARY.ID%TYPE;
V2 DICTIONARY.ID%TYPE;
V3 DICTIONARY.ID%TYPE;
V4 DICTIONARY.ID%TYPE;
V5 DICTIONARY.ID%TYPE;

BEGIN
     --部门子项
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='部门'; 
     
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'总经办',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'维护部',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'开发部',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'市场部',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'后勤办',2);
     COMMIT;
     
     --车辆颜色
     /*SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='车辆颜色'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'黑色',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'红色',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'白色',2);
     COMMIT;*/
     
     --车辆品牌
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='车辆品牌'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'大众',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'奥迪',2);
     COMMIT;
     
     --车辆型号
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='大众'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'帕萨特',3);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'捷达',3);
     COMMIT;
     
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='奥迪'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'A6L',3);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'A8',3);
     COMMIT;
     
     --单位类型
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='单位类型'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'4S商家',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'油气站',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'车管所',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'保险公司',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'其它',2);
     COMMIT;
     
     --维修类别
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='维修类别'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'小维修',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'大维修',2);
     COMMIT;
     
     --保养类别
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='保养类别'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'日常保养',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'大保养',2);
     COMMIT;
     
     --违章项目
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='违章项目'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'未按提示停车',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'闯红灯',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'酒驾',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'超速',2);
     COMMIT;
     
     --费用名称
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='费用名称'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'高速通行费',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'国道通行费',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'停车费',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'洗车费',2);
     COMMIT;
     
     --保险种类
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='保险种类'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'交强险',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'商业险',2);
     COMMIT;
     
     --职务
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='职务'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'总经理',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'主任',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'职员',2);
     COMMIT;
     
     --驾照类型
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='驾照类型'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'A1',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'A2',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'B1',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'C1',2);
     COMMIT;
     
     --油料标号添加
     SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='油料标号'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'92#',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'95#',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'93#',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'97#',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'98#',2);
     COMMIT;
     
     --车辆状态
     /*SELECT ID INTO V_ID FROM DICTIONARY WHERE TEXT='车辆状态'; 
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'可用',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'出车',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'维修',2);
     COMMIT;
     INSERT INTO DICTIONARY(ID,PID,TEXT,LEV)
     VALUES(SEQ_DICTIONARY_ID.NEXTVAL,V_ID,'其它',2);
     COMMIT;*/
     
     
     --用户职务部门更新
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='职员';
     SELECT ID INTO V2 FROM DICTIONARY WHERE TEXT='开发部';
     UPDATE Q_USERS SET POST_ID=V1,DEPT_ID=V2;
     COMMIT;
     
     --驾驶员部门以及准驾车型更新
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='后勤办';
     SELECT ID INTO V2 FROM DICTIONARY WHERE TEXT='A1';
     UPDATE DRIVER SET DEPT_ID=V1,DRIVER_TYPE=V2;
     COMMIT;
     
     --往来单位类型更新
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='油气站';
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='延长壳牌大明宫加油站';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='延长壳牌太白路加油站';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='4S商家';
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='广本白云4S店';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='浐灞大众4S店';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='高新奥迪4S店';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='车管所';
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='东郊车管所';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='保险公司';
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='平安保险';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     
     SELECT ID INTO V3 FROM DICTIONARY WHERE TEXT='单位类型';
     SELECT ID INTO V1 FROM DICTIONARY WHERE PID=V3 AND TEXT='其它';
     SELECT ID INTO V2 FROM CURRENT_UNIT WHERE UNIT_NAME='纺织城高速收费站';
     UPDATE CURRENT_UNIT SET UNIT_TYPE=V1 WHERE ID=V2;
     COMMIT;
     
     --车辆信息更新
     SELECT ID INTO V_ID FROM CAR WHERE CAR_NO='陕A88888';
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='大众';
     SELECT ID INTO V2 FROM DICTIONARY WHERE TEXT='帕萨特';
     --SELECT ID INTO V3 FROM DICTIONARY WHERE TEXT='黑色';
     SELECT ID INTO V4 FROM CURRENT_UNIT WHERE UNIT_NAME='浐灞大众4S店';
     SELECT ID INTO V5 FROM DICTIONARY WHERE TEXT='后勤办';
     UPDATE CAR 
        SET CAR_BRAND=V1,CAR_MODEL=V2,SUP_ID=V4,DEPT_ID=V5 
      WHERE ID=V_ID;    
     COMMIT;
     
     SELECT ID INTO V_ID FROM CAR WHERE CAR_NO='陕A66666';
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='奥迪';
     SELECT ID INTO V2 FROM DICTIONARY WHERE TEXT='A6L';
     --SELECT ID INTO V3 FROM DICTIONARY WHERE TEXT='黑色';
     SELECT ID INTO V4 FROM CURRENT_UNIT WHERE UNIT_NAME='高新奥迪4S店';
     SELECT ID INTO V5 FROM DICTIONARY WHERE TEXT='后勤办';
     UPDATE CAR 
        SET CAR_BRAND=V1,CAR_MODEL=V2,SUP_ID=V4,DEPT_ID=V5 
      WHERE ID=V_ID;
     COMMIT;
     
     SELECT ID INTO V_ID FROM CAR WHERE CAR_NO='陕A00001';
     SELECT ID INTO V1 FROM DICTIONARY WHERE TEXT='奥迪';
     SELECT ID INTO V2 FROM DICTIONARY WHERE TEXT='A8';
     --SELECT ID INTO V3 FROM DICTIONARY WHERE TEXT='黑色';
     SELECT ID INTO V4 FROM CURRENT_UNIT WHERE UNIT_NAME='高新奥迪4S店';
     SELECT ID INTO V5 FROM DICTIONARY WHERE TEXT='后勤办';
     UPDATE CAR 
        SET CAR_BRAND=V1,CAR_MODEL=V2,SUP_ID=V4,DEPT_ID=V5 
      WHERE ID=V_ID;
     COMMIT;
     
     
     
     
END;


