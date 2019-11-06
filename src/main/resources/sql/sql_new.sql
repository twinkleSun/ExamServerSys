use examsystem;

-- ----------------------------
-- 用户信息表
-- ----------------------------
-- DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '用户密码',
  `role` VARCHAR (10) NOT NULL COMMENT '用户类型:student/admin',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `user` VALUES ('1','admin', 'admin', 'admin');
-- INSERT INTO `user` VALUES ('2','student', 'student', 'student');

-- ----------------------------
-- 考试组信息
-- ----------------------------
-- DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组ID',
  `name` varchar(20) NOT NULL COMMENT '组名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `group` VALUES ('1','测试组1');


-- ----------------------------
-- 考生与组的多对多关系表
-- ----------------------------
-- DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组ID',
  `group_id` int(11) NOT NULL  COMMENT '组ID',
  `student_id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `group_user` VALUES ('1','1','1');


-- ----------------------------
-- 知识点信息
-- ----------------------------
-- DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '知识点ID',
  `name` varchar(20) NOT NULL COMMENT '知识点名称',
  `description` varchar(80) default NULL COMMENT '知识点描述',
  `level` int(11) NOT NULL COMMENT '知识点级别',
  `parent_id` int(11) default NULL COMMENT '父类知识点ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `knowledge` VALUES ('1','数据库基础','Database','1','0');


-- ----------------------------
-- 题目信息表/题库
-- ----------------------------
-- DROP TABLE IF EXISTS `question_library`;
CREATE TABLE `question_library` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `description` TEXT() DEFAULT null comment '题目描述',
  `content` TEXT() NOT NULL COMMENT '题干',
  `options` TEXT()  default NULL COMMENT '选项{}分隔成json格式字符串',
  `answer` TEXT()  default NULL COMMENT '答案,用|分隔',
  `type` VARCHAR (45) NOT NULL COMMENT '题目类型：单选题：single/多选题：multi/判断题：judge/主观题：subjective',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `question_library` VALUES ('1','java基本数据类型有哪些', '[{A、byte}{B、short}{C、int}{D、char}]', 'A|B|C|D', 'multi');

-- ----------------------------
-- 题目-知识点关系表
-- ----------------------------
-- DROP TABLE IF EXISTS `ques_knowledge`;
CREATE TABLE `ques_knowledge`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组ID',
  `question_id` int(11) NOT NULL  COMMENT '组ID',
  `knowledge_id` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES `question_library` (`id`),
  FOREIGN KEY (`knowledge_id`) REFERENCES `knowledge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `ques_knowledge` VALUES ('1','1','1');

-- ----------------------------
-- 试卷-题目信息表
-- ---------------------------
-- DROP TABLE IF EXISTS `test_paper`;
CREATE TABLE `test_paper_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `paper_code` VARCHAR (45) NOT NULL COMMENT '试卷识别码',
  `question_id` int(11) NOT NULL COMMENT '题目ID',
  `must_or_not` int(11) NOT NULL COMMENT '必做为1/选做为0',
  `score` DOUBLE(4,2) NOT NULL COMMENT '题目分数',
  `def_answer` TEXT() DEFAULT NULL COMMENT '题目的标准答案',
  `category_content` VARCHAR (45) DEFAULT NULL COMMENT '第几大题，如：一、数据库基础',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`question_id`) REFERENCES `question_library` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `test_paper` VALUES ('1','1101','1','0' ,'10', 'ABCD','一、数据库基础');


-- ----------------------------
-- 考试信息表
-- ----------------------------
-- DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_name` varchar(45) NOT NULL COMMENT '考试名',
  `paper_code` VARCHAR(40) NOT NULL COMMENT '考试代码',
  `begin_time` VARCHAR (45) NOT NULL COMMENT '开放考试时间',
  `end_time` VARCHAR (45) NOT NULL COMMENT '关闭考试时间',
  `duration` VARCHAR (45) NOT NULL COMMENT '考试时长,单位分钟',
  `status` VARCHAR (10) DEFAULT null COMMENT '考试目前状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `exam` VALUES ('1','新员工java测试', '1101', '2019-9-8 08:00:00', '2019-9-8 18:00:00', '90');


-- ----------------------------
-- 考试与组关系表
-- ----------------------------
-- DROP TABLE IF EXISTS `exam_group`;
CREATE TABLE `exam_group`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_id` int(11) NOT NULL COMMENT '考试信息ID',
  `group_id` int(11) NOT NULL COMMENT '参与考试的组ID',
   PRIMARY KEY (`id`),
  FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `exam_group` VALUES ('1','1');


-- ----------------------------
-- 考生考试成绩表
-- ----------------------------
-- DROP TABLE IF EXISTS `student_point`;
CREATE TABLE `student_point`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `paper_code` varchar(40) default null comment '试卷识别码',
  `exam_id` int(11) NOT NULL  COMMENT '考试ID',
  `student_id` int(11) NOT NULL COMMENT '考生ID',
  `objective_grade` DOUBLE(4,2) DEFAULT NULL COMMENT '客观题分数',
  `objective_status` int(2) DEFAULT NULL COMMENT '客观题批改状态',
  `subjective_status` int(2) DEFAULT NULL COMMENT '主观题批改状态',
  `subjective_grade` DOUBLE(4,2) DEFAULT NULL COMMENT '主观题分数',
  `extra_point` DOUBLE(4,2) default null comment '附加分',
  `paper_total_point` DOUBLE(4,2) default null comment '总分',
  `student_total_point` DOUBLE(4,2) default null comment '学生总分',
  `end_flag` int(11) default 1 comment'结束考试标志',
  `left_time` varchar(255) default null comment'距离考试剩余时间',
  `in_time` varchar(255) default null comment'进场时间',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `student_point` VALUES ('1','1101','1','1','1','1','100','80');

-- ----------------------------
-- 考生答案表
-- ----------------------------
-- DROP TABLE IF EXISTS `student_point_detail`;
CREATE TABLE `student_point_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `paper_code` VARCHAR(45) NOT NULL COMMENT '试卷识别码',
  `exam_id` int(11) NOT NULL  COMMENT '考试ID',
  `question_id` int(11) NOT NULL COMMENT '题目ID',
  `student_id` int(11) NOT NULL COMMENT '考生ID',
  `def_point` int(5) DEFAULT NULL COMMENT '每题总分',
  `student_answer` VARCHAR (255) DEFAULT NULL COMMENT '考生答案',
  `student_point` DOUBLE(4,2) DEFAULT NULL COMMENT '考生分数',
  `question_status` int(11) default null comment '1为已批改，0为未批改',
  `stamp` int(2) default 0 comment'学生前端标记',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- INSERT INTO `student_point_detail` VALUES ('1','1101', '1', '1','ABCD','AB','0','1');

-- ----------------------------
-- 试卷详情表表
-- ----------------------------
CREATE TABLE `test_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `paper_code` VARCHAR(45) NOT NULL COMMENT '试卷识别码',
  `create_time` VARCHAR(45) DEFAULT NULL COMMENT '创建时间',
  `last_modified_time` VARCHAR (45) DEFAULT NULL COMMENT '上次修改时间',
  `create_user_id` int(11) NOT NULL COMMENT '创建人ID',
  `title` VARCHAR(45) DEFAULT NULL COMMENT '试卷标题',
  `description` VARCHAR(45) DEFAULT NULL COMMENT '试卷描述',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
