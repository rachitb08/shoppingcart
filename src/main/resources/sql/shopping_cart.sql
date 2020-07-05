CREATE TABLE `product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `inventory` BIGINT(20) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cart_line_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKdv9ak3oxi556vc1voyc2927na` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','TV Set','300');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Game Console','200');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Sofa','100');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Icecream','5');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Beer','3');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Phone','500');
INSERT INTO `product` (`inventory`, `name`, `price`) VALUES('100','Watch','30');