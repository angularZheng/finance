CREATE TABLE `fc_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
   `status` int(11) DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `fc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(80) CHARACTER SET utf8 DEFAULT NULL,
  `country` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `head_img_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `open_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `province` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `vip_level` int(11) DEFAULT NULL,
   `status` int(11) DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2aqalc5qjhk82nhx5gvm70l7y` (`role_id`),
  CONSTRAINT `FK2aqalc5qjhk82nhx5gvm70l7y` FOREIGN KEY (`role_id`) REFERENCES `fc_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;