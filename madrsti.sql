-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2019 at 03:30 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `madrsti`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `grade_id` int(11) NOT NULL,
  `class_id` varchar(32) NOT NULL,
  `capacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`grade_id`, `class_id`, `capacity`) VALUES
(1, 'A', 30),
(1, 'B', 40),
(1, 'C', 30),
(1, 'D', 10),
(2, 'A', 50),
(2, 'B', 40),
(2, 'C', 50),
(2, 'D', 40),
(3, 'A', 50),
(4, 'A', 100),
(5, 'A', 50),
(6, 'A', 40);

-- --------------------------------------------------------

--
-- Table structure for table `grades`
--

CREATE TABLE `grades` (
  `grade_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grades`
--

INSERT INTO `grades` (`grade_id`, `name`) VALUES
(1, '1st Primary'),
(2, '2st Primary'),
(3, '3st Primary'),
(4, '4st Primary'),
(5, '5st Primary'),
(6, '6st Primary');

-- --------------------------------------------------------

--
-- Table structure for table `results`
--

CREATE TABLE `results` (
  `student_id` int(10) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `year` varchar(4) NOT NULL,
  `first_midterm_exam` double NOT NULL,
  `first_final_exam` double NOT NULL,
  `second_midterm_exam` double NOT NULL,
  `second_final_exam` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `results`
--

INSERT INTO `results` (`student_id`, `subject_id`, `year`, `first_midterm_exam`, `first_final_exam`, `second_midterm_exam`, `second_final_exam`) VALUES
(444444444, 1, '2019', 25, 25, 50, 70);

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `subject_id` int(11) NOT NULL,
  `staff_id` bigint(20) UNSIGNED NOT NULL,
  `grade_id` int(11) NOT NULL,
  `class_id` varchar(32) NOT NULL,
  `day` int(1) NOT NULL,
  `time` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `schedules`
--

INSERT INTO `schedules` (`subject_id`, `staff_id`, `grade_id`, `class_id`, `day`, `time`) VALUES
(6, 1111111111, 1, 'A', 2, 3),
(10, 1111111111, 1, 'A', 5, 3),
(13, 1111111111, 1, 'A', 4, 3),
(15, 1111111111, 1, 'A', 2, 1),
(16, 1111111111, 1, 'A', 1, 2),
(18, 1111111111, 1, 'A', 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` bigint(20) UNSIGNED NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `type` varchar(8) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(160) NOT NULL,
  `job_description` varchar(32) NOT NULL,
  `nationality` varchar(100) NOT NULL,
  `phone_number` int(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `major` varchar(100) NOT NULL,
  `degree` varchar(32) NOT NULL,
  `education` varchar(64) NOT NULL,
  `graduate_year` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `full_name`, `type`, `gender`, `birthday`, `address`, `job_description`, `nationality`, `phone_number`, `email`, `major`, `degree`, `education`, `graduate_year`) VALUES
(1111111111, 'Ali Jamal Aldien Ben Mussa', 'Employee', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Bachelor', 'Tripoli University', '2020'),
(2222222222, 'Yousef Abdelkarim Breka', 'Employee', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Libyan', 'Benghazi University', '2020'),
(3333333333, 'Anonymous', 'Teacher', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Bachelor', 'Tripoli University', '2020'),
(4444444444, 'Hamza Bashir', 'Teacher', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Bachelor', 'Tripoli University', '2020'),
(5555555555, 'Mohammed Alosta', 'Teacher', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Bachelor', 'Tripoli University', '2020'),
(6666666666, 'Anwer Elhaj', 'Teacher', 'Male', '1999-09-16', 'Airport road, Tripoli, Libya', 'Admistrator', 'Libyan', 915023739, 'alibenmussa@gmail.com', 'Software Engineering', 'Bachelor', 'Tripoli University', '2020');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` int(10) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `state` varchar(32) NOT NULL,
  `gender` varchar(32) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(200) NOT NULL,
  `nationality` varchar(100) NOT NULL,
  `relative_name` varchar(100) NOT NULL,
  `relation` varchar(100) NOT NULL,
  `phone_number` int(10) NOT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `class_id` varchar(32) DEFAULT NULL,
  `health_status` varchar(100) NOT NULL,
  `notes` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `full_name`, `state`, `gender`, `birthday`, `address`, `nationality`, `relative_name`, `relation`, `phone_number`, `grade_id`, `class_id`, `health_status`, `notes`) VALUES
(1234, 'Yousef breaka', 'Mustajed', 'Male', '1999-09-16', 'Tripoli, Libya', 'Libyan', 'Jamal Ben Mussa', 'Father', 926360268, 1, 'B', 'True', 'OK'),
(444444444, 'Ali Jamal Aldien Ben Mussa', 'Mustajed', 'Male', '1999-09-16', 'Tripoli, Libya', 'Libyan', 'Jamal Ben Mussa', 'Father', 926360268, 1, 'B', 'True', 'OK');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `subject_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `grade_id` int(11) NOT NULL,
  `full_mark` double NOT NULL,
  `passing_mark` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`subject_id`, `name`, `grade_id`, `full_mark`, `passing_mark`) VALUES
(6, 'Paint', 1, 200, 120),
(10, 'Arabic Language', 2, 220, 100),
(13, 'Science', 1, 200, 100),
(15, 'Islam', 1, 180, 60),
(16, 'English Language', 5, 200, 100),
(18, 'Arabic Language', 2, 200, 100);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  `permission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `permission`) VALUES
(1111111111, '1', '1', 1),
(2222222222, '2', '2', 2),
(3333333333, '3', '3', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`grade_id`,`class_id`);

--
-- Indexes for table `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`grade_id`) USING BTREE;

--
-- Indexes for table `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`student_id`,`subject_id`,`year`),
  ADD KEY `subject_id` (`subject_id`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`subject_id`,`staff_id`,`grade_id`,`class_id`),
  ADD KEY `grade_id` (`grade_id`,`class_id`),
  ADD KEY `staff_id` (`staff_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `students_ibfk_1` (`grade_id`,`class_id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `grade_id` (`grade_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `results`
--
ALTER TABLE `results`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`grade_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `schedules_ibfk_3` FOREIGN KEY (`grade_id`,`class_id`) REFERENCES `classes` (`grade_id`, `class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_4` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedules_ibfk_5` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`grade_id`,`class_id`) REFERENCES `classes` (`grade_id`, `class_id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
