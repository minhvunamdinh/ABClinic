-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: db_hospital
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_active` int DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `role` int DEFAULT NULL,
  `is_working` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (4,'receptionist','$2a$10$K3y5QktkZDvN3uy6Nc1ydOm8gS2wkVWhyb6bhed5axtuxKtQJuDEC',1,'Receptionist','xxxxxx',NULL,1,3,0,1,'receptionist@gmail.com','0123456789'),(5,'doctor','$2a$10$ry8v.Wi5TvyP.SUR79uupuQRtZtaCrGiNux7XgwGGPNECPPvT8yEW',1,'Doctor','xxxxxx',NULL,1,2,0,1,'doctor@gmail.com','0123456789'),(6,'boss','$2a$10$Lbr3hdKUMO86VyeA7B5QHesITjCA01RVoL0vDEy75gm9DYkHALpxa',1,'Boss','xxxxxx',NULL,1,1,0,1,'boss@gmail.com','0123456789'),(7,'dohoang','$2a$10$7pAOK4OSuAPgzyWQZT262O4XqpodM6kWreiP0WNlVpX3NZBe0ryTi',1,'Đỗ Việt Hoàng','xxxxxx',NULL,1,2,0,1,'hoangdv@gmail.com','0123456789'),(8,'sang','$2a$10$Hs1gKEWPYLkbqqmkLZ9cIOMlPVm4FGYjqyx92I3P37GmbfQb6LNLu',1,'Bùi Minh Sáng','xxxxxx',NULL,1,2,0,1,'sang@gmail.com','0123456789'),(9,'minhvu','$2a$10$xTJ2L5jt8bqdUle/RWkSkOeroJXixP8bSpmQp5jLRSjsJeuOvox.W',0,'Minh Vũ2','xxxxxx123','2000-10-26',-1,3,NULL,NULL,'minhvu2@gmail.com','0976545452');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_working`
--

DROP TABLE IF EXISTS `clinic_working`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clinic_working` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `no` int DEFAULT NULL COMMENT 'so thu tu kham benh',
  `created_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_working`
--

LOCK TABLES `clinic_working` WRITE;
/*!40000 ALTER TABLE `clinic_working` DISABLE KEYS */;
INSERT INTO `clinic_working` VALUES (62,7,1,1,'2022-07-17',3),(63,7,2,1,'2022-07-18',3),(64,7,15,2,'2022-07-18',3),(65,7,18,1,'2022-07-20',3),(66,7,19,2,'2022-07-20',3),(67,7,1,1,'2022-07-23',3),(68,7,2,2,'2022-07-23',3),(69,7,20,1,'2022-07-25',3),(70,7,21,2,'2022-07-25',3),(71,7,17,1,'2022-07-26',3),(72,7,19,2,'2022-07-26',3),(73,7,22,3,'2022-07-26',3),(74,7,2,1,'2022-07-27',3),(75,5,1,2,'2022-07-27',3),(79,5,1,1,'2022-07-31',3),(80,5,2,2,'2022-07-31',3),(81,5,16,1,'2022-08-02',3),(82,5,17,2,'2022-08-02',3),(83,7,19,3,'2022-08-02',3),(84,7,22,4,'2022-08-02',3),(85,7,23,5,'2022-08-02',3),(86,7,24,6,'2022-08-02',3),(87,7,2,7,'2022-08-02',3),(88,7,20,8,'2022-08-02',3),(89,5,25,9,'2022-08-02',3),(90,5,26,10,'2022-08-02',3),(91,5,27,11,'2022-08-02',3),(92,5,28,12,'2022-08-02',3),(93,5,29,13,'2022-08-02',0),(94,7,2,1,'2022-08-04',3),(95,7,30,2,'2022-08-04',3),(96,7,31,3,'2022-08-04',3),(97,5,1,4,'2022-08-04',3),(98,7,23,1,'2022-08-09',3),(99,7,32,2,'2022-08-09',3),(100,7,18,1,'2022-08-10',2),(101,7,20,2,'2022-08-10',3),(102,7,23,3,'2022-08-10',1),(103,7,33,4,'2022-08-10',2),(104,7,25,1,'2022-08-14',3),(105,7,34,2,'2022-08-14',3),(106,7,35,3,'2022-08-14',3),(107,7,36,4,'2022-08-14',3),(108,7,36,5,'2022-08-14',3),(109,7,1,6,'2022-08-14',1);
/*!40000 ALTER TABLE `clinic_working` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Nguyễn Văn A','0123654798','1','Công chức','Cầu giấy','1999-06-16','Việt Nam','abcvdf@gmail.com','hehe','2022-07-26'),(2,'Trần Văn B','0123456789','-1','Nhân viên','Hoàng Maicvb','2022-08-06 00:00:00','Việt Nam1','zxczxczxc@gmail.com','bệnh hen','2022-07-26'),(15,'Trần Văn C','0123456789','-1','Nhân viên','Hoàng Maicvb','2022-08-06 00:00:00','Việt Nam','zxczxczxc@gmail.com','bệnh hen','2022-07-26'),(16,'Trần Văn D','0123456789','-1','Nhân viên','Hoàng Maicvb','2022-08-06 00:00:00','Việt Nam','zxczxczxc@gmail.com','bệnh hen','2022-07-27'),(17,'Do Viet Hoang','0944245859','1',' Sinh Vien FU','TTT','2000-05-05 00:00:00','','hoangdvhe140531@fpt.edu.vn','FF','2022-07-27'),(18,'Do Viet Hoang','0944245859','1',' Sinh Vien FU','TTT','2000-05-05 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aassd','2022-07-27'),(19,'Sinh Vien FPT','0944245859','0',' Sinh Viên FU','TTT','2000-07-07 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','sdsd','2022-07-28'),(20,'Gà456','0944245859','1',' Sinh Vien FU','TTT','2000-06-06 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','dab','2022-07-28'),(21,'Ga do','0944245859','-1',' Sinh Vien FU','TTT','2000-08-08 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','sd','2022-07-30'),(22,'Do Viet Hoang','0944245859','1',' Sinh Vien FU','TTT','2000-01-01 00:00:00','Vietnam','hoangdvhe140531@fpt','','2022-07-31'),(23,'Bui Minh Sang','0944249999','-1',' Sinh Vien FU','TTT','2000-08-15 00:00:00','Vietnam','sangbm@fpt.edu.vn','TTTT','2022-08-02'),(24,'Minh Vũ','0944245555','1',' Sinh Vien FU','Hồ Gươm','2000-05-10 00:00:00','Vietnam','vunamdinh@fpt.edu.vn','','2022-08-02'),(25,'HELOOTOO','0944445859','1',' Sinh Vien FU','TTT','2000-07-02 00:00:00','Vietnam','helo@fpt.edu.vn','sss','2022-08-02'),(26,'HELOOOOOO','0944245859','1',' Sinh Vien FU','TTT','2004-08-02 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','SAO','2022-08-02'),(27,'Do Viet Hoang 2','0944245859','1',' Sinh Vien FU','TTT','2009-06-19 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aaa','2022-08-02'),(28,'HJHJHJ','0944244444','1',' Sinh Vien FU','TTT','2009-08-07 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aaa','2022-08-02'),(29,'Ga do123','0944245859','1',' Sinh Vien FU','TTT','2008-08-02 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aaa','2022-08-02'),(30,'Đừng tin ai','0944245859','1',' Sinh Vien FU','TTT','2005-07-08 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aaaa','2022-08-04'),(31,'Tin tôi','0944245859','-1',' Sinh Vien FU2','TTT','2000-04-06 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','aaa','2022-08-04'),(32,'HEEHEE','0944245859','1',' Sinh Viên FU','TTT','2001-08-10 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','ssss','2022-08-09'),(33,'Trần Thị Thu Hà','0944245859','1',' Sinh Vien FU','TTT','2016-03-11 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','Rát họng','2022-08-10'),(34,'Nguyễn Bảo Hoàng','0967567656','1',' Sinh Vien FU','TTTTTTT','2003-06-10 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','dfgh','2022-08-14'),(35,'Trần Thị Thu Thảo','0944245859','0',' Sinh Vien FU','TTTTTT','2002-06-04 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','saJJJJ','2022-08-14'),(36,'HEN','0944245859','1',' Sinh Vien FU','TUTTI','2002-07-10 00:00:00','Vietnam','hoangdvhe140531@fpt.edu.vn','af','2022-08-14');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `lst_test` varchar(2000) DEFAULT NULL,
  `lst_medicine` varchar(2000) DEFAULT NULL,
  `time_return` varchar(255) DEFAULT NULL,
  `examination_card` varchar(255) DEFAULT NULL,
  `examination_fee` varchar(255) DEFAULT NULL,
  `total_cost_price` double DEFAULT NULL,
  `total_sell_price` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `income` double DEFAULT NULL,
  `clinic_working_id` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `test_result` varchar(2000) DEFAULT NULL,
  `diagnostic_result` varchar(2000) DEFAULT NULL,
  `lst_cost_price` varchar(2000) DEFAULT NULL,
  `lst_sell_price` varchar(2000) DEFAULT NULL,
  `is_discounted` int DEFAULT '0',
  `conclusion` text,
  `prescription` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (19,'BN_6450','Insulin,GOT/AST,Bilirubin trực tiếp,LDL-Cholesterol,Fe,Amylase,Vitamin B9,Estradiol,Cyfra 21-1,SCC,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,NULL,NULL,NULL,1720000,2100000,NULL,NULL,62,'2022-07-17',NULL,NULL,NULL,NULL,0,NULL,NULL),(20,'BN_2123','C-Peptide,Creatinin,GGT,ALP,Globumin,Triglyceride,Canxi toàn phần,Transferrin,Ferritin,Amylase,CRP hs,Cyfra 21-1,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***',NULL,NULL,NULL,NULL,1320000,1600000,NULL,NULL,63,'2022-07-18',NULL,NULL,NULL,NULL,0,NULL,NULL),(21,'BN_3025','Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,NULL,NULL,NULL,1320000,1600000,NULL,NULL,65,'2022-07-18',NULL,NULL,NULL,NULL,0,NULL,NULL),(22,'BN_2145','Insulin,GOT/AST,Bilirubin trực tiếp,LDL-Cholesterol,Fe,Amylase,Vitamin B9,Estradiol,Cyfra 21-1,SCC,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,NULL,NULL,NULL,1160000,1400000,NULL,NULL,64,'2022-07-20',NULL,NULL,NULL,NULL,0,NULL,NULL),(23,'BN_7249','Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,NULL,NULL,NULL,1480000,1800000,NULL,NULL,66,'2022-07-20',NULL,NULL,NULL,NULL,0,NULL,NULL),(24,'BN_6524','Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe',NULL,NULL,NULL,NULL,760000,900000,NULL,NULL,67,'2022-07-23',NULL,NULL,NULL,NULL,0,NULL,NULL),(25,'BN_549','HbA1C,C-Peptide,Nghiệm pháp tăng ĐM,Creatinin,Acid uric,GPT/ALT,GGT,LDH,ALP,Bilirubin gián tiếp,Globumin,Triglyceride,Canxi ion,Transferrin',NULL,NULL,NULL,NULL,1240000,1500000,NULL,NULL,68,'2022-07-23',NULL,NULL,NULL,NULL,0,NULL,NULL),(26,'BN_1665','Glucose,C-Peptide,Nghiệm pháp tăng ĐM,Creatinin,GPT/ALT,GGT,Protein toàn phần,Transferrin,Troponin T,CRP định lượng,Anti HCV***,HBsAg ***',NULL,NULL,NULL,NULL,1160000,1400000,NULL,NULL,69,'2022-07-25',NULL,NULL,NULL,NULL,0,NULL,NULL),(27,'BN_2633','Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,NULL,NULL,NULL,2280000,2800000,NULL,NULL,70,'2022-07-25',NULL,NULL,NULL,NULL,0,NULL,NULL),(28,'BN_5697','Acid uric,GGT,Bilirubin toàn phần,Globumin,Canxi ion,Transferrin,Ferritin,CK-total,Amylase,CRP hs,HBeAb md tự động***,HBsAg ***',NULL,NULL,NULL,NULL,1000000,1200000,NULL,NULL,71,'2022-07-26',NULL,NULL,NULL,NULL,0,NULL,NULL),(29,'BN_5370','Glucose,C-Peptide,Creatinin,GPT/ALT,GGT,Bilirubin toàn phần,Bilirubin gián tiếp,Protein toàn phần,Globumin,HDL-Cholesterol,Canxi ion,Canxi toàn phần,Transferrin,Ferritin,Amylase,CRP hs,CA 15-3,Cyfra 21-1,SCC,Máu lắng *,Sức bền thẩm thấu hồng cầu,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,NULL,NULL,NULL,1880000,2300000,NULL,NULL,72,'2022-07-26',NULL,NULL,NULL,NULL,0,NULL,NULL),(30,'BN_9486','C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,NULL,NULL,NULL,1480000,1800000,NULL,NULL,73,'2022-07-26',NULL,NULL,NULL,NULL,0,NULL,NULL),(31,'BN_9234','Nghiệm pháp tăng ĐM,Acid uric,GGT,Lypase,CRP hs,Vitamin B9,Máu lắng *,INR (ống đông máu)',NULL,NULL,NULL,NULL,840000,1000000,NULL,NULL,75,'2022-07-27',NULL,NULL,NULL,NULL,0,NULL,NULL),(32,'BN_3916','C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,NULL,NULL,NULL,1240000,1500000,NULL,NULL,74,'2022-07-27',NULL,NULL,NULL,NULL,0,NULL,NULL),(33,'BN_1709','Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cyfra 21-1,SCC,INR (ống đông máu),HBsAg ***',NULL,'2022-08-04 00:00:00',NULL,NULL,840000,1000000,NULL,NULL,79,'2022-07-31','Tốt','hhhh','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0',0,NULL,NULL),(34,'BN_7029','C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,'2022-08-05 00:00:00',NULL,NULL,1400000,1700000,NULL,NULL,80,'2022-07-31','ádas','áddd','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0',0,NULL,NULL),(35,'BN_8268','Ure,Acid uric,GGT,LDH,Bilirubin toàn phần,Protein toàn phần',NULL,'2022-08-03 00:00:00',NULL,NULL,680000,800000,NULL,NULL,81,'2022-08-02','Non cái hand','Gà','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(36,'BN_1096','GGT,Bilirubin toàn phần,Protein toàn phần,Globumin,Triglyceride',NULL,'2022-08-03 00:00:00',NULL,NULL,600000,700000,NULL,NULL,82,'2022-08-02','SAO','Đẹp trai','80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(37,'BN_8452','GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe,Lypase,RF,Albumin niệu',NULL,'2022-08-02 00:00:00',NULL,NULL,920000,1100000,NULL,NULL,83,'2022-08-02','Tốt','Đẹp trai','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(38,'BN_3595','Glucose,HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GOT/AST,GGT,ALP,Bilirubin trực tiếp,Globumin,Cholesterol,Triglyceride,Canxi ion,Fe,Transferrin,Ferritin,FSH,SCC,CEA,AFP,PSA Free',NULL,'2022-08-02 00:00:00',NULL,NULL,1960000,2400000,NULL,NULL,84,'2022-08-02','Chưa tốt','Handsome','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(39,'BN_5157','Glucose,HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GPT/ALT,GGT,LDH,ALP,Globumin,Cholesterol,Triglyceride,Canxi ion,Fe,Transferrin,Folate,Troponin T,Lypase,CRP định lượng,CRP hs,RF,Vitamin B9,CA 125,SCC,AFP,Công thức máu,Micro Albumin niệu',NULL,'2022-08-04 00:00:00',NULL,NULL,2360000,2900000,NULL,NULL,85,'2022-08-02','Dẹp tai','Nhà giàu','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0',1,NULL,NULL),(40,'BN_3505','HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GPT/ALT,GGT,LDH,Bilirubin trực tiếp,Cholesterol,Fe,Ferritin,Vitamin A,Cortisol 8h,FT3,SCC,CEA,AFP,PSA Total,PSA Free,Công thức máu,ELISA ấu trùng giun đũa',NULL,'2022-08-04 00:00:00',NULL,NULL,1880000,2300000,NULL,NULL,86,'2022-08-02','Học giỏi','Đẹp zai','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0',1,NULL,NULL),(41,'BN_6273','Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe,SCC,AFP,Micro Albumin niệu,Albumin niệu',NULL,'2022-08-06 00:00:00',NULL,NULL,1000000,1200000,NULL,NULL,89,'2022-08-02','OK','KKK','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0',1,NULL,NULL),(42,'BN_3740','Acid uric,GGT,LDH,Bilirubin toàn phần,Bilirubin trực tiếp,Protein toàn phần,Albumin,HDL-Cholesterol',NULL,'2022-08-04 00:00:00',NULL,NULL,680000,800000,NULL,NULL,87,'2022-08-02','AA','Toost','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,0',1,NULL,NULL),(43,'BN_3027','GGT,Protein toàn phần,Ferritin,Amylase,CRP hs,Vitamin B9',NULL,'2022-08-06 00:00:00',NULL,NULL,680000,800000,NULL,NULL,88,'2022-08-02','KKK','KLKLKL','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(44,'BN_1882','HbA1C,Acid uric,GGT,Globumin,Triglyceride,Canxi ion,Fe,Prolactin,Progesterol,TG,CA 15-3,ELISA ấu trùng giun đũa,Kí sinh trùng trong phân',NULL,'2022-08-08 00:00:00',NULL,NULL,2280000,2800000,NULL,NULL,90,'2022-08-02','Máu: 78\r\nKết luận','JJJ','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,0,0,80000.0,0,0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,0,0,100000.0,0,0',1,NULL,NULL),(45,'BN_8961','Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Globumin,HDL-Cholesterol,Canxi toàn phần,Ferritin,CK-total,Amylase,CRP hs,CA 72-4',NULL,'2022-08-10 00:00:00',NULL,NULL,920000,1100000,NULL,NULL,91,'2022-08-02','CON gà','Ốm','80000.0,80000.0,80000.0,80000.0,80000.0,0,0,80000.0,0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,0,0,100000.0,0,100000.0,100000.0,100000.0',1,NULL,NULL),(46,'BN_4492','GGT,Bilirubin toàn phần,Protein toàn phần,Cortisol 8h,PTH',NULL,NULL,NULL,NULL,600000,700000,NULL,NULL,92,'2022-08-02','aa','Tốt','80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(47,'BN_1877','Bilirubin toàn phần,Protein toàn phần,Troponin T,CRP định lượng,Vitamin D *,Estradiol,B HCG,PTH,TSH,CA 15-3',NULL,'2022-08-06 00:00:00',NULL,NULL,760000,900000,NULL,NULL,96,'2022-08-04','Gà','Gà','80000.0,80000.0,80000.0,80000.0,80000.0,0,0,80000.0,0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,0,0,100000.0,0,100000.0',1,NULL,NULL),(48,'BN_628','Protein toàn phần,Canxi toàn phần,Ferritin,RF,PIVKA ***,Máu lắng *',NULL,'2022-08-09 00:00:00',NULL,NULL,600000,700000,NULL,NULL,95,'2022-08-04','Gà','AAA','80000.0,0,80000.0,80000.0,80000.0,80000.0','100000.0,0,100000.0,100000.0,100000.0,100000.0',1,NULL,NULL),(49,'BN_6072','Bilirubin toàn phần,Protein toàn phần,Globumin,Triglyceride,Canxi ion,Transferrin,Ferritin,CK-total,Amylase,CRP hs,Vitamin B9,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,'2022-08-07 00:00:00',NULL,NULL,1240000,1500000,NULL,NULL,94,'2022-08-04','Mắt mờ','ÂSSS','80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0,80000.0,80000.0,80000.0,80000.0,0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0,100000.0,100000.0,100000.0,100000.0,0,100000.0',1,NULL,NULL),(50,'BN_1065','Amylase,CRP hs,Estradiol,CA 15-3',NULL,'2022-08-06 00:00:00',NULL,NULL,440000,500000,NULL,NULL,97,'2022-08-04','Tốt','jjjj','80000.0,80000.0,0,80000.0','100000.0,100000.0,0,100000.0',1,'N/A','N/A'),(51,'BN_2039','Nghiệm pháp tăng ĐM,Acid uric,Bilirubin toàn phần,Protein toàn phần,Amylase',NULL,'2022-08-11 00:00:00',NULL,NULL,600000,700000,NULL,NULL,99,'2022-08-09','KKKO','','80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0',1,'N/A','N/A'),(52,'BN_6449','Acid uric,GGT,Bilirubin toàn phần,LDL-Cholesterol,PTH',NULL,'2022-08-11 00:00:00',NULL,NULL,600000,700000,NULL,NULL,98,'2022-08-09','kkkkk','Gà','80000.0,80000.0,80000.0,80000.0,80000.0','100000.0,100000.0,100000.0,100000.0,100000.0',1,'N/A','N/A'),(53,'BN_8020','GGT,Protein toàn phần,HDL-Cholesterol,Vitamin B9',NULL,'2022-08-13 00:00:00',NULL,NULL,440000,500000,NULL,NULL,101,'2022-08-10','ĐAU HỌNG','HO LỚN','80000.0,80000.0,0,80000.0','100000.0,100000.0,0,100000.0',1,'N/A','N/A');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicine` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `is_active` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `test_name` varchar(255) DEFAULT NULL,
  `cost_price` double DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `test_type_id` int DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (6,'Glucose',80000,100000,2,1),(7,'HbA1C',80000,100000,2,1),(8,'Insulin',80000,100000,2,1),(9,'C-Peptide',80000,100000,2,1),(10,'Nghiệm pháp tăng ĐM',80000,100000,2,1),(11,'Ure',80000,100000,2,1),(12,'Creatinin',80000,100000,2,1),(13,'Acid uric',80000,100000,2,1),(14,'GOT/AST',80000,100000,2,1),(15,'GPT/ALT',80000,100000,2,1),(16,'GGT',80000,100000,2,1),(17,'LDH',80000,100000,2,1),(18,'ALP',80000,100000,2,1),(19,'Bilirubin toàn phần',80000,100000,2,1),(20,'Bilirubin trực tiếp',80000,100000,2,1),(21,'Bilirubin gián tiếp',NULL,NULL,2,1),(22,'Protein toàn phần',80000,100000,2,1),(23,'Albumin',NULL,NULL,2,1),(24,'Globumin',80000,100000,2,1),(25,'A/G',NULL,NULL,2,1),(26,'Cholesterol',80000,100000,2,1),(27,'Triglyceride',80000,100000,2,1),(28,'HDL-Cholesterol',NULL,NULL,2,1),(29,'LDL-Cholesterol',80000,100000,2,1),(30,'Canxi ion',80000,100000,2,1),(31,'Canxi toàn phần',NULL,NULL,2,1),(32,'Fe',80000,100000,2,1),(33,'Transferrin',80000,100000,2,1),(34,'Ferritin',80000,100000,2,1),(35,'Folate',NULL,NULL,2,1),(36,'Điện giải đồ (Na, K, Cl)',80000,100000,2,1),(37,'CK-total',NULL,NULL,2,1),(38,'CK-MB',NULL,NULL,2,1),(39,'Troponin T',80000,100000,2,1),(40,'Amylase',80000,100000,2,1),(41,'Lypase',80000,100000,2,1),(42,'CRP định lượng',80000,100000,2,1),(43,'CRP hs',80000,100000,2,1),(44,'RF',80000,100000,2,1),(45,'Vitamin D *',80000,100000,2,1),(46,'Vitamin B9',80000,100000,2,1),(47,'Vitamin B12 ***',80000,100000,2,1),(48,'Vitamin A',80000,100000,2,1),(49,'FSH',NULL,NULL,2,1),(50,'LH',NULL,NULL,2,1),(51,'Prolactin',NULL,NULL,2,1),(52,'Estradiol',NULL,NULL,2,1),(53,'Testosterone',NULL,NULL,2,1),(54,'Progesterol',NULL,NULL,2,1),(55,'B HCG',NULL,NULL,2,1),(56,'Cortisol 8h',80000,100000,2,1),(57,'ACTH *',80000,100000,2,1),(58,'PTH',80000,100000,2,1),(59,'FT3',NULL,NULL,2,1),(60,'FT4',NULL,NULL,2,1),(61,'TSH',NULL,NULL,2,1),(62,'TRAb ***',NULL,NULL,2,1),(63,'Anti TPO',NULL,NULL,2,1),(64,'TG',NULL,NULL,2,1),(65,'Anti TG *',NULL,NULL,2,1),(66,'CA 125',80000,100000,2,1),(67,'CA 15-3',80000,100000,2,1),(68,'CA 72-4',80000,100000,2,1),(69,'CA 19-9',80000,100000,2,1),(70,'Cyfra 21-1',80000,100000,2,1),(71,'PIVKA ***',80000,100000,2,1),(72,'Pepsinogen (2 ống đỏ)',80000,100000,2,1),(73,'SCC',80000,100000,2,1),(74,'NSE ***',80000,100000,2,1),(75,'CEA',80000,100000,2,1),(76,'AFP',80000,100000,2,1),(77,'PSA Total',80000,140000,2,1),(78,'PSA Free',80000,120000,2,1),(79,'Công thức máu',80000,100000,1,1),(80,'Máu lắng *',80000,100000,1,1),(81,'Đông máu CB (ố đông máu)',80000,230000,1,1),(82,'Sức bền thẩm thấu hồng cầu',NULL,NULL,1,1),(83,'INR (ống đông máu)',80000,110000,1,1),(84,'Anti HBs (HbsAb định lượng)',NULL,NULL,3,1),(85,'Anti HCV***',80000,100000,3,1),(86,'HAV IgM***',80000,100000,3,1),(87,'HBeAg md tự động***',NULL,NULL,3,1),(88,'HBeAb md tự động***',NULL,NULL,3,1),(89,'HBeAg định lượng***',NULL,NULL,3,1),(90,'HIV ***',80000,100000,3,1),(91,'HBsAg ***',80000,100000,3,1),(92,'HBV-DNA',80000,150000,3,1),(93,'ELISA ấu trùng giun đũa',NULL,NULL,3,1),(94,'ELISA giun luồn ruột',NULL,NULL,3,1),(95,'ELISA ấu trùng giun đầu gai',NULL,NULL,3,1),(96,'Kí sinh trùng trong phân',NULL,NULL,4,1),(97,'Tìm HC, BC trong phân',NULL,NULL,4,1),(98,'Trứng giun, sán soi tươi',NULL,NULL,4,1),(99,'Tổng phân tích nước tiểu',NULL,NULL,5,1),(100,'Micro Albumin niệu',NULL,NULL,5,1),(101,'Albumin niệu',90000,120000,5,1),(102,'KLI',50000,145000,2,1);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_result`
--

DROP TABLE IF EXISTS `test_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_result` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `clinic_working_id` int DEFAULT NULL,
  `test_result` varchar(255) DEFAULT NULL,
  `lst_medicine` varchar(2000) DEFAULT NULL,
  `lst_test` varchar(2000) DEFAULT NULL,
  `examination_card` varchar(255) DEFAULT NULL,
  `examination_fee` varchar(255) DEFAULT NULL,
  `time_return` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `diagnostic_result` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `total_cost_price` double DEFAULT NULL,
  `total_sell_price` double DEFAULT NULL,
  `is_called` int DEFAULT NULL,
  `conclusion` text,
  `prescription` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_result`
--

LOCK TABLES `test_result` WRITE;
/*!40000 ALTER TABLE `test_result` DISABLE KEYS */;
INSERT INTO `test_result` VALUES (39,'BN_6450',62,'asdsd',NULL,'Insulin,GOT/AST,Bilirubin trực tiếp,LDL-Cholesterol,Fe,Amylase,Vitamin B9,Estradiol,Cyfra 21-1,SCC,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,'1900000.0','2022-07-25',1,'','2022-07-17',1520000,1900000,0,NULL,NULL),(40,'BN_2123',63,'aasd',NULL,'C-Peptide,Creatinin,GGT,ALP,Globumin,Triglyceride,Canxi toàn phần,Transferrin,Ferritin,Amylase,CRP hs,Cyfra 21-1,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***',NULL,'1400000.0','2022-07-25',1,'','2022-07-18',1120000,1400000,0,NULL,NULL),(41,'BN_2145',64,'aaaa',NULL,'Insulin,GOT/AST,Bilirubin trực tiếp,LDL-Cholesterol,Fe,Amylase,Vitamin B9,Estradiol,Cyfra 21-1,SCC,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,'1200000.0','2022-07-25',1,'D','2022-07-18',960000,1200000,0,NULL,NULL),(42,'BN_3025',65,'asaaa',NULL,'Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,'1400000.0','2022-07-31',1,'sadas','2022-07-20',1120000,1400000,0,NULL,NULL),(43,'BN_7249',66,'2022',NULL,'Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,'1600000.0','2022-07-31',1,'asdsd','2022-07-20',1280000,1600000,0,NULL,NULL),(44,'BN_6524',67,'asds',NULL,'Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe',NULL,'700000.0','2022-08-01',1,'adsad','2022-07-23',560000,700000,0,NULL,NULL),(45,'BN_549',68,'asd',NULL,'HbA1C,C-Peptide,Nghiệm pháp tăng ĐM,Creatinin,Acid uric,GPT/ALT,GGT,LDH,ALP,Bilirubin gián tiếp,Globumin,Triglyceride,Canxi ion,Transferrin',NULL,'1300000.0','2022-08-01',1,'asd','2022-07-23',1040000,1300000,0,NULL,NULL),(46,'BN_1665',69,'asd',NULL,'Glucose,C-Peptide,Nghiệm pháp tăng ĐM,Creatinin,GPT/ALT,GGT,Protein toàn phần,Transferrin,Troponin T,CRP định lượng,Anti HCV***,HBsAg ***',NULL,'1200000.0','2022-08-01',1,'asd','2022-07-25',960000,1200000,0,NULL,NULL),(47,'BN_2633',70,'asd',NULL,'Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,HDL-Cholesterol,Ferritin,CK-total,Vitamin B9,CA 15-3,Cyfra 21-1,SCC,AFP,Máu lắng *,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,'2600000.0','2022-08-01',1,'asd','2022-07-25',2080000,2600000,0,NULL,NULL),(48,'BN_5697',71,'asd',NULL,'Acid uric,GGT,Bilirubin toàn phần,Globumin,Canxi ion,Transferrin,Ferritin,CK-total,Amylase,CRP hs,HBeAb md tự động***,HBsAg ***',NULL,'1000000.0','2022-08-01',1,'asdsd','2022-07-26',800000,1000000,0,NULL,NULL),(49,'BN_5370',72,'Binh Thuong',NULL,'Glucose,C-Peptide,Creatinin,GPT/ALT,GGT,Bilirubin toàn phần,Bilirubin gián tiếp,Protein toàn phần,Globumin,HDL-Cholesterol,Canxi ion,Canxi toàn phần,Transferrin,Ferritin,Amylase,CRP hs,CA 15-3,Cyfra 21-1,SCC,Máu lắng *,Sức bền thẩm thấu hồng cầu,INR (ống đông máu),Anti HCV***,HBeAb md tự động***,HBsAg ***,ELISA giun luồn ruột,Albumin niệu',NULL,'2100000.0','2022-08-01',1,'Test','2022-07-26',1680000,2100000,0,NULL,NULL),(50,'BN_9486',73,'rách cơ',NULL,'C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,'1600000.0','2022-08-08',1,'GGG','2022-07-26',1280000,1600000,0,NULL,NULL),(51,'BN_9234',75,'Good',NULL,'Nghiệm pháp tăng ĐM,Acid uric,GGT,Lypase,CRP hs,Vitamin B9,Máu lắng *,INR (ống đông máu)',NULL,'800000.0','2022-08-01',1,'ádasd','2022-07-27',640000,800000,0,NULL,NULL),(52,'BN_3916',74,'aaa',NULL,'C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,'1300000.0','2022-08-01',1,'ádasd','2022-07-27',1040000,1300000,0,NULL,NULL),(55,'BN_1709',79,'Tốt',NULL,'Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cyfra 21-1,SCC,INR (ống đông máu),HBsAg ***',NULL,'800000.0','2022-08-04',1,'hhhh','2022-07-31',640000,800000,1,NULL,NULL),(56,'BN_7029',80,'ádas',NULL,'C-Peptide,Creatinin,GPT/ALT,ALP,Globumin,Triglyceride,Transferrin,Ferritin,Troponin T,Amylase,CRP định lượng,CA 72-4,PIVKA ***,NSE ***,Anti HCV***,HBsAg ***,ELISA giun luồn ruột',NULL,'1500000.0','2022-08-05',1,'áddd','2022-07-31',1200000,1500000,0,NULL,NULL),(57,'BN_8268',81,'Non cái hand',NULL,'Ure,Acid uric,GGT,LDH,Bilirubin toàn phần,Protein toàn phần',NULL,'600000.0','2022-08-03',1,'Gà','2022-08-02',480000,600000,0,NULL,NULL),(58,'BN_1096',82,'SAO',NULL,'GGT,Bilirubin toàn phần,Protein toàn phần,Globumin,Triglyceride',NULL,'500000.0','2022-08-03',1,'Đẹp trai','2022-08-02',400000,500000,0,NULL,NULL),(59,'BN_8452',83,'Tốt',NULL,'GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe,Lypase,RF,Albumin niệu',NULL,'900000.0','2022-08-02',1,'Đẹp trai','2022-08-02',720000,900000,0,NULL,NULL),(60,'BN_3595',84,'Chưa tốt',NULL,'Glucose,HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GOT/AST,GGT,ALP,Bilirubin trực tiếp,Globumin,Cholesterol,Triglyceride,Canxi ion,Fe,Transferrin,Ferritin,FSH,SCC,CEA,AFP,PSA Free',NULL,'2200000.0','2022-08-02',1,'Handsome','2022-08-02',1760000,2200000,0,NULL,NULL),(61,'BN_5157',85,'Dẹp tai',NULL,'Glucose,HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GPT/ALT,GGT,LDH,ALP,Globumin,Cholesterol,Triglyceride,Canxi ion,Fe,Transferrin,Folate,Troponin T,Lypase,CRP định lượng,CRP hs,RF,Vitamin B9,CA 125,SCC,AFP,Công thức máu,Micro Albumin niệu',NULL,'2700000.0','2022-08-04',1,'Nhà giàu','2022-08-02',2160000,2700000,0,NULL,NULL),(62,'BN_3505',86,'Học giỏi',NULL,'HbA1C,Insulin,C-Peptide,Ure,Creatinin,Acid uric,GPT/ALT,GGT,LDH,Bilirubin trực tiếp,Cholesterol,Fe,Ferritin,Vitamin A,Cortisol 8h,FT3,SCC,CEA,AFP,PSA Total,PSA Free,Công thức máu,ELISA ấu trùng giun đũa',NULL,'2100000.0','2022-08-04',1,'Đẹp zai','2022-08-02',1680000,2100000,0,NULL,NULL),(63,'BN_3740',87,'AA',NULL,'Acid uric,GGT,LDH,Bilirubin toàn phần,Bilirubin trực tiếp,Protein toàn phần,Albumin,HDL-Cholesterol',NULL,'600000.0','2022-08-04',1,'Toost','2022-08-02',480000,600000,0,NULL,NULL),(64,'BN_6273',89,'OK',NULL,'Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Cholesterol,LDL-Cholesterol,Fe,SCC,AFP,Micro Albumin niệu,Albumin niệu',NULL,'1000000.0','2022-08-06',1,'KKK','2022-08-02',800000,1000000,0,NULL,NULL),(65,'BN_3027',88,'KKK',NULL,'GGT,Protein toàn phần,Ferritin,Amylase,CRP hs,Vitamin B9',NULL,'600000.0','2022-08-06',1,'KLKLKL','2022-08-02',480000,600000,0,NULL,NULL),(66,'BN_1882',90,'Máu: 78\r\nKết luận',NULL,'HbA1C,Acid uric,GGT,Globumin,Triglyceride,Canxi ion,Fe,Prolactin,Progesterol,TG,CA 15-3,ELISA ấu trùng giun đũa,Kí sinh trùng trong phân',NULL,'2600000.0','2022-08-08',1,'JJJ','2022-08-02',2080000,2600000,0,NULL,NULL),(67,'BN_8961',91,'CON gà',NULL,'Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Globumin,HDL-Cholesterol,Canxi toàn phần,Ferritin,CK-total,Amylase,CRP hs,CA 72-4',NULL,'900000.0','2022-08-10',1,'Ốm','2022-08-02',720000,900000,1,NULL,NULL),(68,'BN_4492',92,'aa',NULL,'GGT,Bilirubin toàn phần,Protein toàn phần,Cortisol 8h,PTH',NULL,'500000.0',NULL,1,'Tốt','2022-08-02',400000,500000,0,NULL,NULL),(69,'BN_1877',96,'Gà',NULL,'Bilirubin toàn phần,Protein toàn phần,Troponin T,CRP định lượng,Vitamin D *,Estradiol,B HCG,PTH,TSH,CA 15-3',NULL,'700000.0','2022-08-06',1,'Gà','2022-08-04',560000,700000,0,NULL,NULL),(70,'BN_628',95,'Gà',NULL,'Protein toàn phần,Canxi toàn phần,Ferritin,RF,PIVKA ***,Máu lắng *',NULL,'500000.0','2022-08-09',1,'AAA','2022-08-04',400000,500000,0,NULL,NULL),(71,'BN_6072',94,'Mắt mờ',NULL,'Bilirubin toàn phần,Protein toàn phần,Globumin,Triglyceride,Canxi ion,Transferrin,Ferritin,CK-total,Amylase,CRP hs,Vitamin B9,Máu lắng *,INR (ống đông máu),HBeAb md tự động***,HBsAg ***',NULL,'1300000.0','2022-08-07',1,'ÂSSS','2022-08-04',1040000,1300000,0,NULL,NULL),(72,'BN_1065',97,'Tốt',NULL,'Amylase,CRP hs,Estradiol,CA 15-3',NULL,'300000.0','2022-08-06',1,'jjjj','2022-08-04',240000,300000,0,NULL,NULL),(73,'BN_2039',99,'KKKO',NULL,'Nghiệm pháp tăng ĐM,Acid uric,Bilirubin toàn phần,Protein toàn phần,Amylase',NULL,'500000.0','2022-08-11',1,'','2022-08-09',400000,500000,0,NULL,NULL),(74,'BN_6449',98,'kkkkk',NULL,'Acid uric,GGT,Bilirubin toàn phần,LDL-Cholesterol,PTH',NULL,'500000.0','2022-08-11',1,'Gà','2022-08-09',400000,500000,0,NULL,NULL),(76,'BN_8020',101,'ĐAU HỌNG',NULL,'GGT,Protein toàn phần,HDL-Cholesterol,Vitamin B9',NULL,'300000.0','2022-08-13',1,'HO LỚN','2022-08-10',240000,300000,0,NULL,NULL),(77,'BN_55',103,'N/A',NULL,'Nghiệm pháp tăng ĐM,Acid uric,GGT,Bilirubin toàn phần,Protein toàn phần,Canxi toàn phần,Ferritin',NULL,'600000.0',NULL,1,'GHI KẾT Quả Chuẩn đoán','2022-08-10',480000,600000,0,NULL,NULL);
/*!40000 ALTER TABLE `test_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_type`
--

DROP TABLE IF EXISTS `test_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_type` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  `status` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_type`
--

LOCK TABLES `test_type` WRITE;
/*!40000 ALTER TABLE `test_type` DISABLE KEYS */;
INSERT INTO `test_type` VALUES (1,'XN máu',1),(2,'XN hóa sinh',1),(3,'XN vi sinh',1),(4,'XN phân',1),(5,'XN nước tiểu',1),(6,'XN Tổng Thể',NULL);
/*!40000 ALTER TABLE `test_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-14 22:29:30
