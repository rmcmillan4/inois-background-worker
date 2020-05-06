CREATE TABLE dbo.dfcs (
    ID INT PRIMARY KEY IDENTITY (1, 1),
    CREATED DATETIME NOT NULL,
    CREATED_BY VARCHAR(255) NOT NULL,
    UPDATED DATETIME NOT NULL,
    UPDATED_BY VARCHAR(255) NOT NULL,
    CD_ADOPT_CNTRY VARCHAR(255),
    CD_ADOPT_COUNTY VARCHAR(255),
    CD_PERSON_CHAR VARCHAR(255),
    CD_PERSON_DEATH VARCHAR(255),
    CD_ADOPT_STATE VARCHAR(255),
    CD_PERSON_ETHNIC_GROUP VARCHAR(255),
    CD_PERSON_IMMIGRATION_STATUS VARCHAR(255),
    CD_PERSON_LANGUAGE VARCHAR(255),
    CD_PERSON_LIV_ARR VARCHAR(255),
    CD_PERSON_MARITAL_STATUS VARCHAR(255),
    CD_PERSON_PROOF_CITIZENSHIP VARCHAR(255),
    CD_PERSON_RELIGION VARCHAR(255),
    CD_PERSON_SEX VARCHAR(255),
    CD_PERSON_STATUS VARCHAR(255),
    CD_PERSON_SUFFIX VARCHAR(255),
    CD_PERSON_TITLE VARCHAR(255),
    CD_SINGLE_MOTHER_FATHER VARCHAR(255),
    DT_DISSOLUTION VARCHAR(255),
    DT_PERSON_BIRTH VARCHAR(255),
    DT_PERSON_DEATH VARCHAR(255),
    GENDER VARCHAR(255),
    ID_PERSON VARCHAR(255),
    IND_AUTO_PERS_MERGE VARCHAR(255),
    IND_PERSON_DOB_APPROX VARCHAR(255),
    IND_PREV_ADOPTED VARCHAR(255),
    IND_PUBLIC VARCHAR(255),
    IND_SINGLE_PAR_ADOPT VARCHAR(255),
    NBR_PERSON_AGE VARCHAR(255),
    NBR_PERSON_ID_NUMBER VARCHAR(255),
    NBR_PERSON_PHONE VARCHAR(255),
    NM_PERSON_FIRST VARCHAR(255),
    NM_PERSON_LAST VARCHAR(255),
    NM_PERSON_FULL VARCHAR(255),
    TXT_NAME_OF_ADO_AGENCY VARCHAR(255),
    TXT_PERSON_OCCUPATION VARCHAR(255),
    TXT_PERSON_OTHER_RELATIONSHIPS VARCHAR(255),
    ID_PERSON_hash VARCHAR(255) NOT NULL,
    NBR_PERSON_ID_NUMBER_hash VARCHAR(255) NOT NULL,
    BATCH_IDENTIFIER VARCHAR(255) NOT NULL
    FOREIGN KEY (ID_PERSON_hash) REFERENCES dbo.dfcs_hash (HASH)
    FOREIGN KEY (NBR_PERSON_ID_NUMBER_hash) REFERENCES dbo.dfcs_hash (HASH)
);


INSERT INTO [dbo].[file_processing_status] VALUES (1, 'File uploaded - ingest pending');
INSERT INTO [dbo].[file_processing_status] VALUES (2, 'Ingest started - processing file');
INSERT INTO [dbo].[file_processing_status] VALUES (3, 'Ingest completed successfully');
INSERT INTO [dbo].[file_processing_status] VALUES (4, 'Error - invalid file format for ingest');
INSERT INTO [dbo].[file_processing_status] VALUES (5, 'Error - unable to decrypt file for ingest');
INSERT INTO [dbo].[file_processing_status] VALUES (6, 'Error - unable to complete secondary hashing');
INSERT INTO [dbo].[file_processing_status] VALUES (7, 'Error - unable to connect / insert data into DB');
INSERT INTO [dbo].[file_processing_status] VALUES (8, 'Error - file size too large, out of memory');

CREATE TABLE dbo.dfcs_hash (
      HASH VARCHAR(255) PRIMARY KEY NOT NULL,
      CREATED DATETIME NOT NULL,
      CREATED_BY VARCHAR(255) NOT NULL,
      UPDATED DATETIME NOT NULL,
      UPDATED_BY VARCHAR(255) NOT NULL,
      COLUMN_HASHED VARCHAR(255) NOT NULL,
      PREVIOUS_HASHES VARCHAR(4000)
);

/****** Object:  Table [dbo].[dfcs]    Script Date: 5/5/2020 5:13:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[dfcs](
                             [ID] [int] IDENTITY(1,1) NOT NULL,
                             [CREATED] [datetime] NOT NULL,
                             [CREATED_BY] [varchar](255) NOT NULL,
                             [UPDATED] [datetime] NOT NULL,
                             [UPDATED_BY] [varchar](255) NOT NULL,
                             [CD_ADOPT_CNTRY] [varchar](255) NULL,
                             [CD_ADOPT_COUNTY] [varchar](255) NULL,
                             [CD_PERSON_CHAR] [varchar](255) NULL,
                             [CD_PERSON_DEATH] [varchar](255) NULL,
                             [CD_ADOPT_STATE] [varchar](255) NULL,
                             [CD_PERSON_ETHNIC_GROUP] [varchar](255) NULL,
                             [CD_PERSON_IMMIGRATION_STATUS] [varchar](255) NULL,
                             [CD_PERSON_LANGUAGE] [varchar](255) NULL,
                             [CD_PERSON_LIV_ARR] [varchar](255) NULL,
                             [CD_PERSON_MARITAL_STATUS] [varchar](255) NULL,
                             [CD_PERSON_PROOF_CITIZENSHIP] [varchar](255) NULL,
                             [CD_PERSON_RELIGION] [varchar](255) NULL,
                             [CD_PERSON_SEX] [varchar](255) NULL,
                             [CD_PERSON_STATUS] [varchar](255) NULL,
                             [CD_PERSON_SUFFIX] [varchar](255) NULL,
                             [CD_PERSON_TITLE] [varchar](255) NULL,
                             [CD_SINGLE_MOTHER_FATHER] [varchar](255) NULL,
                             [DT_DISSOLUTION] [varchar](255) NULL,
                             [DT_PERSON_BIRTH] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [DT_PERSON_DEATH] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [GENDER] [varchar](255) NULL,
                             [ID_PERSON] [varchar](255) NULL,
                             [IND_AUTO_PERS_MERGE] [varchar](255) NULL,
                             [IND_PERSON_DOB_APPROX] [varchar](255) NULL,
                             [IND_PREV_ADOPTED] [varchar](255) NULL,
                             [IND_PUBLIC] [varchar](255) NULL,
                             [IND_SINGLE_PAR_ADOPT] [varchar](255) NULL,
                             [NBR_PERSON_AGE] [varchar](255) NULL,
                             [NBR_PERSON_ID_NUMBER] [varchar](255) NULL,
                             [NBR_PERSON_PHONE] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [NM_PERSON_FIRST] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [NM_PERSON_LAST] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [NM_PERSON_FULL] [varchar](255) COLLATE Latin1_General_BIN2 ENCRYPTED WITH (COLUMN_ENCRYPTION_KEY = [CEK_Auto1], ENCRYPTION_TYPE = Deterministic, ALGORITHM = 'AEAD_AES_256_CBC_HMAC_SHA_256') NULL,
                             [TXT_NAME_OF_ADO_AGENCY] [varchar](255) NULL,
                             [TXT_PERSON_OCCUPATION] [varchar](255) NULL,
                             [TXT_PERSON_OTHER_RELATIONSHIPS] [varchar](255) NULL,
                             [ID_PERSON_hash] [varchar](255) NOT NULL,
                             [NBR_PERSON_ID_NUMBER_hash] [varchar](255) NOT NULL,
                             [BATCH_IDENTIFIER] [varchar](255) NOT NULL,
                             CONSTRAINT [PK__dfcs__3214EC27958BA633] PRIMARY KEY CLUSTERED
                                 (
                                  [ID] ASC
                                     )WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO