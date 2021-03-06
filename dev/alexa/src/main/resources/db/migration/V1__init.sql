CREATE TABLE website(
  `id` BIGINT(8) AUTO_INCREMENT PRIMARY KEY,
  `rank` INT(8) DEFAULT 0,
  `domain` VARCHAR(255) DEFAULT NULL,
  `title` VARCHAR(255) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `keywords` VARCHAR(255) DEFAULT NULL,
  `icp` VARCHAR(255) DEFAULT NULL
) ENGINE=InnoDB CHARSET=utf8;
