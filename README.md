Secure Password Storage
=======================

This code provides a secure way to store passwords in a database. It is based on the secure salt technique, to store the passwords as a pair of values.
This is achieved in a way that if two users have identical passwords different values are stored in both columns, making it much more expensive to discover the passwords of users, even having access to the database

## Motivation

A simple and secure way to generate hashed passwords for store it in database and check if a specific password is valid

## Usage

### The createHashedPassword function requests a string and returns an object with the password and the salt. Now it is only necessary to store this pair for values in database.
```java
PasswordStorageService service = new PasswordStorageService();
PassSaltDTO result = new PassSaltDTO();
try {
	result= service.createHashedPassword(PASS);
} catch (PasswordStorageException e) {
	//log this expection
}
```
### The function verifyPassword, is passed the string containing the password to be verified, with the pair of database values. Logically returns if this password is correct or not.
```java
PasswordStorageService service = new PasswordStorageService();
PassSaltDTO dataBaseResult = new PassSaltDTO("some_password_value", "some_salt_value");
boolean isCorrect=false;
try {
	isCorrect = service.verifyPassword(PASS, dataBaseResult);
} catch (PasswordStorageException e) {
	//log this expection
}
```

## Tests

Test code coverage

## Contributors

Gabi Carballo

## License

Copyright 2017 GlobalTMS

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
