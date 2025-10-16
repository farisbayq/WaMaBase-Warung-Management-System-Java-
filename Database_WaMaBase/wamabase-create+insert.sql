-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2024 at 09:32 AM
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
-- Database: `wamabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `ItemID` char(5) NOT NULL,
  `StockQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`ItemID`, `StockQuantity`) VALUES
('IT001', 50),
('IT002', 94),
('IT003', 100),
('IT004', 75),
('IT005', 60),
('IT006', 40),
('IT007', 19),
('IT008', 90),
('IT009', 35),
('IT010', 25),
('IT011', 55),
('IT012', 70),
('IT013', 45),
('IT014', 60),
('IT015', 35),
('IT016', 80),
('IT017', 50);

-- --------------------------------------------------------

--
-- Table structure for table `mscustomer`
--

CREATE TABLE `mscustomer` (
  `CustomerID` char(5) NOT NULL,
  `CustomerName` varchar(50) NOT NULL,
  `AccountNumber` varchar(30) DEFAULT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mscustomer`
--

INSERT INTO `mscustomer` (`CustomerID`, `CustomerName`, `AccountNumber`, `PhoneNumber`) VALUES
('CU001', 'Furqon', '1234567890', '0899999999'),
('CU002', 'Danu Inten', '0987654321', '0888888888'),
('CU003', 'Budi Santoso', '1230984567', '08123456789'),
('CU004', 'Ani Wijaya', '9876543210', '08234567890'),
('CU005', 'Siti Aminah', '4567891230', '08345678901'),
('CU006', 'Rudi Hartono', '3210987654', '08456789012'),
('CU007', 'Dewi Lestari', '2109876543', '08567890123'),
('CU008', 'Joko Prasetyo', '6789012345', '08678901234'),
('CU009', 'Lina Rahmawati', '8901234567', '08789012345'),
('CU010', 'Hendra Setiawan', '7654321098', '08890123456'),
('CU011', 'Rina Wulandari', '3456789012', '08901234567'),
('CU012', 'Agus Priyanto', '9012345678', '08134567890'),
('CU013', 'Nurul Hidayah', '5678901234', '08245678901'),
('CU014', 'Eko Suyatno', '2345678901', '08356789012'),
('CU015', 'Fitri Handayani', '6789012345', '08467890123'),
('CU016', 'Rahmat Hidayat', '4567890123', '08578901234'),
('CU017', 'Sri Mulyani', '8901234567', '08689012345'),
('CU018', 'Andi Susanto', '1234567890', '08790123456'),
('CU019', 'Mega Putri', '6789012345', '081285311943'),
('CU020', 'Yudi Prasetya', '6801718849', '08912345678'),
('CU021', 'Farhan Maulana', '5678901234', '08123456780'),
('CU022', 'Wulan Sari', '7890123456', '08234567891'),
('CU023', 'Samsul Arifin', '8901234567', '08345678902'),
('CU024', 'Maya Kristina', '1234567890', '08456789013'),
('CU025', 'asd', '123', '123');

-- --------------------------------------------------------

--
-- Table structure for table `msitem`
--

CREATE TABLE `msitem` (
  `ItemID` char(5) NOT NULL,
  `ItemName` varchar(50) NOT NULL,
  `ItemPrice` int(11) NOT NULL,
  `ItemNetWeight` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msitem`
--

INSERT INTO `msitem` (`ItemID`, `ItemName`, `ItemPrice`, `ItemNetWeight`) VALUES
('IT001', 'Mini Oneo Original', 10000, 500),
('IT002', 'Danu Milk Segar', 20000, 1000),
('IT003', 'Susu Segar Indomoo', 15000, 1000),
('IT004', 'Roti Manis Rotina', 12000, 500),
('IT005', 'Telur Ayam Telung', 20000, 600),
('IT006', 'Beras Wangi PadiKu', 50000, 5000),
('IT007', 'Gula Pasir Manisku', 25000, 2000),
('IT008', 'Apel Merah Frufresh', 18000, 1500),
('IT009', 'Daging Ayam Lezato', 70000, 3000),
('IT010', 'Jus Jeruk Sunjus', 30000, 1200),
('IT011', 'Keju Lumer Cheesina', 45000, 900),
('IT012', 'Kentang Goreng Krusti', 20000, 2500),
('IT013', 'Beras Indomas', 50000, 5000),
('IT014', 'Gula RasaManis', 15000, 1000),
('IT015', 'Minyak GorengSari', 30000, 2000),
('IT016', 'Teh Cap Poci', 12000, 500),
('IT017', 'Kopi BubukJaya', 20000, 250);

-- --------------------------------------------------------

--
-- Table structure for table `msstaff`
--

CREATE TABLE `msstaff` (
  `StaffID` char(5) NOT NULL,
  `StaffName` varchar(50) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `StaffAddress` varchar(50) DEFAULT NULL,
  `StaffEmail` varchar(50) NOT NULL,
  `StaffPassword` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `msstaff`
--

INSERT INTO `msstaff` (`StaffID`, `StaffName`, `PhoneNumber`, `StaffAddress`, `StaffEmail`, `StaffPassword`) VALUES
('ST001', 'Muhammad Ridho', '081234567890', 'Jl. Merdeka No. 45, Jakarta', 'ridho@email.com', 'password123'),
('ST002', 'Haris Khoiryah', '085678912345', 'Jl. Sudirman No. 23, Bandung', 'haris@email.com', 'password456'),
('ST003', 'Arief Pratama', '081234567890', 'Jalan Melati No. 12, Jakarta', 'arief@email.com', 'arief123'),
('ST004', 'Lina Sari', '082345678901', 'Jalan Anggrek No. 15, Bandung', 'lina@email.com', 'lina456'),
('ST005', 'Yusuf Prakoso', '083456789012', 'Jalan Cemara No. 21, Surabaya', 'yusuf@email.com', 'yusuf789'),
('ST006', 'Rina Kusuma', '084567890123', 'Jalan Kenanga No. 30, Yogyakarta', 'rina@email.com', 'rina123'),
('ST007', 'Hadi Wibowo', '085678901234', 'Jalan Flamboyan No. 8, Medan', 'hadi@email.com', 'hadi456'),
('ST008', 'Sari Dewanti', '086789012345', 'Jalan Mawar No. 18, Makassar', 'sari@email.com', 'sari789'),
('ST009', 'Rizky Saputra', '087890123456', 'Jalan Tulip No. 10, Semarang', 'rizky@email.com', 'rizky123'),
('ST010', 'Indah Permata', '088901234567', 'Jalan Dahlia No. 5, Palembang', 'indah@email.com', 'indah456');

-- --------------------------------------------------------

--
-- Table structure for table `mssupplier`
--

CREATE TABLE `mssupplier` (
  `SupplierID` char(5) NOT NULL,
  `SupplierName` varchar(50) NOT NULL,
  `SupplierEmail` varchar(50) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `SupplierAddress` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mssupplier`
--

INSERT INTO `mssupplier` (`SupplierID`, `SupplierName`, `SupplierEmail`, `PhoneNumber`, `SupplierAddress`) VALUES
('SU001', 'Supplier A', 'supA@email.com', '0811111111', 'Alamat A'),
('SU002', 'Supplier B', 'supB@email.com', '0811222222', 'Alamat B');

-- --------------------------------------------------------

--
-- Table structure for table `paymentmethod`
--

CREATE TABLE `paymentmethod` (
  `PayMethodID` char(5) NOT NULL,
  `PayMethodName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paymentmethod`
--

INSERT INTO `paymentmethod` (`PayMethodID`, `PayMethodName`) VALUES
('PM001', 'Cash'),
('PM002', 'Credit Card'),
('PM003', 'QRIS');

-- --------------------------------------------------------

--
-- Table structure for table `transactiondetail`
--

CREATE TABLE `transactiondetail` (
  `TransactionID` char(5) NOT NULL,
  `ItemID` char(5) DEFAULT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactiondetail`
--

INSERT INTO `transactiondetail` (`TransactionID`, `ItemID`, `Quantity`) VALUES
('TR001', 'IT001', 2),
('TR002', 'IT002', 3),
('TR003', 'IT001', 2),
('TR004', 'IT002', 4),
('TR005', 'IT003', 1),
('TR006', 'IT004', 3),
('TR007', 'IT005', 5),
('TR008', 'IT006', 2),
('TR009', 'IT007', 6),
('TR010', 'IT008', 3),
('TR011', 'IT009', 4),
('TR012', 'IT010', 2),
('TR013', 'IT011', 5),
('TR014', 'IT012', 3),
('TR015', 'IT013', 1),
('TR016', 'IT014', 2),
('TR017', 'IT015', 4),
('TR018', 'IT016', 3),
('TR019', 'IT017', 6),
('TR020', 'IT001', 2),
('TR021', 'IT002', 5),
('TR022', 'IT003', 3),
('TR023', 'IT002', 2),
('TR023', 'IT005', 3),
('TR023', 'IT012', 1),
('TR024', 'IT007', 4),
('TR024', 'IT015', 2),
('TR025', 'IT001', 3),
('TR025', 'IT006', 2),
('TR025', 'IT010', 4),
('TR025', 'IT017', 1),
('TR026', 'IT003', 5),
('TR026', 'IT009', 2),
('TR026', 'IT014', 3),
('TR027', 'IT008', 6),
('TR027', 'IT013', 2),
('TR028', 'IT004', 2),
('TR028', 'IT011', 3),
('TR028', 'IT016', 4),
('TR028', 'IT017', 2),
('TR029', 'IT002', 3),
('TR029', 'IT007', 2),
('TR030', 'IT005', 4),
('TR030', 'IT008', 5),
('TR030', 'IT012', 3),
('TR031', 'IT006', 2),
('TR031', 'IT009', 1),
('TR031', 'IT010', 3),
('TR031', 'IT013', 2),
('TR032', 'IT004', 4),
('TR032', 'IT015', 5),
('TR033', 'IT003', 6),
('TR033', 'IT011', 2),
('TR033', 'IT016', 1),
('TR034', 'IT002', 3),
('TR034', 'IT014', 4),
('TR034', 'IT017', 2),
('TR035', 'IT005', 2),
('TR035', 'IT008', 3),
('TR036', 'IT001', 5),
('TR036', 'IT006', 4),
('TR036', 'IT012', 2),
('TR036', 'IT013', 3),
('TR037', 'IT004', 6),
('TR037', 'IT009', 3),
('TR038', 'IT007', 4),
('TR038', 'IT010', 2),
('TR038', 'IT015', 3),
('TR038', 'IT017', 1),
('TR039', 'IT003', 2),
('TR039', 'IT008', 5),
('TR039', 'IT014', 4),
('TR040', 'IT002', 3),
('TR040', 'IT005', 2),
('TR040', 'IT009', 1),
('TR041', 'IT004', 6),
('TR041', 'IT011', 4),
('TR041', 'IT016', 2),
('TR042', 'IT001', 3),
('TR042', 'IT006', 5),
('TR042', 'IT013', 4),
('TR042', 'IT017', 2),
('TR043', 'IT002', 6),
('TR043', 'IT007', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactionheader`
--

CREATE TABLE `transactionheader` (
  `TransactionID` char(5) NOT NULL,
  `StaffID` char(5) DEFAULT NULL,
  `PayMethodID` char(5) DEFAULT NULL,
  `CustomerID` char(5) DEFAULT NULL,
  `TransactionDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionheader`
--

INSERT INTO `transactionheader` (`TransactionID`, `StaffID`, `PayMethodID`, `CustomerID`, `TransactionDate`) VALUES
('TR001', 'ST001', 'PM001', 'CU001', '2024-12-15'),
('TR002', 'ST002', 'PM002', 'CU002', '2024-12-16'),
('TR003', 'ST003', 'PM001', 'CU001', '2022-01-15'),
('TR004', 'ST004', 'PM002', 'CU002', '2022-02-20'),
('TR005', 'ST005', 'PM003', 'CU003', '2022-03-18'),
('TR006', 'ST006', 'PM001', 'CU004', '2022-05-25'),
('TR007', 'ST007', 'PM002', 'CU005', '2022-06-30'),
('TR008', 'ST008', 'PM003', 'CU006', '2022-07-12'),
('TR009', 'ST009', 'PM001', 'CU007', '2022-09-15'),
('TR010', 'ST010', 'PM002', 'CU008', '2022-10-22'),
('TR011', 'ST003', 'PM003', 'CU009', '2023-01-10'),
('TR012', 'ST004', 'PM001', 'CU010', '2023-03-19'),
('TR013', 'ST005', 'PM002', 'CU011', '2023-04-25'),
('TR014', 'ST006', 'PM003', 'CU012', '2023-06-17'),
('TR015', 'ST007', 'PM001', 'CU013', '2023-07-22'),
('TR016', 'ST008', 'PM002', 'CU014', '2023-08-10'),
('TR017', 'ST009', 'PM003', 'CU015', '2023-09-05'),
('TR018', 'ST010', 'PM001', 'CU016', '2023-11-12'),
('TR019', 'ST003', 'PM002', 'CU017', '2024-01-09'),
('TR020', 'ST004', 'PM003', 'CU018', '2024-02-15'),
('TR021', 'ST005', 'PM001', 'CU019', '2024-03-22'),
('TR022', 'ST006', 'PM002', 'CU020', '2024-04-15'),
('TR023', 'ST003', 'PM001', 'CU015', '2022-03-14'),
('TR024', 'ST007', 'PM002', 'CU005', '2023-06-18'),
('TR025', 'ST002', 'PM003', 'CU021', '2022-11-22'),
('TR026', 'ST010', 'PM001', 'CU008', '2024-01-10'),
('TR027', 'ST005', 'PM003', 'CU013', '2023-02-27'),
('TR028', 'ST001', 'PM002', 'CU018', '2024-03-08'),
('TR029', 'ST009', 'PM001', 'CU004', '2022-07-19'),
('TR030', 'ST004', 'PM003', 'CU022', '2023-09-11'),
('TR031', 'ST006', 'PM002', 'CU010', '2024-05-05'),
('TR032', 'ST008', 'PM001', 'CU020', '2023-12-15'),
('TR033', 'ST001', 'PM003', 'CU002', '2022-04-23'),
('TR034', 'ST007', 'PM002', 'CU017', '2023-01-19'),
('TR035', 'ST003', 'PM001', 'CU006', '2024-08-28'),
('TR036', 'ST010', 'PM002', 'CU009', '2022-12-07'),
('TR037', 'ST002', 'PM003', 'CU024', '2023-06-15'),
('TR038', 'ST005', 'PM001', 'CU012', '2024-03-19'),
('TR039', 'ST008', 'PM002', 'CU014', '2023-09-01'),
('TR040', 'ST004', 'PM003', 'CU001', '2024-04-12'),
('TR041', 'ST006', 'PM001', 'CU019', '2023-11-21'),
('TR042', 'ST009', 'PM002', 'CU003', '2022-05-09'),
('TR043', 'ST001', 'PM001', 'CU025', '2024-12-16');

-- --------------------------------------------------------

--
-- Table structure for table `transactionsupplydetail`
--

CREATE TABLE `transactionsupplydetail` (
  `SupplyTransactionID` char(5) NOT NULL,
  `ItemID` char(5) DEFAULT NULL,
  `SupplyQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionsupplydetail`
--

INSERT INTO `transactionsupplydetail` (`SupplyTransactionID`, `ItemID`, `SupplyQuantity`) VALUES
('TS001', 'IT001', 10),
('TS002', 'IT002', 20);

-- --------------------------------------------------------

--
-- Table structure for table `transactionsupplyheader`
--

CREATE TABLE `transactionsupplyheader` (
  `SupplyTransactionID` char(5) NOT NULL,
  `SupplierID` char(5) DEFAULT NULL,
  `ProcurementDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactionsupplyheader`
--

INSERT INTO `transactionsupplyheader` (`SupplyTransactionID`, `SupplierID`, `ProcurementDate`) VALUES
('TS001', 'SU001', '2024-12-10'),
('TS002', 'SU002', '2024-12-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `mscustomer`
--
ALTER TABLE `mscustomer`
  ADD PRIMARY KEY (`CustomerID`);

--
-- Indexes for table `msitem`
--
ALTER TABLE `msitem`
  ADD PRIMARY KEY (`ItemID`);

--
-- Indexes for table `msstaff`
--
ALTER TABLE `msstaff`
  ADD PRIMARY KEY (`StaffID`);

--
-- Indexes for table `mssupplier`
--
ALTER TABLE `mssupplier`
  ADD PRIMARY KEY (`SupplierID`);

--
-- Indexes for table `paymentmethod`
--
ALTER TABLE `paymentmethod`
  ADD PRIMARY KEY (`PayMethodID`);

--
-- Indexes for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD KEY `ItemID` (`ItemID`),
  ADD KEY `TransactionID` (`TransactionID`);

--
-- Indexes for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `StaffID` (`StaffID`),
  ADD KEY `PayMethodID` (`PayMethodID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `transactionsupplydetail`
--
ALTER TABLE `transactionsupplydetail`
  ADD PRIMARY KEY (`SupplyTransactionID`),
  ADD KEY `ItemID` (`ItemID`);

--
-- Indexes for table `transactionsupplyheader`
--
ALTER TABLE `transactionsupplyheader`
  ADD PRIMARY KEY (`SupplyTransactionID`),
  ADD KEY `SupplierID` (`SupplierID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`ItemID`) REFERENCES `msitem` (`ItemID`);

--
-- Constraints for table `transactiondetail`
--
ALTER TABLE `transactiondetail`
  ADD CONSTRAINT `transactiondetail_ibfk_1` FOREIGN KEY (`TransactionID`) REFERENCES `transactionheader` (`TransactionID`),
  ADD CONSTRAINT `transactiondetail_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `msitem` (`ItemID`);

--
-- Constraints for table `transactionheader`
--
ALTER TABLE `transactionheader`
  ADD CONSTRAINT `transactionheader_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `msstaff` (`StaffID`),
  ADD CONSTRAINT `transactionheader_ibfk_2` FOREIGN KEY (`PayMethodID`) REFERENCES `paymentmethod` (`PayMethodID`),
  ADD CONSTRAINT `transactionheader_ibfk_3` FOREIGN KEY (`CustomerID`) REFERENCES `mscustomer` (`CustomerID`);

--
-- Constraints for table `transactionsupplydetail`
--
ALTER TABLE `transactionsupplydetail`
  ADD CONSTRAINT `transactionsupplydetail_ibfk_1` FOREIGN KEY (`SupplyTransactionID`) REFERENCES `transactionsupplyheader` (`SupplyTransactionID`),
  ADD CONSTRAINT `transactionsupplydetail_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `msitem` (`ItemID`);

--
-- Constraints for table `transactionsupplyheader`
--
ALTER TABLE `transactionsupplyheader`
  ADD CONSTRAINT `transactionsupplyheader_ibfk_1` FOREIGN KEY (`SupplierID`) REFERENCES `mssupplier` (`SupplierID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
