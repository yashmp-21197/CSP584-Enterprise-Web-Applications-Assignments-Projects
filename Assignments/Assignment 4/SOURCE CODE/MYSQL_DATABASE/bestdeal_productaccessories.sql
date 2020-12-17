-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: bestdeal
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productaccessories`
--

DROP TABLE IF EXISTS `productaccessories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productaccessories` (
  `productname` varchar(20) NOT NULL,
  `accessoryname` varchar(20) NOT NULL,
  PRIMARY KEY (`productname`,`accessoryname`),
  KEY `accessoryname` (`accessoryname`),
  CONSTRAINT `productaccessories_ibfk_1` FOREIGN KEY (`productname`) REFERENCES `productdetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productaccessories_ibfk_2` FOREIGN KEY (`accessoryname`) REFERENCES `productdetails` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productaccessories`
--

LOCK TABLES `productaccessories` WRITE;
/*!40000 ALTER TABLE `productaccessories` DISABLE KEYS */;
INSERT INTO `productaccessories` VALUES ('galaxy_note20_5g','ace_2'),('iphone_11_pro','ace_2'),('iphone_xs_max','ace_2'),('p30_pro','ace_2'),('p40','ace_2'),('galaxy_note20_5g','alpha_100'),('iphone_11_pro','alpha_100'),('iphone_xs_max','alpha_100'),('p30_pro','alpha_100'),('p40','alpha_100'),('a9g','club_700bt'),('galaxy_note20_5g','club_700bt'),('iphone_11_pro','club_700bt'),('iphone_xs_max','club_700bt'),('macbook-air','club_700bt'),('nano99','club_700bt'),('p30_pro','club_700bt'),('p40','club_700bt'),('pavilion_15','club_700bt'),('q70t','club_700bt'),('spectre_x360','club_700bt'),('surface_3','club_700bt'),('tu8000','club_700bt'),('xps_15','club_700bt'),('z9g','club_700bt'),('a9g','club_950nc'),('galaxy_note20_5g','club_950nc'),('iphone_11_pro','club_950nc'),('iphone_xs_max','club_950nc'),('macbook-air','club_950nc'),('nano99','club_950nc'),('p30_pro','club_950nc'),('p40','club_950nc'),('pavilion_15','club_950nc'),('q70t','club_950nc'),('spectre_x360','club_950nc'),('surface_3','club_950nc'),('tu8000','club_950nc'),('xps_15','club_950nc'),('z9g','club_950nc'),('galaxy_note20_5g','fit'),('iphone_11_pro','fit'),('iphone_xs_max','fit'),('p30_pro','fit'),('p40','fit'),('galaxy_note20_5g','galaxy'),('iphone_11_pro','galaxy'),('iphone_xs_max','galaxy'),('p30_pro','galaxy'),('p40','galaxy'),('galaxy_note20_5g','galaxy_3'),('iphone_11_pro','galaxy_3'),('iphone_xs_max','galaxy_3'),('p30_pro','galaxy_3'),('p40','galaxy_3'),('a9g','gear_vr'),('nano99','gear_vr'),('q70t','gear_vr'),('tu8000','gear_vr'),('z9g','gear_vr'),('galaxy_note20_5g','gen_5'),('iphone_11_pro','gen_5'),('iphone_xs_max','gen_5'),('p30_pro','gen_5'),('p40','gen_5'),('galaxy_note20_5g','go'),('iphone_11_pro','go'),('iphone_xs_max','go'),('p30_pro','go'),('p40','go'),('galaxy_note20_5g','go_explore'),('iphone_11_pro','go_explore'),('iphone_xs_max','go_explore'),('p30_pro','go_explore'),('p40','go_explore'),('galaxy_note20_5g','gt_2_pro'),('iphone_11_pro','gt_2_pro'),('iphone_xs_max','gt_2_pro'),('p30_pro','gt_2_pro'),('p40','gt_2_pro'),('galaxy_note20_5g','inspire_2'),('iphone_11_pro','inspire_2'),('iphone_xs_max','inspire_2'),('p30_pro','inspire_2'),('p40','inspire_2'),('galaxy_note20_5g','mi_band_5'),('iphone_11_pro','mi_band_5'),('iphone_xs_max','mi_band_5'),('p30_pro','mi_band_5'),('p40','mi_band_5'),('a9g','playstation_vr'),('nano99','playstation_vr'),('q70t','playstation_vr'),('tu8000','playstation_vr'),('z9g','playstation_vr'),('a9g','quest'),('nano99','quest'),('q70t','quest'),('tu8000','quest'),('z9g','quest'),('a9g','quiet_comfort_35'),('galaxy_note20_5g','quiet_comfort_35'),('iphone_11_pro','quiet_comfort_35'),('iphone_xs_max','quiet_comfort_35'),('macbook-air','quiet_comfort_35'),('nano99','quiet_comfort_35'),('p30_pro','quiet_comfort_35'),('p40','quiet_comfort_35'),('pavilion_15','quiet_comfort_35'),('q70t','quiet_comfort_35'),('spectre_x360','quiet_comfort_35'),('surface_3','quiet_comfort_35'),('tu8000','quiet_comfort_35'),('xps_15','quiet_comfort_35'),('z9g','quiet_comfort_35'),('a9g','rift_s'),('nano99','rift_s'),('q70t','rift_s'),('tu8000','rift_s'),('z9g','rift_s'),('galaxy_note20_5g','series_3'),('iphone_11_pro','series_3'),('iphone_xs_max','series_3'),('p30_pro','series_3'),('p40','series_3'),('galaxy_note20_5g','series_5'),('iphone_11_pro','series_5'),('iphone_xs_max','series_5'),('p30_pro','series_5'),('p40','series_5'),('a9g','studio_3'),('galaxy_note20_5g','studio_3'),('iphone_11_pro','studio_3'),('iphone_xs_max','studio_3'),('macbook-air','studio_3'),('nano99','studio_3'),('p30_pro','studio_3'),('p40','studio_3'),('pavilion_15','studio_3'),('q70t','studio_3'),('spectre_x360','studio_3'),('surface_3','studio_3'),('tu8000','studio_3'),('xps_15','studio_3'),('z9g','studio_3'),('galaxy_note20_5g','t_5'),('iphone_11_pro','t_5'),('iphone_xs_max','t_5'),('p30_pro','t_5'),('p40','t_5'),('galaxy_note20_5g','tt_15'),('iphone_11_pro','tt_15'),('iphone_xs_max','tt_15'),('p30_pro','tt_15'),('p40','tt_15'),('a9g','vive_cosmos'),('nano99','vive_cosmos'),('q70t','vive_cosmos'),('tu8000','vive_cosmos'),('z9g','vive_cosmos'),('a9g','wh_1000xm4'),('galaxy_note20_5g','wh_1000xm4'),('iphone_11_pro','wh_1000xm4'),('iphone_xs_max','wh_1000xm4'),('macbook-air','wh_1000xm4'),('nano99','wh_1000xm4'),('p30_pro','wh_1000xm4'),('p40','wh_1000xm4'),('pavilion_15','wh_1000xm4'),('q70t','wh_1000xm4'),('spectre_x360','wh_1000xm4'),('surface_3','wh_1000xm4'),('tu8000','wh_1000xm4'),('xps_15','wh_1000xm4'),('z9g','wh_1000xm4');
/*!40000 ALTER TABLE `productaccessories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-08 13:24:45
