USE [master]
GO
/****** Object:  Database [ABClinic]    Script Date: 6/16/2022 12:29:57 AM ******/
CREATE DATABASE [ABClinic]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ABClinic', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ABClinic.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ABClinic_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ABClinic_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [ABClinic] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ABClinic].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ABClinic] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ABClinic] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ABClinic] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ABClinic] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ABClinic] SET ARITHABORT OFF 
GO
ALTER DATABASE [ABClinic] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ABClinic] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ABClinic] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ABClinic] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ABClinic] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ABClinic] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ABClinic] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ABClinic] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ABClinic] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ABClinic] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ABClinic] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ABClinic] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ABClinic] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ABClinic] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ABClinic] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ABClinic] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ABClinic] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ABClinic] SET RECOVERY FULL 
GO
ALTER DATABASE [ABClinic] SET  MULTI_USER 
GO
ALTER DATABASE [ABClinic] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ABClinic] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ABClinic] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ABClinic] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ABClinic] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'ABClinic', N'ON'
GO
ALTER DATABASE [ABClinic] SET QUERY_STORE = OFF
GO
USE [ABClinic]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[role_id] [int] NULL,
	[is_active] [bit] NULL,
	[salt] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CusRes]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CusRes](
	[code] [int] NOT NULL,
	[created_by] [nvarchar](50) NULL,
	[created_at] [nvarchar](50) NULL,
	[test_result] [nvarchar](50) NULL,
	[examination_card] [nvarchar](50) NULL,
	[time_return] [nvarchar](50) NULL,
	[cus_id] [int] NOT NULL,
	[list_test] [varchar](50) NULL,
	[note] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullname] [nvarchar](50) NULL,
	[phone] [nchar](20) NULL,
	[gender] [bit] NULL,
	[job] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[dob] [nvarchar](20) NULL,
	[country] [nvarchar](20) NULL,
	[description] [nvarchar](50) NULL,
	[status] [nchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[code] [int] IDENTITY(1,1) NOT NULL,
	[cus_id] [int] NULL,
	[created_at] [nvarchar](20) NULL,
	[total] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InvoiceDetail]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [int] NULL,
	[name] [nvarchar](50) NULL,
	[cost_price] [float] NULL,
	[sell_price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profile]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profile](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[fullname] [nvarchar](50) NULL,
	[address] [nvarchar](50) NULL,
	[phone] [nchar](20) NULL,
	[email] [nvarchar](50) NULL,
	[dob] [nvarchar](20) NULL,
	[gender] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] IDENTITY(1,1) NOT NULL,
	[role_name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Test]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Test](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type_id] [int] NULL,
	[name] [nvarchar](50) NULL,
	[cost_price] [float] NULL,
	[sell_price] [float] NULL,
	[is_active] [bit] NULL,
	[form] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TestResultForm]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TestResultForm](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](50) NULL,
	[testid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TypeTest]    Script Date: 6/16/2022 12:29:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypeTest](
	[type_id] [int] IDENTITY(1,1) NOT NULL,
	[type_test_name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([id], [username], [password], [role_id], [is_active], [salt]) VALUES (14, N'dohoang', N'7d70b4e94f373807fc2e85bd49fb196d', 2, 1, N'wn1VNZrQxO7/T87SRF6g9Q==')
INSERT [dbo].[Account] ([id], [username], [password], [role_id], [is_active], [salt]) VALUES (15, N'hn', N'123456', 2, 1, N'gggg')
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (137273, N'14', NULL, N'', N'', N'', 5, N' 6, 8, 13, 19,', N'ABDCD')
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (140532, N'14', NULL, N'Mat nao', N'kém', N'2022-06-19', 1, N' 6, 8, 16, 17, 18,', N'Tạm ổn định đã')
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (140672, N'14', NULL, NULL, NULL, NULL, 3, NULL, NULL)
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (140777, N'14', NULL, N'Mat nao', N'Chua on', N'2022-06-18', 2, NULL, NULL)
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (140832, N'14', NULL, N'Mất não', N'Chua on chút nào', N'2022-06-19', 4, N' 7, 8, 14, 16, 9,', N'Quá kém')
INSERT [dbo].[CusRes] ([code], [created_by], [created_at], [test_result], [examination_card], [time_return], [cus_id], [list_test], [note]) VALUES (768231, N'14', NULL, N'', N'', N'', 6, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (1, N'Bui Minh Sang', N'09546854            ', 0, N'Sinh Vien FU', N'Hoa Lac', N'2000-12-12', N'HHHYUE', N'Chong mat', N'Done      ')
INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (2, N'Thai Thanh', N'09546854            ', 1, N'Sinh Viên FU', N'Hoa Lac', N'2000-12-12', N'Thanh Hoa', N'dau dau', N'Doing     ')
INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (3, N'Vu', N'09546854            ', 0, N'Sinh Vien', N'Hoa Lac', N'2000-12-12', N'hahhsd', N'dau dau', N'Doing     ')
INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (4, N'Thai Thanh', N'09546854            ', 1, N'Sinh Vien Ga', N'Hoa Lac', N'2000-12-12', N'hahhsd', N'dau dau', N'Doing     ')
INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (5, N'Bui Minh Sang', N'09546854            ', 0, N'Sinh Vien', N'Hoa Lac4', N'2000-12-12', N'hahhsd', N'dau dau', N'Doing     ')
INSERT [dbo].[Customer] ([id], [fullname], [phone], [gender], [job], [address], [dob], [country], [description], [status]) VALUES (6, N'Bui Minh Sang', N'09546854            ', 1, N'Sinh Vien', N'Hoa Lac', N'2000-12-12', N'hahhsd', N'dau dau qua', N'Waiting   ')
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Profile] ON 

INSERT [dbo].[Profile] ([id], [user_id], [fullname], [address], [phone], [email], [dob], [gender]) VALUES (1, 14, N'Do Viet Hoang', N'TTT', N'0944245859          ', N'hoangdvhe140531@fpt.edu.vn', N'2022-05-12', 1)
SET IDENTITY_INSERT [dbo].[Profile] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (1, N'Boss')
INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (2, N'Bác Sĩ')
INSERT [dbo].[Role] ([role_id], [role_name]) VALUES (3, N'Lễ Tân')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
SET IDENTITY_INSERT [dbo].[Test] ON 

INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (1, 1, N'Glucozo', 1000, 2000, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (2, 1, N'Isulin', 2000, 2500, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (3, 1, N'GGT', 1500, 3000, 0, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (4, 1, N'Ure', 4000, 6000, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (5, 1, N'A/G', 1200, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (6, 1, N'Bilirubin toàn phần', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (7, 1, N'Canxi ion', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (8, 1, N'LH', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (9, 3, N'Kí sinh trùng trong phân', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (10, 3, N'Tìm HC/BC trong phân', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (11, 3, N'Trứng giun', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (12, 2, N'Ani HCV', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (13, 2, N'HAV IgM', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (14, 2, N'HIV', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (15, 2, N'ELISA ấu trùng giun đũa', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (16, 2, N'ELISA giun lườn ruột', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (17, 4, N'Tổng phân tích nước tiểu', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (18, 4, N'Micro Albumin niệu', NULL, NULL, 1, NULL)
INSERT [dbo].[Test] ([id], [type_id], [name], [cost_price], [sell_price], [is_active], [form]) VALUES (19, 4, N'Albumin niệu', NULL, NULL, 1, NULL)
SET IDENTITY_INSERT [dbo].[Test] OFF
GO
SET IDENTITY_INSERT [dbo].[TypeTest] ON 

INSERT [dbo].[TypeTest] ([type_id], [type_test_name]) VALUES (1, N'XN Hóa Sinh')
INSERT [dbo].[TypeTest] ([type_id], [type_test_name]) VALUES (2, N'XN Vi Sinh')
INSERT [dbo].[TypeTest] ([type_id], [type_test_name]) VALUES (3, N'XN Phân')
INSERT [dbo].[TypeTest] ([type_id], [type_test_name]) VALUES (4, N'XN Nước Tiểu')
SET IDENTITY_INSERT [dbo].[TypeTest] OFF
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[CusRes]  WITH CHECK ADD  CONSTRAINT [FK_CusRes_Customer] FOREIGN KEY([cus_id])
REFERENCES [dbo].[Customer] ([id])
GO
ALTER TABLE [dbo].[CusRes] CHECK CONSTRAINT [FK_CusRes_Customer]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_Customer] FOREIGN KEY([cus_id])
REFERENCES [dbo].[Customer] ([id])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_Customer]
GO
ALTER TABLE [dbo].[InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceDetail_Invoice] FOREIGN KEY([code])
REFERENCES [dbo].[Invoice] ([code])
GO
ALTER TABLE [dbo].[InvoiceDetail] CHECK CONSTRAINT [FK_InvoiceDetail_Invoice]
GO
ALTER TABLE [dbo].[Profile]  WITH CHECK ADD  CONSTRAINT [FK_Profile_Account] FOREIGN KEY([user_id])
REFERENCES [dbo].[Account] ([id])
GO
ALTER TABLE [dbo].[Profile] CHECK CONSTRAINT [FK_Profile_Account]
GO
ALTER TABLE [dbo].[Test]  WITH CHECK ADD  CONSTRAINT [FK_Test_TypeTest] FOREIGN KEY([type_id])
REFERENCES [dbo].[TypeTest] ([type_id])
GO
ALTER TABLE [dbo].[Test] CHECK CONSTRAINT [FK_Test_TypeTest]
GO
ALTER TABLE [dbo].[TestResultForm]  WITH CHECK ADD  CONSTRAINT [FK_TestResultForm_Test] FOREIGN KEY([testid])
REFERENCES [dbo].[Test] ([id])
GO
ALTER TABLE [dbo].[TestResultForm] CHECK CONSTRAINT [FK_TestResultForm_Test]
GO
USE [master]
GO
ALTER DATABASE [ABClinic] SET  READ_WRITE 
GO
