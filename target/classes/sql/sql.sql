-- ----------------------------
-- 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '用户密码',
  `user_type` VARCHAR (45) NOT NULL COMMENT '用户类型:student/admin',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `userinfo` VALUES ('1','admin', 'admin', 'admin');


-- ----------------------------
-- 考试组信息
-- ----------------------------
DROP TABLE IF EXISTS `groupinfo`;
CREATE TABLE `groupinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组ID',
  `group_name` varchar(45) NOT NULL COMMENT '组名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `groupinfo` VALUES ('1','测试组名');


-- ----------------------------
-- 考生与组的多对多关系表
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`(
  `group_id` int(11) NOT NULL  COMMENT '组ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  FOREIGN KEY (`group_id`) REFERENCES `groupinfo` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `group_user` VALUES ('1','1');


-- ----------------------------
-- 题目信息表/题库
-- ----------------------------
DROP TABLE IF EXISTS `questioninfo`;
CREATE TABLE `questioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) NOT NULL COMMENT '题干',
  `options` varchar(255)  NOT NULL COMMENT '选项',
  `answer` varchar(255)  NOT NULL COMMENT '答案',
  `type` VARCHAR (45) NOT NULL COMMENT '题目类型：单选题：single/多选题：multi/判断题：judge/主观题：zhuguan',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `questioninfo` VALUES ('1','java基本数据类型有哪些', 'A、byte#B、short#C、int#D、char', 'ABCD', 'multi');


-- ----------------------------
-- 试卷-题目信息表
-- ---------------------------
DROP TABLE IF EXISTS `paper_question`;
CREATE TABLE `paper_question` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `paper_code` VARCHAR (45) NOT NULL COMMENT '试卷识别码',
  `question_id` int(11) NOT NULL COMMENT '题目ID',
  `score` DOUBLE(4,2) NOT NULL COMMENT '题目分数',
  `answer` VARCHAR (45) DEFAULT NULL COMMENT '题目的标准答案',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES `questioninfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `paper_question` VALUES ('1','1101','1', '10', 'ABCD');


-- ----------------------------
-- 考试信息表
-- ----------------------------
DROP TABLE IF EXISTS `examinfo`;
CREATE TABLE `examinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_name` varchar(45) NOT NULL COMMENT '考试名',
  `paper_code` int(11) NOT NULL COMMENT '考试代码',
  `begin_time` VARCHAR (45) NOT NULL COMMENT '开放考试时间',
  `end_time` VARCHAR (45) NOT NULL COMMENT '关闭考试时间',
  `duration` int(11) NOT NULL COMMENT '考试时长,单位分钟',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `examinfo` VALUES ('1','新员工java测试', '1101', '2019-9-8 08:00:00', '2019-9-8 18:00:00', '90');


-- ----------------------------
-- 考生答案表(主观题)
-- ----------------------------
DROP TABLE IF EXISTS `answerinfo`;
CREATE TABLE `answerinfo` (
  `question_id` int(11) NOT NULL COMMENT '题目ID',
  `paper_id` int(11) NOT NULL COMMENT '试卷识别码',
  `user_id` int(11) NOT NULL COMMENT '考生ID',
  `answer` VARCHAR (45) DEFAULT NULL COMMENT '考生答案',
  FOREIGN KEY (`question_id`) REFERENCES `questioninfo` (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `answerinfo` VALUES ('1','1101', '1', 'ABCD');


-- ----------------------------
-- 考试与组关系表
-- ----------------------------
DROP TABLE IF EXISTS `exam_group`;
CREATE TABLE `exam_group`(
  `exam_id` int(11) NOT NULL COMMENT '考试信息ID',
  `group_id` int(11) NOT NULL COMMENT '参与考试的组ID',
  FOREIGN KEY (`group_id`) REFERENCES `groupinfo` (`id`),
  FOREIGN KEY (`exam_id`) REFERENCES `examinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `exam_group` VALUES ('1','1');


-- ----------------------------
-- 考生考试成绩表
-- ----------------------------
DROP TABLE IF EXISTS `paper_user`;
CREATE TABLE `paper_user`(
  `paper_code` int(11) NOT NULL  COMMENT '试卷识别码',
  `user_id` int(11) NOT NULL COMMENT '考生ID',
  `keguan_grade` DOUBLE(4,2) DEFAULT NULL COMMENT '客观题分数',
  `zhuguan_grade` DOUBLE(4,2) DEFAULT NULL COMMENT '主观题分数',
  FOREIGN KEY (`user_id`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `paper_user` VALUES ('1101','1','','');
