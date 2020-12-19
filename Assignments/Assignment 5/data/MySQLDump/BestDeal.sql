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
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `storeid` int NOT NULL AUTO_INCREMENT,
  `street` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `state` varchar(40) DEFAULT NULL,
  `zip` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`storeid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'780 Lynnway','Lynn','MA','1905'),(2,'250 Rt 59','Airmont','NY','10901'),(3,'255 W Main St','Avon','CT','6001'),(4,'1972 Hwy 431','Boaz','AL','35957'),(5,'207 South Washington','Naperville','IL','60540'),(6,'1186 Roseville Pkwy','Roseville','CA','95678'),(7,'71 Silhavy Rd Ste A-1','Valparaiso','IN','46383'),(8,'1555 Lake Woodlands','Dr Spring','TX','77380'),(9,'7541 W Bell Road Suite 101','Peoria','AZ','85382'),(10,'232 State Street','Madison','WI','53703');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `userid` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `useroccupation` varchar(40) DEFAULT NULL,
  `userage` int DEFAULT NULL,
  `useraddress` varchar(40) DEFAULT NULL,
  `creditcardno` varchar(40) DEFAULT NULL,
  `orderid` int NOT NULL,
  `purchasedate` varchar(12) DEFAULT NULL,
  `shipdate` varchar(12) DEFAULT NULL,
  `actualshipdate` varchar(12) DEFAULT NULL,
  `productid` varchar(40) NOT NULL,
  `productname` varchar(40) DEFAULT NULL,
  `producttype` varchar(40) DEFAULT NULL,
  `productmanufacturer` varchar(40) DEFAULT NULL,
  `productquantity` int DEFAULT NULL,
  `productprice` double DEFAULT NULL,
  `shippingcost` double DEFAULT NULL,
  `productdiscount` double DEFAULT NULL,
  `totalsales` double DEFAULT NULL,
  `reviewrating` double DEFAULT NULL,
  `trackingid` varchar(40) DEFAULT NULL,
  `storeid` int DEFAULT NULL,
  `storeaddress` varchar(50) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `transactionstatus` varchar(20) DEFAULT NULL,
  `iscancelled` tinyint(1) DEFAULT NULL,
  `isreturned` tinyint(1) DEFAULT NULL,
  `isdeliveredontime` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','10-16-2020','alpha_100','alpha 100','pet tracker','garmin',1,599.99,5,0,484.992,1,'xyz1',0,'','abc','Approved',1,1,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','10-16-2020','home_mini','home mini','voice assistant','google',3,39.99,5,0,122.973,1,'xyz1',0,'','abc','Approved',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',1,'10-02-2020','10-16-2020','10-16-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',2,999.99,5,0,1809.982,0,'xyz1',0,'','abc','Approved',1,0,1),('yash1','yash patel','student',24,'','12345',2,'10-02-2020','10-16-2020','10-17-2020','bar_5.1','bar 5.1','sound system','jbl',2,499.99,5,10,909.9820000000001,1,'xyz2',10,'232 State Street, Madison, WI, 53703','53703','disputed',1,1,0),('yash1','yash patel','student',24,'','12345',2,'10-02-2020','10-16-2020','10-17-2020','macbook-air','macbook air','laptop','apple',5,999.99,5,10,2714.973,1,'xyz2',10,'232 State Street, Madison, WI, 53703','53703','disputed',1,1,0),('yash1','yash patel','student',24,'','12345',2,'10-02-2020','10-16-2020','10-17-2020','tu8000','tu8000','tv','samsung',1,349.99,5,10,319.99100000000004,5,'xyz2',10,'232 State Street, Madison, WI, 53703','53703','disputed',1,1,0),('yash2','yash patel','student',24,'abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','10-16-2020','fit','fit','fitness watch','huawei',1,99.99,5,40,64.994,1,'xyz3',0,'','abc','disputed',1,1,1),('yash2','yash patel','student',24,'abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','10-16-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,1,'xyz3',0,'','abc','disputed',1,0,1),('yash2','yash patel','student',24,'abc, abc, abc, abc','12345',3,'10-02-2020','10-16-2020','10-16-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,1,'xyz3',0,'','abc','disputed',1,1,1),('yash1','yash patel','student',24,'','12345',4,'10-02-2020','10-16-2020','10-17-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',1,999.99,5,10,904.991,2,'xyz4',4,'1972 Hwy 431, Boaz, AL, 35957','35957','Approved',1,0,0),('yash1','yash patel','student',24,'','12345',4,'10-02-2020','10-16-2020','10-17-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,2,'xyz4',4,'1972 Hwy 431, Boaz, AL, 35957','35957','disputed',0,1,0),('yash1','yash patel','student',24,'','12345',4,'10-02-2020','10-16-2020','10-17-2020','wh_1000xm4','wh 1000xm4','headphone','sony',1,399.99,5,30,284.993,2,'xyz4',4,'1972 Hwy 431, Boaz, AL, 35957','35957','Approved',0,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','10-16-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,1,'xyz5',0,'','abc','disputed',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','10-16-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,1,'xyz5',0,'','abc','disputed',1,1,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','21197',5,'10-02-2020','10-16-2020','10-16-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,1,'xyz5',0,'','abc','disputed',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',6,'10-10-2020','10-24-2020','10-25-2020','home_mini','home mini','voice assistant','google',1,39.99,5,10,40.991,1,'xyz6',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',7,'10-11-2020','10-25-2020','10-25-2020','echo_dot','echo dot','voice assistant','amazon',2,49.99,5,10,99.982,0,'xyz7',0,'','abc','Approved',0,0,1),('yash1','yash patel','student',24,'','12345',8,'10-11-2020','10-25-2020','10-26-2020','q70t','q70t','tv','samsung',1,2699.99,5,10,2434.991,0,'xyz8',1,'780 Lynnway, Lynn, MA, 1905','1905','disputed',0,1,0),('yash2','yash patel','student',24,'abc, abc, abc, abc','12345',9,'10-11-2020','10-25-2020','10-25-2020','tu8000','tu8000','tv','samsung',1,349.99,5,10,319.99100000000004,0,'xyz9',0,'','abc','disputed',0,1,1),('yash2','yash patel','student',24,'','12345',10,'10-11-2020','10-25-2020','10-26-2020','nest','nest','voice assistant','google',3,89.99,5,10,257.973,1,'xyz10',2,'250 Rt 59, Airmont, NY, 10901','10901','Approved',0,1,0),('yash3','yash patel','student',24,'abc, abc, abc, abc','12345',11,'10-11-2020','10-25-2020','10-25-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',2,999.99,5,10,1809.982,0,'xyz11',0,'','abc','Approved',0,0,1),('yash3','yash patel','student',24,'','12345',12,'10-11-2020','10-25-2020','10-26-2020','series_5','series 5','smart watch','apple',2,299.99,5,40,369.988,1,'xyz12',3,'255 W Main St, Avon, CT, 6001','6001','disputed',0,1,0),('yash4','yash patel','student',24,'','12345',13,'10-11-2020','10-25-2020','10-25-2020','p30_pro','p30 pro','phone','huawei',1,799.99,5,10,724.991,2,'xyz13',4,'1972 Hwy 431, Boaz, AL, 35957','35957','disputed',0,0,1),('yash4','yash patel','student',24,'abc, abc, abc, abc','12345',14,'10-11-2020','10-25-2020','10-26-2020','galaxy','galaxy','smart watch','samsung',1,239.99,5,40,148.994,1,'xyz14',0,'','abc','disputed',0,1,0),('yash5','yash patel','student',24,'abc, abc, abc, abc','12345',15,'10-11-2020','10-25-2020','10-25-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,0,'xyz15',0,'','abc','disputed',0,1,1),('yash5','yash patel','student',24,'','12345',16,'10-11-2020','10-25-2020','10-26-2020','bar_5.1','bar 5.1','sound system','jbl',1,499.99,5,10,454.99100000000004,2,'xyz16',5,'207 South Washington, Naperville, IL, 60540','60540','disputed',0,0,0),('yash6','yash patel','student',24,'','12345',17,'10-11-2020','10-25-2020','10-25-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,3,'xyz17',6,'1186 Roseville Pkwy, Roseville, CA, 95678','95678','Approved',0,0,1),('yash6','yash patel','student',24,'abc, abc, abc, abc','12345',18,'10-11-2020','10-25-2020','10-26-2020','bar_5.1','bar 5.1','sound system','jbl',1,499.99,5,10,454.99100000000004,0,'xyz18',0,'','abc','disputed',0,1,0),('yash7','yash patel','student',24,'abc, abc, abc, abc','12345',19,'10-11-2020','10-25-2020','10-25-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,0,'xyz19',0,'','abc','Approved',0,0,1),('yash7','yash patel','student',24,'','12345',20,'10-11-2020','10-25-2020','10-26-2020','macbook-air','macbook air','laptop','apple',1,999.99,5,10,904.991,3,'xyz20',7,'71 Silhavy Rd Ste A-1, Valparaiso, IN, 46383','46383','Approved',0,1,0),('yash8','yash patel','student',24,'','12345',21,'10-11-2020','10-25-2020','10-25-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,4,'xyz21',8,'1555 Lake Woodlands, Dr Spring, TX, 77380','77380','Approved',0,0,1),('yash8','yash patel','student',24,'abc, abc, abc, abc','12345',22,'10-11-2020','10-25-2020','10-26-2020','macbook-air','macbook air','laptop','apple',1,999.99,5,10,904.991,1,'xyz22',0,'','abc','disputed',0,1,0),('yash9','yash patel','student',24,'abc, abc, abc, abc','12345',23,'10-11-2020','10-25-2020','10-25-2020','fit','fit','fitness watch','huawei',1,99.99,5,40,64.994,0,'xyz23',0,'','abc','disputed',0,0,1),('yash9','yash patel','student',24,'','12345',24,'10-11-2020','10-25-2020','10-26-2020','wh_1000xm4','wh 1000xm4','headphone','sony',1,399.99,5,30,284.993,4,'xyz24',9,'7541 W Bell Road Suite 101, Peoria, AZ, 85382','85383','Approved',0,0,0),('yash10','yash patel','student',24,'','12345',25,'10-11-2020','10-25-2020','10-25-2020','fit','fit','fitness watch','huawei',1,99.99,5,40,64.994,5,'xyz25',10,'232 State Street, Madison, WI, 53703','53703','disputed',0,0,1),('yash10','yash patel','student',24,'abc, abc, abc, abc','12345',26,'10-11-2020','10-25-2020','10-26-2020','wh_1000xm4','wh 1000xm4','headphone','sony',1,399.99,5,30,284.993,0,'xyz26',0,'','abc','disputed',0,0,0),('yash11','yash patel','student',24,'abc, abc, abc, abc','12345',27,'10-11-2020','10-25-2020','10-25-2020','home_mini','home mini','voice assistant','google',1,39.99,5,10,40.991,1,'xyz27',0,'','abc','Approved',0,0,1),('yash11','yash patel','student',24,'','12345',28,'10-11-2020','10-25-2020','10-26-2020','tu8000','tu8000','tv','samsung',1,349.99,5,10,319.99100000000004,0,'xyz28',1,'780 Lynnway, Lynn, MA, 1905','1905','disputed',0,1,0),('yash12','yash patel','student',24,'','12345',29,'10-11-2020','10-25-2020','10-25-2020','home_mini','home mini','voice assistant','google',1,39.99,5,10,40.991,1,'xyz29',2,'250 Rt 59, Airmont, NY, 10901','10901','Approved',0,0,1),('yash12','yash patel','student',24,'abc, abc, abc, abc','12345',30,'10-11-2020','10-25-2020','10-26-2020','tu8000','tu8000','tv','samsung',1,349.99,5,10,319.99100000000004,0,'xyz30',0,'','abc','disputed',0,1,0),('yash13','yash patel','student',24,'abc, abc, abc, abc','12345',31,'10-11-2020','10-25-2020','10-25-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',1,999.99,5,10,904.991,0,'xyz31',0,'','abc','Approved',0,0,1),('yash13','yash patel','student',24,'','12345',32,'10-11-2020','10-25-2020','10-26-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,1,'xyz32',3,'255 W Main St, Avon, CT, 6001','6001','disputed',0,1,0),('yash14','yash patel','student',24,'','12345',33,'10-11-2020','10-25-2020','10-25-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',1,999.99,5,10,904.991,2,'xyz33',4,'1972 Hwy 431, Boaz, AL, 35957','35957','Approved',0,0,1),('yash14','yash patel','student',24,'abc, abc, abc, abc','12345',34,'10-11-2020','10-25-2020','10-26-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,0,'xyz34',0,'','abc','disputed',0,1,0),('yash15','yash patel','student',24,'abc, abc, abc, abc','12345',35,'10-11-2020','10-25-2020','10-25-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,0,'xyz35',0,'','abc','Approved',0,0,1),('yash15','yash patel','student',24,'','12345',36,'10-11-2020','10-25-2020','10-26-2020','bar_5.1','bar 5.1','sound system','jbl',1,499.99,5,10,454.99100000000004,2,'xyz36',5,'207 South Washington, Naperville, IL, 60540','60540','Approved',0,0,0),('yash16','yash patel','student',24,'','12345',37,'10-11-2020','10-25-2020','10-25-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,3,'xyz37',6,'1186 Roseville Pkwy, Roseville, CA, 95678','95678','Approved',0,0,1),('yash16','yash patel','student',24,'abc, abc, abc, abc','12345',38,'10-11-2020','10-25-2020','10-26-2020','bar_5.1','bar 5.1','sound system','jbl',1,499.99,5,10,454.99100000000004,0,'xyz38',0,'','abc','Approved',0,0,0),('yash17','yash patel','student',24,'abc, abc, abc, abc','12345',39,'10-11-2020','10-25-2020','10-25-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,0,'xyz39',0,'','abc','Approved',0,0,1),('yash17','yash patel','student',24,'','12345',40,'10-11-2020','10-25-2020','10-26-2020','macbook-air','macbook air','laptop','apple',1,999.99,5,10,904.991,3,'xyz40',7,'71 Silhavy Rd Ste A-1, Valparaiso, IN, 46383','46383','Approved',0,0,0),('yash18','yash patel','student',24,'','12345',41,'10-11-2020','10-25-2020','10-25-2020','playstation_vr','playstation vr','vr','sony',1,699.99,5,50,354.995,4,'xyz41',8,'1555 Lake Woodlands, Dr Spring, TX, 77380','77380','Approved',0,0,1),('yash18','yash patel','student',24,'abc, abc, abc, abc','12345',42,'10-11-2020','10-25-2020','10-26-2020','macbook-air','macbook air','laptop','apple',1,999.99,5,10,904.991,0,'xyz42',0,'','abc','Approved',0,0,0),('yash19','yash patel','student',24,'abc, abc, abc, abc','12345',43,'10-11-2020','10-25-2020','10-25-2020','fit','fit','fitness watch','huawei',1,99.99,5,40,64.994,0,'xyz43',0,'','abc','Approved',0,0,1),('yash19','yash patel','student',24,'','12345',44,'10-11-2020','10-25-2020','10-26-2020','wh_1000xm4','wh 1000xm4','headphone','sony',1,399.99,5,30,284.993,4,'xyz44',9,'7541 W Bell Road Suite 101, Peoria, AZ, 85382','85383','Approved',0,0,0),('yash20','yash patel','student',24,'','12345',45,'10-11-2020','10-25-2020','10-25-2020','fit','fit','fitness watch','huawei',1,99.99,5,40,64.994,5,'xyz45',10,'232 State Street, Madison, WI, 53703','53703','Approved',0,0,1),('yash20','yash patel','student',24,'abc, abc, abc, abc','12345',46,'10-11-2020','10-25-2020','10-26-2020','wh_1000xm4','wh 1000xm4','headphone','sony',1,399.99,5,30,284.993,0,'xyz46',0,'','abc','Approved',0,0,0),('yash1','yash patel','student',24,'','12345',47,'10-11-2020','10-25-2020','10-25-2020','t_5','t 5','pet tracker','garmin',1,249.99,5,20,204.99200000000002,0,'xyz47',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',0,0,1),('yash1','yash patel','student',24,'','12345',48,'10-11-2020','10-25-2020','10-26-2020','echo_dot','echo dot','voice assistant','amazon',1,49.99,5,10,49.991,0,'xyz48',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',0,0,0),('yash1','yash patel','student',24,'','12345',49,'10-11-2020','10-25-2020','10-25-2020','iphone_xs_max','iPhone XS Max','phone','apple',3,999.99,5,10,904.991,0,'xyz49',1,'780 Lynnway, Lynn, MA, 1905','1905','disputed',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','21197',50,'10-11-2020','10-25-2020','10-26-2020','home_max','home max','voice assistant','google',1,299.99,5,10,274.99100000000004,0,'xyz50',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',24,'','12345',51,'10-11-2020','10-25-2020','10-25-2020','home_max','home max','voice assistant','google',4,299.99,5,10,274.99100000000004,0,'xyz51',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',1,0,1),('yash23','yash patel','student',24,'','12345',52,'10-11-2020','10-25-2020','10-26-2020','home_mini','home mini','voice assistant','google',1,39.99,5,10,40.991,0,'xyz52',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',53,'10-22-2020','11-05-2020','11-05-2020','gen_5','gen 5','smart watch','fossil',5,299.99,5,40,924.97,0,'xyz53',0,'','abc','Approved',0,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',54,'10-24-2020','11-07-2020','11-08-2020','gear_vr','gear vr','vr','samsung',1,59.99,5,50,34.995000000000005,0,'xyz54',0,'','abc','Approved',0,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',55,'10-24-2020','11-07-2020','11-07-2020','gear_vr','gear vr','vr','samsung',9,59.99,5,50,314.95500000000004,0,'xyz55',0,'','abc','Approved',0,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',56,'10-24-2020','11-07-2020','11-08-2020','a9g','a9g','tv','sony',7,2299.99,5,0,16134.929999999998,0,'xyz56',0,'','abc','disputed',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',56,'10-24-2020','11-07-2020','11-08-2020','home_mini','home mini','voice assistant','google',1,39.99,5,0,44.99,0,'xyz56',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',57,'10-24-2020','11-07-2020','11-07-2020','a9g','a9g','tv','sony',7,2299.99,5,0,16134.929999999998,0,'xyz57',0,'','abc','disputed',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',58,'10-24-2020','11-07-2020','11-08-2020','go_explore','go explore','pet tracker','whistle',1,199.99,5,20,164.99200000000002,0,'xyz58',0,'','abc','disputed',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',58,'10-24-2020','11-07-2020','11-08-2020','home_mini','home mini','voice assistant','google',1,39.99,5,0,44.99,0,'xyz58',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',24,'','12345',59,'10-25-2020','11-08-2020','11-08-2020','home_max','home max','voice assistant','google',5,299.99,5,0,1524.95,4,'xyz59',8,'1555 Lake Woodlands, Dr Spring, TX, 77380','77380','Approved',1,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','21197',60,'10-25-2020','11-08-2020','11-09-2020','home_mini','home mini','voice assistant','google',5,39.99,5,0,224.95000000000002,0,'xyz60',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',61,'10-28-2020','11-11-2020','11-11-2020','home_mini','home mini','voice assistant','google',5,39.99,5,0,224.95000000000002,3.5555555555555554,'xyz61',0,'','abc','Approved',1,0,1),('yash1','yash patel','student',24,'','12345',62,'10-28-2020','11-11-2020','11-12-2020','home_mini','home mini','voice assistant','google',5,39.99,5,0,224.95000000000002,3.5555555555555554,'xyz62',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',1,0,0),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',63,'11-06-2020','11-20-2020','11-20-2020','home_mini','home mini','voice assistant','google',1,39.99,5,0,44.99,3.5555555555555554,'xyz63',0,'','abc','Approved',0,0,1),('yash1','yash patel','student',24,'','12345',64,'11-06-2020','11-20-2020','11-20-2020','home_max','home max','voice assistant','google',3,299.99,5,0,914.97,5,'xyz64',1,'780 Lynnway, Lynn, MA, 1905','1905','Approved',0,0,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',65,'11-06-2020','11-20-2020','11-21-2020','iphone_11_pro','iPhone 11 Pro','phone','apple',1,999.99,5,0,1004.99,3.8,'xyz65',0,'','abc','Approved',1,0,0),('yash1','yash patel','student',25,'abc, abc, abc, abc','12345',66,'11-06-2020','11-20-2020','11-20-2020','series_5','series 5','smart watch','apple',1,299.99,5,40,184.994,3,'xyz66',0,'','abc','disputed',1,1,1),('yash1','yash patel','student',24,'abc, abc, abc, abc','12345',67,'11-08-2020','11-22-2020','11-22-2020','home_mini','home mini','voice assistant','google',5,39.99,5,0,224.95000000000002,3.5555555555555554,'xyz67',0,'','abc','Approved',0,0,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `repassword` varchar(40) DEFAULT NULL,
  `usertype` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('yash1','21197','21197','customer'),('yash2','21197','21197','customer'),('nirav1','21197','21197','store manager'),('adarsh1','21197','21197','salesman'),('yash3','21197','21197','customer'),('yash4','21197','21197','customer'),('yash5','21197','21197','customer'),('yash6','21197','21197','customer'),('yash7','21197','21197','customer'),('yash8','21197','21197','customer'),('yash9','21197','21197','customer'),('yash10','21197','21197','customer'),('yash11','21197','21197','customer'),('yash13','21197','21197','customer'),('yash12','21197','21197','customer'),('yash14','21197','21197','customer'),('yash15','21197','21197','customer'),('yash16','21197','21197','customer'),('yash17','21197','21197','customer'),('yash18','21197','21197','customer'),('yash19','21197','21197','customer'),('yash20','21197','21197','customer'),('yash21','21197','21197','customer'),('yash22','21197','21197','customer'),('yash23','21197','21197','customer'),('yash24','21197','21197','customer');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-22 16:59:01
