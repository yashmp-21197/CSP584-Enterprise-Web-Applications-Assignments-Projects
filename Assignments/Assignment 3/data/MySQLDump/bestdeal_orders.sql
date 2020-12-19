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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `userid` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `useraddress` varchar(40) DEFAULT NULL,
  `creditcardno` varchar(40) DEFAULT NULL,
  `orderid` int NOT NULL,
  `purchasedate` varchar(12) DEFAULT NULL,
  `shipdate` varchar(12) DEFAULT NULL,
  `productid` varchar(40) NOT NULL,
  `producttype` varchar(40) DEFAULT NULL,
  `productquantity` int DEFAULT NULL,
  `productprice` double DEFAULT NULL,
  `shippingcost` double DEFAULT NULL,
  `productdiscount` double DEFAULT NULL,
  `totalsales` double DEFAULT NULL,
  `storeid` int DEFAULT NULL,
  `storeaddress` varchar(50) DEFAULT NULL,
  `iscancelled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('yash1','yash patel','abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','alpha_100','pet tracker',1,599.99,5,0,484.992,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','home_mini','voice assistant',3,39.99,5,0,122.973,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','iphone_11_pro','phone',2,999.99,5,0,1809.982,0,'',1),('yash1','yash patel','','12345',2,'10-02-2020','10-16-2020','bar_5.1','sound system',2,499.99,5,10,909.9820000000001,10,'232 State Street, Madison, WI, 53703',1),('yash1','yash patel','','12345',2,'10-02-2020','10-16-2020','macbook-air','laptop',5,999.99,5,10,2714.973,10,'232 State Street, Madison, WI, 53703',1),('yash1','yash patel','','12345',2,'10-02-2020','10-16-2020','tu8000','tv',1,349.99,5,10,319.99100000000004,10,'232 State Street, Madison, WI, 53703',1),('yash2','yash patel','abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','fit','fitness watch',1,99.99,5,40,64.994,0,'',1),('yash2','yash patel','abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','playstation_vr','vr',1,699.99,5,50,354.995,0,'',1),('yash2','yash patel','abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','series_5','smart watch',1,299.99,5,40,184.994,0,'',1),('yash1','yash patel','','12345',4,'10-02-2020','10-16-2020','iphone_11_pro','phone',1,999.99,5,10,904.991,4,'1972 Hwy 431, Boaz, AL, 35957',1),('yash1','yash patel','','12345',4,'10-02-2020','10-16-2020','series_5','smart watch',1,299.99,5,40,184.994,4,'1972 Hwy 431, Boaz, AL, 35957',0),('yash1','yash patel','','12345',4,'10-02-2020','10-16-2020','wh_1000xm4','headphone',1,399.99,5,30,284.993,4,'1972 Hwy 431, Boaz, AL, 35957',0),('yash1','yash patel','abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','playstation_vr','vr',1,699.99,5,50,354.995,0,'',1),('yash1','yash patel','abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','series_5','smart watch',1,299.99,5,40,184.994,0,'',1),('yash1','yash patel','abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',6,'10-10-2020','10-24-2020','home_mini','voice assistant',1,39.99,5,10,40.991,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',7,'10-11-2020','10-25-2020','echo_dot','voice assistant',2,49.99,5,10,99.982,0,'',0),('yash1','yash patel','','12345',8,'10-11-2020','10-25-2020','q70t','tv',1,2699.99,5,10,2434.991,1,'780 Lynnway, Lynn, MA, 1905',0),('yash2','yash patel','abc, abc, abc, abc','12345',9,'10-11-2020','10-25-2020','tu8000','tv',1,349.99,5,10,319.99100000000004,0,'',0),('yash2','yash patel','','12345',10,'10-11-2020','10-25-2020','nest','voice assistant',3,89.99,5,10,257.973,2,'250 Rt 59, Airmont, NY, 10901',0),('yash3','yash patel','abc, abc, abc, abc','12345',11,'10-11-2020','10-25-2020','iphone_11_pro','phone',2,999.99,5,10,1809.982,0,'',0),('yash3','yash patel','','12345',12,'10-11-2020','10-25-2020','series_5','smart watch',2,299.99,5,40,369.988,3,'255 W Main St, Avon, CT, 6001',0),('yash4','yash patel','','12345',13,'10-11-2020','10-25-2020','p30_pro','phone',1,799.99,5,10,724.991,4,'1972 Hwy 431, Boaz, AL, 35957',0),('yash4','yash patel','abc, abc, abc, abc','12345',14,'10-11-2020','10-25-2020','galaxy','smart watch',1,239.99,5,40,148.994,0,'',0),('yash5','yash patel','abc, abc, abc, abc','12345',15,'10-11-2020','10-25-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,0,'',0),('yash5','yash patel','','12345',16,'10-11-2020','10-25-2020','bar_5.1','sound system',1,499.99,5,10,454.99100000000004,5,'207 South Washington, Naperville, IL, 60540',0),('yash6','yash patel','','12345',17,'10-11-2020','10-25-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,6,'1186 Roseville Pkwy, Roseville, CA, 95678',0),('yash6','yash patel','abc, abc, abc, abc','12345',18,'10-11-2020','10-25-2020','bar_5.1','sound system',1,499.99,5,10,454.99100000000004,0,'',0),('yash7','yash patel','abc, abc, abc, abc','12345',19,'10-11-2020','10-25-2020','playstation_vr','vr',1,699.99,5,50,354.995,0,'',0),('yash7','yash patel','','12345',20,'10-11-2020','10-25-2020','macbook-air','laptop',1,999.99,5,10,904.991,7,'71 Silhavy Rd Ste A-1, Valparaiso, IN, 46383',0),('yash8','yash patel','','12345',21,'10-11-2020','10-25-2020','playstation_vr','vr',1,699.99,5,50,354.995,8,'1555 Lake Woodlands, Dr Spring, TX, 77380',0),('yash8','yash patel','abc, abc, abc, abc','12345',22,'10-11-2020','10-25-2020','macbook-air','laptop',1,999.99,5,10,904.991,0,'',0),('yash9','yash patel','abc, abc, abc, abc','12345',23,'10-11-2020','10-25-2020','fit','fitness watch',1,99.99,5,40,64.994,0,'',0),('yash9','yash patel','','12345',24,'10-11-2020','10-25-2020','wh_1000xm4','headphone',1,399.99,5,30,284.993,9,'7541 W Bell Road Suite 101, Peoria, AZ, 85382',0),('yash10','yash patel','','12345',25,'10-11-2020','10-25-2020','fit','fitness watch',1,99.99,5,40,64.994,10,'232 State Street, Madison, WI, 53703',0),('yash10','yash patel','abc, abc, abc, abc','12345',26,'10-11-2020','10-25-2020','wh_1000xm4','headphone',1,399.99,5,30,284.993,0,'',0),('yash11','yash patel','abc, abc, abc, abc','12345',27,'10-11-2020','10-25-2020','home_mini','voice assistant',1,39.99,5,10,40.991,0,'',0),('yash11','yash patel','','12345',28,'10-11-2020','10-25-2020','tu8000','tv',1,349.99,5,10,319.99100000000004,1,'780 Lynnway, Lynn, MA, 1905',0),('yash12','yash patel','','12345',29,'10-11-2020','10-25-2020','home_mini','voice assistant',1,39.99,5,10,40.991,2,'250 Rt 59, Airmont, NY, 10901',0),('yash12','yash patel','abc, abc, abc, abc','12345',30,'10-11-2020','10-25-2020','tu8000','tv',1,349.99,5,10,319.99100000000004,0,'',0),('yash13','yash patel','abc, abc, abc, abc','12345',31,'10-11-2020','10-25-2020','iphone_11_pro','phone',1,999.99,5,10,904.991,0,'',0),('yash13','yash patel','','12345',32,'10-11-2020','10-25-2020','series_5','smart watch',1,299.99,5,40,184.994,3,'255 W Main St, Avon, CT, 6001',0),('yash14','yash patel','','12345',33,'10-11-2020','10-25-2020','iphone_11_pro','phone',1,999.99,5,10,904.991,4,'1972 Hwy 431, Boaz, AL, 35957',0),('yash14','yash patel','abc, abc, abc, abc','12345',34,'10-11-2020','10-25-2020','series_5','smart watch',1,299.99,5,40,184.994,0,'',0),('yash15','yash patel','abc, abc, abc, abc','12345',35,'10-11-2020','10-25-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,0,'',0),('yash15','yash patel','','12345',36,'10-11-2020','10-25-2020','bar_5.1','sound system',1,499.99,5,10,454.99100000000004,5,'207 South Washington, Naperville, IL, 60540',0),('yash16','yash patel','','12345',37,'10-11-2020','10-25-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,6,'1186 Roseville Pkwy, Roseville, CA, 95678',0),('yash16','yash patel','abc, abc, abc, abc','12345',38,'10-11-2020','10-25-2020','bar_5.1','sound system',1,499.99,5,10,454.99100000000004,0,'',0),('yash17','yash patel','abc, abc, abc, abc','12345',39,'10-11-2020','10-25-2020','playstation_vr','vr',1,699.99,5,50,354.995,0,'',0),('yash17','yash patel','','12345',40,'10-11-2020','10-25-2020','macbook-air','laptop',1,999.99,5,10,904.991,7,'71 Silhavy Rd Ste A-1, Valparaiso, IN, 46383',0),('yash18','yash patel','','12345',41,'10-11-2020','10-25-2020','playstation_vr','vr',1,699.99,5,50,354.995,8,'1555 Lake Woodlands, Dr Spring, TX, 77380',0),('yash18','yash patel','abc, abc, abc, abc','12345',42,'10-11-2020','10-25-2020','macbook-air','laptop',1,999.99,5,10,904.991,0,'',0),('yash19','yash patel','abc, abc, abc, abc','12345',43,'10-11-2020','10-25-2020','fit','fitness watch',1,99.99,5,40,64.994,0,'',0),('yash19','yash patel','','12345',44,'10-11-2020','10-25-2020','wh_1000xm4','headphone',1,399.99,5,30,284.993,9,'7541 W Bell Road Suite 101, Peoria, AZ, 85382',0),('yash20','yash patel','','12345',45,'10-11-2020','10-25-2020','fit','fitness watch',1,99.99,5,40,64.994,10,'232 State Street, Madison, WI, 53703',0),('yash20','yash patel','abc, abc, abc, abc','12345',46,'10-11-2020','10-25-2020','wh_1000xm4','headphone',1,399.99,5,30,284.993,0,'',0),('yash1','yash patel','','12345',47,'10-11-2020','10-25-2020','t_5','pet tracker',1,249.99,5,20,204.99200000000002,1,'780 Lynnway, Lynn, MA, 1905',0),('yash1','yash patel','','12345',48,'10-11-2020','10-25-2020','echo_dot','voice assistant',1,49.99,5,10,49.991,1,'780 Lynnway, Lynn, MA, 1905',0),('yash1','yash patel','','12345',49,'10-11-2020','10-25-2020','iphone_xs_max','phone',3,999.99,5,10,904.991,1,'780 Lynnway, Lynn, MA, 1905',1),('yash1','yash patel','abc, abc, abc, abc','21197',50,'10-11-2020','10-25-2020','home_max','voice assistant',1,299.99,5,10,274.99100000000004,0,'',1),('yash1','yash patel','','12345',51,'10-11-2020','10-25-2020','home_max','voice assistant',4,299.99,5,10,274.99100000000004,1,'780 Lynnway, Lynn, MA, 1905',1),('yash23','yash patel','','12345',52,'10-11-2020','10-25-2020','home_mini','voice assistant',1,39.99,5,10,40.991,1,'780 Lynnway, Lynn, MA, 1905',1),('yash1','yash patel','abc, abc, abc, abc','12345',53,'10-22-2020','11-05-2020','gen_5','smart watch',5,299.99,5,40,924.97,0,'',0),('yash1','yash patel','abc, abc, abc, abc','12345',54,'10-24-2020','11-07-2020','gear_vr','vr',1,59.99,5,50,34.995000000000005,0,'',0),('yash1','yash patel','abc, abc, abc, abc','12345',55,'10-24-2020','11-07-2020','gear_vr','vr',9,59.99,5,50,314.95500000000004,0,'',0),('yash1','yash patel','abc, abc, abc, abc','12345',56,'10-24-2020','11-07-2020','a9g','tv',7,2299.99,5,0,16134.929999999998,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',56,'10-24-2020','11-07-2020','home_mini','voice assistant',1,39.99,5,0,44.99,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',57,'10-24-2020','11-07-2020','a9g','tv',7,2299.99,5,0,16134.929999999998,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',58,'10-24-2020','11-07-2020','go_explore','pet tracker',1,199.99,5,20,164.99200000000002,0,'',1),('yash1','yash patel','abc, abc, abc, abc','12345',58,'10-24-2020','11-07-2020','home_mini','voice assistant',1,39.99,5,0,44.99,0,'',1),('yash1','yash patel','','12345',59,'10-25-2020','11-08-2020','home_max','voice assistant',5,299.99,5,0,1524.95,8,'1555 Lake Woodlands, Dr Spring, TX, 77380',1),('yash1','yash patel','abc, abc, abc, abc','21197',60,'10-25-2020','11-08-2020','home_mini','voice assistant',5,39.99,5,0,224.95000000000002,0,'',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-25 21:10:33
