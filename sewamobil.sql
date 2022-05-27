-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2022 at 04:00 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sewamobil`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `ProductId` varchar(5) NOT NULL,
  `UserId` varchar(5) NOT NULL,
  `Quantity` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `Id` varchar(5) NOT NULL,
  `Name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Id`, `Name`) VALUES
('1', 'Minibus'),
('2', 'Family Car'),
('3', 'Sedan');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `Id` varchar(5) NOT NULL,
  `CategoryId` varchar(5) NOT NULL,
  `Name` varchar(15) NOT NULL,
  `Price` int(11) NOT NULL,
  `Rating` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`Id`, `CategoryId`, `Name`, `Price`, `Rating`) VALUES
('FC001', '2', 'Honda Odyssey', 450000, '9/10'),
('FC002', '2', 'KIA Telluride', 500000, '8/10'),
('FC003', '2', 'Toyota Avalon', 500000, '9/10'),
('FC004', '2', 'Toyota Camry', 500000, '8/10'),
('FC005', '2', 'Ford Expedition', 750000, '9/10'),
('MB001', '1', 'Isuzu Elf 2.8 M', 600000, '8/10'),
('MB002', '1', 'Mitsubishi Colt', 500000, '5/10'),
('MB003', '1', 'Suzuki APV', 350000, '8/10'),
('MB004', '1', 'Isuzu Lexia', 1100000, '9/10'),
('MB005', '1', 'Toyota Hiace', 320000, '9/10'),
('MB006', '1', 'Hyundai H1', 550000, '9/10'),
('MB007', '1', 'DFSK Gelora', 460000, '10/10'),
('MB008', '1', 'Toyota GranAce', 750000, '10/10'),
('SN002', '3', 'Mazda 3', 500000, '9/10'),
('SN003', '3', 'Nissan Versa', 310000, '7/10'),
('SN004', '3', 'Honda Accord', 650000, '9/10');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id` varchar(5) NOT NULL,
  `Name` varchar(15) NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Address` varchar(25) NOT NULL,
  `Date of Birth` date NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `Username` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL,
  `Role` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id`, `Name`, `Gender`, `Address`, `Date of Birth`, `PhoneNumber`, `Username`, `Password`, `Role`) VALUES
('US001', 'Calvin Arihta', 'Male', 'CalvinArihta St.', '1945-06-21', '08521000001', 'Calvin', 'Calvin', 'Customer'),
('US002', 'Admin', 'Male', 'AdminAdmin', '1900-01-01', '-', 'Admin', 'Admin', 'Admin'),
('US003', 'Member 1', 'Female', 'Kemanggisan Street', '2000-01-01', '-', 'member123', 'member123', 'Customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`ProductId`,`UserId`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `CategoryId` (`CategoryId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ProductId`) REFERENCES `product` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
