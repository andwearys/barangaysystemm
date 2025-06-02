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
-- Database: `barangaysystemreports`
--

-- --------------------------------------------------------

--
-- Table structure for table `audittrail`
--

CREATE TABLE `audittrail` (
  `Audit Trail ID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `User Name` varchar(50) NOT NULL,
  `Action Performed` varchar(50) NOT NULL,
  `Resident ID` varchar(50) DEFAULT NULL,
  `User ID` varchar(50) DEFAULT NULL,
  `AuditTrailType` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `audittrail`
--

INSERT INTO `audittrail` (`Audit Trail ID`, `Date`, `Time`, `User Name`, `Action Performed`, `Resident ID`, `User ID`, `AuditTrailType`) VALUES
(1, '2025-06-01', '01:40:12', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(2, '2025-06-01', '01:40:52', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(3, '2025-06-01', '02:12:38', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(4, '2025-06-01', '02:13:07', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(5, '2025-06-01', '02:13:15', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(6, '2025-06-01', '02:14:40', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(7, '2025-06-01', '02:24:59', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(8, '2025-06-01', '03:22:10', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(9, '2025-06-01', '03:24:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(10, '2025-06-01', '03:28:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(11, '2025-06-01', '03:32:04', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(12, '2025-06-01', '03:37:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(13, '2025-06-01', '03:39:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(14, '2025-06-01', '03:43:01', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(15, '2025-06-01', '03:44:57', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(16, '2025-06-01', '03:46:04', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(17, '2025-06-01', '03:47:54', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(18, '2025-06-01', '03:50:22', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(19, '2025-06-01', '03:50:58', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(20, '2025-06-01', '03:53:27', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(21, '2025-06-01', '03:56:01', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(22, '2025-06-01', '03:57:50', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(23, '2025-06-01', '03:59:01', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(24, '2025-06-01', '04:03:08', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(25, '2025-06-01', '04:05:40', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(26, '2025-06-01', '04:08:22', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(27, '2025-06-01', '04:10:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(28, '2025-06-01', '04:13:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(29, '2025-06-01', '04:17:29', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(30, '2025-06-01', '04:23:27', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(31, '2025-06-01', '04:25:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(32, '2025-06-01', '04:28:56', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(33, '2025-06-01', '04:30:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(34, '2025-06-01', '04:33:17', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(35, '2025-06-01', '04:36:15', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(36, '2025-06-01', '04:37:52', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(37, '2025-06-01', '04:39:33', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(38, '2025-06-01', '04:41:09', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(39, '2025-06-01', '04:42:13', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(40, '2025-06-01', '04:46:43', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(41, '2025-06-01', '04:49:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(42, '2025-06-01', '05:02:19', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(43, '2025-06-01', '05:03:31', 'Andrea Maria Reyes', 'REGISTER', NULL, '5', 'User'),
(44, '2025-06-01', '05:03:37', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(45, '2025-06-01', '05:03:45', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(46, '2025-06-01', '05:04:56', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(47, '2025-06-01', '05:05:02', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(48, '2025-06-01', '05:05:42', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(49, '2025-06-01', '10:01:30', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(50, '2025-06-01', '10:05:07', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(51, '2025-06-01', '10:08:53', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(52, '2025-06-01', '10:29:07', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(53, '2025-06-01', '10:41:31', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(54, '2025-06-01', '10:52:53', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(55, '2025-06-01', '10:54:12', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(56, '2025-06-01', '11:11:55', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(57, '2025-06-01', '11:13:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(58, '2025-06-01', '11:15:01', 'Andrea Maria Reyes', 'UPDATE', '2', NULL, 'Resident'),
(59, '2025-06-01', '11:17:27', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(60, '2025-06-01', '11:18:40', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(61, '2025-06-01', '11:24:44', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(62, '2025-06-01', '11:29:42', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(63, '2025-06-01', '12:02:22', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(64, '2025-06-01', '12:06:32', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(65, '2025-06-01', '12:06:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(66, '2025-06-01', '12:12:24', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(67, '2025-06-01', '12:17:35', 'Andrea Maria Reyes', 'REGISTER', '5', NULL, 'Resident'),
(68, '2025-06-01', '12:20:29', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(69, '2025-06-01', '12:26:18', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(70, '2025-06-01', '12:41:55', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(71, '2025-06-01', '12:48:49', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(72, '2025-06-01', '01:01:05', 'Andrea Maria Reyes', 'REGISTER', NULL, '6', 'User'),
(73, '2025-06-01', '01:01:12', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(74, '2025-06-01', '01:01:20', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(75, '2025-06-01', '01:04:20', 'Cute Me', 'LOGOUT', NULL, '6', 'Logs'),
(76, '2025-06-01', '01:04:28', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(77, '2025-06-01', '01:04:36', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(78, '2025-06-01', '01:04:42', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(79, '2025-06-01', '01:05:59', 'Andrea Maria Reyes', 'UPDATE', NULL, '6', 'User'),
(80, '2025-06-01', '01:06:34', 'Andrea Maria Reyes', 'UPDATE', NULL, '6', 'User'),
(81, '2025-06-01', '01:07:42', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(82, '2025-06-01', '01:07:49', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(83, '2025-06-01', '01:08:21', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(84, '2025-06-01', '01:08:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(85, '2025-06-01', '01:08:53', 'Andrea Maria Reyes', 'UPDATE', NULL, '5', 'User'),
(86, '2025-06-01', '01:09:03', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(87, '2025-06-01', '01:09:09', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(88, '2025-06-01', '01:09:26', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(89, '2025-06-01', '01:09:48', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(90, '2025-06-01', '01:10:08', 'Andrea Maria Reyes', 'UPDATE', NULL, '5', 'User'),
(91, '2025-06-01', '01:10:14', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(92, '2025-06-01', '01:10:20', 'Michael Galicia', 'LOGIN', NULL, '5', 'Logs'),
(93, '2025-06-01', '01:10:24', 'Michael Galicia', 'LOGOUT', NULL, '5', 'Logs'),
(94, '2025-06-01', '01:10:34', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(95, '2025-06-01', '01:10:52', 'Andrea Maria Reyes', 'UPDATE', NULL, '6', 'User'),
(96, '2025-06-01', '01:10:58', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(97, '2025-06-01', '01:11:04', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(98, '2025-06-01', '01:11:07', 'Cute Me', 'LOGOUT', NULL, '6', 'Logs'),
(99, '2025-06-01', '01:11:29', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(100, '2025-06-01', '01:11:41', 'Andrea Maria Reyes', 'UPDATE', NULL, '6', 'User'),
(101, '2025-06-01', '01:11:44', 'Andrea Maria Reyes', 'UPDATE', NULL, '6', 'User'),
(102, '2025-06-01', '01:11:53', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(103, '2025-06-01', '01:11:59', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(104, '2025-06-01', '01:12:10', 'Cute Me', 'LOGOUT', NULL, '6', 'Logs'),
(105, '2025-06-01', '01:12:16', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(106, '2025-06-01', '01:46:55', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(107, '2025-06-01', '01:52:23', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(108, '2025-06-01', '01:58:45', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(109, '2025-06-01', '02:58:42', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(110, '2025-06-01', '03:01:07', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(111, '2025-06-01', '03:07:33', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(112, '2025-06-01', '03:10:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(113, '2025-06-01', '03:36:06', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(114, '2025-06-01', '03:37:18', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(115, '2025-06-01', '03:42:08', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(116, '2025-06-01', '03:49:27', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(117, '2025-06-01', '03:50:23', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(118, '2025-06-01', '03:53:52', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(119, '2025-06-01', '03:55:08', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(120, '2025-06-01', '03:55:35', 'Cute Me', 'LOGIN', NULL, '6', 'Logs'),
(121, '2025-06-01', '03:56:19', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(122, '2025-06-01', '03:59:12', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(123, '2025-06-01', '04:01:23', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(124, '2025-06-01', '04:02:02', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(125, '2025-06-01', '04:06:43', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(126, '2025-06-01', '04:30:32', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(127, '2025-06-01', '05:25:50', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(128, '2025-06-01', '05:49:50', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(129, '2025-06-01', '05:57:45', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(130, '2025-06-01', '06:02:42', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(131, '2025-06-01', '06:21:14', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(132, '2025-06-01', '06:38:56', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(133, '2025-06-01', '07:12:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(134, '2025-06-01', '09:53:05', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(135, '2025-06-01', '10:00:35', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(136, '2025-06-01', '10:15:38', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(137, '2025-06-01', '10:16:34', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(138, '2025-06-01', '10:32:37', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(139, '2025-06-01', '10:32:41', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(140, '2025-06-01', '11:16:53', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(141, '2025-06-02', '12:03:22', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(142, '2025-06-02', '12:05:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(143, '2025-06-02', '12:06:15', 'Andrea Maria Reyes', 'ARCHIVE', '1', NULL, 'Resident'),
(144, '2025-06-02', '12:06:25', 'Andrea Maria Reyes', 'RESTORED ARCHIVE', '1', NULL, 'Resident'),
(145, '2025-06-02', '12:06:44', 'Andrea Maria Reyes', 'PROFILE UPDATE', NULL, '4', 'User'),
(146, '2025-06-02', '12:08:07', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(147, '2025-06-02', '12:09:48', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(148, '2025-06-02', '12:11:28', 'Andrea Maria Reyes', 'LOGIN FAILED', NULL, '4', 'Logs'),
(149, '2025-06-02', '12:11:34', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(150, '2025-06-02', '12:13:03', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(151, '2025-06-02', '12:16:03', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(152, '2025-06-02', '12:18:04', 'Andrea Maria Reyes', 'ARCHIVE', '3', NULL, 'Resident'),
(153, '2025-06-02', '12:19:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(154, '2025-06-02', '12:21:35', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(155, '2025-06-02', '12:25:44', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(156, '2025-06-02', '12:27:54', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(157, '2025-06-02', '12:28:48', 'Andrea Maria Reyes', 'REGISTER', '6', NULL, 'Resident'),
(158, '2025-06-02', '12:29:13', 'Andrea Maria Reyes', 'UPDATE', '6', NULL, 'Resident'),
(159, '2025-06-02', '12:29:35', 'Andrea Maria Reyes', 'ARCHIVE', '1', NULL, 'Resident'),
(160, '2025-06-02', '12:30:00', 'Andrea Maria Reyes', 'RESTORED ARCHIVE', '1', NULL, 'Resident'),
(161, '2025-06-02', '12:30:04', 'Andrea Maria Reyes', 'RESTORED ARCHIVE', '3', NULL, 'Resident'),
(162, '2025-06-02', '12:30:49', 'Andrea Maria Reyes', 'PROFILE UPDATE', NULL, '4', 'User'),
(163, '2025-06-02', '12:31:32', 'Andrea Maria Reyes', 'UPDATE', NULL, '3', 'User'),
(164, '2025-06-02', '12:31:45', 'Andrea Maria Reyes', 'ARCHIVE', NULL, '1', 'User'),
(165, '2025-06-02', '12:32:01', 'Andrea Maria Reyes', 'ARCHIVE', NULL, '1', 'User'),
(166, '2025-06-02', '12:32:57', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(167, '2025-06-02', '12:33:08', 'Andrea Maria Reyes', 'ARCHIVE', NULL, '2', 'User'),
(168, '2025-06-02', '12:33:13', 'Andrea Maria Reyes', 'RESTORED ARCHIVE', NULL, '1', 'User'),
(169, '2025-06-02', '12:33:40', 'Andrea Maria Reyes', 'UPDATE', NULL, '1', 'User'),
(170, '2025-06-02', '12:33:45', 'Andrea Maria Reyes', 'UPDATE', NULL, '1', 'User'),
(171, '2025-06-02', '10:01:38', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(172, '2025-06-02', '10:22:00', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(173, '2025-06-02', '10:22:34', 'Andrea Maria Reyes', 'UPDATE', NULL, '1', 'User'),
(174, '2025-06-02', '10:22:54', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(175, '2025-06-02', '10:23:39', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(176, '2025-06-02', '10:25:26', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(177, '2025-06-02', '01:16:10', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(178, '2025-06-02', '01:24:01', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(179, '2025-06-02', '01:26:23', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(180, '2025-06-02', '01:27:45', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(181, '2025-06-02', '01:28:38', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(182, '2025-06-02', '01:30:08', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(183, '2025-06-02', '02:24:03', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(184, '2025-06-02', '02:24:08', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(185, '2025-06-02', '02:24:21', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(186, '2025-06-02', '02:25:23', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(187, '2025-06-02', '02:29:54', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(188, '2025-06-02', '02:50:28', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(189, '2025-06-02', '02:51:00', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(190, '2025-06-02', '02:51:07', 'Sheila Garatta', 'LOGIN', NULL, '3', 'Logs'),
(191, '2025-06-02', '02:52:25', 'Sheila Garatta', 'LOGOUT', NULL, '3', 'Logs'),
(192, '2025-06-02', '02:52:41', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(193, '2025-06-02', '02:54:26', 'Andrea Maria Reyes', 'REGISTER', '7', NULL, 'Resident'),
(194, '2025-06-02', '02:54:51', 'Andrea Maria Reyes', 'UPDATE', '7', NULL, 'Resident'),
(195, '2025-06-02', '03:14:36', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(196, '2025-06-02', '03:20:38', 'Andrea Maria Reyes', 'ARCHIVE', '1', NULL, 'Resident'),
(197, '2025-06-02', '03:20:47', 'Andrea Maria Reyes', 'RESTORED ARCHIVE', '1', NULL, 'Resident'),
(198, '2025-06-02', '04:09:37', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs'),
(199, '2025-06-02', '04:10:17', 'Andrea Maria Reyes', 'ARCHIVE', '2', NULL, 'Resident'),
(200, '2025-06-02', '04:12:42', 'Andrea Maria Reyes', 'LOGOUT', NULL, '4', 'Logs'),
(201, '2025-06-02', '04:43:21', 'Andrea Maria Reyes', 'LOGIN', NULL, '4', 'Logs');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `reportID` int(11) NOT NULL,
  `reportBy` varchar(50) NOT NULL,
  `reportTitle` varchar(50) NOT NULL,
  `reportDate` date NOT NULL,
  `reporTime` time NOT NULL,
  `reportPurpose` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`reportID`, `reportBy`, `reportTitle`, `reportDate`, `reporTime`, `reportPurpose`) VALUES
(1, 'Cute Me', 'Document Transaction', '2025-06-01', '01:02:27', 'monthly report'),
(2, 'Cute Me', 'Audit Between Dates', '2025-06-01', '01:03:18', 'hnfng'),
(3, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '12:34:45', 'HEHE'),
(4, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '12:34:58', 'HEHE'),
(5, 'Andrea Maria Reyes', 'Audit Between Dates', '2025-06-02', '12:35:26', 'TTHSTHS'),
(6, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '12:36:52', 'bdbd'),
(7, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '10:23:55', 'dsifdhba'),
(8, 'Andrea Maria Reyes', 'Audit Between Dates', '2025-06-02', '10:24:10', 'thdhtdt'),
(9, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '02:34:02', 'sjhdbcajbd'),
(10, 'Andrea Maria Reyes', 'Audit Between Dates', '2025-06-02', '02:34:26', 'dsfd'),
(11, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '02:58:54', 'HAHAHHAA'),
(12, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '02:59:12', 'HAHAHHAA'),
(13, 'Andrea Maria Reyes', 'Audit Between Dates', '2025-06-02', '02:59:38', 'Hahahah'),
(14, 'Andrea Maria Reyes', 'Document Transaction', '2025-06-02', '04:12:16', 'fafafaa'),
(15, 'Andrea Maria Reyes', 'Audit Between Dates', '2025-06-02', '04:12:33', 'fbdbd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `audittrail`
--
ALTER TABLE `audittrail`
  ADD PRIMARY KEY (`Audit Trail ID`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`reportID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `audittrail`
--
ALTER TABLE `audittrail`
  MODIFY `Audit Trail ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=202;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `reportID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
