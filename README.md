## Overview
This project to demonstrate the way to use different encrypt and decrypt algorithms using Java library

## Flows
### Sharing key
<img src="picture/share_key.png" width="800">

### Encrypt and Decrypt
<img src="picture/encrypt_and_decrypt.png" width="800">

## Run
### Server
```
cd crypto-server
./gradlew clean build bootRun -x test --args='--server.port=8081'
```

### Client
```
cd crypto-client
./gradlew run
```