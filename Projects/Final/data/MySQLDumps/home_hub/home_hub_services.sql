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
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `service_provider_id` varchar(40) DEFAULT NULL,
  `service_name` varchar(40) DEFAULT NULL,
  `service_category_super` varchar(40) DEFAULT NULL,
  `service_category_sub` varchar(40) DEFAULT NULL,
  `service_images` varchar(150) DEFAULT NULL,
  `service_description` varchar(200) DEFAULT NULL,
  `service_rate` double DEFAULT NULL,
  `service_status` varchar(10) DEFAULT NULL,
  `service_admin_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (4,'deepak1','service 1','cleaning','home sanitization','cleaning_home sanitization_2.jpg;cleaning_home sanitization_3.jpg;cleaning_home sanitization_4.jpg','description 1',23,'active','accepted'),(5,'deepak1','service 2','cleaning','kitchen','cleaning_kitchen_2.jpg;cleaning_kitchen_4.jpg;cleaning_kitchen_3.jpg;cleaning_kitchen_3.jpg','description 2',12,'active','accepted'),(6,'deepak2','service 3','cleaning','bedroom','cleaning_bedroom_0.jpg;cleaning_bedroom_1.jpg','des 3',23,'active','accepted'),(7,'deepak2','service 4','cleaning','bathroom','cleaning_bathroom_3.jpg;cleaning_bathroom_3.jpg','des 4',34,'active','accepted'),(8,'deepak3','service 5','cleaning','living room','cleaning_living room_2.jpg;cleaning_living room_1.jpg','des 5',10,'active','accepted'),(9,'deepak3','service 6','cleaning','workplace','cleaning_workplace_0.jpg;cleaning_workplace_0.jpg;cleaning_workplace_4.jpg;cleaning_workplace_4.jpg','des 6',20,'active','accepted'),(10,'deepak4','service 7','plumbing','drain repair','plumbing_drain repair_4.jpg;plumbing_drain repair_3.jpg','des 7',22,'active','accepted'),(11,'deepak4','service 8','plumbing','sink replace','plumbing_sink replace_4.jpg;plumbing_sink replace_4.jpg;plumbing_sink replace_4.jpg;plumbing_sink replace_0.jpg','des 8',23,'active','accepted'),(12,'deepak5','service 9','plumbing','toilet repair','plumbing_toilet repair_1.jpg;plumbing_toilet repair_2.jpg;plumbing_toilet repair_3.jpg','des 9',23,'active','accepted'),(13,'deepak5','service 10','plumbing','plumbing service','plumbing_plumbing service_0.jpg','des 10',25,'active','accepted'),(14,'deepak6','service 11','pest control','cockroach','pest control_cockroach_2.jpg;pest control_cockroach_0.jpg','des 11',26,'active','accepted'),(15,'deepak6','service 12','pest control','bed bugs','pest control_bed bugs_0.jpg;pest control_bed bugs_3.jpg;pest control_bed bugs_1.jpg','des 12',27,'active','accepted'),(16,'deepak7','service 13','pest control','ants','pest control_ants_0.jpg;pest control_ants_2.jpg','des 13',25,'active','accepted'),(17,'deepak7','service 14','pest control','termite','pest control_termite_3.jpg;pest control_termite_2.jpg','des 14',23,'active','accepted'),(18,'deepak8','service 15','pest control','mosquito','','des 15',32,'active','accepted'),(19,'deepak8','service 16','moving','packers and movers','moving_packers and movers_4.jpg;moving_packers and movers_3.jpg','des 16',32,'active','accepted'),(20,'deepak9','service 17','electrics','new wiring','','des 17',32,'active','accepted'),(21,'deepak9','service 18','electrics','break down','electrics_break down_0.jpg;electrics_break down_2.jpg;electrics_break down_0.jpg;electrics_break down_1.jpg','des 18',23,'active','accepted'),(22,'deepak10','service 19','electrics','shot circuit','electrics_shot circuit_3.jpg;electrics_shot circuit_4.jpg;electrics_shot circuit_2.jpg','des 19',24,'active','accepted'),(23,'deepak10','service 20','electrics','repair or fixes','electrics_repair or fixes_3.jpg;electrics_repair or fixes_2.jpg;electrics_repair or fixes_0.jpg','des 20',32,'active','accepted'),(24,'deepak11','service 21','carpenter','furniture repair','carpenter_furniture repair_0.jpg;carpenter_furniture repair_1.jpg;carpenter_furniture repair_3.jpg;carpenter_furniture repair_0.jpg','des 21',22,'active','accepted'),(25,'deepak11','service 22','carpenter','furniture assemble','carpenter_furniture assemble_0.jpg;carpenter_furniture assemble_3.jpg;carpenter_furniture assemble_0.jpg;carpenter_furniture assemble_0.jpg','des 22',34,'active','accepted'),(26,'deepak12','service 23','carpenter','wood work','carpenter_wood work_3.jpg;carpenter_wood work_1.jpg;carpenter_wood work_0.jpg;carpenter_wood work_1.jpg','des 23',32,'active','accepted'),(27,'deepak12','service 24','carpenter','wood polishing','carpenter_wood polishing_4.jpg;carpenter_wood polishing_4.jpg;carpenter_wood polishing_0.jpg','des 24',30,'active','accepted'),(28,'deepak13','service 25','carpenter','customize furniture','carpenter_customize furniture_1.jpg;carpenter_customize furniture_3.jpg;carpenter_customize furniture_1.jpg','des 25',23,'active','accepted'),(29,'deepak13','service 26','painting','wall painting','painting_wall painting_4.jpg;painting_wall painting_2.jpg;painting_wall painting_4.jpg;painting_wall painting_1.jpg','des 26',34,'active','accepted'),(30,'deepak14','service 27','painting','furniture painting','','des 27',34,'active','accepted'),(31,'deepak14','service 28','appliances','air condition','','des 28',35,'active','accepted'),(32,'deepak15','service 29','appliances','microwave','appliances_microwave_3.jpg;appliances_microwave_4.jpg','des 20',30,'active','accepted'),(33,'deepak15','service 30','appliances','refrigerator','appliances_refrigerator_1.jpg;appliances_refrigerator_0.jpg;appliances_refrigerator_3.jpg','des 30',27,'active','accepted'),(34,'deepak16','service 31','appliances','washing machine','appliances_washing machine_0.jpg;appliances_washing machine_3.jpg;appliances_washing machine_3.jpg;appliances_washing machine_1.jpg','des 31',28,'active','accepted'),(35,'deepak16','service 32','appliances','water purifier','appliances_water purifier_3.jpg;appliances_water purifier_3.jpg;appliances_water purifier_3.jpg','des 32',18,'inactive','accepted'),(36,'deepak17','service 33','appliances','room heater','appliances_room heater_2.jpg','des 33',15,'inactive','rejected'),(37,'deepak17','service 34','appliances','television','appliances_television_1.jpg;appliances_television_3.jpg','des 34',14,'inactive','rejected'),(38,'deepak18','service 35','appliances','chimney','','des 35',20,'active','accepted'),(39,'deepak18','service 36','cleaning','home sanitization','','des 36',28,'active','accepted'),(40,'deepak19','service 37','cleaning','kitchen','cleaning_kitchen_1.jpg;cleaning_kitchen_0.jpg','des 37',27,'active','accepted'),(41,'deepak19','service 38','cleaning','bedroom','cleaning_bedroom_1.jpg;cleaning_bedroom_2.jpg','des 38',35,'active','accepted'),(42,'deepak20','service 39','cleaning','bathroom','cleaning_bathroom_0.jpg;cleaning_bathroom_2.jpg;cleaning_bathroom_4.jpg;cleaning_bathroom_2.jpg','des 39',29,'active','accepted'),(43,'deepak20','service 40','cleaning','living room','','des 40',29,'active','accepted'),(44,'deepak21','service 41','cleaning','workplace','cleaning_workplace_1.jpg','des 41',34,'active','accepted'),(45,'deepak21','service 42','plumbing','drain repair','','des 42',36,'active','accepted'),(46,'deepak22','service 43','plumbing','sink replace','plumbing_sink replace_0.jpg;plumbing_sink replace_4.jpg;plumbing_sink replace_1.jpg','des 43',25,'active','accepted'),(47,'deepak22','service 44','plumbing','toilet repair','','des 44',25,'active','accepted'),(48,'deepak23','service 45','plumbing','plumbing service','plumbing_plumbing service_3.jpg;plumbing_plumbing service_2.jpg','des 45',18,'active','accepted'),(49,'deepak23','service 46','pest control','cockroach','pest control_cockroach_1.jpg;pest control_cockroach_1.jpg;pest control_cockroach_2.jpg','des 46',35,'active','accepted'),(50,'deepak24','service 47','pest control','bed bugs','pest control_bed bugs_4.jpg','des 47',25,'active','accepted'),(51,'deepak24','service 48','pest control','ants','pest control_ants_4.jpg;pest control_ants_3.jpg;pest control_ants_2.jpg;pest control_ants_1.jpg','des 48',34,'active','accepted'),(52,'deepak25','service 49','pest control','termite','','des 49',22,'active','accepted'),(53,'deepak25','service 50','pest control','mosquito','pest control_mosquito_4.jpg;pest control_mosquito_3.jpg;pest control_mosquito_4.jpg;pest control_mosquito_1.jpg','des 50',35,'active','accepted'),(54,'deepak26','service 51','moving','packers and movers','moving_packers and movers_4.jpg;moving_packers and movers_4.jpg;moving_packers and movers_0.jpg;moving_packers and movers_3.jpg','des 51',30,'active','accepted'),(55,'deepak26','service 52','electrics','new wiring','electrics_new wiring_2.jpg;electrics_new wiring_4.jpg','des 52',32,'inactive','rejected'),(56,'deepak27','service 53','electrics','break down','','des 53',22,'active','accepted'),(57,'deepak27','service 54','electrics','shot circuit','electrics_shot circuit_2.jpg;electrics_shot circuit_2.jpg','des 54',25,'active','accepted'),(58,'deepak28','service 55','electrics','repair or fixes','','des 55',35,'active','accepted'),(59,'deepak28','service 56','carpenter','furniture repair','','des 56',26,'active','accepted'),(60,'deepak29','service 57','carpenter','furniture assemble','carpenter_furniture assemble_0.jpg;carpenter_furniture assemble_0.jpg;carpenter_furniture assemble_0.jpg;carpenter_furniture assemble_2.jpg','des 57',33,'inactive','pending'),(61,'deepak29','service 58','carpenter','wood work','carpenter_wood work_4.jpg;carpenter_wood work_3.jpg;carpenter_wood work_4.jpg;carpenter_wood work_3.jpg','des 58',26,'inactive','pending'),(62,'deepak30','service 59','carpenter','wood polishing','','des 59',12,'inactive','pending'),(63,'deepak30','service 60','carpenter','customize furniture','carpenter_customize furniture_4.jpg;carpenter_customize furniture_3.jpg;carpenter_customize furniture_0.jpg;carpenter_customize furniture_3.jpg','des 60',22,'inactive','pending'),(64,'deepak32','service 90','cleaning','bathroom','cleaning_bathroom_3.jpg;cleaning_bathroom_4.jpg','des 90',25,'active','accepted'),(65,'deepak32','service 1000','cleaning','bedroom','cleaning_bedroom_2.jpg;cleaning_bedroom_1.jpg','des 2000',45,'inactive','rejected');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
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
