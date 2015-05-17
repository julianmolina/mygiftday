-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-05-2015 a las 04:50:41
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mygiftday`
--
CREATE DATABASE IF NOT EXISTS `mygiftday` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mygiftday`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bonus`
--

DROP TABLE IF EXISTS `bonus`;
CREATE TABLE IF NOT EXISTS `bonus` (
  `id_bonus` int(11) NOT NULL AUTO_INCREMENT,
  `percentage` double DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_bonus`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `bonus`
--

INSERT INTO `bonus` (`id_bonus`, `percentage`, `amount`, `status`, `register_date`, `update`) VALUES
(1, 5, 50000, 1, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contribution`
--

DROP TABLE IF EXISTS `contribution`;
CREATE TABLE IF NOT EXISTS `contribution` (
  `id_contribution` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `id_bonus` int(11) NOT NULL,
  `message` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_contribution`),
  KEY `fk_contributor_user1_idx` (`id_user`),
  KEY `fk_contributor_event1_idx` (`id_event`),
  KEY `fk_contributor_bonus1_idx` (`id_bonus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `id_event_type` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `iwant` text,
  `date` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `value_product` double DEFAULT NULL,
  `first_amount` double DEFAULT NULL,
  `last_date` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_event`),
  KEY `fk_event_event_type_idx` (`id_event_type`),
  KEY `fk_event_user1_idx` (`id_user`),
  KEY `fk_event_product1_idx` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event_type`
--

DROP TABLE IF EXISTS `event_type`;
CREATE TABLE IF NOT EXISTS `event_type` (
  `id_event_type` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(45) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `update` timestamp NULL DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_event_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `event_type`
--

INSERT INTO `event_type` (`id_event_type`, `event_type`, `status`, `update`, `register_date`) VALUES
(1, 'Birthday', 1, NULL, NULL),
(2, 'Graduation', 1, NULL, NULL),
(3, 'Baby Shower', 1, NULL, NULL),
(4, 'Christmas', 1, NULL, NULL),
(5, 'Thanksgiving', 1, NULL, NULL),
(6, 'Special Event', 1, NULL, NULL),
(7, 'Marriage', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `id_provider` int(11) NOT NULL,
  `product` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `picture` text,
  `status` tinyint(4) NOT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  KEY `fk_product_provider1_idx` (`id_provider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provider`
--

DROP TABLE IF EXISTS `provider`;
CREATE TABLE IF NOT EXISTS `provider` (
  `id_provider` int(11) NOT NULL AUTO_INCREMENT,
  `provider` text,
  `status` tinyint(4) NOT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  `update` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_provider`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_facebook` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `last` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `update` timestamp NULL DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_user`, `id_facebook`, `name`, `last`, `email`, `birthday`, `status`, `update`, `register_date`) VALUES
(1, 34563456, 'luis', 'arevalo', 'j@d.com', '2001-01-01 00:00:00', 1, '2015-05-17 07:05:45', '2015-05-17 07:05:45');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contribution`
--
ALTER TABLE `contribution`
  ADD CONSTRAINT `fk_contributor_bonus1` FOREIGN KEY (`id_bonus`) REFERENCES `bonus` (`id_bonus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_contributor_event1` FOREIGN KEY (`id_event`) REFERENCES `event` (`id_event`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_contributor_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `fk_event_event_type` FOREIGN KEY (`id_event_type`) REFERENCES `event_type` (`id_event_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_event_product1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_event_user1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_provider1` FOREIGN KEY (`id_provider`) REFERENCES `provider` (`id_provider`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
