-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.30-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sanpedro
DROP DATABASE IF EXISTS `sanpedro`;
CREATE DATABASE IF NOT EXISTS `sanpedro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sanpedro`;

-- Volcando estructura para tabla sanpedro.almacen
DROP TABLE IF EXISTS `almacen`;
CREATE TABLE IF NOT EXISTS `almacen` (
  `idProductoAlmacenaje` int(11) NOT NULL,
  `nombreProductoAlmacen` varchar(50) NOT NULL,
  `tipoProductoAlmacen` varchar(50) NOT NULL,
  `cantidadProductoAlmacen` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idProductoAlmacenaje`),
  KEY `FK_almacen_usuario` (`idUsuario`),
  CONSTRAINT `FK_almacen_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.almacen: ~0 rows (aproximadamente)
DELETE FROM `almacen`;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.almacenruta
DROP TABLE IF EXISTS `almacenruta`;
CREATE TABLE IF NOT EXISTS `almacenruta` (
  `idProductoAlmacenado` int(11) NOT NULL,
  `idRuta` int(11) NOT NULL,
  `fechaHoraSolicitud` datetime NOT NULL,
  KEY `FK_almacenRuta_almacen` (`idProductoAlmacenado`),
  KEY `FK_almacenRuta_rutas` (`idRuta`),
  CONSTRAINT `FK_almacenRuta_almacen` FOREIGN KEY (`idProductoAlmacenado`) REFERENCES `almacen` (`idProductoAlmacenaje`),
  CONSTRAINT `FK_almacenRuta_rutas` FOREIGN KEY (`idRuta`) REFERENCES `rutas` (`idRutas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.almacenruta: ~0 rows (aproximadamente)
DELETE FROM `almacenruta`;
/*!40000 ALTER TABLE `almacenruta` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacenruta` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.bodega
DROP TABLE IF EXISTS `bodega`;
CREATE TABLE IF NOT EXISTS `bodega` (
  `idBodega` int(11) NOT NULL,
  `nombreMaterial` char(50) NOT NULL,
  `tipoMaterial` char(50) NOT NULL,
  `cantidadMaterial` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idBodega`),
  KEY `FK_bodega_usuario` (`idUsuario`),
  CONSTRAINT `FK_bodega_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.bodega: ~0 rows (aproximadamente)
DELETE FROM `bodega`;
/*!40000 ALTER TABLE `bodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `bodega` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.bodegaproducto
DROP TABLE IF EXISTS `bodegaproducto`;
CREATE TABLE IF NOT EXISTS `bodegaproducto` (
  `idBodega` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `fechaHoraSolicitud` datetime NOT NULL,
  KEY `FK_bodegaproducto_bodega` (`idBodega`),
  KEY `FK_bodegaproducto_producto` (`idProducto`),
  CONSTRAINT `FK_bodegaproducto_bodega` FOREIGN KEY (`idBodega`) REFERENCES `bodega` (`idBodega`),
  CONSTRAINT `FK_bodegaproducto_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.bodegaproducto: ~0 rows (aproximadamente)
DELETE FROM `bodegaproducto`;
/*!40000 ALTER TABLE `bodegaproducto` DISABLE KEYS */;
/*!40000 ALTER TABLE `bodegaproducto` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.comprabodega
DROP TABLE IF EXISTS `comprabodega`;
CREATE TABLE IF NOT EXISTS `comprabodega` (
  `idCompra` int(11) NOT NULL,
  `idBodega` int(11) NOT NULL,
  `fechaHoraIngreso` datetime NOT NULL,
  KEY `FK_comprabodega_compras` (`idCompra`),
  KEY `FK_comprabodega_bodega` (`idBodega`),
  CONSTRAINT `FK_comprabodega_bodega` FOREIGN KEY (`idBodega`) REFERENCES `bodega` (`idBodega`),
  CONSTRAINT `FK_comprabodega_compras` FOREIGN KEY (`idCompra`) REFERENCES `compras` (`idCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.comprabodega: ~0 rows (aproximadamente)
DELETE FROM `comprabodega`;
/*!40000 ALTER TABLE `comprabodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprabodega` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.compras
DROP TABLE IF EXISTS `compras`;
CREATE TABLE IF NOT EXISTS `compras` (
  `idCompra` int(11) NOT NULL,
  `nombreMaterialCompra` char(50) NOT NULL,
  `tipoMaterialCompra` char(50) NOT NULL,
  `cantidadMaterialCompra` int(11) NOT NULL,
  `costoMaterialCompra` float NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `FK_compras_usuario` (`idUsuario`),
  CONSTRAINT `FK_compras_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.compras: ~0 rows (aproximadamente)
DELETE FROM `compras`;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.pedidoalmacen
DROP TABLE IF EXISTS `pedidoalmacen`;
CREATE TABLE IF NOT EXISTS `pedidoalmacen` (
  `idProductoAlmacenado` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `fechaHoraPedido` datetime NOT NULL,
  PRIMARY KEY (`idProductoAlmacenado`),
  KEY `FK_pedidoAlmacen_pedidos` (`idPedido`),
  CONSTRAINT `FK_pedidoAlmacen_almacen` FOREIGN KEY (`idProductoAlmacenado`) REFERENCES `almacen` (`idProductoAlmacenaje`),
  CONSTRAINT `FK_pedidoAlmacen_pedidos` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.pedidoalmacen: ~0 rows (aproximadamente)
DELETE FROM `pedidoalmacen`;
/*!40000 ALTER TABLE `pedidoalmacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidoalmacen` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.pedidos
DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE IF NOT EXISTS `pedidos` (
  `idPedido` int(11) NOT NULL,
  `nombreProductoPedido` char(50) NOT NULL,
  `cantidadProductoPedido` int(11) NOT NULL,
  `destinoPedido` char(50) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `FK_pedidos_usuario` (`idUsuario`),
  CONSTRAINT `FK_pedidos_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.pedidos: ~0 rows (aproximadamente)
DELETE FROM `pedidos`;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.pedidoventa
DROP TABLE IF EXISTS `pedidoventa`;
CREATE TABLE IF NOT EXISTS `pedidoventa` (
  `idPedido` int(11) NOT NULL,
  `idVenta` int(11) NOT NULL,
  `fechaHoraVenta` datetime NOT NULL,
  KEY `FK_pedidoVenta_pedidos` (`idPedido`),
  KEY `FK_pedidoVenta_ventas` (`idVenta`),
  CONSTRAINT `FK_pedidoVenta_pedidos` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`),
  CONSTRAINT `FK_pedidoVenta_ventas` FOREIGN KEY (`idVenta`) REFERENCES `ventas` (`idVenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.pedidoventa: ~0 rows (aproximadamente)
DELETE FROM `pedidoventa`;
/*!40000 ALTER TABLE `pedidoventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidoventa` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.producto
DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `idProducto` int(11) NOT NULL,
  `nombreProducto` char(50) NOT NULL,
  `tipoProducto` int(11) NOT NULL,
  `precioProducto` float NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FK_producto_usuario` (`idUsuario`),
  CONSTRAINT `FK_producto_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.producto: ~0 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.productoalmacen
DROP TABLE IF EXISTS `productoalmacen`;
CREATE TABLE IF NOT EXISTS `productoalmacen` (
  `idProducto` int(11) NOT NULL,
  `idProductoAlmacen` int(11) NOT NULL,
  `fechaHoraAlmacenje` datetime NOT NULL,
  KEY `FK_productoalmacen_producto` (`idProducto`),
  KEY `FK_productoalmacen_almacen` (`idProductoAlmacen`),
  CONSTRAINT `FK_productoalmacen_almacen` FOREIGN KEY (`idProductoAlmacen`) REFERENCES `almacen` (`idProductoAlmacenaje`),
  CONSTRAINT `FK_productoalmacen_producto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.productoalmacen: ~0 rows (aproximadamente)
DELETE FROM `productoalmacen`;
/*!40000 ALTER TABLE `productoalmacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoalmacen` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.rutas
DROP TABLE IF EXISTS `rutas`;
CREATE TABLE IF NOT EXISTS `rutas` (
  `idRutas` int(11) NOT NULL,
  `encargadoRuta` varchar(50) NOT NULL,
  `nombreRuta` varchar(50) NOT NULL,
  PRIMARY KEY (`idRutas`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.rutas: ~0 rows (aproximadamente)
DELETE FROM `rutas`;
/*!40000 ALTER TABLE `rutas` DISABLE KEYS */;
/*!40000 ALTER TABLE `rutas` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `matriculaUsuario` int(11) NOT NULL,
  `nombreUsuario` varchar(50) NOT NULL,
  `contraseñaUsuario` char(50) NOT NULL,
  PRIMARY KEY (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.usuario: ~0 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para tabla sanpedro.ventas
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE IF NOT EXISTS `ventas` (
  `idVenta` int(11) NOT NULL,
  `montoVenta` float NOT NULL,
  `origenVenta` char(50) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `FK_ventas_usuario` (`idUsuario`),
  CONSTRAINT `FK_ventas_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`matriculaUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sanpedro.ventas: ~0 rows (aproximadamente)
DELETE FROM `ventas`;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
