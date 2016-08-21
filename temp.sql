-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: tireshop
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cashtransfer`
--

DROP TABLE IF EXISTS `cashtransfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cashtransfer` (
  `CTID` bigint(20) NOT NULL,
  `AMOUNT` double DEFAULT NULL,
  `CASHTRANSFERTYPE` int(11) DEFAULT NULL,
  `DATEADDED` date DEFAULT NULL,
  `TIMEADDED` time DEFAULT NULL,
  `LOGSESSION_SEID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CTID`),
  KEY `FK_CASHTRANSFER_LOGSESSION_SEID` (`LOGSESSION_SEID`),
  CONSTRAINT `FK_CASHTRANSFER_LOGSESSION_SEID` FOREIGN KEY (`LOGSESSION_SEID`) REFERENCES `logsession` (`SEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashtransfer`
--

LOCK TABLES `cashtransfer` WRITE;
/*!40000 ALTER TABLE `cashtransfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `cashtransfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `construction`
--

DROP TABLE IF EXISTS `construction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `construction` (
  `CID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `construction`
--

LOCK TABLES `construction` WRITE;
/*!40000 ALTER TABLE `construction` DISABLE KEYS */;
INSERT INTO `construction` VALUES (1,'Radial');
/*!40000 ALTER TABLE `construction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `IID` bigint(20) NOT NULL,
  `CATEGORY` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TUBETYPE` varchar(255) DEFAULT NULL,
  `CONSTRUCTION_CID` bigint(20) DEFAULT NULL,
  `MANUFACTURER_MANUID` bigint(20) DEFAULT NULL,
  `VEHICLETYPE_VID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IID`),
  KEY `FK_ITEM_MANUFACTURER_MANUID` (`MANUFACTURER_MANUID`),
  KEY `FK_ITEM_CONSTRUCTION_CID` (`CONSTRUCTION_CID`),
  KEY `FK_ITEM_VEHICLETYPE_VID` (`VEHICLETYPE_VID`),
  CONSTRAINT `FK_ITEM_CONSTRUCTION_CID` FOREIGN KEY (`CONSTRUCTION_CID`) REFERENCES `construction` (`CID`),
  CONSTRAINT `FK_ITEM_MANUFACTURER_MANUID` FOREIGN KEY (`MANUFACTURER_MANUID`) REFERENCES `manufacturer` (`MANUID`),
  CONSTRAINT `FK_ITEM_VEHICLETYPE_VID` FOREIGN KEY (`VEHICLETYPE_VID`) REFERENCES `vehicletype` (`VID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Tire','Normal 16\" 255/65','Tube_Less',1,1,1),(2,'Tire','110/20 14\"','Tube_Less',1,1,1),(3,'Tire','175/65 14\"','Tube_Less',1,1,1),(4,'Tire','185/55 15\"','Tube_Less',1,1,1),(5,'Tire','255/65 14\"','Tube_Less',1,2,2);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logsession`
--

DROP TABLE IF EXISTS `logsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logsession` (
  `SEID` bigint(20) NOT NULL,
  `DATEENDED` date DEFAULT NULL,
  `DATESTARTED` date DEFAULT NULL,
  `FINALISED` tinyint(1) DEFAULT '0',
  `TIMEENDED` time DEFAULT NULL,
  `TIMESTARTED` time DEFAULT NULL,
  `TURNOVER` double DEFAULT NULL,
  `LOGUSER_UID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SEID`),
  KEY `FK_LOGSESSION_LOGUSER_UID` (`LOGUSER_UID`),
  CONSTRAINT `FK_LOGSESSION_LOGUSER_UID` FOREIGN KEY (`LOGUSER_UID`) REFERENCES `loguser` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logsession`
--

LOCK TABLES `logsession` WRITE;
/*!40000 ALTER TABLE `logsession` DISABLE KEYS */;
INSERT INTO `logsession` VALUES (1,NULL,'2012-10-09',0,NULL,'23:27:30',0,1);
/*!40000 ALTER TABLE `logsession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loguser`
--

DROP TABLE IF EXISTS `loguser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loguser` (
  `UID` bigint(20) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `DATEADDED` date DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PW` varchar(255) DEFAULT NULL,
  `TIMEADDED` time DEFAULT NULL,
  `TPNO` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loguser`
--

LOCK TABLES `loguser` WRITE;
/*!40000 ALTER TABLE `loguser` DISABLE KEYS */;
INSERT INTO `loguser` VALUES (1,'Galle','2012-02-05','Sandaruwan','1','12:30:00','0713833068','z');
/*!40000 ALTER TABLE `loguser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `MANUID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MANUID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (1,'DSI'),(2,'MRF');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetail` (
  `ODID` bigint(20) NOT NULL AUTO_INCREMENT,
  `QTY` int(11) DEFAULT NULL,
  `UNITPRICE` double DEFAULT NULL,
  `ORDER_OID` bigint(20) DEFAULT NULL,
  `SUPPLYORDERDETAIL_SODID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ODID`),
  KEY `FK_ORDERDETAIL_ORDER_OID` (`ORDER_OID`),
  KEY `FK_ORDERDETAIL_SUPPLYORDERDETAIL_SODID` (`SUPPLYORDERDETAIL_SODID`),
  CONSTRAINT `FK_ORDERDETAIL_ORDER_OID` FOREIGN KEY (`ORDER_OID`) REFERENCES `orders` (`OID`),
  CONSTRAINT `FK_ORDERDETAIL_SUPPLYORDERDETAIL_SODID` FOREIGN KEY (`SUPPLYORDERDETAIL_SODID`) REFERENCES `supplyorderdetail` (`SODID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1,1,1400,1,1),(2,1,1400,2,1),(3,1,1400,3,1),(4,1,1400,4,1),(5,1,1400,5,1),(6,1,1400,6,1),(7,1,1800,7,4),(8,1,1800,8,4),(9,1,200,8,3),(10,1,200,9,3),(11,1,1800,10,4),(12,1,200,10,3),(13,1,1800,11,4),(14,1,200,12,3),(15,1,1800,12,4),(16,2,1400,13,1),(17,1,1600,13,2),(18,1,1400,14,6),(19,1,1600,14,5);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OID` bigint(20) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CUSTNAME` varchar(255) DEFAULT NULL,
  `DATEADDED` date DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `PAIDAMOUNT` double DEFAULT NULL,
  `TIMEADDED` time DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `TPNO` varchar(255) DEFAULT NULL,
  `LOGSESSION_SEID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  KEY `FK_ORDERS_LOGSESSION_SEID` (`LOGSESSION_SEID`),
  CONSTRAINT `FK_ORDERS_LOGSESSION_SEID` FOREIGN KEY (`LOGSESSION_SEID`) REFERENCES `logsession` (`SEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'No Address','Sandaruwan','2012-10-09',63,1400,'23:38:39',1400,'No Contacts',1),(2,'No Address','Thilina','2012-10-09',140,1400,'23:43:24',1400,'No Contacts',1),(3,'No Address','Hiran','2012-10-10',140,1300,'13:56:54',1400,'No Contacts',1),(4,'No Address','Thilina','2012-10-10',140,1300,'13:58:24',1400,'No Contacts',1),(5,'No Address','Sandaruwan','2012-10-10',140,1300,'14:02:57',1400,'No Contacts',1),(6,'No Address','Thilina','2012-10-10',140,1500,'14:04:44',1400,'No Contacts',1),(7,'No Address','Sandaruwan','2012-10-10',180,1700,'14:07:16',1800,'No Contacts',1),(8,'No Address','Thilina','2012-10-10',200,1800,'14:10:46',2000,'No Contacts',1),(9,'No Address','Shan','2012-10-10',20,180,'14:13:42',200,'No Contacts',1),(10,'No Address','Sangeeth','2012-10-10',200,1800,'14:29:06',2000,'No Contacts',1),(11,'No Address','Sanjaya','2012-10-10',180,1700,'14:33:57',1800,'No Contacts',1),(12,'No Address','Thilina','2012-10-10',200,1800,'14:40:02',2000,'No Contacts',1),(13,'No Address','Sandaruwan','2012-10-10',440,4000,'19:52:38',4400,'No Contacts',1),(14,'No Address','Thlina','2012-10-11',300,3000,'10:19:29',3000,'No Contacts',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES ('SEQ_GEN',0);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `SUID` bigint(20) NOT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `COMPNAME` varchar(255) DEFAULT NULL,
  `DATEADDED` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TIMEADDED` time DEFAULT NULL,
  `TPNO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SUID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Galle','Zegates ','2012-10-09','','Thilina','23:35:33','0713833068'),(2,'Galle','DSI Warehouse','2012-10-10','','Sandun Tharaka','20:54:29','0712833068');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplyorder`
--

DROP TABLE IF EXISTS `supplyorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplyorder` (
  `SOID` bigint(20) NOT NULL,
  `DATEADDED` date DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `TIMEADDED` time DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `LOGSESSION_SEID` bigint(20) DEFAULT NULL,
  `SUPPLIER_SUID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SOID`),
  KEY `FK_SUPPLYORDER_SUPPLIER_SUID` (`SUPPLIER_SUID`),
  KEY `FK_SUPPLYORDER_LOGSESSION_SEID` (`LOGSESSION_SEID`),
  CONSTRAINT `FK_SUPPLYORDER_LOGSESSION_SEID` FOREIGN KEY (`LOGSESSION_SEID`) REFERENCES `logsession` (`SEID`),
  CONSTRAINT `FK_SUPPLYORDER_SUPPLIER_SUID` FOREIGN KEY (`SUPPLIER_SUID`) REFERENCES `supplier` (`SUID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplyorder`
--

LOCK TABLES `supplyorder` WRITE;
/*!40000 ALTER TABLE `supplyorder` DISABLE KEYS */;
INSERT INTO `supplyorder` VALUES (1,'2012-10-09',0,'23:36:19',12000,1,1),(2,'2012-10-09',0,'23:41:54',28000,1,1),(3,'2012-10-10',0,'13:44:56',36000,1,1),(4,'2012-10-10',0,'13:45:23',30000,1,1),(5,'2012-10-10',0,'20:58:21',42000,1,1),(6,'2012-10-10',0,'21:04:07',22000,1,2);
/*!40000 ALTER TABLE `supplyorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplyorderdetail`
--

DROP TABLE IF EXISTS `supplyorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplyorderdetail` (
  `SODID` bigint(20) NOT NULL,
  `BUYINGPRICE` double DEFAULT NULL,
  `QTY` int(11) DEFAULT NULL,
  `REMAININGQTY` int(11) DEFAULT NULL,
  `SELLINGPRICE` double DEFAULT NULL,
  `ITEM_IID` bigint(20) DEFAULT NULL,
  `SUPPLYORDER_SOID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SODID`),
  KEY `FK_SUPPLYORDERDETAIL_SUPPLYORDER_SOID` (`SUPPLYORDER_SOID`),
  KEY `FK_SUPPLYORDERDETAIL_ITEM_IID` (`ITEM_IID`),
  CONSTRAINT `FK_SUPPLYORDERDETAIL_ITEM_IID` FOREIGN KEY (`ITEM_IID`) REFERENCES `item` (`IID`),
  CONSTRAINT `FK_SUPPLYORDERDETAIL_SUPPLYORDER_SOID` FOREIGN KEY (`SUPPLYORDER_SOID`) REFERENCES `supplyorder` (`SOID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplyorderdetail`
--

LOCK TABLES `supplyorderdetail` WRITE;
/*!40000 ALTER TABLE `supplyorderdetail` DISABLE KEYS */;
INSERT INTO `supplyorderdetail` VALUES (1,1200,10,0,1400,1,1),(2,1400,20,18,1600,2,2),(3,1800,20,15,2000,3,3),(4,1500,20,8,1800,4,4),(5,1500,30,28,1600,1,5),(6,1200,20,19,1400,2,5),(7,2200,10,10,2400,5,6);
/*!40000 ALTER TABLE `supplyorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicletype`
--

DROP TABLE IF EXISTS `vehicletype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicletype` (
  `VID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`VID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicletype`
--

LOCK TABLES `vehicletype` WRITE;
/*!40000 ALTER TABLE `vehicletype` DISABLE KEYS */;
INSERT INTO `vehicletype` VALUES (1,'Two Wheelers'),(2,'Four Wheelers'),(3,'Six Wheelers');
/*!40000 ALTER TABLE `vehicletype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-11 10:28:13
