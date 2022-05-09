-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2022 at 06:15 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `serviceetudiant`
--

-- --------------------------------------------------------

--
-- Table structure for table `gl_demandescolarite`
--

CREATE TABLE `gl_demandescolarite` (
  `idDemande` int(12) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `typedemande` varchar(100) DEFAULT NULL,
  `traitee` tinyint(1) DEFAULT NULL,
  `CIN` varchar(30) NOT NULL,
  `apogee` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gl_demandescolarite`
--

INSERT INTO `gl_demandescolarite` (`idDemande`, `Email`, `fullName`, `typedemande`, `traitee`, `CIN`, `apogee`, `etat`) VALUES
(12, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Convention de stage', 1, 'B78561', 17021310, 'Acceptée'),
(13, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Relevé de notes', 1, 'B78561', 17021310, 'Acceptée'),
(14, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Attestation de scolarité', 1, 'B78561', 17021310, 'Acceptée'),
(15, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Relevé de notes', 1, 'B78561', 17021310, 'Rufusée'),
(16, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Attestation de scolarité', 1, 'B78561', 17021310, 'Acceptée'),
(51, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Attestation de scolarité', 1, 'B78561', 17021310, 'Acceptée'),
(52, 'youness.chetouan@etu.uae.ac.ma', 'CHETOUAN YOUNESS', 'Relevé de notes', 0, 'BP7412', 17021281, 'En attente'),
(53, 'kaoutar.derouach@etu.uae.ac.ma', 'DEROUACH KAOUTAR', 'Relevé de notes', 1, 'BK705048', 18043980, 'Acceptée'),
(55, 'ismail.abdelouahab@etu.uae.ac.ma', 'ABDELOUAHAB ISMAIL', 'Attestation de scolarité', 0, 'B78953', 17021310, 'En attente'),
(56, 'kaoutar.derouach@etu.uae.ac.ma', 'DEROUACH KAOUTAR', 'Attestation de scolarité', 0, 'BK705048', 18043980, 'En attente');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(2) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `name`, `password`) VALUES
(1, 'admin', '1234'),
(2, 'ensate', 'ensate2021');

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `idnote` int(15) NOT NULL,
  `module` varchar(100) DEFAULT NULL,
  `valeur` double DEFAULT NULL,
  `annee` varchar(10) DEFAULT NULL,
  `semestre` int(1) DEFAULT NULL,
  `id_etudiant` int(12) DEFAULT NULL,
  `cne` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`idnote`, `module`, `valeur`, `annee`, `semestre`, `id_etudiant`, `cne`) VALUES
(1, 'Systéme d\'exploitation', 14.45, '2020/2021', 6, 13, 'R133530368'),
(2, 'théorie de langages et compilation', 16.5, '2020/2021', 6, 13, 'R133530368'),
(3, 'programmation web1', 15, '2020/2021', 6, 13, 'R133530368'),
(4, 'programmation C', 13.48, '2020/2021', 6, 13, 'R133530368'),
(5, 'Architecture d\'ordinateurs et assembleur', 15.75, '2020/2021', 6, 13, 'R133530368'),
(6, 'Electronique numérique', 17.22, '2020/2021', 6, 13, 'R133530368'),
(7, 'Proba, Stat et stoch', 16.25, '2020/2021', 5, 13, 'R133530368'),
(8, 'B.D.Relationnelles', 15.6, '2020/2021', 5, 13, 'R133530368'),
(9, 'Réseaux Informatiques1', 16.4, '2020/2021', 5, 13, 'R133530368'),
(10, 'Recherche opérationnel et théorie des graphes', 19, '2020/2021', 5, 13, 'R133530368'),
(11, 'Management1', 16, '2020/2021', 5, 13, 'R133530368'),
(12, ' Outils Ling. et Tech', 15.85, '2020/2021', 5, 13, 'R133530368'),
(13, ' Proba, Stat et stoch\r\n', 12, '2020/2021', 5, 2, 'R133530368'),
(14, 'B.D.Relationnelles', 10.75, '2020/2021', 5, 2, 'R133530368'),
(15, 'Réseaux Informatiques1', 13.4, '2020/2021', 5, 2, 'R133530368'),
(16, 'Recherche Opérationnel et Théorie de Graphes', 14, '2020/2021', 5, 2, 'R133530368'),
(17, 'Management1', 12.4, '2020/2021', 5, 2, 'R133530368'),
(18, 'Outils Ling. et Tech.', 15.3, '2020/2021', 5, 2, 'R133530368'),
(19, 'Programmation C', 9.15, '2020/2021', 6, 2, 'R133530368'),
(20, 'Electronique Numérique', 15.83, '2020/2021', 6, 2, 'R133530368'),
(21, 'Th. des Langages et compilation', 13.425, '2020/2021', 6, 2, 'R133530368'),
(22, 'Programmation Web1', 12, '2020/2021', 6, 2, 'R133530368'),
(23, 'Systèmes d\'exploitation\r\n', 12, '2020/2021', 6, 2, 'R133530368'),
(24, 'Arch. des Ordinateurs et Assembleur\r\n', 16.5, '2020/2021', 6, 2, 'R133530368'),
(25, 'po', 45, '2020/2021', 4, 4, 'hklio');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `idstudent` int(12) NOT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `apogee` int(11) DEFAULT NULL,
  `cin` varchar(100) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `niveau` varchar(255) NOT NULL,
  `date_naiss` varchar(50) NOT NULL,
  `lieu` varchar(50) NOT NULL,
  `filiere` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`idstudent`, `fullName`, `apogee`, `cin`, `Email`, `niveau`, `date_naiss`, `lieu`, `filiere`) VALUES
(1, 'BENCHAJI MEHDI', 18046363, 'Bk704025', 'mehdi.benchaji@etu.uae.ac.ma', 'GI2', '', '', ''),
(2, 'ABDELOUAHAB ISMAIL', 17021310, 'B78953', 'ismail.abdelouahab@etu.uae.ac.ma', 'GI2', '', '', ''),
(3, 'AHIDAR RACHID', 18043585, 'B78561', 'rachid.ahidar@etu.uae.ac.ma', 'GI2', '', '', ''),
(4, 'AIT RHOUDANE RACHID', 18044724, 'B85621', 'rachid.aitrhoudane@etu.uae.ac.ma', 'GI2', '', '', ''),
(5, 'BELABBES WAIL', 17021324, 'B78963', 'wail.belabbes@etu.uae.ac.ma', 'GI2', '', '', ''),
(6, 'BENCHELH RANIA', 20030761, 'BK45631', 'rania.benchelh@etu.uae.ac.ma', 'GI2', '', '', ''),
(7, 'AKIL ALI', 18036089, 'B7856', 'ali.akil@etu.uae.ac.ma', 'GI2', '', '', ''),
(8, 'BICHARA ISMAIL', 18043949, 'BK8563', 'ismail.bichara@etu.uae.ac.ma \r\n', 'GI2', '', '', ''),
(9, 'BOUALIL NIZAR', 18043953, 'BK8561', 'nizar.boualil@etu.uae.ac.ma', 'GI2', '', '', ''),
(10, 'CHAHID FATIMA', 18030274, 'BM7896', 'fatima.chahid@etu.uae.ac.ma', 'GI2', '', '', ''),
(11, 'CHARCHAOUI AYOUB', 18043966, 'B7417', 'ayoub.charchaoui@etu.uae.ac.ma', 'GI2', '', '', ''),
(12, 'CHETOUAN YOUNESS', 17021281, 'BP7412', 'youness.chetouan@etu.uae.ac.ma', 'GI2', '', '', ''),
(13, 'DEROUACH KAOUTAR', 18043980, 'BK705048', 'kaoutar.derouach@etu.uae.ac.ma', '2éme Année du Cycle Ingénieur ', '15/12/2000', 'Casablanca', 'Génie Informatique'),
(14, 'DOUAH MOUAD', 18043974, 'BK9632', 'mouad.douah@etu.uae.ac.ma', 'GI2', '', '', ''),
(15, 'EL ALAOUI MARIAME', 17021405, 'BP8542', 'mariame.elalaoui@etu.uae.ac.ma', 'GI2', '', '', ''),
(16, 'EL FARROUH ANASS', 18044001, 'BK7412', 'anass.elfarrouh@etu.uae.ac.ma', 'GI2', '', '', ''),
(18, 'EL GHIBARI HAMZA', 18043999, 'BM8563', 'hamza.elghibari@etu.uae.ac.ma', 'GI2', '', '', ''),
(19, 'EL GHZIRI YASSIN', 18044050, 'B7463', 'yassin.elghziri@etu.uae.ac.ma', 'GI2', '', '', ''),
(20, 'EL HALLA AHMED', 18044049, 'B9632', 'ahmed.elhalla@etu.uae.ac.ma', 'GI2', '', '', ''),
(21, 'EL JAMAOUI FATIMA-ZOHRA', 17021543, 'B78963', 'fatimazohra.eljamoui@etu.uae.ac.ma', 'GI2', '', '', ''),
(22, 'EL MAHI SAFAE', 17021395, 'BN7896', 'safae.elmahi@etu.uae.ac.ma', 'GI2', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `gl_demandescolarite`
--
ALTER TABLE `gl_demandescolarite`
  ADD PRIMARY KEY (`idDemande`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`idnote`),
  ADD KEY `fk_id_etudiant` (`id_etudiant`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`idstudent`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `gl_demandescolarite`
--
ALTER TABLE `gl_demandescolarite`
  MODIFY `idDemande` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `idnote` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `idstudent` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `fk_id_etudiant` FOREIGN KEY (`id_etudiant`) REFERENCES `student` (`idstudent`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
