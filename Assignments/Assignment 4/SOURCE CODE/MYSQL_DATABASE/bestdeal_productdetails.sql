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
-- Table structure for table `productdetails`
--

DROP TABLE IF EXISTS `productdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productdetails` (
  `id` varchar(20) NOT NULL,
  `producttype` varchar(20) DEFAULT NULL,
  `productname` varchar(40) DEFAULT NULL,
  `productprice` double DEFAULT NULL,
  `productimage` varchar(40) DEFAULT NULL,
  `productmanufacturer` varchar(40) DEFAULT NULL,
  `productcondition` varchar(40) DEFAULT NULL,
  `productdiscount` double DEFAULT NULL,
  `productonsale` tinyint(1) DEFAULT NULL,
  `productcount` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productdetails`
--

LOCK TABLES `productdetails` WRITE;
/*!40000 ALTER TABLE `productdetails` DISABLE KEYS */;
INSERT INTO `productdetails` VALUES ('a9g','tv','a9g',2299.99,'a9g.jpg','sony','old',0,1,7),('ace_2','fitness watch','ace 2',69.99,'ace_2.jpg','fitbit','old',40,1,400),('alpha_100','pet tracker','alpha 100',599.99,'alpha_100.jpg','garmin','new',20,0,200),('bar_5.1','sound system','bar 5.1',499.99,'bar_5.1.jpg','jbl','new',0,0,100),('club_700bt','headphone','club 700bt',199.99,'club_700bt.jpg','jbl','old',30,1,300),('club_950nc','headphone','club 950nc',299.99,'club_950nc.jpg','jbl','new',30,0,300),('cq5dam','sound system','Surround Speakers',299.99,'cq5dam.jpg','bose','old',0,1,100),('echo_dot','voice assistant','echo dot',49.99,'echo_dot.jpg','amazon','old',0,1,100),('echo_studio','voice assistant','echo studio',250,'echo_studio.jpg','amazon','new',10,1,50),('fit','fitness watch','fit',99.99,'fit.jpg','huawei','new',40,0,400),('galaxy','smart watch','galaxy',239.99,'galaxy.jpg','samsung','new',40,0,400),('galaxy_3','smart watch','galaxy 3',399.99,'galaxy_3.jpg','samsung','old',40,1,400),('galaxy_note20_5g','phone','Galaxy Note20 5G',999.99,'galaxy_note20_5g.jpg','samsung','new',0,0,100),('gear_vr','vr','gear vr',59.99,'gear_vr.jpg','samsung','new',50,0,490),('gen_5','smart watch','gen 5',299.99,'gen_5.jpg','fossil','new',40,0,400),('go','pet tracker','go',129.99,'go.jpg','whistle','old',20,1,200),('go_explore','pet tracker','go explore',199.99,'go_explore.jpg','whistle','new',20,0,200),('gt_2_pro','fitness watch','gt 2 pro',199.99,'gt_2_pro.jpg','huawei','old',40,1,400),('home_max','voice assistant','home max',299.99,'home_max.jpg','google','new',0,0,97),('home_mini','voice assistant','home mini',39.99,'home_mini.jpg','google','old',0,1,92),('inspire_2','fitness watch','inspire 2',99.99,'inspire_2.jpg','fitbit','new',40,0,400),('iphone_11_pro','phone','iPhone 11 Pro',999.99,'iphone_11_pro.jpg','apple','old',0,1,100),('iphone_xs_max','phone','iPhone XS Max',999.99,'iphone_xs_max.jpg','apple','new',0,0,100),('ls650','sound system','lifestyle 650',3999.99,'ls650.jpg','bose','new',0,0,100),('macbook-air','laptop','macbook air',999.99,'macbook-air.jpg','apple','old',0,1,100),('mi_band_5','fitness watch','mi band 5',39.99,'mi_band_5.jpg','xiaomi','new',40,0,400),('nano99','tv','nano99',4299.99,'nano99.jpg','lg','new',0,0,100),('nest','voice assistant','nest',89.99,'nest.jpg','google','old',0,1,100),('p30_pro','phone','p30 pro',799.99,'p30_pro.jpg','huawei','old',0,1,100),('p40','phone','p40',799.99,'p40.jpg','huawei','new',0,0,100),('pavilion_15','laptop','pavilion 15',499.99,'pavilion_15.jpg','hp','old',0,1,100),('playstation_vr','vr','playstation vr',699.99,'playstation_vr.jpg','sony','new',50,0,500),('q70t','tv','q70t',2699.99,'q70t.jpg','samsung','new',0,0,100),('quest','vr','quest',599.99,'quest.jpg','oculus','new',50,0,500),('quiet_comfort_35','headphone','quiet comfort 35',299.99,'quiet_comfort_35.jpg','bose','old',30,1,300),('rift_s','vr','rift s',399.99,'rift_s.jpg','oculus','old',50,1,500),('series_3','smart watch','series 3',169.99,'series_3.jpg','apple','old',40,1,400),('series_5','smart watch','series 5',299.99,'series_5.jpg','apple','new',40,0,400),('spectre_x360','laptop','spectre x360',1199.99,'spectre_x360.jpg','hp','new',0,0,100),('studio_3','headphone','studio 3',349.99,'studio_3.jpg','beats','new',30,0,300),('surface_3','laptop','surface 3',1999.99,'surface_3.jpg','microsoft','new',0,0,100),('t_5','pet tracker','t 5',249.99,'t_5.jpg','garmin','old',20,1,200),('tt_15','pet tracker','tt 15',699.99,'tt_15.jpg','garmin','old',20,1,200),('tu8000','tv','tu8000',349.99,'tu8000.jpg','samsung','old',0,1,100),('vive_cosmos','vr','vive cosmos',699.99,'vive_cosmos.jpg','htc','new',50,0,500),('wh_1000xm4','headphone','wh 1000xm4',399.99,'wh_1000xm4.jpg','sony','new',30,0,300),('xps_15','laptop','xps 15',1099.99,'xps_15.jpg','dell','old',0,1,100),('z606','sound system','z606 5.1',129.99,'z606.jpg','logitech','old',0,1,100),('z906','sound system','z906 5.1',399.99,'z906.jpg','logitech','new',0,0,100),('z9g','tv','z9g',7999.99,'z9g.jpg','sony','new',0,0,100);
/*!40000 ALTER TABLE `productdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-08 13:24:46
