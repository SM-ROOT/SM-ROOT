-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         10.1.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para appmo
CREATE DATABASE IF NOT EXISTS `appmo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `appmo`;

-- Volcando estructura para tabla appmo.appmo_client
CREATE TABLE IF NOT EXISTS `appmo_client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `addres` varchar(200) NOT NULL,
  `numberaddres` varchar(50) NOT NULL,
  `cp` int(11) NOT NULL,
  `colony` varchar(200) NOT NULL,
  `city` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_client: ~2 rows (aproximadamente)
DELETE FROM `appmo_client`;
/*!40000 ALTER TABLE `appmo_client` DISABLE KEYS */;
INSERT INTO `appmo_client` (`idClient`, `name`, `phone`, `addres`, `numberaddres`, `cp`, `colony`, `city`, `state`) VALUES
	(1, 'Gerardo Eduardo Perez Mayorga', '9631382799', 'Las Flores', '14', 30065, 'Las Flores', 'Comitan de DOminguez', 'Chiapas'),
	(2, 'Antonio de Jesus ', '9632589754', 'Las Flores de Nevda', '15', 30065, 'Las flores', 'ComitÃ¡n de Dominguez', 'Chiapas');
/*!40000 ALTER TABLE `appmo_client` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_material
CREATE TABLE IF NOT EXISTS `appmo_material` (
  `idIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`idIngredient`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_material: ~2 rows (aproximadamente)
DELETE FROM `appmo_material`;
/*!40000 ALTER TABLE `appmo_material` DISABLE KEYS */;
INSERT INTO `appmo_material` (`idIngredient`, `name`) VALUES
	(1, 'Azucar'),
	(2, 'Manteca');
/*!40000 ALTER TABLE `appmo_material` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_order
CREATE TABLE IF NOT EXISTS `appmo_order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `nameClient` char(50) NOT NULL DEFAULT 'otro',
  `destination` char(50) NOT NULL DEFAULT 'otro',
  `date` date NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_order: ~0 rows (aproximadamente)
DELETE FROM `appmo_order`;
/*!40000 ALTER TABLE `appmo_order` DISABLE KEYS */;
INSERT INTO `appmo_order` (`idOrder`, `nameClient`, `destination`, `date`, `status`) VALUES
	(46, 'Gerardo Eduardo Perez Mayorga', '12', '2019-08-15', 0),
	(47, 'Gerardo Eduardo Perez Mayorga', '1', '2019-08-15', 0),
	(48, 'Gerardo Eduardo Perez Mayorga', '12', '2019-08-15', 0),
	(49, 'Gerardo Eduardo Perez Mayorga', '12', '2019-08-15', 0),
	(50, 'Gerardo Eduardo Perez Mayorga', 'qwe', '2019-08-15', 0),
	(51, 'Gerardo Eduardo Perez Mayorga', '12', '2019-08-15', 0);
/*!40000 ALTER TABLE `appmo_order` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_orderproduct
CREATE TABLE IF NOT EXISTS `appmo_orderproduct` (
  `idOrderProduct` int(11) NOT NULL AUTO_INCREMENT,
  `typeProduct` varchar(50) NOT NULL,
  `nameProduct` varchar(50) NOT NULL,
  `quantityProduct` int(11) NOT NULL,
  PRIMARY KEY (`idOrderProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_orderproduct: ~0 rows (aproximadamente)
DELETE FROM `appmo_orderproduct`;
/*!40000 ALTER TABLE `appmo_orderproduct` DISABLE KEYS */;
INSERT INTO `appmo_orderproduct` (`idOrderProduct`, `typeProduct`, `nameProduct`, `quantityProduct`) VALUES
	(49, 'Dulce', 'Harina ', 20);
/*!40000 ALTER TABLE `appmo_orderproduct` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_order_orderproduct
CREATE TABLE IF NOT EXISTS `appmo_order_orderproduct` (
  `idOrder_orderProduct` int(11) NOT NULL AUTO_INCREMENT,
  `idOrder` int(11) NOT NULL,
  `idOrderProduct` int(11) NOT NULL,
  PRIMARY KEY (`idOrder_orderProduct`),
  KEY `FKorder` (`idOrder`),
  KEY `FKorderProduct` (`idOrderProduct`),
  CONSTRAINT `FKorder` FOREIGN KEY (`idOrder`) REFERENCES `appmo_order` (`idOrder`),
  CONSTRAINT `FKorderProduct` FOREIGN KEY (`idOrderProduct`) REFERENCES `appmo_orderproduct` (`idOrderProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_order_orderproduct: ~0 rows (aproximadamente)
DELETE FROM `appmo_order_orderproduct`;
/*!40000 ALTER TABLE `appmo_order_orderproduct` DISABLE KEYS */;
INSERT INTO `appmo_order_orderproduct` (`idOrder_orderProduct`, `idOrder`, `idOrderProduct`) VALUES
	(36, 46, 49);
/*!40000 ALTER TABLE `appmo_order_orderproduct` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_product
CREATE TABLE IF NOT EXISTS `appmo_product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `nameProduct` varchar(50) NOT NULL,
  `costProduct` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_product: ~0 rows (aproximadamente)
DELETE FROM `appmo_product`;
/*!40000 ALTER TABLE `appmo_product` DISABLE KEYS */;
INSERT INTO `appmo_product` (`idProduct`, `nameProduct`, `costProduct`) VALUES
	(6, 'Maiz', 0.00),
	(7, 'Harina de trigo', 0.00),
	(8, 'Maiz', 0.00),
	(9, 'Maiz', 0.00),
	(10, 'Maiz', 0.00);
/*!40000 ALTER TABLE `appmo_product` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_purchase
CREATE TABLE IF NOT EXISTS `appmo_purchase` (
  `idPurchase` int(11) NOT NULL AUTO_INCREMENT,
  `purchaseDate` date NOT NULL,
  PRIMARY KEY (`idPurchase`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_purchase: ~11 rows (aproximadamente)
DELETE FROM `appmo_purchase`;
/*!40000 ALTER TABLE `appmo_purchase` DISABLE KEYS */;
INSERT INTO `appmo_purchase` (`idPurchase`, `purchaseDate`) VALUES
	(79, '2019-08-13'),
	(80, '2019-08-13'),
	(81, '2019-08-13'),
	(82, '2019-08-13'),
	(83, '2019-08-13'),
	(84, '2019-08-14'),
	(85, '2019-08-14'),
	(86, '2019-08-14'),
	(87, '2019-08-14'),
	(88, '2019-08-14'),
	(89, '2019-08-14'),
	(90, '2019-08-14');
/*!40000 ALTER TABLE `appmo_purchase` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_purchasematerial
CREATE TABLE IF NOT EXISTS `appmo_purchasematerial` (
  `idPurchaseMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `quantity` int(10) NOT NULL,
  `measure` varchar(50) NOT NULL,
  `coste` decimal(50,0) NOT NULL,
  PRIMARY KEY (`idPurchaseMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_purchasematerial: ~5 rows (aproximadamente)
DELETE FROM `appmo_purchasematerial`;
/*!40000 ALTER TABLE `appmo_purchasematerial` DISABLE KEYS */;
INSERT INTO `appmo_purchasematerial` (`idPurchaseMaterial`, `name`, `quantity`, `measure`, `coste`) VALUES
	(227, 'Harina', 12, 'COSTALES', 12),
	(228, 'Harina', 12, 'COSTALES', 34),
	(229, 'Huevo', 30, 'CAJAS', 20),
	(230, 'Harina', 40, 'BULTOS', 34),
	(231, '121', 123, 'CAJAS', 23123);
/*!40000 ALTER TABLE `appmo_purchasematerial` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_purchase_purchasematerial
CREATE TABLE IF NOT EXISTS `appmo_purchase_purchasematerial` (
  `idPurchase_purchaseMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `idPurchase` int(11) NOT NULL DEFAULT '0',
  `idPurchaseMaterial` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPurchase_purchaseMaterial`),
  KEY `FKpurchase` (`idPurchase`),
  KEY `FKpurchaseMaterial` (`idPurchaseMaterial`),
  CONSTRAINT `FKpurchase` FOREIGN KEY (`idPurchase`) REFERENCES `appmo_purchase` (`idPurchase`),
  CONSTRAINT `FKpurchaseMaterial` FOREIGN KEY (`idPurchaseMaterial`) REFERENCES `appmo_purchasematerial` (`idPurchaseMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_purchase_purchasematerial: ~5 rows (aproximadamente)
DELETE FROM `appmo_purchase_purchasematerial`;
/*!40000 ALTER TABLE `appmo_purchase_purchasematerial` DISABLE KEYS */;
INSERT INTO `appmo_purchase_purchasematerial` (`idPurchase_purchaseMaterial`, `idPurchase`, `idPurchaseMaterial`) VALUES
	(101, 83, 227),
	(102, 85, 228),
	(103, 88, 229),
	(104, 89, 230),
	(105, 90, 231);
/*!40000 ALTER TABLE `appmo_purchase_purchasematerial` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_recipe
CREATE TABLE IF NOT EXISTS `appmo_recipe` (
  `idRecipe` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idRecipe`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_recipe: ~0 rows (aproximadamente)
DELETE FROM `appmo_recipe`;
/*!40000 ALTER TABLE `appmo_recipe` DISABLE KEYS */;
INSERT INTO `appmo_recipe` (`idRecipe`, `name`, `type`) VALUES
	(1, 'Pan de muerto', 'Dulce');
/*!40000 ALTER TABLE `appmo_recipe` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_recipeingredient
CREATE TABLE IF NOT EXISTS `appmo_recipeingredient` (
  `idRecipeIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `nameIngredient` varchar(50) NOT NULL DEFAULT '',
  `measureIngredient` varchar(50) NOT NULL DEFAULT '',
  `quantityIngredient` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idRecipeIngredient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_recipeingredient: ~0 rows (aproximadamente)
DELETE FROM `appmo_recipeingredient`;
/*!40000 ALTER TABLE `appmo_recipeingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `appmo_recipeingredient` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_recipe_recipeingredient
CREATE TABLE IF NOT EXISTS `appmo_recipe_recipeingredient` (
  `idRecipe_RecipeIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `idRecipe` int(11) NOT NULL,
  `idRecipeIngredient` int(11) NOT NULL,
  PRIMARY KEY (`idRecipe_RecipeIngredient`),
  KEY `FKrecipe` (`idRecipe`),
  KEY `FKrecipeIngredient` (`idRecipeIngredient`),
  CONSTRAINT `FKrecipe` FOREIGN KEY (`idRecipe`) REFERENCES `appmo_recipe` (`idRecipe`),
  CONSTRAINT `FKrecipeIngredient` FOREIGN KEY (`idRecipeIngredient`) REFERENCES `appmo_recipeingredient` (`idRecipeIngredient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_recipe_recipeingredient: ~0 rows (aproximadamente)
DELETE FROM `appmo_recipe_recipeingredient`;
/*!40000 ALTER TABLE `appmo_recipe_recipeingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `appmo_recipe_recipeingredient` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_route
CREATE TABLE IF NOT EXISTS `appmo_route` (
  `idRoute` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `curator` varchar(200) NOT NULL,
  PRIMARY KEY (`idRoute`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_route: ~6 rows (aproximadamente)
DELETE FROM `appmo_route`;
/*!40000 ALTER TABLE `appmo_route` DISABLE KEYS */;
INSERT INTO `appmo_route` (`idRoute`, `name`, `curator`) VALUES
	(1, 'AS', 'ASD'),
	(2, 'k', ''),
	(3, 'afs', 'dfsd'),
	(4, 'afs', 'dfsd'),
	(5, 'afs', 'dfsd'),
	(6, 'afs', 'dfsd'),
	(7, 'jhd', 'Gerardo'),
	(8, 'asd', 'Gerardo Eduardo ');
/*!40000 ALTER TABLE `appmo_route` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_sale
CREATE TABLE IF NOT EXISTS `appmo_sale` (
  `idSale` int(11) NOT NULL AUTO_INCREMENT,
  `origin` varchar(50) NOT NULL,
  `totalCost` decimal(10,2) NOT NULL DEFAULT '0.00',
  `date` date NOT NULL,
  PRIMARY KEY (`idSale`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_sale: ~0 rows (aproximadamente)
DELETE FROM `appmo_sale`;
/*!40000 ALTER TABLE `appmo_sale` DISABLE KEYS */;
INSERT INTO `appmo_sale` (`idSale`, `origin`, `totalCost`, `date`) VALUES
	(1, 'otro', 200.00, '0000-00-00'),
	(2, 'otro', 500.00, '2019-08-07'),
	(3, 'San critobal', 0.00, '2019-08-14');
/*!40000 ALTER TABLE `appmo_sale` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_store
CREATE TABLE IF NOT EXISTS `appmo_store` (
  `idBread` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `type` varchar(50) NOT NULL,
  `quantity` varchar(50) NOT NULL,
  PRIMARY KEY (`idBread`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_store: ~0 rows (aproximadamente)
DELETE FROM `appmo_store`;
/*!40000 ALTER TABLE `appmo_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `appmo_store` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_supply
CREATE TABLE IF NOT EXISTS `appmo_supply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rfc` varchar(100) NOT NULL,
  `number` varchar(50) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `addres` varchar(100) NOT NULL,
  `numberaddres` int(11) NOT NULL,
  `cp` int(11) NOT NULL,
  `colony` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_supply: ~0 rows (aproximadamente)
DELETE FROM `appmo_supply`;
/*!40000 ALTER TABLE `appmo_supply` DISABLE KEYS */;
INSERT INTO `appmo_supply` (`id`, `rfc`, `number`, `mail`, `addres`, `numberaddres`, `cp`, `colony`, `city`, `state`) VALUES
	(1, 'wer', '234', 'wer', 'ewr', 234, 234, 'f', 'sdf', 'Aguascalientes');
/*!40000 ALTER TABLE `appmo_supply` ENABLE KEYS */;

-- Volcando estructura para tabla appmo.appmo_user
CREATE TABLE IF NOT EXISTS `appmo_user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `subnamepather` varchar(50) NOT NULL,
  `subnamemother` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `addres` varchar(50) NOT NULL,
  `numberaddres` int(11) NOT NULL,
  `cp` int(11) NOT NULL,
  `colony` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla appmo.appmo_user: ~3 rows (aproximadamente)
DELETE FROM `appmo_user`;
/*!40000 ALTER TABLE `appmo_user` DISABLE KEYS */;
INSERT INTO `appmo_user` (`idUser`, `name`, `subnamepather`, `subnamemother`, `phone`, `type`, `password`, `addres`, `numberaddres`, `cp`, `colony`, `city`, `state`) VALUES
	(1, 'vic', 'Mendez', 'Martinez', '9191257895', 'GERENTE', '123', '', 0, 0, '', '', ''),
	(2, 'ger', 'Perez', 'Mayorga', '9631382799', 'GERENTE', '123', 'Conocido', 14, 30065, 'Las flfoes', 'Comi', 'Chiapas'),
	(4, 'asd', 'asd', 'asd', '', 'Jefe de Almacen', '', 'asd', 212, 12, 'dasd', 'asd', 'asd');
/*!40000 ALTER TABLE `appmo_user` ENABLE KEYS */;

-- Volcando estructura para disparador appmo.tri_orderproduct
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_orderproduct` AFTER INSERT ON `appmo_orderproduct` FOR EACH ROW BEGIN
DECLARE var_idOrder INT;
SET var_idOrder = (SELECT idOrder FROM appmo_order WHERE idOrder = (SELECT MAX(idOrder) FROM appmo_order));
INSERT INTO appmo_order_orderproduct (idOrder, idOrderproduct) VALUES (var_idOrder, NEW.idorderProduct);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador appmo.tri_purchaseCompraa
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_purchaseCompraa` AFTER INSERT ON `appmo_purchasematerial` FOR EACH ROW BEGIN
DECLARE var_idPurchase INT;
SET var_idPurchase = (SELECT idPurchase FROM appmo_purchase WHERE idPurchase =  (SELECT MAX(idPurchase) FROM appmo_purchase));
INSERT INTO appmo_purchase_purchasematerial (idPurchase, idPurchaseMaterial) VALUES (var_idPurchase, NEW.idPurchaseMaterial);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador appmo.tri_recipeIngredient
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_recipeIngredient` AFTER INSERT ON `appmo_recipeingredient` FOR EACH ROW BEGIN
DECLARE var_idRecipe INT;
SET var_idRecipe = (SELECT idRecipe FROM appmo_recipe WHERE idRecipe = (SELECT MAX(idRecipe) FROM appmo_recipe));
INSERT INTO appmo_recipe_recipeIngredient (idRecipe, idRecipeIngredient) VALUES (var_idRecipe, NEW.idRecipeIngredient);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador appmo.tri_sale
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tri_sale` AFTER UPDATE ON `appmo_order` FOR EACH ROW BEGIN 
IF NEW.status = 1 THEN BEGIN
DECLARE var_totalCost DECIMAL (10,2);
SET var_totalCost = (SELECT SUM(appmo_product.costProduct*appmo_orderproduct.quantityProduct) AS totalCost
FROM appmo_orderproduct, appmo_order, appmo_order_orderproduct, appmo_product
WHERE appmo_orderproduct.idOrderproduct = appmo_order_orderproduct.idorderproduct AND
appmo_order.idOrder = appmo_order_orderproduct.idorder AND
appmo_product.nameProduct = appmo_orderproduct.nameProduct AND
appmo_order_orderproduct.idorder = NEW.idOrder);
INSERT INTO appmo_sale (origin, totalCost, date) VALUES (NEW.destination, var_totalCost, CURRENT_DATE);
END; END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
