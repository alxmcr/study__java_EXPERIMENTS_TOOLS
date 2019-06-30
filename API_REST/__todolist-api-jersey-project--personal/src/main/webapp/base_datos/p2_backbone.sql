-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-01-2015 a las 22:21:11
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `p2_backbone`
--
CREATE DATABASE IF NOT EXISTS `p2_backbone` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `p2_backbone`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarea`
--

DROP TABLE IF EXISTS `tarea`;
CREATE TABLE IF NOT EXISTS `tarea` (
  `idtarea` varchar(50) NOT NULL,
  `texto` varchar(50) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `horaCreacion` time NOT NULL,
  `fechaModificacion` date NOT NULL,
  `horaModificacion` time NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tarea`
--

INSERT INTO `tarea` (`idtarea`, `texto`, `fechaCreacion`, `horaCreacion`, `fechaModificacion`, `horaModificacion`, `estado`) VALUES
('1e2e27e4-8eba-11e4-9234-0123456789ab', 'Programacion Basica', '2015-01-05', '13:31:23', '2015-01-18', '17:18:32', 'archivada'),
('1ehiifxyzp8kmfcq7rsylcd6yb', 'Backbone', '2015-01-15', '01:10:31', '2015-01-18', '17:19:06', 'concluida'),
('1kvfjbmp1dynsdr81kzp7c3pic', 'Angular', '2015-01-02', '01:15:29', '2015-01-18', '17:19:21', 'concluida'),
('1ntgrkaujqe93tkq6otwa79z4r', 'casa', '2015-01-17', '17:19:40', '2015-01-18', '17:19:46', 'concluida'),
('9ql0oirftvtaff1qtt5d5drhg', 'UI_UX', '2015-01-16', '01:18:22', '2015-01-16', '01:18:22', 'pendiente'),
('aeb9fb74-8eb8-11e4-9234-0123456789ab', 'Backend', '2015-01-01', '10:10:12', '2015-01-07', '09:13:13', 'pendiente'),
('b19e54b4-8ece-11e4-9234-0123456789ab', 'Diseño Web Online', '2015-01-06', '14:22:24', '2015-01-09', '08:12:23', 'pendiente'),
('bslxf3wpumjvu6mao8iv4ypzd', 'mongodb', '2015-01-16', '01:22:33', '2015-01-16', '01:22:33', 'concluida'),
('eeb9fb74-8eb8-11e4-9234-0123456789ab', 'mysql', '2014-12-30', '10:18:44', '2015-01-18', '16:25:24', 'archivada'),
('f19e54b4-8ece-11e3-9234-0123456789ab', 'Brasil', '2015-01-02', '11:28:43', '2015-01-18', '11:46:48', 'archivada'),
('k6335k5otbur8v93a0o5vfn9e', 'SQL Lite', '2015-01-16', '01:25:56', '2015-01-16', '01:25:56', 'pendiente'),
('ogfxbyuzo3ocfztsjtjfimr3y', 'node js', '2015-01-16', '01:20:09', '2015-01-16', '01:20:09', 'pendiente'),
('v5extvahy84kw7at76mpr035x', 'JQuery', '2015-01-16', '01:36:07', '2015-01-16', '01:36:07', 'pendiente'),
('wef6ubebtiq1tp1147ly8pc32', 'Javascript', '2015-01-16', '01:32:05', '2015-01-16', '01:32:05', 'pendiente'),
('xasbo1b8f19lu1fe32gattvlt', 'illustrator', '2015-01-18', '16:22:55', '2015-01-18', '16:22:55', 'pendiente'),
('xe2e27e4-8eba-11e4-9234-0123456789a', 'swift', '2015-01-04', '13:16:16', '2015-01-18', '16:23:23', 'archivada');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tarea`
--
ALTER TABLE `tarea`
 ADD PRIMARY KEY (`idtarea`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
