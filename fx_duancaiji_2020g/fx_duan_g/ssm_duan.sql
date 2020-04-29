/*
 Navicat Premium Data Transfer

 Source Server         : mysql_localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : ssm_duan

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/01/2020 10:14:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for d_collect_config
-- ----------------------------
DROP TABLE IF EXISTS `d_collect_config`;
CREATE TABLE `d_collect_config`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `meterId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '仪表Id',
  `fieldId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采集参数Id',
  `cycle` int(255) NULL DEFAULT NULL COMMENT '采集周期',
  `factor` decimal(10, 3) NULL DEFAULT NULL COMMENT '读数因子',
  `unit` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认单位',
  `data1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mudbus寄存器起始地址',
  `data2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '读取mudbus寄存器长度',
  `protocolId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协议',
  `parseId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解析协议Id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `meterId`(`meterId`, `fieldId`, `protocolId`, `parseId`) USING BTREE,
  INDEX `d_collect_config_ibfk_1`(`fieldId`) USING BTREE,
  INDEX `protocolId`(`protocolId`) USING BTREE,
  INDEX `parseId`(`parseId`) USING BTREE,
  CONSTRAINT `d_collect_config_ibfk_1` FOREIGN KEY (`fieldId`) REFERENCES `d_field` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_collect_config_ibfk_2` FOREIGN KEY (`protocolId`) REFERENCES `d_protocol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_collect_config_ibfk_3` FOREIGN KEY (`parseId`) REFERENCES `d_parser` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk1` FOREIGN KEY (`meterId`) REFERENCES `d_meter` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_collect_config
-- ----------------------------
INSERT INTO `d_collect_config` VALUES ('1', '1', '4043', 900, 0.010, 'kV', '6', '6', '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', 'ABB2FF86-92BB-4D29-8643-1966D801C001');
INSERT INTO `d_collect_config` VALUES ('2', '2', '4043', 900, 0.100, 'kV', 'B613', '0', '9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '4C055AC7-3785-43A2-9787-04092DF179BA');

-- ----------------------------
-- Table structure for d_com
-- ----------------------------
DROP TABLE IF EXISTS `d_com`;
CREATE TABLE `d_com`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `baudRate` int(255) NULL DEFAULT NULL COMMENT '波特率',
  `parity` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校验位',
  `dataBits` int(255) NULL DEFAULT NULL COMMENT '数据位',
  `stopBit` int(255) NULL DEFAULT NULL COMMENT '停止位',
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '暂留',
  `Isdefault` tinyint(1) NULL DEFAULT NULL COMMENT '是否默认',
  `portName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '串口名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_com
-- ----------------------------
INSERT INTO `d_com` VALUES ('1', 'COM2', 9600, 'NONE', 8, 1, NULL, 0, '测试串口', NULL);
INSERT INTO `d_com` VALUES ('2', 'COM', 300, '8', 8, 1, NULL, 0, '测试串口', '');
INSERT INTO `d_com` VALUES ('3', 'COM5', 9600, 'EVEN', 8, 1, NULL, 0, '虚拟串口', NULL);

-- ----------------------------
-- Table structure for d_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `d_dictionary`;
CREATE TABLE `d_dictionary`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_dictionary
-- ----------------------------
INSERT INTO `d_dictionary` VALUES ('6a44136e-f999-4321-80f1-b23618d994f8', 'xml保存路径', 'F:\\xml');

-- ----------------------------
-- Table structure for d_field
-- ----------------------------
DROP TABLE IF EXISTS `d_field`;
CREATE TABLE `d_field`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `defaultUnit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认单位',
  `defaultCycle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认采集周期',
  `paramType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数类型',
  `defaultFactor` decimal(10, 3) NULL DEFAULT NULL COMMENT '默认因子',
  `InventedParameterType` tinyint(1) NULL DEFAULT NULL COMMENT '虚拟参数类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_field
-- ----------------------------
INSERT INTO `d_field` VALUES ('4034', 'A相电流', 'A', '300', '1', 0.001, 0);
INSERT INTO `d_field` VALUES ('4035', 'B相电流', 'A', '300', '2', 0.001, 0);
INSERT INTO `d_field` VALUES ('4036', 'C相电流', 'A', '300', '3', 0.001, 0);
INSERT INTO `d_field` VALUES ('4037', '有功功率', 'kW', '300', '4', 0.000, 0);
INSERT INTO `d_field` VALUES ('4038', '有功电能', 'kWh', '300', '5', 0.100, 0);
INSERT INTO `d_field` VALUES ('4039', '（当前）正向有功总电能-FX03', 'KWh', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4040', '（当前）正向无功总电能-FX03', 'Kvarh', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4041', 'A相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4042', 'B相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4043', 'C相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4044', 'A相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4045', 'B相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4046', 'C相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4047', '瞬时有功总功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4048', 'A相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4049', 'B相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4050', 'C相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4051', '瞬时无功总功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4052', 'A相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4053', 'B相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4054', 'C相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4055', '总功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4056', 'A相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4057', 'B相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4058', 'C相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4059', '瞬时视在总功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4060', 'A相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4061', 'B相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4062', 'C相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4063', '电压不平衡率-FX03', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4064', '电流不平衡率-FX03', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4065', 'A相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4066', 'B相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4067', 'C相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4068', 'A相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4069', 'B相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4070', 'C相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4071', 'A相电压总谐波（3次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4072', 'A相电压总谐波（5次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4073', 'A相电压总谐波（7次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4074', 'A相电压总谐波（9次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4075', 'A相电压总谐波（11次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4076', 'A相电压总谐波（13次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4077', 'A相电压总谐波（15次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4078', 'A相电压总谐波（17次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4079', 'A相电压总谐波（19次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4080', 'A相电压总谐波（21次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4081', '（当前）正向有功总电能-FX03', 'KWh', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4082', '（当前）正向无功总电能-FX03', 'Kvarh', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4083', 'A相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4084', 'B相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4085', 'C相电压-FX03', 'V', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4086', 'A相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4087', 'B相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4088', 'C相电流-FX03', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4089', '瞬时有功总功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4090', 'A相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4091', 'B相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4092', 'C相有功功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4093', '瞬时无功总功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4094', 'A相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4095', 'B相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4096', 'C相无功功率-FX03', 'kvar', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4097', '总功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4098', 'A相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4099', 'B相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4100', 'C相功率因数-FX03', NULL, '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4101', '瞬时视在总功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4102', 'A相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4103', 'B相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4104', 'C相视在功率-FX03', 'KW', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4105', '电压不平衡率-FX03', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4106', '电流不平衡率-FX03', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4107', 'A相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4108', 'B相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4109', 'C相电压总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4110', 'A相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4111', 'B相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4112', 'C相电流总谐波', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4113', 'A相电压总谐波（3次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4114', 'A相电压总谐波（5次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4115', 'A相电压总谐波（7次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4116', 'A相电压总谐波（9次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4117', 'A相电压总谐波（11次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4118', 'A相电压总谐波（13次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4119', 'A相电压总谐波（15次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4120', 'A相电压总谐波（17次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4121', 'A相电压总谐波（19次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4122', 'A相电压总谐波（21次）', '%', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4123', '正向有功总电能-07', 'kwh', '900', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4124', 'AB相电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4125', 'BC线电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4126', 'CA线电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4145', 'A相电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4146', 'B相电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4147', 'C相电压-07', 'V', '900', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4148', 'A相电流-07', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4149', 'B相电流-07', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4150', 'C相电流-07', 'A', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4151', '瞬时总有功功率-07', 'KW', '900', '0', 0.000, 0);
INSERT INTO `d_field` VALUES ('4152', '总功率因数-07', '.', '900', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4153', '单项有功电能-07', 'Kwh', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4154', '单项电压-07', 'V', '60', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4155', '单项电流-07', 'A', '60', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4156', '单项有功功率-07', 'KW', '60', '0', 0.000, 0);
INSERT INTO `d_field` VALUES ('4157', '单相功率因数-07', '.', '60', '0', 0.001, 0);
INSERT INTO `d_field` VALUES ('4158', '正向有功总电能-FX', 'KWh', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4159', '反向有功总电能-FX', 'KWh', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4160', 'A相电压-FX', 'V', '60', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4161', 'B相电压-FX', 'V', '60', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4162', 'C相电压-FX', 'V', '60', '0', 0.100, 0);
INSERT INTO `d_field` VALUES ('4163', 'A相电流-FX', 'A', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4164', 'B相电流-FX', 'A', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4165', 'C相电流-FX', 'A', '60', '0', 0.010, 0);
INSERT INTO `d_field` VALUES ('4218', '当前剩余金额', '元', '900', '0', 0.010, 0);

-- ----------------------------
-- Table structure for d_meter
-- ----------------------------
DROP TABLE IF EXISTS `d_meter`;
CREATE TABLE `d_meter`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `modelId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号Id',
  `macAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Mac地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `companyId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司Id',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `comId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Com口Id',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否可用 0 不可用 1可用',
  `protocolId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '协议id',
  `parserId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '解析协议Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comId`(`comId`) USING BTREE,
  INDEX `d_meter_ibfk_1`(`modelId`) USING BTREE,
  CONSTRAINT `d_meter_ibfk_1` FOREIGN KEY (`modelId`) REFERENCES `d_model` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_meter_ibfk_2` FOREIGN KEY (`comId`) REFERENCES `d_com` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_meter
-- ----------------------------
INSERT INTO `d_meter` VALUES ('1', '223', NULL, '测试仪表fx03', '', '01', '2', 1, '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', 'ABB2FF86-92BB-4D29-8643-1966D801C001');
INSERT INTO `d_meter` VALUES ('2', '244', '02222222222', '测试仪表fx05', NULL, '19100005', '2', 1, '9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '4C055AC7-3785-43A2-9787-04092DF179BA');
INSERT INTO `d_meter` VALUES ('3', '255', '', '虚拟07仪表', NULL, '000000000003', '3', 1, '0F196A7E-8D52-4CEE-B71D-B1975041D138', '404967CA-79B0-46D5-8222-2A7F349B21A2');

-- ----------------------------
-- Table structure for d_model
-- ----------------------------
DROP TABLE IF EXISTS `d_model`;
CREATE TABLE `d_model`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `isBigEndian` tinyint(1) NULL DEFAULT NULL COMMENT '是 否大端序',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `isDefault` tinyint(1) NULL DEFAULT NULL COMMENT '是否默认',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_model
-- ----------------------------
INSERT INTO `d_model` VALUES ('222', 0, '木星Energy+', 1, NULL);
INSERT INTO `d_model` VALUES ('223', 2, 'FX03高压', 0, NULL);
INSERT INTO `d_model` VALUES ('224', 0, '三星单相', 0, NULL);
INSERT INTO `d_model` VALUES ('225', 2, 'FX03低压', 0, NULL);
INSERT INTO `d_model` VALUES ('227', 0, '三星三相', 0, NULL);
INSERT INTO `d_model` VALUES ('228', 0, '威胜', 0, NULL);
INSERT INTO `d_model` VALUES ('229', 0, '科陆12', 0, NULL);
INSERT INTO `d_model` VALUES ('230', 0, '科陆15', 0, NULL);
INSERT INTO `d_model` VALUES ('233', 0, '科陆', 0, NULL);
INSERT INTO `d_model` VALUES ('244', 0, 'FX05', 1, NULL);
INSERT INTO `d_model` VALUES ('255', 0, '虚拟仪表型号', 0, NULL);
INSERT INTO `d_model` VALUES ('5bb05a5a-38e2-4f8e-954f-f87073d76bd0', NULL, 'FX03高压', NULL, '');
INSERT INTO `d_model` VALUES ('9c04b9c3-5492-4f19-bbff-2ab83bed6d83', NULL, 'FX03高压1111', NULL, '');

-- ----------------------------
-- Table structure for d_model_field
-- ----------------------------
DROP TABLE IF EXISTS `d_model_field`;
CREATE TABLE `d_model_field`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `modelId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号ID',
  `fieldId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fieldId`(`fieldId`) USING BTREE,
  INDEX `meterModelId`(`modelId`) USING BTREE,
  CONSTRAINT `d_model_field_ibfk_1` FOREIGN KEY (`fieldId`) REFERENCES `d_field` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_model_field_ibfk_2` FOREIGN KEY (`modelId`) REFERENCES `d_model` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_model_field
-- ----------------------------
INSERT INTO `d_model_field` VALUES ('2e22d43f-5266-41c3-8136-4a51e1d2164e', '222', '4035', NULL);
INSERT INTO `d_model_field` VALUES ('33b46d04-9b38-4c53-9f91-058f9125b434', '255', '4038', NULL);
INSERT INTO `d_model_field` VALUES ('4475', '223', '4039', '6');
INSERT INTO `d_model_field` VALUES ('4476', '223', '4040', '7');
INSERT INTO `d_model_field` VALUES ('4477', '223', '4041', '8');
INSERT INTO `d_model_field` VALUES ('4478', '223', '4042', '9');
INSERT INTO `d_model_field` VALUES ('4479', '223', '4043', '10');
INSERT INTO `d_model_field` VALUES ('4480', '223', '4044', '11');
INSERT INTO `d_model_field` VALUES ('4481', '223', '4045', '12');
INSERT INTO `d_model_field` VALUES ('4482', '223', '4046', '13');
INSERT INTO `d_model_field` VALUES ('4483', '223', '4047', '14');
INSERT INTO `d_model_field` VALUES ('4484', '223', '4048', '15');
INSERT INTO `d_model_field` VALUES ('4485', '223', '4049', '16');
INSERT INTO `d_model_field` VALUES ('4486', '223', '4050', '17');
INSERT INTO `d_model_field` VALUES ('4487', '223', '4051', '18');
INSERT INTO `d_model_field` VALUES ('4488', '223', '4052', '19');
INSERT INTO `d_model_field` VALUES ('4489', '223', '4053', '20');
INSERT INTO `d_model_field` VALUES ('4490', '223', '4054', '21');
INSERT INTO `d_model_field` VALUES ('4491', '223', '4055', '22');
INSERT INTO `d_model_field` VALUES ('4492', '223', '4056', '23');
INSERT INTO `d_model_field` VALUES ('4493', '223', '4057', '24');
INSERT INTO `d_model_field` VALUES ('4494', '223', '4058', '25');
INSERT INTO `d_model_field` VALUES ('4495', '223', '4059', '26');
INSERT INTO `d_model_field` VALUES ('4496', '223', '4060', '27');
INSERT INTO `d_model_field` VALUES ('4497', '223', '4061', '28');
INSERT INTO `d_model_field` VALUES ('4498', '223', '4062', '29');
INSERT INTO `d_model_field` VALUES ('4499', '223', '4063', '30');
INSERT INTO `d_model_field` VALUES ('4500', '223', '4064', '31');
INSERT INTO `d_model_field` VALUES ('4501', '223', '4065', '32');
INSERT INTO `d_model_field` VALUES ('4502', '223', '4066', '33');
INSERT INTO `d_model_field` VALUES ('4503', '223', '4067', '34');
INSERT INTO `d_model_field` VALUES ('4504', '223', '4068', '35');
INSERT INTO `d_model_field` VALUES ('4505', '223', '4069', '36');
INSERT INTO `d_model_field` VALUES ('4506', '223', '4070', '37');
INSERT INTO `d_model_field` VALUES ('4507', '223', '4071', '38');
INSERT INTO `d_model_field` VALUES ('4508', '223', '4072', '39');
INSERT INTO `d_model_field` VALUES ('4509', '223', '4073', '40');
INSERT INTO `d_model_field` VALUES ('4510', '223', '4074', '41');
INSERT INTO `d_model_field` VALUES ('4511', '223', '4075', '42');
INSERT INTO `d_model_field` VALUES ('4512', '223', '4076', '43');
INSERT INTO `d_model_field` VALUES ('4513', '223', '4077', '44');
INSERT INTO `d_model_field` VALUES ('4514', '223', '4078', '45');
INSERT INTO `d_model_field` VALUES ('4515', '223', '4079', '46');
INSERT INTO `d_model_field` VALUES ('4516', '223', '4080', '47');
INSERT INTO `d_model_field` VALUES ('4517', '225', '4039', '59');
INSERT INTO `d_model_field` VALUES ('4518', '225', '4040', '60');
INSERT INTO `d_model_field` VALUES ('4519', '225', '4041', '61');
INSERT INTO `d_model_field` VALUES ('4520', '225', '4042', '62');
INSERT INTO `d_model_field` VALUES ('4521', '225', '4043', '63');
INSERT INTO `d_model_field` VALUES ('4522', '225', '4044', '64');
INSERT INTO `d_model_field` VALUES ('4523', '225', '4045', '65');
INSERT INTO `d_model_field` VALUES ('4524', '225', '4046', '66');
INSERT INTO `d_model_field` VALUES ('4525', '225', '4047', '67');
INSERT INTO `d_model_field` VALUES ('4526', '225', '4048', '68');
INSERT INTO `d_model_field` VALUES ('4527', '225', '4049', '69');
INSERT INTO `d_model_field` VALUES ('4528', '225', '4050', '70');
INSERT INTO `d_model_field` VALUES ('4529', '225', '4051', '71');
INSERT INTO `d_model_field` VALUES ('4530', '225', '4052', '72');
INSERT INTO `d_model_field` VALUES ('4531', '225', '4053', '73');
INSERT INTO `d_model_field` VALUES ('4532', '225', '4054', '74');
INSERT INTO `d_model_field` VALUES ('4533', '225', '4055', '75');
INSERT INTO `d_model_field` VALUES ('4534', '225', '4056', '76');
INSERT INTO `d_model_field` VALUES ('4535', '225', '4057', '77');
INSERT INTO `d_model_field` VALUES ('4536', '225', '4058', '78');
INSERT INTO `d_model_field` VALUES ('4537', '225', '4059', '79');
INSERT INTO `d_model_field` VALUES ('4538', '225', '4060', '80');
INSERT INTO `d_model_field` VALUES ('4539', '225', '4061', '81');
INSERT INTO `d_model_field` VALUES ('4540', '225', '4062', '82');
INSERT INTO `d_model_field` VALUES ('4541', '225', '4063', '83');
INSERT INTO `d_model_field` VALUES ('4542', '225', '4064', '84');
INSERT INTO `d_model_field` VALUES ('4543', '225', '4065', '85');
INSERT INTO `d_model_field` VALUES ('4544', '225', '4066', '86');
INSERT INTO `d_model_field` VALUES ('4545', '225', '4067', '87');
INSERT INTO `d_model_field` VALUES ('4546', '225', '4068', '88');
INSERT INTO `d_model_field` VALUES ('4547', '225', '4069', '89');
INSERT INTO `d_model_field` VALUES ('4548', '225', '4070', '90');
INSERT INTO `d_model_field` VALUES ('4549', '225', '4071', '91');
INSERT INTO `d_model_field` VALUES ('4550', '225', '4072', '92');
INSERT INTO `d_model_field` VALUES ('4551', '225', '4073', '93');
INSERT INTO `d_model_field` VALUES ('4552', '225', '4074', '94');
INSERT INTO `d_model_field` VALUES ('4553', '225', '4075', '95');
INSERT INTO `d_model_field` VALUES ('4554', '225', '4076', '96');
INSERT INTO `d_model_field` VALUES ('4555', '225', '4077', '97');
INSERT INTO `d_model_field` VALUES ('4556', '225', '4078', '98');
INSERT INTO `d_model_field` VALUES ('4557', '225', '4079', '99');
INSERT INTO `d_model_field` VALUES ('4558', '225', '4080', '100');
INSERT INTO `d_model_field` VALUES ('4600', '227', '4123', '107');
INSERT INTO `d_model_field` VALUES ('4612', '228', '4123', '134');
INSERT INTO `d_model_field` VALUES ('4615', '228', '4145', '137');
INSERT INTO `d_model_field` VALUES ('4623', '227', '4145', '116');
INSERT INTO `d_model_field` VALUES ('4624', '228', '4146', '145');
INSERT INTO `d_model_field` VALUES ('4625', '228', '4147', '146');
INSERT INTO `d_model_field` VALUES ('4626', '228', '4148', '147');
INSERT INTO `d_model_field` VALUES ('4627', '228', '4149', '148');
INSERT INTO `d_model_field` VALUES ('4628', '228', '4150', '149');
INSERT INTO `d_model_field` VALUES ('4629', '228', '4151', '150');
INSERT INTO `d_model_field` VALUES ('4630', '228', '4152', '151');
INSERT INTO `d_model_field` VALUES ('4631', '227', '4146', '117');
INSERT INTO `d_model_field` VALUES ('4632', '227', '4147', '118');
INSERT INTO `d_model_field` VALUES ('4633', '227', '4148', '119');
INSERT INTO `d_model_field` VALUES ('4634', '227', '4149', '120');
INSERT INTO `d_model_field` VALUES ('4635', '227', '4150', '121');
INSERT INTO `d_model_field` VALUES ('4636', '227', '4151', '122');
INSERT INTO `d_model_field` VALUES ('4637', '227', '4152', '123');
INSERT INTO `d_model_field` VALUES ('4638', '230', '4123', '161');
INSERT INTO `d_model_field` VALUES ('4639', '230', '4145', '162');
INSERT INTO `d_model_field` VALUES ('4640', '230', '4146', '163');
INSERT INTO `d_model_field` VALUES ('4641', '230', '4147', '164');
INSERT INTO `d_model_field` VALUES ('4642', '230', '4148', '165');
INSERT INTO `d_model_field` VALUES ('4643', '230', '4149', '166');
INSERT INTO `d_model_field` VALUES ('4644', '230', '4150', '167');
INSERT INTO `d_model_field` VALUES ('4645', '230', '4151', '168');
INSERT INTO `d_model_field` VALUES ('4646', '230', '4152', '169');
INSERT INTO `d_model_field` VALUES ('4647', '229', '4123', '152');
INSERT INTO `d_model_field` VALUES ('4648', '229', '4145', '153');
INSERT INTO `d_model_field` VALUES ('4649', '229', '4146', '154');
INSERT INTO `d_model_field` VALUES ('4650', '229', '4147', '155');
INSERT INTO `d_model_field` VALUES ('4651', '229', '4148', '156');
INSERT INTO `d_model_field` VALUES ('4652', '229', '4149', '157');
INSERT INTO `d_model_field` VALUES ('4653', '229', '4150', '158');
INSERT INTO `d_model_field` VALUES ('4654', '229', '4151', '159');
INSERT INTO `d_model_field` VALUES ('4655', '229', '4152', '160');
INSERT INTO `d_model_field` VALUES ('4656', '224', '4153', '52');
INSERT INTO `d_model_field` VALUES ('4657', '224', '4154', '53');
INSERT INTO `d_model_field` VALUES ('4658', '224', '4155', '54');
INSERT INTO `d_model_field` VALUES ('4659', '224', '4156', '55');
INSERT INTO `d_model_field` VALUES ('4660', '224', '4157', '56');
INSERT INTO `d_model_field` VALUES ('4671', '233', '4162', '170');
INSERT INTO `d_model_field` VALUES ('4672', '233', '4161', '171');
INSERT INTO `d_model_field` VALUES ('4673', '233', '4160', '172');
INSERT INTO `d_model_field` VALUES ('4687', '233', '4163', '173');
INSERT INTO `d_model_field` VALUES ('4688', '233', '4164', '174');
INSERT INTO `d_model_field` VALUES ('4689', '233', '4165', '175');
INSERT INTO `d_model_field` VALUES ('4692', '233', '4159', '178');
INSERT INTO `d_model_field` VALUES ('4693', '233', '4158', '179');
INSERT INTO `d_model_field` VALUES ('4776', '227', '4218', '125');
INSERT INTO `d_model_field` VALUES ('4777', '224', '4218', '58');
INSERT INTO `d_model_field` VALUES ('4788', '244', '4043', NULL);
INSERT INTO `d_model_field` VALUES ('4e8e5800-cac4-493a-9d02-96739c9a4a26', '222', '4036', NULL);
INSERT INTO `d_model_field` VALUES ('8eb7a539-fc26-48aa-85ef-36b2a3c11e42', '222', '4038', NULL);
INSERT INTO `d_model_field` VALUES ('8ef6ac01-8e86-4e11-b6cc-d2ea4a82d7e2', '255', '4036', NULL);
INSERT INTO `d_model_field` VALUES ('9382488c-9ffa-4212-a033-124aad97108d', '255', '4037', NULL);
INSERT INTO `d_model_field` VALUES ('a4ab8546-fc68-4abd-9c76-7ae81792e727', '222', '4034', NULL);
INSERT INTO `d_model_field` VALUES ('ae8f3ec7-4354-4d95-8665-21f398d38f7b', '255', '4034', NULL);
INSERT INTO `d_model_field` VALUES ('db788586-ad8e-480b-aa00-e609df07fbd3', '222', '4037', NULL);
INSERT INTO `d_model_field` VALUES ('f4172b71-5ea0-41e7-a2be-d39243b237c2', '255', '4035', NULL);

-- ----------------------------
-- Table structure for d_model_protocol
-- ----------------------------
DROP TABLE IF EXISTS `d_model_protocol`;
CREATE TABLE `d_model_protocol`  (
  `modelId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `protocolId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `modelId`(`modelId`) USING BTREE,
  INDEX `protocolId`(`protocolId`) USING BTREE,
  CONSTRAINT `d_model_protocol_ibfk_1` FOREIGN KEY (`modelId`) REFERENCES `d_model` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_model_protocol_ibfk_2` FOREIGN KEY (`protocolId`) REFERENCES `d_protocol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_model_protocol
-- ----------------------------
INSERT INTO `d_model_protocol` VALUES ('255', '0F196A7E-8D52-4CEE-B71D-B1975041D138', '0');
INSERT INTO `d_model_protocol` VALUES ('223', '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', '1');
INSERT INTO `d_model_protocol` VALUES ('244', '9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '10');
INSERT INTO `d_model_protocol` VALUES ('222', '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', '2');
INSERT INTO `d_model_protocol` VALUES ('224', '0F196A7E-8D52-4CEE-B71D-B1975041D138', '3');
INSERT INTO `d_model_protocol` VALUES ('225', '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', '4');
INSERT INTO `d_model_protocol` VALUES ('227', '0F196A7E-8D52-4CEE-B71D-B1975041D138', '5');
INSERT INTO `d_model_protocol` VALUES ('228', '93197d47-477d-4002-a419-ea4ce342e486', '6');
INSERT INTO `d_model_protocol` VALUES ('229', '93197d47-477d-4002-a419-ea4ce342e486', '7');
INSERT INTO `d_model_protocol` VALUES ('230', '49799db7-63c5-4bc3-813e-187c9d066f88', '8');
INSERT INTO `d_model_protocol` VALUES ('233', '9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '9');

-- ----------------------------
-- Table structure for d_model_protocol_field_template
-- ----------------------------
DROP TABLE IF EXISTS `d_model_protocol_field_template`;
CREATE TABLE `d_model_protocol_field_template`  (
  `modelId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `protocolId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fieldId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data1` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data2` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`modelId`, `protocolId`, `fieldId`) USING BTREE,
  INDEX `protocolId`(`protocolId`) USING BTREE,
  INDEX `fieldId`(`fieldId`) USING BTREE,
  CONSTRAINT `d_model_protocol_field_template_ibfk_1` FOREIGN KEY (`modelId`) REFERENCES `d_model` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_model_protocol_field_template_ibfk_2` FOREIGN KEY (`protocolId`) REFERENCES `d_protocol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_model_protocol_field_template_ibfk_3` FOREIGN KEY (`fieldId`) REFERENCES `d_model_field` (`fieldId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_model_protocol_field_template
-- ----------------------------
INSERT INTO `d_model_protocol_field_template` VALUES ('223', '20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', '4043', '6', '6', NULL);
INSERT INTO `d_model_protocol_field_template` VALUES ('244', '9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '4043', 'B613', '0', 'B613');
INSERT INTO `d_model_protocol_field_template` VALUES ('255', '0F196A7E-8D52-4CEE-B71D-B1975041D138', '4043', '02010300', '0', '02010300');

-- ----------------------------
-- Table structure for d_parser
-- ----------------------------
DROP TABLE IF EXISTS `d_parser`;
CREATE TABLE `d_parser`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_parser
-- ----------------------------
INSERT INTO `d_parser` VALUES ('404967CA-79B0-46D5-8222-2A7F349B21A2', 'DTL64507');
INSERT INTO `d_parser` VALUES ('4C055AC7-3785-43A2-9787-04092DF179BA', '645');
INSERT INTO `d_parser` VALUES ('69de3af8-6684-4a4f-9d3e-8c2aaa07ae03', '64507-F');
INSERT INTO `d_parser` VALUES ('ABB2FF86-92BB-4D29-8643-1966D801C001', 'Modbus RTU');
INSERT INTO `d_parser` VALUES ('e157e378-fad5-4947-ae2e-bf8bd08b842f', '64507-F2015');

-- ----------------------------
-- Table structure for d_protocol
-- ----------------------------
DROP TABLE IF EXISTS `d_protocol`;
CREATE TABLE `d_protocol`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_protocol
-- ----------------------------
INSERT INTO `d_protocol` VALUES ('0F196A7E-8D52-4CEE-B71D-B1975041D138', '645_07', NULL);
INSERT INTO `d_protocol` VALUES ('20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', 'MODBUS_03', NULL);
INSERT INTO `d_protocol` VALUES ('49799db7-63c5-4bc3-813e-187c9d066f88', '64507-F2015', NULL);
INSERT INTO `d_protocol` VALUES ('93197d47-477d-4002-a419-ea4ce342e486', '64507-F', NULL);
INSERT INTO `d_protocol` VALUES ('9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '645_97', NULL);
INSERT INTO `d_protocol` VALUES ('BA1E36E0-341C-4640-BDC9-6FCFA628EB03', 'MODBUS_04', NULL);
INSERT INTO `d_protocol` VALUES ('e205e91e-421b-42e7-b274-440b8fa89d3f', '645_97_15', NULL);

-- ----------------------------
-- Table structure for d_protocol_parser
-- ----------------------------
DROP TABLE IF EXISTS `d_protocol_parser`;
CREATE TABLE `d_protocol_parser`  (
  `protocolId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '协议Id',
  `parserId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '解析协议Id',
  UNIQUE INDEX `唯一索引`(`protocolId`, `parserId`) USING BTREE,
  INDEX `parserId`(`parserId`) USING BTREE,
  CONSTRAINT `d_protocol_parser_ibfk_1` FOREIGN KEY (`protocolId`) REFERENCES `d_protocol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `d_protocol_parser_ibfk_2` FOREIGN KEY (`parserId`) REFERENCES `d_parser` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of d_protocol_parser
-- ----------------------------
INSERT INTO `d_protocol_parser` VALUES ('0F196A7E-8D52-4CEE-B71D-B1975041D138', '404967CA-79B0-46D5-8222-2A7F349B21A2');
INSERT INTO `d_protocol_parser` VALUES ('9C4A1BB5-C3FA-4C90-B33D-4732994EF966', '4C055AC7-3785-43A2-9787-04092DF179BA');
INSERT INTO `d_protocol_parser` VALUES ('49799db7-63c5-4bc3-813e-187c9d066f88', '69de3af8-6684-4a4f-9d3e-8c2aaa07ae03');
INSERT INTO `d_protocol_parser` VALUES ('93197d47-477d-4002-a419-ea4ce342e486', '69de3af8-6684-4a4f-9d3e-8c2aaa07ae03');
INSERT INTO `d_protocol_parser` VALUES ('20D7F49A-9D79-400A-AE77-FAE6BAA9AB83', 'ABB2FF86-92BB-4D29-8643-1966D801C001');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` int(11) NOT NULL COMMENT '父级菜单id',
  `parent_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `type` tinyint(4) NOT NULL COMMENT '菜单类型 1:目录 2：菜单 2：按钮',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权编码',
  `color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` tinyint(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, '系统管理', 0, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_permission` VALUES (2, '用户管理', 1, '系统管理', 2, 'user/listUI', 'user:listUI', NULL, NULL, 1);
INSERT INTO `t_permission` VALUES (3, '新增', 2, '用户管理', 3, NULL, 'user:add', 'btn-primary', 'icon-ok', 2);
INSERT INTO `t_permission` VALUES (4, '编辑', 2, '用户管理', 3, NULL, 'user:update', 'btn-warning', 'icon-edit', 3);
INSERT INTO `t_permission` VALUES (5, '删除', 2, '用户管理', 3, NULL, 'user:delete', 'btn-danger', 'icon-trash', 4);
INSERT INTO `t_permission` VALUES (6, '角色管理', 1, '系统管理', 2, 'role/listUI', 'role:listUI', NULL, NULL, 2);
INSERT INTO `t_permission` VALUES (7, '新增', 6, '角色管理', 3, NULL, 'role:add', 'btn-primary', 'icon-ok', 2);
INSERT INTO `t_permission` VALUES (8, '编辑', 6, '角色管理', 3, NULL, 'role:update', 'btn-warning', 'icon-edit', 3);
INSERT INTO `t_permission` VALUES (9, '删除', 6, '角色管理', 3, NULL, 'role:delete', 'btn-danger', 'icon-trash', 4);
INSERT INTO `t_permission` VALUES (10, '权限管理', 1, '系统管理', 2, 'permission/listUI', 'permission:listUI', NULL, NULL, 3);
INSERT INTO `t_permission` VALUES (11, '新增', 10, '权限管理', 3, NULL, 'permission:add', 'btn-primary', 'icon-ok', 1);
INSERT INTO `t_permission` VALUES (12, '编辑', 10, '权限管理', 3, NULL, 'permission:update', 'btn-warning', 'icon-edit', 2);
INSERT INTO `t_permission` VALUES (13, '删除', 10, '权限管理', 3, NULL, 'permission:delete', 'btn-danger', 'icon-trash', 3);
INSERT INTO `t_permission` VALUES (14, '设置角色', 2, '用户管理', 3, NULL, 'user:setRole', 'btn-success', 'icon-cog', 1);
INSERT INTO `t_permission` VALUES (15, '设置权限', 6, '角色管理', 3, NULL, 'role:setPermission', 'btn-success', 'icon-cog', 1);
INSERT INTO `t_permission` VALUES (18, '采集管理', 0, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_permission` VALUES (19, '采集启停', 18, '采集管理', 2, 'collectstartup/operationUI', 'collectstartup:operationUI', NULL, NULL, 1);
INSERT INTO `t_permission` VALUES (20, '启动采集', 19, '采集启停', 3, NULL, 'collectstartup:startup', 'btn-primary', 'icon-ok', 2);
INSERT INTO `t_permission` VALUES (21, '串口管理', 25, '设备管理', 2, 'com/listUI', 'com:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (22, '新增', 21, '串口管理', 3, NULL, 'com:add', 'btn-primary', 'icon-ok', 2);
INSERT INTO `t_permission` VALUES (23, '编辑', 21, '串口管理', 3, NULL, 'com:update', 'btn-warning', 'icon-edit', 3);
INSERT INTO `t_permission` VALUES (24, '删除', 21, '串口管理', 3, NULL, 'com:delete', 'btn-danger', 'icon-trash', 4);
INSERT INTO `t_permission` VALUES (25, '设备管理', 0, NULL, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_permission` VALUES (26, '型号管理', 25, '设备管理', 2, 'model/listUI', 'model:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (27, '新增', 26, '型号管理', 3, NULL, 'model:add', 'btn-primary', 'icon-ok', 1);
INSERT INTO `t_permission` VALUES (28, '编辑', 26, '型号管理', 3, NULL, 'model:update', 'btn-warning', 'icon-edit', 2);
INSERT INTO `t_permission` VALUES (29, '删除', 26, '型号管理', 3, NULL, 'model:delete', 'btn-danger', 'icon-trash', 3);
INSERT INTO `t_permission` VALUES (30, '仪表管理', 25, '设备管理', 2, 'meter/listUI', 'meter:listUI', NULL, NULL, 2);
INSERT INTO `t_permission` VALUES (31, '新增', 30, '仪表管理', 3, NULL, 'meter:add', 'btn-primary', 'icon-ok', 2);
INSERT INTO `t_permission` VALUES (32, '编辑', 30, '仪表管理', 3, NULL, 'meter:update', 'btn-warning', 'icon-edit', 3);
INSERT INTO `t_permission` VALUES (33, '删除', 30, '仪表管理', 3, NULL, 'meter:delete', 'btn-danger', 'icon-trash', 4);
INSERT INTO `t_permission` VALUES (34, '设置协议', 26, '型号管理', 3, '', 'model:setProtocol', 'btn-success', 'icon-cog', 1);
INSERT INTO `t_permission` VALUES (35, '参数管理', 18, '采集管理', 2, 'field/listUI', 'field:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (36, '新增', 35, '参数管理', 3, '', 'field:add', 'btn-primary', 'icon-ok', 1);
INSERT INTO `t_permission` VALUES (37, '编辑', 35, '参数管理', 3, '', 'field:update', 'btn-warning', 'icon-edit', 2);
INSERT INTO `t_permission` VALUES (38, '删除', 35, '参数管理', 3, '', 'field:delete', 'btn-danger', 'icon-trash', 3);
INSERT INTO `t_permission` VALUES (39, '设置参数', 26, '型号管理', 3, '', 'model:setField', 'btn-success', 'icon-cog', 4);
INSERT INTO `t_permission` VALUES (40, '型号参数', 25, '设备管理', 2, 'modelfield/listUI', 'model:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (41, '采集配置', 18, '采集管理', 2, 'collectSetting/listUI', 'collectSetting/:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (42, '新增', 41, '采集配置', 3, '', 'collectSetting:add', 'btn-primary', 'icon-ok', 1);
INSERT INTO `t_permission` VALUES (43, '编辑', 41, '采集配置', 3, '', 'collectSetting:update', 'btn-warning', 'icon-edit', 2);
INSERT INTO `t_permission` VALUES (44, '删除', 41, '采集配置', 3, '', 'collectSetting:delete', 'btn-danger', 'icon-trash', 3);
INSERT INTO `t_permission` VALUES (45, '采集参数', 18, '采集管理', 2, 'collectField/listUI', 'collectField:listUI', NULL, NULL, 0);
INSERT INTO `t_permission` VALUES (46, '新增', 45, '采集参数', 3, '', 'collectField:add', 'btn-primary', 'icon-ok', 1);
INSERT INTO `t_permission` VALUES (47, '编辑', 45, '采集参数', 3, '', 'collectField:update', 'btn-warning', 'icon-edit', 2);
INSERT INTO `t_permission` VALUES (48, '删除', 45, '采集参数', 3, '', 'collectField:delete', 'btn-danger', 'icon-trash', 3);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `descr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '最高权限');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 383 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (18, 3, 1);
INSERT INTO `t_role_permission` VALUES (19, 3, 2);
INSERT INTO `t_role_permission` VALUES (20, 3, 3);
INSERT INTO `t_role_permission` VALUES (21, 3, 6);
INSERT INTO `t_role_permission` VALUES (22, 3, 7);
INSERT INTO `t_role_permission` VALUES (383, 1, 1);
INSERT INTO `t_role_permission` VALUES (384, 1, 2);
INSERT INTO `t_role_permission` VALUES (385, 1, 3);
INSERT INTO `t_role_permission` VALUES (386, 1, 4);
INSERT INTO `t_role_permission` VALUES (387, 1, 5);
INSERT INTO `t_role_permission` VALUES (388, 1, 14);
INSERT INTO `t_role_permission` VALUES (389, 1, 6);
INSERT INTO `t_role_permission` VALUES (390, 1, 7);
INSERT INTO `t_role_permission` VALUES (391, 1, 8);
INSERT INTO `t_role_permission` VALUES (392, 1, 9);
INSERT INTO `t_role_permission` VALUES (393, 1, 15);
INSERT INTO `t_role_permission` VALUES (394, 1, 10);
INSERT INTO `t_role_permission` VALUES (395, 1, 11);
INSERT INTO `t_role_permission` VALUES (396, 1, 12);
INSERT INTO `t_role_permission` VALUES (397, 1, 13);
INSERT INTO `t_role_permission` VALUES (398, 1, 18);
INSERT INTO `t_role_permission` VALUES (399, 1, 19);
INSERT INTO `t_role_permission` VALUES (400, 1, 20);
INSERT INTO `t_role_permission` VALUES (401, 1, 35);
INSERT INTO `t_role_permission` VALUES (402, 1, 36);
INSERT INTO `t_role_permission` VALUES (403, 1, 37);
INSERT INTO `t_role_permission` VALUES (404, 1, 38);
INSERT INTO `t_role_permission` VALUES (405, 1, 41);
INSERT INTO `t_role_permission` VALUES (406, 1, 42);
INSERT INTO `t_role_permission` VALUES (407, 1, 43);
INSERT INTO `t_role_permission` VALUES (408, 1, 44);
INSERT INTO `t_role_permission` VALUES (409, 1, 45);
INSERT INTO `t_role_permission` VALUES (410, 1, 48);
INSERT INTO `t_role_permission` VALUES (411, 1, 25);
INSERT INTO `t_role_permission` VALUES (412, 1, 21);
INSERT INTO `t_role_permission` VALUES (413, 1, 22);
INSERT INTO `t_role_permission` VALUES (414, 1, 23);
INSERT INTO `t_role_permission` VALUES (415, 1, 24);
INSERT INTO `t_role_permission` VALUES (416, 1, 26);
INSERT INTO `t_role_permission` VALUES (417, 1, 27);
INSERT INTO `t_role_permission` VALUES (418, 1, 28);
INSERT INTO `t_role_permission` VALUES (419, 1, 29);
INSERT INTO `t_role_permission` VALUES (420, 1, 34);
INSERT INTO `t_role_permission` VALUES (421, 1, 39);
INSERT INTO `t_role_permission` VALUES (422, 1, 30);
INSERT INTO `t_role_permission` VALUES (423, 1, 31);
INSERT INTO `t_role_permission` VALUES (424, 1, 32);
INSERT INTO `t_role_permission` VALUES (425, 1, 33);
INSERT INTO `t_role_permission` VALUES (426, 1, 40);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `status` tinyint(1) NOT NULL COMMENT '状态1:启用 0：禁用',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@163.com', NULL, 'abc', 1, '2018-07-01 12:42:20', '2018-07-01 12:42:22');
INSERT INTO `t_user` VALUES (4, 'nhm', 'e10adc3949ba59abbe56e057f20f883e', '123@qq.com', '', NULL, 0, '2019-12-27 07:42:26', '2019-12-27 07:42:26');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1);
INSERT INTO `t_user_role` VALUES (5, 3, 1);
INSERT INTO `t_user_role` VALUES (6, 3, 3);

-- ----------------------------
-- View structure for v_collectfield
-- ----------------------------
DROP VIEW IF EXISTS `v_collectfield`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_collectfield` AS select `cc`.`id` AS `id`,`cc`.`meterId` AS `meterId`,`c`.`name` AS `comName`,`cc`.`cycle` AS `cycle`,`cc`.`factor` AS `factor`,`m`.`name` AS `meterName`,`m`.`address` AS `meterAddress`,`m`.`enable` AS `enable`,`md`.`name` AS `modelName`,`md`.`isBigEndian` AS `isBigEndian`,`cc`.`fieldId` AS `fieldId`,`f`.`name` AS `fieldName`,`f`.`InventedParameterType` AS `InventedParameterType`,`p`.`id` AS `protocolId`,`p`.`name` AS `protocolName`,`ps`.`id` AS `parseId`,`ps`.`name` AS `parseName`,`cc`.`data1` AS `data1`,`cc`.`data2` AS `data2`,`cc`.`unit` AS `unit` from ((((((`d_collect_config` `cc` join `d_meter` `m` on((`cc`.`meterId` = `m`.`id`))) join `d_com` `c` on((`m`.`comId` = `c`.`id`))) join `d_model` `md` on((`m`.`modelId` = `md`.`id`))) join `d_field` `f` on((`cc`.`fieldId` = `f`.`id`))) join `d_protocol` `p` on((`cc`.`protocolId` = `p`.`id`))) join `d_parser` `ps` on((`cc`.`parseId` = `ps`.`id`)));

-- ----------------------------
-- View structure for v_model_field
-- ----------------------------
DROP VIEW IF EXISTS `v_model_field`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_model_field` AS select `mf`.`id` AS `id`,`mf`.`modelId` AS `modelId`,`f`.`id` AS `fieldId`,`f`.`name` AS `name`,`f`.`defaultUnit` AS `defaultUnit`,`f`.`defaultCycle` AS `defaultCycle`,`f`.`paramType` AS `paramType`,`f`.`defaultFactor` AS `defaultFactor`,`f`.`InventedParameterType` AS `InventedParameterType` from (`d_model_field` `mf` join `d_field` `f` on((`mf`.`fieldId` = `f`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
