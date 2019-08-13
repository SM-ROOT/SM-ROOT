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

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_material
CREATE TABLE IF NOT EXISTS `appmo_material` (
  `idIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`idIngredient`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_order
CREATE TABLE IF NOT EXISTS `appmo_order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `nameClient` char(50) NOT NULL DEFAULT 'otro',
  `destination` char(50) NOT NULL DEFAULT 'otro',
  `date` date NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_orderproduct
CREATE TABLE IF NOT EXISTS `appmo_orderproduct` (
  `idOrderProduct` int(11) NOT NULL AUTO_INCREMENT,
  `nameProduct` char(50) NOT NULL,
  `typeProduct` varchar(50) NOT NULL DEFAULT '',
  `quantityProduct` int(11) NOT NULL,
  PRIMARY KEY (`idOrderProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_product
CREATE TABLE IF NOT EXISTS `appmo_product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `nameProduct` varchar(50) NOT NULL,
  `costProduct` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_purchase
CREATE TABLE IF NOT EXISTS `appmo_purchase` (
  `idPurchase` int(11) NOT NULL AUTO_INCREMENT,
  `purchaseDate` date NOT NULL,
  PRIMARY KEY (`idPurchase`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_purchasematerial
CREATE TABLE IF NOT EXISTS `appmo_purchasematerial` (
  `idPurchaseMaterial` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `quantity` int(10) NOT NULL,
  `measure` varchar(50) NOT NULL,
  `coste` decimal(50,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPurchaseMaterial`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_recipe
CREATE TABLE IF NOT EXISTS `appmo_recipe` (
  `idRecipe` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `type` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`idRecipe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_recipeingredient
CREATE TABLE IF NOT EXISTS `appmo_recipeingredient` (
  `idRecipeIngredient` int(11) NOT NULL AUTO_INCREMENT,
  `nameIngredient` varchar(50) NOT NULL DEFAULT '',
  `measureIngredient` varchar(50) NOT NULL DEFAULT '',
  `quantityIngredient` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idRecipeIngredient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_route
CREATE TABLE IF NOT EXISTS `appmo_route` (
  `idRoute` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `curator` varchar(200) NOT NULL,
  PRIMARY KEY (`idRoute`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_sale
CREATE TABLE IF NOT EXISTS `appmo_sale` (
  `idSale` int(11) NOT NULL AUTO_INCREMENT,
  `origin` varchar(50) NOT NULL,
  `totalCost` decimal(10,2) NOT NULL DEFAULT '0.00',
  `date` date NOT NULL,
  PRIMARY KEY (`idSale`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla appmo.appmo_store
CREATE TABLE IF NOT EXISTS `appmo_store` (
  `idBread` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `type` varchar(50) NOT NULL,
  `quantity` varchar(50) NOT NULL,
  PRIMARY KEY (`idBread`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

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
INSERT INTO appmo_purchase_purchasematerial (idPurchase, idPurchaseMaterial) VALUES (23, NEW.idPurchaseMaterial);
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
