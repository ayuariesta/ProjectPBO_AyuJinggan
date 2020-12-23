-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 23, 2020 at 11:26 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbsepeda`
--

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `idkaryawan` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jeniskelamin` enum('P','L') NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `telepon` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`idkaryawan`, `nama`, `jeniskelamin`, `alamat`, `telepon`) VALUES
(1, 'Dinda Jinggan', 'P', 'Jalan Kenanga no.110', '081798289'),
(2, 'Ayu Ariesta', 'P', 'Jalan Benjosari gang 2 no.89', '08657487509'),
(4, 'salsabila', 'P', 'Jl. Soekarno No.1', '081234567890');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `idkategori` int(11) NOT NULL,
  `jenis` varchar(255) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idkategori`, `jenis`, `keterangan`) VALUES
(11, 'BMX', 'digunakan untuk balapan dengan mengkombinasikan atraksi-atraksi ekstrem yang seru sekaligus menegangkan'),
(12, 'Gunung', 'digunakan di area pegunungan dengan medan yang cukup berat'),
(13, 'Lipat', 'digunakan untuk rute pendek di jalan beraspal'),
(14, 'Balap', 'digunakan untuk race atau balapan'),
(15, 'Hybrid', 'digunakan di jalan raya');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('ayuariesta', '123'),
('dindajinggan', '456');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `idpeminjaman` int(11) NOT NULL,
  `idsepeda` int(11) NOT NULL,
  `idku` int(11) NOT NULL,
  `idkaryawan` int(11) NOT NULL,
  `waktupeminjaman` date NOT NULL,
  `waktupengembalian` date NOT NULL,
  `jumlahsepeda` int(11) NOT NULL,
  `hari` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`idpeminjaman`, `idsepeda`, `idku`, `idkaryawan`, `waktupeminjaman`, `waktupengembalian`, `jumlahsepeda`, `hari`, `harga`, `total`) VALUES
(14, 1, 15, 3, '2020-10-20', '2020-10-22', 2, 2, 30000, 120000),
(15, 3, 16, 3, '2020-11-10', '2020-11-11', 1, 1, 30000, 30000),
(16, 3, 15, 3, '2020-12-01', '2020-12-02', 3, 1, 30000, 90000),
(17, 2, 16, 1, '2020-12-15', '2020-12-17', 3, 2, 15000, 90000);

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `idpengembalian` int(11) NOT NULL,
  `idpeminjaman` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengembalian`
--

INSERT INTO `pengembalian` (`idpengembalian`, `idpeminjaman`, `status`) VALUES
(15, 12, 'Sudah');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `idpengguna` int(11) NOT NULL,
  `jenis` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`idpengguna`, `jenis`) VALUES
(6, 'Biasa'),
(7, 'Spesial');

-- --------------------------------------------------------

--
-- Table structure for table `penggunaku`
--

CREATE TABLE `penggunaku` (
  `idku` int(11) NOT NULL,
  `idpengguna` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `telepon` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penggunaku`
--

INSERT INTO `penggunaku` (`idku`, `idpengguna`, `nama`, `alamat`, `telepon`) VALUES
(15, 6, 'Dinda Jinggan', 'Jalan Alas Putih no.11', '087756869770'),
(16, 7, 'Ayu Ariesta', 'Jalan Andong no.2', '085655789678');

-- --------------------------------------------------------

--
-- Table structure for table `sepeda`
--

CREATE TABLE `sepeda` (
  `idsepeda` int(11) NOT NULL,
  `idkategori` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sepeda`
--

INSERT INTO `sepeda` (`idsepeda`, `idkategori`, `nama`, `keterangan`) VALUES
(1, 11, 'Fury Rotor', 'Merah'),
(2, 11, 'Hotshot XT 3.0 Rotor 16', 'Biru'),
(3, 11, 'Jumper', 'Kuning'),
(4, 12, 'MTB Cross Country', 'Hijau'),
(5, 12, 'MTB Trail', 'Hitam'),
(6, 12, 'MTB All Mountain', 'Hitam'),
(7, 13, 'Police Texas', 'Merah'),
(8, 13, 'Evergreen', 'Biru'),
(9, 13, 'Pacific 2980 RX', 'Pink'),
(10, 14, 'Element Police 911', 'Hitam'),
(11, 14, 'Element Toronto 700c', 'Hijau'),
(12, 14, 'Element FRC 85', 'Kuning'),
(13, 15, 'United SLick 71', 'Biru'),
(14, 15, 'Pacific TRX 2707', 'Putih'),
(15, 15, 'Polygon Heist 5', 'Pink');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idtransaksi` int(11) NOT NULL,
  `idpengembalian` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idtransaksi`, `idpengembalian`, `total`) VALUES
(1, 15, 150000),
(2, 15, 150000),
(3, 15, 150000),
(4, 15, 150000),
(5, 15, 150000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`idkaryawan`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idkategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`idpeminjaman`),
  ADD KEY `idsepeda` (`idsepeda`),
  ADD KEY `idkaryawan` (`idkaryawan`),
  ADD KEY `idpengguna` (`idku`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`idpengembalian`),
  ADD KEY `idpeminjaman` (`idpeminjaman`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`idpengguna`);

--
-- Indexes for table `penggunaku`
--
ALTER TABLE `penggunaku`
  ADD PRIMARY KEY (`idku`),
  ADD KEY `idpengguna` (`idpengguna`);

--
-- Indexes for table `sepeda`
--
ALTER TABLE `sepeda`
  ADD PRIMARY KEY (`idsepeda`),
  ADD KEY `idkategori` (`idkategori`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idtransaksi`),
  ADD KEY `idpengembalian` (`idpengembalian`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `idkaryawan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `idkategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `idpeminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `idpengembalian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `idpengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `penggunaku`
--
ALTER TABLE `penggunaku`
  MODIFY `idku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `sepeda`
--
ALTER TABLE `sepeda`
  MODIFY `idsepeda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idtransaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
