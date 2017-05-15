-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 onlinequerstionnairesurvey 的数据库结构
CREATE DATABASE IF NOT EXISTS `onlinequerstionnairesurvey` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `onlinequerstionnairesurvey`;

-- 导出  表 onlinequerstionnairesurvey.questionnaire 结构
CREATE TABLE IF NOT EXISTS `questionnaire` (
  `qes_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问卷的主键',
  `qes_information` text COLLATE utf8_bin NOT NULL COMMENT '问卷的主要内容，题目',
  `username` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户表的主键，问卷的创建者',
  `qes_profix` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '说明问卷作用,调查目标',
  `qes_title` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '问卷的名字',
  `qes_result` text COLLATE utf8_bin COMMENT '问卷调查的分析结果',
  `is_public` tinyint(4) DEFAULT '0' COMMENT '标记问卷是否公开0不公开1公开',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '问卷创建时间',
  `start_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '问卷开始时间',
  `end_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '问卷结束时间',
  PRIMARY KEY (`qes_id`),
  KEY `FK_questionnaire_user` (`username`),
  CONSTRAINT `FK_questionnaire_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='问卷表,存储问卷的相关信息';

-- 正在导出表  onlinequerstionnairesurvey.questionnaire 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
INSERT INTO `questionnaire` (`qes_id`, `qes_information`, `username`, `qes_profix`, `qes_title`, `qes_result`, `is_public`, `create_time`, `start_time`, `end_time`) VALUES
	(1, '[{"options":{"A":"37.5","B":"38","C":"40","D":"42"},"stem":"日本鞋码240在欧码中是多大？"},{"options":{"A":"二氧化碳","B":"蛋白质","C":"糖"},"stem":"碳水化合物是指什么?"},{"options":{"A":"335ML","B":"320ML","C":"325ML"},"stem":"一听雪碧的净含量为？"}]', '2014110444', '对个人的生活常识调查', '生活常识在线考', NULL, 0, '2017-05-14 21:17:38', '2017-05-01 00:00:00', '2017-05-31 00:00:00'),
	(3, '[{"options":{"A":"是","B":"否"},"stem":"你现在是否单身"},{"options":{"A":"是的","B":"肯定不是","C":"看情况"},"stem":"你认为在校期间两人的费用应该合在一起使用吗"},{"options":{"A":"可以","B":"绝对不可以","C":"陌生人"},"stem":"分手后还可以做朋友吗"}]', 'qaz123456', '大学生关于爱情观的调查', '大学生爱情观调查', NULL, 1, '2017-05-15 15:21:00', '2017-05-15 00:00:00', '2017-05-18 00:00:00');
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;

-- 导出  表 onlinequerstionnairesurvey.questionnaire_answer 结构
CREATE TABLE IF NOT EXISTS `questionnaire_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `qes_id` int(11) NOT NULL COMMENT 'questionnaire表的主键',
  `qes_r_set` text COLLATE utf8_bin COMMENT '相应问卷的答案',
  `qes_r_set_any` text COLLATE utf8_bin COMMENT '问卷的分析结果',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '答案创建时间',
  `session_id` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '临时唯一标志',
  PRIMARY KEY (`id`),
  KEY `FK_questionnaire_answer_questionnaire` (`qes_id`),
  CONSTRAINT `FK_questionnaire_answer_questionnaire` FOREIGN KEY (`qes_id`) REFERENCES `questionnaire` (`qes_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用于记录问卷的答案，及个人问卷的结果信息';

-- 正在导出表  onlinequerstionnairesurvey.questionnaire_answer 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `questionnaire_answer` DISABLE KEYS */;
INSERT INTO `questionnaire_answer` (`id`, `qes_id`, `qes_r_set`, `qes_r_set_any`, `create_time`, `session_id`) VALUES
	(1, 1, '1=C;2=A;3=C', NULL, '2017-05-14 21:19:33', '5C147D6664789C35F58B7F6EC230930A'),
	(2, 1, '1=A;2=B;3=C', NULL, '2017-05-15 14:59:06', '7BB28855537DC654413267D8BB456DAC'),
	(3, 1, '1=B;2=C;3=B', NULL, '2017-05-15 15:03:48', '7BB28855537DC654413267D8BB456DAC'),
	(5, 3, '1=B;2=B;3=B', NULL, '2017-05-15 15:21:38', '7BB28855537DC654413267D8BB456DAC');
/*!40000 ALTER TABLE `questionnaire_answer` ENABLE KEYS */;

-- 导出  表 onlinequerstionnairesurvey.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `user_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示用户,1表示管理员',
  `user_phone` bigint(20) DEFAULT NULL COMMENT '用户手机号',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表,记录用户信息';

-- 正在导出表  onlinequerstionnairesurvey.user 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `password`, `user_type`, `user_phone`) VALUES
	('2014110440', 'qaz123', 0, 17780615345),
	('2014110444', 'qaz123456', 1, 18080485346),
	('abc123456', 'qaz123456', 0, 18080485345),
	('az1234567', 'qaz123456', 0, 18080485346),
	('qaz1213', 'qaz123456', 0, 18080455346),
	('qaz123456', 'qaz123456', 0, 18080485346),
	('qaz1234567', 'qaz123456', 0, 18080485346),
	('qqqqqqq', '1111111111111', 0, 18811111111),
	('sfs', '123123', 0, 18384259728);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  触发器 onlinequerstionnairesurvey.TR_A_questionnaire 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `TR_A_questionnaire` BEFORE DELETE ON `questionnaire` FOR EACH ROW BEGIN
delete from questionnaire_answer where qes_id = old.qes_id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
