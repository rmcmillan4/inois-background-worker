# INOIS Background Worker

A Spring Batch project for securely processing and storing sensitive data for INOIS participating agencies. 

## Prerequisites
### System
- Java 11 or later
- Maven 3.5.0 or later

### Required Environment Variables
The following environment variables should be initialized in your environment in order to allow access to required
Azure security and storage resources:
```
APPSETTING_KvAccessClientId      (Azure Key Vault Property)
APPSETTING_KvAccessClientSecret  (Azure Key Vault Property)
APPSETTING_KvUrl                 (Azure Key Vault Property)
APPSETTING_TenantId              (Azure Key Vault Property)
APPSETTING_StorageAccountKey     (Azure Blob Storage Property)
APPSETTING_BlobContainerIngest   (Azure Blob Storage Property)
APPSETTING_StorageAccountName    (Azure Blob Storage Property)
CoreJdbcConnectionString         (Connection String to the INOIS Core Database)
EntityJdbcConnectionString       (Connection String to the INOIS Entity Database)
```

## Install the Application
In the main project folder execute the following command:
```
mvn clean install
```
This will produce a 'target' folder containing a jar file 'demo-0.0.1-SNAPSHOT.jar'.  This is a packaged version of the 
application.

## Running the Application
After successful installation, execute the following command:
```
java -jar demo-0.0.1-SNAPSHOT.jar
```
