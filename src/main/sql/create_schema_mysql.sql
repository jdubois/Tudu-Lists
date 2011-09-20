
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE TABLE `property` (
  `pkey` varchar(100) NOT NULL,
  `value` varchar(200) NULL,
  PRIMARY KEY  (`pkey`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.host","localhost");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.port","25");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.user","");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.password","");
INSERT INTO `property` (`pkey`,`value`) VALUES ("smtp.from","Tudu Lists");

CREATE TABLE `role` (
  `role` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role` (`role`) VALUES ("ROLE_ADMIN");
INSERT INTO `role` (`role`) VALUES ("ROLE_USER");

CREATE TABLE `todo` (
  `id` varchar(32) NOT NULL default '',
  `creationDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `description` varchar(255) NOT NULL default '',
  `priority` int(4) NOT NULL default '0',
  `completed` tinyint(1) NOT NULL default '0',
  `completionDate` datetime default NULL,
  `todoList_id` varchar(32) NOT NULL default '',
  `dueDate` datetime default NULL,
  `assignedUser_login` varchar(50) NULL,
  `hasNotes` tinyint(1) NOT NULL default '0',
  `notes` varchar(10000) NULL default '',
  PRIMARY KEY  (`id`),
  KEY `todoList_id_index` (`todoList_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `todo_list` (
  `id` varchar(32) NOT NULL default '',
  `name` varchar(100) NOT NULL default '',
  `rssAllowed` tinyint(1) NOT NULL default '0',
  `lastUpdate` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tuser` (
  `login` varchar(50) NOT NULL default '',
  `password` varchar(50) NOT NULL default '',
  `enabled` tinyint(1) NOT NULL default '0',
  `firstName` varchar(100) default '',
  `lastName` varchar(100) default '',
  `email` varchar(150) default '',
  `dateFormat` varchar(10) NOT NULL default '',
  `creationDate` datetime NOT NULL default '0000-00-00 00:00:00',
  `lastAccessDate` datetime NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tuser` (`login`,`password`,`enabled`,`firstName`,`lastName`,`email`,`dateFormat`,`creationDate`,`lastAccessDate`) VALUES ("admin","password","1","Admin","User","","MM/dd/yyyy","2000-01-01 00:00:00","2000-01-01 00:00:00");
INSERT INTO `tuser` (`login`,`password`,`enabled`,`firstName`,`lastName`,`email`,`dateFormat`,`creationDate`,`lastAccessDate`) VALUES ("user","password","1","Default","User","","MM/dd/yyyy","2000-01-01 00:00:00","2000-01-01 00:00:00");

CREATE TABLE `tuser_role` (
  `tuser_login` varchar(50) NOT NULL default '',
  `roles_role` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`tuser_login`,`roles_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tuser_role` (`tuser_login`,`roles_role`) VALUES ("admin","ROLE_ADMIN");
INSERT INTO `tuser_role` (`tuser_login`,`roles_role`) VALUES ("admin","ROLE_USER");

CREATE TABLE `tuser_todo_list` (
  `users_login` varchar(50) NOT NULL default '',
  `todoLists_id` varchar(32) NOT NULL default '',
  PRIMARY KEY  (`users_login`,`todoLists_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE todo ADD CONSTRAINT `FK_t_assigned_user` FOREIGN KEY `FK_t_assigned_user` (`assignedUser_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE todo ADD CONSTRAINT `FK_t_todo_list` FOREIGN KEY `FK_t_todo_list` (`todoList_id`)
    REFERENCES `todo_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

ALTER TABLE tuser_role ADD CONSTRAINT `FK_tr_tuser` FOREIGN KEY `FK_tr_tuser` (`tuser_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
ALTER TABLE tuser_role ADD INDEX `FK_tr_tuser` (`tuser_login`);
    
ALTER TABLE tuser_role ADD CONSTRAINT `FK_tr_role` FOREIGN KEY `FK_tr_role` (`roles_role`)
    REFERENCES `role` (`role`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
    
ALTER TABLE tuser_todo_list ADD CONSTRAINT `FK_ttl_users` FOREIGN KEY `FK_ttl_users` (`users_login`)
    REFERENCES `tuser` (`login`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;
ALTER TABLE tuser_todo_list ADD INDEX `FK_ttl_users` (`users_login`);

ALTER TABLE tuser_todo_list ADD CONSTRAINT `FK_ttl_todo_list` FOREIGN KEY `FK_ttl_todo_list` (`todoLists_id`)
    REFERENCES `todo_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
