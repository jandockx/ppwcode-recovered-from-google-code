use [master]
go

CREATE DATABASE [UserManagement] 
  ON PRIMARY (NAME = N'UserManagement', FILENAME = N'D:\Databases\UserManagement.mdf' , SIZE = 3072KB , FILEGROWTH = 1024KB )
  LOG ON     (NAME = N'UserManagement_log', FILENAME = N'D:\Databases\UserManagement_log.ldf' , SIZE = 1024KB , FILEGROWTH = 10%)
go
alter database [UserManagement] set trustworthy on
go

use [UserManagement]
go
exec sp_changedbowner 'sa'
go

set ansi_nulls on
go
set quoted_identifier on
go

create xml schema collection [dbo].[messages] 
as 
N'<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified"><xsd:complexType name="message"><xsd:simpleContent><xsd:extension base="xsd:string"><xsd:attribute name="lcid" type="xsd:int" use="required" /></xsd:extension></xsd:simpleContent></xsd:complexType><xsd:element name="messages" type="messages" /><xsd:complexType name="messages"><xsd:complexContent><xsd:restriction base="xsd:anyType"><xsd:sequence><xsd:element name="message" type="message" minOccurs="0" maxOccurs="unbounded" /></xsd:sequence></xsd:restriction></xsd:complexContent></xsd:complexType></xsd:schema>'
go

