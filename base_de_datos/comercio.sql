-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-06-2023 a las 19:02:16
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `comercio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `idCategoria` int(3) NOT NULL,
  `categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idCategoria`, `categoria`) VALUES
(1, 'Tecnologia'),
(2, 'Ocio'),
(3, 'Decoracion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `precio` double(6,2) NOT NULL,
  `cantidad` int(4) NOT NULL,
  `rutaImagen` varchar(50) NOT NULL,
  `codCategoria` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `precio`, `cantidad`, `rutaImagen`, `codCategoria`) VALUES
(1, 'Portátil', 799.99, 10, 'laptop.jpg', 1),
(2, 'Monopoly', 16.95, 26, 'monopoly.jpg', 2),
(3, 'Teclado', 78.99, 5, 'teclado.jpg', 1),
(4, 'Luces led', 12.82, 34, 'led.jpg', 3),
(5, 'Patinete', 89.76, 9, 'skateboard.jpg', 2),
(6, 'Libro', 14.55, 60, 'libro.jpg', 2),
(7, 'Auriculares', 65.97, 28, 'auriculares.jpg', 1),
(8, 'Jarrón', 14.99, 3, 'jarron.jpg', 3),
(9, 'Ratón', 31.85, 18, 'raton.jpg', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `idValoracion` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `valoracion` int(1) NOT NULL,
  `comentario` varchar(500) NOT NULL,
  `codProducto` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`idValoracion`, `nombre`, `valoracion`, `comentario`, `codProducto`) VALUES
(1, 'Andrea', 3, 'Me gustan los colores, pero no es lo que esperaba', 4),
(2, 'Daniel', 4, 'El portatil es muy bueno, recomiendo que lo compren', 1),
(3, 'Pedro', 3, 'Este equipo es muy ligero, pero me ha dado problemas con algunas aplicaciones', 1),
(4, 'Paula', 1, 'No me han gustado nada los auriculares, me dan muchos fallos con el sonido', 7),
(5, 'Jorge', 4, 'Muy buen producto', 8),
(6, 'Raquel', 5, 'Me ha gustado mucho este teclado. Funciona muy bien y es muy bonito.', 3),
(7, 'Pedro', 3, 'Lo he estado probando en mi nuevo ordenador y he de decir que me ha parecido bastante bueno, pero prefiero otros modelos.', 3),
(8, 'Vicente', 3, 'El color del patinete no me convence, estaría bien que pudieras elegir otros colores.', 5),
(9, 'Andrea', 4, 'Me parece que está muy bien en relación calidad-precio. Si buscas un portátil este es una buena opción.', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(5) NOT NULL,
  `nombreComprador` varchar(50) NOT NULL,
  `cantidad` int(4) NOT NULL,
  `fecha` date NOT NULL,
  `precio` double(6,2) NOT NULL,
  `codProducto` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `nombreComprador`, `cantidad`, `fecha`, `precio`, `codProducto`) VALUES
(1, 'Daniel', 1, '2023-05-20', 799.99, 1),
(2, 'Carlos', 2, '2023-05-20', 16.95, 2),
(3, 'Andrea', 4, '2023-05-21', 12.82, 4),
(4, 'Roberto', 1, '2023-05-31', 16.95, 2),
(5, 'Federico', 3, '2023-05-29', 14.99, 8),
(6, 'Paula', 1, '2023-06-01', 65.97, 7),
(7, 'Andrea', 1, '2023-06-02', 799.99, 1),
(8, 'Gabriel', 1, '2023-06-05', 14.55, 6);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProducto`),
  ADD KEY `foraneaCategoria` (`codCategoria`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`idValoracion`),
  ADD KEY `foraneaValoraciones` (`codProducto`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `foraneaVentas` (`codProducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `idProducto` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `idValoracion` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `foraneaCategoria` FOREIGN KEY (`codCategoria`) REFERENCES `categorias` (`idCategoria`);

--
-- Filtros para la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD CONSTRAINT `foraneaValoraciones` FOREIGN KEY (`codProducto`) REFERENCES `productos` (`idProducto`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `foraneaVentas` FOREIGN KEY (`codProducto`) REFERENCES `productos` (`idProducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
