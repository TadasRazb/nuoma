-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 22, 2021 at 01:18 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nuoma`
--

-- --------------------------------------------------------

--
-- Table structure for table `irankiai`
--

CREATE TABLE `irankiai` (
  `id` int(10) UNSIGNED NOT NULL,
  `pavadinimas` varchar(255) NOT NULL,
  `irankio_tipas` varchar(255) NOT NULL,
  `inventoriaus_nr` varchar(255) NOT NULL,
  `isigyjimo_data` int(11) NOT NULL,
  `isigyjimo_kaina` double NOT NULL,
  `nuomos_kaina` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `klientai`
--

CREATE TABLE `klientai` (
  `id` int(10) UNSIGNED NOT NULL,
  `pav_kliento` varchar(255) NOT NULL,
  `fizinis_juridinis` varchar(255) NOT NULL,
  `kontaktas` varchar(255) NOT NULL,
  `id_irankiai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zurnalas`
--

CREATE TABLE `zurnalas` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_kliento` int(11) NOT NULL,
  `id_irankio` int(11) NOT NULL,
  `kada_isnuomota` varchar(255) NOT NULL,
  `kada_grazinta` varchar(255) NOT NULL,
  `bukle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `irankiai`
--
ALTER TABLE `irankiai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `klientai`
--
ALTER TABLE `klientai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zurnalas`
--
ALTER TABLE `zurnalas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `irankiai`
--
ALTER TABLE `irankiai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `klientai`
--
ALTER TABLE `klientai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `zurnalas`
--
ALTER TABLE `zurnalas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
