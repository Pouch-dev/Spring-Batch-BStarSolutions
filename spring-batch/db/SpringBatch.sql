USE [SPRING_BATCH]
GO
/****** Object:  Table [dbo].[BATCH_JOB_EXECUTION]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_EXECUTION](
	[JOB_EXECUTION_ID] [bigint] NOT NULL,
	[VERSION] [bigint] NULL,
	[JOB_INSTANCE_ID] [bigint] NOT NULL,
	[CREATE_TIME] [datetime] NOT NULL,
	[START_TIME] [datetime] NULL,
	[END_TIME] [datetime] NULL,
	[STATUS] [varchar](10) NULL,
	[EXIT_CODE] [varchar](2500) NULL,
	[EXIT_MESSAGE] [varchar](2500) NULL,
	[LAST_UPDATED] [datetime] NULL,
	[JOB_CONFIGURATION_LOCATION] [varchar](2500) NULL,
 CONSTRAINT [PK_BATCH_JOB_EXECUTION] PRIMARY KEY CLUSTERED 
(
	[JOB_EXECUTION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_JOB_EXECUTION_CONTEXT]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_EXECUTION_CONTEXT](
	[JOB_EXECUTION_ID] [bigint] NOT NULL,
	[SHORT_CONTEXT] [varchar](2500) NOT NULL,
	[SERIALIZED_CONTEXT] [varchar](2500) NULL,
 CONSTRAINT [PK_BATCH_JOB_EXECUTION_CONTEXT] PRIMARY KEY CLUSTERED 
(
	[JOB_EXECUTION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_JOB_EXECUTION_PARAMS]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_EXECUTION_PARAMS](
	[JOB_EXECUTION_ID] [bigint] NOT NULL,
	[TYPE_CD] [varchar](6) NOT NULL,
	[KEY_NAME] [varchar](100) NOT NULL,
	[STRING_VAL] [varchar](250) NULL,
	[DATE_VAL] [datetime] NULL,
	[LONG_VAL] [bigint] NULL,
	[DOUBLE_VAL] [float] NULL,
	[IDENTIFYING] [char](1) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_JOB_EXECUTION_SEQ]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_EXECUTION_SEQ](
	[ID] [bigint] NULL,
	[UNIQUE_KEY] [char](1) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_JOB_INSTANCE]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_INSTANCE](
	[JOB_INSTANCE_ID] [bigint] NOT NULL,
	[VERSION] [bigint] NULL,
	[JOB_NAME] [varchar](100) NOT NULL,
	[JOB_KEY] [varchar](32) NOT NULL,
 CONSTRAINT [PK_BATCH_JOB_INSTANCE] PRIMARY KEY CLUSTERED 
(
	[JOB_INSTANCE_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_JOB_SEQ]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_JOB_SEQ](
	[ID] [bigint] NULL,
	[UNIQUE_KEY] [char](1) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_STEP_EXECUTION]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_STEP_EXECUTION](
	[STEP_EXECUTION_ID] [bigint] NOT NULL,
	[VERSION] [bigint] NOT NULL,
	[STEP_NAME] [varchar](100) NOT NULL,
	[JOB_EXECUTION_ID] [bigint] NOT NULL,
	[START_TIME] [datetime] NOT NULL,
	[END_TIME] [datetime] NULL,
	[STATUS] [varchar](10) NULL,
	[COMMIT_COUNT] [bigint] NULL,
	[READ_COUNT] [bigint] NULL,
	[FILTER_COUNT] [bigint] NULL,
	[WRITE_COUNT] [bigint] NULL,
	[READ_SKIP_COUNT] [bigint] NULL,
	[WRITE_SKIP_COUNT] [bigint] NULL,
	[PROCESS_SKIP_COUNT] [bigint] NULL,
	[ROLLBACK_COUNT] [bigint] NULL,
	[EXIT_CODE] [varchar](2500) NULL,
	[EXIT_MESSAGE] [varchar](2500) NULL,
	[LAST_UPDATED] [datetime] NULL,
 CONSTRAINT [PK_BATCH_STEP_EXECUTION] PRIMARY KEY CLUSTERED 
(
	[STEP_EXECUTION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BATCH_STEP_EXECUTION_CONTEXT]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BATCH_STEP_EXECUTION_CONTEXT](
	[STEP_EXECUTION_ID] [bigint] NOT NULL,
	[SHORT_CONTEXT] [varchar](2500) NOT NULL,
	[SERIALIZED_CONTEXT] [varchar](2500) NULL,
 CONSTRAINT [PK_BATCH_STEP_EXECUTION_CONTEXT] PRIMARY KEY CLUSTERED 
(
	[STEP_EXECUTION_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_management]    Script Date: 9/27/2021 5:13:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_management](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[departments] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[salary] [varchar](255) NULL,
	[time] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[BATCH_JOB_EXECUTION] ([JOB_EXECUTION_ID], [VERSION], [JOB_INSTANCE_ID], [CREATE_TIME], [START_TIME], [END_TIME], [STATUS], [EXIT_CODE], [EXIT_MESSAGE], [LAST_UPDATED], [JOB_CONFIGURATION_LOCATION]) VALUES (0, 2, 0, CAST(N'2021-09-27T17:10:05.870' AS DateTime), CAST(N'2021-09-27T17:10:05.930' AS DateTime), CAST(N'2021-09-27T17:10:05.970' AS DateTime), N'FAILED', N'FAILED', N'org.springframework.dao.DataAccessResourceFailureException: Could not increment identity; nested exception is com.microsoft.sqlserver.jdbc.SQLServerException: Invalid object name ''BATCH_STEP_EXECUTION_SEQ''.
	at org.springframework.jdbc.support.incrementer.AbstractIdentityColumnMaxValueIncrementer.getNextKey(AbstractIdentityColumnMaxValueIncrementer.java:114)
	at org.springframework.jdbc.support.incrementer.AbstractDataFieldMaxValueIncrementer.nextLongValue(AbstractDataFieldMaxValueIncrementer.java:128)
	at org.springframework.batch.core.repository.dao.JdbcStepExecutionDao.buildStepExecutionParameters(JdbcStepExecutionDao.java:218)
	at org.springframework.batch.core.repository.dao.JdbcStepExecutionDao.saveStepExecution(JdbcStepExecutionDao.java:149)
	at org.springframework.batch.core.repository.support.SimpleJobRepository.add(SimpleJobRepository.java:183)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:215)
	at com.sun.proxy.$Proxy95.add(Unknown Source)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.jav', CAST(N'2021-09-27T17:10:05.970' AS DateTime), NULL)
GO
INSERT [dbo].[BATCH_JOB_EXECUTION_CONTEXT] ([JOB_EXECUTION_ID], [SHORT_CONTEXT], [SERIALIZED_CONTEXT]) VALUES (0, N'{"@class":"java.util.HashMap"}', NULL)
GO
INSERT [dbo].[BATCH_JOB_EXECUTION_PARAMS] ([JOB_EXECUTION_ID], [TYPE_CD], [KEY_NAME], [STRING_VAL], [DATE_VAL], [LONG_VAL], [DOUBLE_VAL], [IDENTIFYING]) VALUES (0, N'LONG', N'time', N'', CAST(N'1970-01-01T07:00:00.000' AS DateTime), 1632737405838, 0, N'Y')
GO
INSERT [dbo].[BATCH_JOB_EXECUTION_SEQ] ([ID], [UNIQUE_KEY]) VALUES (NULL, NULL)
GO
INSERT [dbo].[BATCH_JOB_INSTANCE] ([JOB_INSTANCE_ID], [VERSION], [JOB_NAME], [JOB_KEY]) VALUES (0, 0, N'ETL-Load', N'4e6e45735a9cec5edf0ac28d129f0d57')
GO
INSERT [dbo].[BATCH_JOB_SEQ] ([ID], [UNIQUE_KEY]) VALUES (NULL, NULL)
GO
ALTER TABLE [dbo].[BATCH_JOB_EXECUTION_PARAMS]  WITH CHECK ADD  CONSTRAINT [JOB_EXEC_PARAMS_FK_INDEX_5] FOREIGN KEY([JOB_EXECUTION_ID])
REFERENCES [dbo].[BATCH_JOB_EXECUTION] ([JOB_EXECUTION_ID])
GO
ALTER TABLE [dbo].[BATCH_JOB_EXECUTION_PARAMS] CHECK CONSTRAINT [JOB_EXEC_PARAMS_FK_INDEX_5]
GO
ALTER TABLE [dbo].[BATCH_STEP_EXECUTION]  WITH CHECK ADD  CONSTRAINT [JOB_EXEC_STEP_FK_INDEX_7] FOREIGN KEY([JOB_EXECUTION_ID])
REFERENCES [dbo].[BATCH_JOB_EXECUTION_CONTEXT] ([JOB_EXECUTION_ID])
GO
ALTER TABLE [dbo].[BATCH_STEP_EXECUTION] CHECK CONSTRAINT [JOB_EXEC_STEP_FK_INDEX_7]
GO
