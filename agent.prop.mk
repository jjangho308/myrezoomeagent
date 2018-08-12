[EXTERNAL]
MAPPER_CLASS_NAME = io.rezoome.external.mk.mapper.MkMapper
MYBATIS_CONFIG_FILE_PATH = io/rezoome/external/mk/mybatis/mybatis-config.xml
DAO_CLASS_NAME = io.rezoome.manager.database.dao.Dao
DAO_MAPPER_CLASS_NAME = io.rezoome.external.mk.mapper.MkDaoMapper
PROP_CLASS_NAME = io.rezoome.external.mk.property.MkProperties
IOREQUEST_CLASS_NAME = io.rezoome.external.mk.iorequest.MkIORequest

[GENERAL]
GET_DATA_METHOD = WAS
  
[THREAD]
THREAD_POOL_CAPAVILITY = 10
HEALTH_CHECK_INTERVAL = 60000

[DB] 
DBMS_TYPE = mysql
DBMS_VERSION = 5.7.21
DB_HOST = localhost
DB_PORT = 1234
DB_NAME = MK_DB
DB_USER_ID = rzroot
DB_PASSWORD = Sgen2018!
DB_MAX_CONNECTION = 1000
DB_INIT_CONNECTION = 1
DB_MAX_WAIT = 5
CONNECTION_POOL_NAME = mk
	  
[AMQ]
AMAZONE_QUEUE_NAME = MK
AMAZONE_SERVER_HOST = ssl://b-cb8c6e8c-f893-4464-aa69-b3501991ef60-1.mq.ap-southeast-2.amazonaws.com:61617
AMAZONE_USER_NAME = rezoome
AMAZONE_USER_PASSWORD = sgen2018!!!!

[Job]
JOB_TEMP_FILE_PATH = ./

[HTTP]
PORTAL_URL = https://dev.rezoome.io/agent
CONNECT_TIMEOUT = 3000
READ_TIMEOUT = 3000
RETRIES = 3
RETRY_DELAY_SEC = 3000

[KEYSTORE]
KEYSTORE_LOCATION = ./mk.keystore
CERT_NAME  = AGENT_CERT
ISSUER_DN = C=DE,O=Organiztion
CERT_PASSWORD  = SgenRezoomeAgentCertPwd2018!@#$

[AGENT VIA]
VIA_AGENCY_URL = http://220.73.139.131/rezoome.php/
VIA_CONNECT_TIMEOUT = 3000
VIA_READ_TIMEOUT = 3000
VIA_AGENT_RETRIES = 3
VIA_RETRY_DELAY_SEC = 3000



[IMSI]
PORTAL_URL_DEV = https://dev.rezoome.io/agent
PORTAL_URL_STG = https://rezoome.io/agent
PORTAL_URL_PRD = https://rezoome.io/agent


