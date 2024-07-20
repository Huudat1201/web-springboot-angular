-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: tlcn
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createday` varchar(255) DEFAULT NULL,
  `deleteday` varchar(255) DEFAULT NULL,
  `namesearch` varchar(255) DEFAULT NULL,
  `personcreate` int(11) NOT NULL,
  `persondelete` int(11) NOT NULL,
  `personupdate` int(11) NOT NULL,
  `updateday` varchar(255) DEFAULT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (2,'2023-10-28 20:53:16.107',NULL,'Iphone',3,0,1,'2023-10-28 20:53:16.107','apple.png','iPhone là dòng điện thoại thông minh do Apple Inc. thiết kế, phát triển và bán. iPhone là một trong những dòng điện thoại thông minh phổ biến nhất trên thế giới, với hơn 2,2 tỷ chiếc đã được bán ra tính đến tháng 9 năm 2023.','apple.png','Apple Iphone'),(3,'2023-10-28 20:53:16.107',NULL,'Samsung',3,0,1,'2023-10-28 20:47:16.738',NULL,'Điện thoại Samsung giá ngon bổ rẻ',NULL,'Samsung'),(4,'2023-10-28 20:53:16.107',NULL,'Xiaomi',3,0,1,'2023-10-28 20:55:38.959',NULL,'Xiaomi là một tập đoàn công nghệ của Trung Quốc, chuyên thiết kế, phát triển và bán các sản phẩm điện tử tiêu dùng chất lượng cao, giá cả phải chăng. Xiaomi là một trong những công ty điện tử tiêu dùng lớn nhất thế giới.',NULL,'Xiaomi'),(5,'2023-10-28 20:53:16.107','2023-12-15 18:07:33.25','Oppo',3,1,1,'2023-10-28 20:58:43.712',NULL,'OPPO là một công ty điện tử tiêu dùng lớn của Trung Quốc, chuyên thiết kế, phát triển và bán các sản phẩm điện tử tiêu dùng chất lượng cao, giá cả phải chăng. OPPO nổi tiếng với các sản phẩm điện thoại thông minh chất lượng cao, camera và hiệu năng.',NULL,'Oppo Smartphone'),(6,'2023-10-28 20:53:16.107',NULL,'Vivo Smartphone',3,0,1,'2023-10-28 21:00:59.748',NULL,'Vivo là một trong những thương hiệu điện thoại thông minh hàng đầu tại Việt Nam. Các điện thoại thông minh của Vivo được người tiêu dùng Việt Nam ưa chuộng nhờ thiết kế thời trang, camera chất lượng và giá cả phải chăng.',NULL,'Vivo Smartphone'),(8,'2023-10-28 20:53:16.107',NULL,'Google pixel',3,0,1,'2023-10-28 21:08:20.255',NULL,'Dòng sản phẩm này được giới thiệu lần đầu tiên vào năm 2013 với Pixel và Pixel XL. Kể từ đó, Google đã phát hành nhiều phiên bản Pixel khác nhau, bao gồm Pixel 2, Pixel 3, Pixel 4, Pixel 5, Pixel 6 và Pixel 7.',NULL,'Google Pixel'),(9,'2023-10-28 20:53:16.107',NULL,'Sam-pham-khac',3,0,1,'2023-10-28 21:09:34.661',NULL,'Huawei, Sony, LG, Gaming Phone...',NULL,'Sản phẩm khác');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-30  9:01:41
