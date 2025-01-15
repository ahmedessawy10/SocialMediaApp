-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 15, 2025 at 11:36 PM
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
-- Database: `socail media project`
--

-- --------------------------------------------------------

--
-- Table structure for table `friend_request`
--

CREATE TABLE `friend_request` (
  `UserID` int(11) NOT NULL,
  `FriendID` int(11) NOT NULL,
  `Acceptance` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `postcomments`
--

CREATE TABLE `postcomments` (
  `UserID` int(11) NOT NULL,
  `PostID` int(11) NOT NULL,
  `Comment` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `postlikes`
--

CREATE TABLE `postlikes` (
  `UserID` int(11) NOT NULL,
  `PostID` int(11) NOT NULL,
  `Love` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `PostID` int(11) NOT NULL,
  `PostText` text DEFAULT NULL,
  `PostImage` mediumblob DEFAULT NULL,
  `PostPublishID` int(11) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `ProfilePicture` mediumblob DEFAULT NULL,
  `Bio` text DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `City` varchar(50) NOT NULL,
  `Street` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `UserName`, `Email`, `Password`, `ProfilePicture`, `Bio`, `Country`, `City`, `Street`) VALUES
(3, 'OsamaHelal2024', 'OsamaHelal2024@gmail.com', '1234', NULL, NULL, NULL, '', ''),
(4, 'moohamed', 'ana@gmail.com', 'hassan', NULL, NULL, 'egypt', 'Cairo', 'hat'),
(5, 'helal', 'ana2@gmail.com', 'galkjgdlkjg', NULL, NULL, 'fdlkjsfjkdf', 'sdfkljdf', 'dsfkljkjdlf'),
(6, 'sdfkljlkjf', 'osamaHelal2023@gmail.com', 'fjfjf', NULL, NULL, 'dfnbdfnmdbf', 'fdklsjfdjkldf', 'ddfjkdhkhdf');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friend_request`
--
ALTER TABLE `friend_request`
  ADD UNIQUE KEY `UserID` (`UserID`,`FriendID`),
  ADD KEY `FriendID` (`FriendID`);

--
-- Indexes for table `postcomments`
--
ALTER TABLE `postcomments`
  ADD KEY `UserID` (`UserID`),
  ADD KEY `PostID` (`PostID`);

--
-- Indexes for table `postlikes`
--
ALTER TABLE `postlikes`
  ADD UNIQUE KEY `UserID` (`UserID`,`PostID`),
  ADD KEY `PostID` (`PostID`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`PostID`),
  ADD KEY `PostPublishID` (`PostPublishID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Password` (`Password`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `PostID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `friend_request`
--
ALTER TABLE `friend_request`
  ADD CONSTRAINT `friend_request_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `friend_request_ibfk_2` FOREIGN KEY (`FriendID`) REFERENCES `user` (`ID`);

--
-- Constraints for table `postcomments`
--
ALTER TABLE `postcomments`
  ADD CONSTRAINT `postcomments_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `postcomments_ibfk_2` FOREIGN KEY (`PostID`) REFERENCES `posts` (`PostID`);

--
-- Constraints for table `postlikes`
--
ALTER TABLE `postlikes`
  ADD CONSTRAINT `postlikes_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `postlikes_ibfk_2` FOREIGN KEY (`PostID`) REFERENCES `posts` (`PostID`);

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`PostPublishID`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`PostPublishID`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
