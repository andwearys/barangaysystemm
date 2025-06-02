-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2025 at 11:19 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nodatabarangaysystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `brgycouncil`
--

CREATE TABLE `brgycouncil` (
  `brgyChairman` varchar(50) NOT NULL,
  `commiteeAppropiation` varchar(50) NOT NULL,
  `commiteeEducation` varchar(50) NOT NULL,
  `commiteeHumanRights` varchar(50) NOT NULL,
  `commiteeEnvironment` varchar(50) NOT NULL,
  `commiteeRules` varchar(50) NOT NULL,
  `commiteeInfrastructure` varchar(50) NOT NULL,
  `commiteeCooperatives` varchar(50) NOT NULL,
  `brgySecretary` varchar(50) NOT NULL,
  `brgyTreasurer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `brgycouncil`
--

INSERT INTO `brgycouncil` (`brgyChairman`, `commiteeAppropiation`, `commiteeEducation`, `commiteeHumanRights`, `commiteeEnvironment`, `commiteeRules`, `commiteeInfrastructure`, `commiteeCooperatives`, `brgySecretary`, `brgyTreasurer`) VALUES
('Hon. Monkey D. Luffy', 'Hon. Lelouch vi Britania', 'Hon. Lucy Heartfilia', 'Hon. Saitama kun', 'Hon. Ichigo Kurosaki', 'Hon. Natsuu Dragneel', 'Hon. Uzumaki Naruto', 'Hon. Gon Freecss', 'Hon. Sakura Haruno', 'Hon. Nico Robin');

-- --------------------------------------------------------

--
-- Table structure for table `docreqreason`
--

CREATE TABLE `docreqreason` (
  `requestReason` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `docreqreason`
--

INSERT INTO `docreqreason` (`requestReason`) VALUES
('JOB APPLICATION'),
('BUSINESS'),
('SCHOOL REQUIREMENT');

-- --------------------------------------------------------

--
-- Table structure for table `documentprice`
--

CREATE TABLE `documentprice` (
  `DocumentID` int(11) NOT NULL,
  `DocumentType` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `documentprice`
--

INSERT INTO `documentprice` (`DocumentID`, `DocumentType`, `Price`) VALUES
(1, 'Barangay Certificate', 30),
(2, 'Barangay Permit', 50),
(3, 'Business Permit', 600);

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `processID` int(11) NOT NULL,
  `fName` varchar(50) NOT NULL,
  `mName` varchar(50) NOT NULL,
  `lName` varchar(50) NOT NULL,
  `addressBlock` int(11) NOT NULL,
  `addressLot` int(11) NOT NULL,
  `brgyCertificate` varchar(50) NOT NULL,
  `brgyPermit` varchar(50) NOT NULL,
  `businessPermit` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `processBy` varchar(50) NOT NULL,
  `confirmationCode` int(50) NOT NULL,
  `amount` varchar(50) NOT NULL,
  `processStatus` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `documenttransaction`
--

CREATE TABLE `documenttransaction` (
  `TransactionID` int(11) NOT NULL,
  `User Name` varchar(50) NOT NULL,
  `Client Name` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Purpose` varchar(50) NOT NULL,
  `Business Name` varchar(50) DEFAULT NULL,
  `BusinessBlock` varchar(50) DEFAULT NULL,
  `BusinessLot` varchar(50) DEFAULT NULL,
  `Time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `onetimelogin`
--

CREATE TABLE `onetimelogin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `onetimelogin`
--

INSERT INTO `onetimelogin` (`username`, `password`, `userType`, `status`) VALUES
('group2', 'group2', 'Admin', 'notUsed');

-- --------------------------------------------------------

--
-- Table structure for table `residents`
--

CREATE TABLE `residents` (
  `ResidentID` int(11) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `mname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `pob` varchar(30) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `registeredvoter` varchar(50) NOT NULL,
  `addressBlock` varchar(30) NOT NULL,
  `addressLot` varchar(30) NOT NULL,
  `father-fname` varchar(20) NOT NULL,
  `father-lname` varchar(20) NOT NULL,
  `father-occupation` varchar(30) NOT NULL,
  `mother-fname` varchar(20) NOT NULL,
  `mother-lname` varchar(20) NOT NULL,
  `mother-occupation` varchar(30) NOT NULL,
  `guardian-fname` varchar(30) NOT NULL,
  `guardian-lname` varchar(30) NOT NULL,
  `guardian-relationship` varchar(30) NOT NULL,
  `guardian-occupation` varchar(30) NOT NULL,
  `spouse-fname` varchar(30) NOT NULL,
  `spouse-lname` varchar(30) NOT NULL,
  `spouse-occupation` varchar(30) NOT NULL,
  `noOfSibblings` int(11) NOT NULL,
  `BirthRank` varchar(30) NOT NULL,
  `image` longblob DEFAULT NULL,
  `archive` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `mname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(30) NOT NULL,
  `contactNumber` varchar(50) NOT NULL,
  `eMail` varchar(50) NOT NULL,
  `addressBlock` varchar(30) NOT NULL,
  `addressLot` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `position` varchar(30) NOT NULL,
  `userType` varchar(30) NOT NULL,
  `about` longtext NOT NULL,
  `AccountStatus` varchar(30) NOT NULL,
  `archive` varchar(30) NOT NULL,
  `image` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `documentprice`
--
ALTER TABLE `documentprice`
  ADD PRIMARY KEY (`DocumentID`);

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`processID`);

--
-- Indexes for table `documenttransaction`
--
ALTER TABLE `documenttransaction`
  ADD PRIMARY KEY (`TransactionID`);

--
-- Indexes for table `residents`
--
ALTER TABLE `residents`
  ADD PRIMARY KEY (`ResidentID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `documentprice`
--
ALTER TABLE `documentprice`
  MODIFY `DocumentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `documents`
--
ALTER TABLE `documents`
  MODIFY `processID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;

--
-- AUTO_INCREMENT for table `documenttransaction`
--
ALTER TABLE `documenttransaction`
  MODIFY `TransactionID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `residents`
--
ALTER TABLE `residents`
  MODIFY `ResidentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
