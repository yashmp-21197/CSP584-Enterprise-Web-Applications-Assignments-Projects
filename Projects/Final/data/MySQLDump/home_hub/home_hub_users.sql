-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: home_hub
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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(40) NOT NULL,
  `user_pswd` varchar(40) DEFAULT NULL,
  `user_type` varchar(40) DEFAULT NULL,
  `user_info_set` tinyint(1) DEFAULT NULL,
  `user_email` varchar(40) DEFAULT NULL,
  `user_name` varchar(40) DEFAULT NULL,
  `user_gender` varchar(1) DEFAULT NULL,
  `user_birthdate` varchar(10) DEFAULT NULL,
  `user_age` int DEFAULT NULL,
  `user_occupation` varchar(40) DEFAULT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_street1` varchar(40) DEFAULT NULL,
  `user_street2` varchar(40) DEFAULT NULL,
  `user_city` varchar(40) DEFAULT NULL,
  `user_state` varchar(40) DEFAULT NULL,
  `user_zip` varchar(40) DEFAULT NULL,
  `user_country` varchar(40) DEFAULT NULL,
  `user_lat` double DEFAULT NULL,
  `user_long` double DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('deepak1','123','service_provider',1,'d1@xyz.com','deepak yadav 1','m','11/02/1997',24,'student 1','1234567890','3001 south king drive','apt 101','chicago','illinois','60616','usa',41.8392839,-87.6163853),('deepak10','123','service_provider',1,'d10@xyz.com','deepak yadav 10','f','11/02/1997',53,'provider 10','1234567890','1100 W Cermak Rd','#116','chicago','illinois','60616','United States',41.8527225,-87.6537184),('deepak11','123','service_provider',1,'d11@xyz.com','deepak yadav 11','m','11/02/1997',76,'provider 11','1234567890','1334 W 18th St','125','chicago','illinois','60616','United States',41.8580717,-87.6602118),('deepak12','123','service_provider',1,'d12@xyz.com','deepak yadav 12','f','11/02/1997',43,'provider 12','1234567890','3320 S Halsted St','345','chicago','illinois','60608','United States',41.8341764,-87.6464284),('deepak13','123','service_provider',1,'d13@xyz.com','deepak yadav 13','f','11/02/1997',22,'provider 13','1234567890','3922 S Wells St','78','chicago','illinois','60609','United States',41.8229331,-87.6328686),('deepak14','123','service_provider',1,'d14@xyz.com','deepak yadav 14','m','11/02/1997',34,'provider 14','1234567890','4555 S Western Blvd','789','chicago','illinois','60609','United States',41.8109103,-87.6831864),('deepak15','123','service_provider',1,'d15@xyz.com','deepak yadav 15','f','11/02/1997',57,'provider 15','1234567890','2803 S Cicero Ave','345','chicago','illinois','60804','United States',41.841408,-87.7411616),('deepak16','123','service_provider',1,'d16@xyz.com','deepak yadav 16','f','11/02/1997',67,'provider 16','1234567890','6639 S Pulaski Rd','859','chicago','illinois','60629','United States',41.7719179,-87.7222257),('deepak17','123','service_provider',1,'d17@xyz.com','deepak yadav 17','f','11/02/1997',67,'provider 17','1234567890','3510 W 79th St','589','chicago','illinois','60652','United States',41.7499286,-87.7106036),('deepak18','123','service_provider',1,'d18@xyz.com','deepak yadav 18','f','11/02/1997',76,'provider 18','1234567890','7200 S Cicero Ave','13','chicago','illinois','60629','United States',41.7627181,-87.7459841),('deepak19','123','service_provider',1,'d19@xyz.com','deepak yadav 19','f','11/02/1997',87,'provider 19','1234567890','13450 S Cicero Ave','98','Crestwood','illinois','60418','United States',41.6479032,-87.7393642),('deepak2','123','service_provider',1,'d2@xyz.com','deepak yadav 2','m','11/02/1997',24,'student 2','1234567890','2951 south king drive','apt 110','chicago','illinois','60616','United States',41.8404099,-87.6164229),('deepak20','123','service_provider',1,'d20@xyz.com','deepak yadav 20','f','11/02/1997',65,'provider 20','1234567890','1928 W Fulton st','UNIT 2N','chicago','illinois','60612','United States',41.8870152,-87.6755672),('deepak21','123','service_provider',1,'d21@xyz.com','deepak yadav 21','f','11/02/1997',64,'provider 21','1234567890','1563 N Wells St','79','chicago','illinois','60610','United States',41.910999,-87.634567),('deepak22','123','service_provider',1,'d22@xyz.com','deepak yadav 22','f','11/02/1997',73,'provider 22','1234567890','1016 W Jackson Blvd','#37','chicago','illinois','60607','United States',41.8780255,-87.6527575),('deepak23','123','service_provider',1,'d23@xyz.com','deepak yadav 23','m','11/02/1997',52,'provider 23','1234567890','2120 W Washington Blvd','67','chicago','illinois','60612','United States',41.8833904,-87.6801106),('deepak24','123','service_provider',1,'d24@xyz.com','deepak yadav 24','f','11/02/1997',53,'provider 24','1234567890','205 W Randolph St','#900','chicago','illinois','60606','United States',41.8839253,-87.6341816),('deepak25','123','service_provider',1,'d25@xyz.com','deepak yadav 25','f','11/02/1997',32,'provider 25','1234567890','130 N LaSalle Dr','89','chicago','illinois','60602','United States',41.8840026,-87.632767),('deepak26','123','service_provider',1,'d26@xyz.com','deepak yadav 26','m','11/02/1997',45,'provider 26','1234567890','1300 S Wabash Ave','#200','chicago','illinois','60605','United States',41.8656129,-87.6261433),('deepak27','123','service_provider',1,'d27@xyz.com','deepak yadav 27','f','11/02/1997',76,'provider 27','1234567890','980 N Michigan Ave','#900','chicago','illinois','60611','United States',41.900629,-87.6244518),('deepak28','123','service_provider',1,'d28@xyz.com','deepak yadav 28','f','11/02/1997',47,'provider 28','1234567890','2501 W Washington Blvd','Suite 301','chicago','illinois','60612','United States',41.8827908,-87.6888856),('deepak29','123','service_provider',1,'d29@xyz.com','deepak yadav 29','f','11/02/1997',56,'provider 29','1234567890','712 E 47th St','279','chicago','illinois','60653','United States',41.809804,-87.608416),('deepak3','123','service_provider',1,'d3@xyz.com','deepak yadav 3','f','11/02/1997',28,'student 3','1234567890','2801 south king drive','apt 1117','chicago','illinois','60616','United States',41.8437077,-87.6165023),('deepak30','123','service_provider',1,'d30@xyz.com','deepak yadav 30','f','11/02/1997',43,'provider 30','1234567890','5113 S Harper Ave','Suite 2018','chicago','illinois','60615','United States',41.8020371,-87.5885223),('deepak31','123','service_provider',1,'d31@xyz.com','deepak yadav 31','m','11/02/1997',24,'student','1234567890','1401 S State St','123','chicago','illinois','60605','usa',41.8634989,-87.6270427),('deepak32','123','service_provider',1,'d32@xyz.com','deepak yadav 32','m','11/02/1997',34,'student','1234567890','1401 S State St','123','chicago','illinois','60605','United States',41.8634989,-87.6270427),('deepak4','123','service_provider',1,'d4@xyz.com','deepak yadav 4','f','11/02/1997',30,'provider 4','1234567890','2851 south king drive','apt 909','chicago','illinois','60616','United States',41.8425894,-87.616469),('deepak5','123','service_provider',1,'d5@xyz.com','deepak yadav 5','m','11/02/1997',45,'provider 5','1234567890','215 E 31st St','567','chicago','illinois','60616','United States',41.8381842,-87.6211659),('deepak6','123','service_provider',1,'d6@xyz.com','deepak yadav 6','f','11/02/1997',54,'provider 6','1234567890','215 E 31st St','56','chicago','illinois','60616','United States',41.8381842,-87.6211659),('deepak7','123','service_provider',1,'d7@xyz.com','deepak yadav 7','f','11/02/1997',56,'provider 7','1234567890','2929 S Wabash','Ave #202','chicago','illinois','60616','United States',41.8409592,-87.6248837),('deepak8','123','service_provider',1,'d8@xyz.com','deepak yadav 8','f','11/02/1997',67,'provider 8','1234567890','2251 S Michigan Ave','456','chicago','illinois','60616','United States',41.8514378,-87.6230742),('deepak9','123','service_provider',1,'d9@xyz.com','deepak yadav 9','f','11/02/1997',34,'provider 9','1234567890','238 W Cermak Rd','123','chicago','illinois','60616','United States',41.8530414,-87.6333303),('nirav1','123','administrator',0,'','','','',-1,'','','','','','','','',0,0),('yash1','123','customer',1,'y1@xyz.com','yash patel 1','m','11/02/1997',24,'student','1234567890','2901 south king drive','apt 1117','chicago','illinois','60616','usa',41.8415404,-87.6164621),('yash10','123','customer',1,'y10@gmail.com','yash10','m','01/04/1985',35,'carpenter','32439320','1515 S Prairie Ave,','704','Chicago','Illinois','60605','United States',41.8617675,-87.6202181),('yash2','123','customer',1,'y2@xyz.com','yash2','m','04/12/1997',23,'student','1243567890','2759 W 37th Pl','123','Chicago','IL','60632','United States',41.8254029,-87.6945269),('yash3','123','customer',1,'y3@gmail.com','yash3','m','04/11/97',23,'student','4567890','1959 N Sheffield Ave','32','Chicago','Illinois','60614','United States',41.9175834,-87.6531227),('yash4','123','customer',1,'y4@gmail.com','yash4','m','07/21/1998',22,'soft enginner','2134798981','906 E 54th St','15','Chicago','Illinois','60615','United States',41.7980368,-87.603498),('yash5','123','customer',1,'y5@gmail.com','yash5','m','01/04/1992',28,'EC','32984304','7700 S Sangamon St','4602','Chicago','IL','60620','United States',41.7540901,-87.6480355),('yash6','123','customer',1,'y6@gmail.com','yash6','m','03/04/1990',30,'painter','263248029','1031 N Riverwalk St,','12','chicago','IL','60610','United States',41.9003749,-87.645957),('yash7','123','customer',1,'y7@gmail.com','yash7','m','09/11/1995',25,'manager','324324989','614 N Paulina St','20','Chicago','IL','60622','United States',41.8929675,-87.6697129),('yash8','123','customer',1,'y8@gmail.com','yash8','m','09/11/1991',29,'professor','48324000','614 N Paulina St','01','Chicago','Illinois','60622','United States',41.8929675,-87.6697129),('yash9','123','customer',1,'y9@gmail.com','yash9','m','01/02/1987',33,'engineer','243089324','1500 S Kenneth Ave,','011','Chicago','Illinois','60622','United States',41.8604039,-87.7362491);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-05 16:31:38
