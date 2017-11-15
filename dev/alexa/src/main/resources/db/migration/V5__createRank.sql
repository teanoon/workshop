CREATE TABLE rank(
  `id` BIGINT(8) AUTO_INCREMENT PRIMARY KEY,
  `value` INT(8) DEFAULT 0,
  `created` DATE NOT NULL,
   `website_id` BIGINT(8) NOT NULL,
  CONSTRAINT `FK_ID` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)
) ENGINE=InnoDB CHARSET=utf8;


