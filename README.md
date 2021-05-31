**Author note**: This code can be used for learning purposes only, and expose some major security concerns if used for a production instance.
# Overview   ![Build Status](https://travis-ci.org/Ahmad-alsanie/OTP.svg?branch=HEAD)
OTP is an open source Java Time-Based One Time Password Library, generates pins that depend on username, password and time. 
It provide support for the following encryption algorithms:
- SHA1
- SHA256
- SHA516
- MD5

The main Java code is in src/main/. It also includes test cases under src/test, additionnal information can be found in documentation comments 

# How to use
For now the available hashing algorithms are (SHA1, SHA256, SHA516, and MD5) to use one of these methods you justt need to create an instance of your desired encryption type and call the **generateTOTP()** method on it, see the following example:

```Java
    TOTP totpInstance = new TOTPSHA512();
		totpInstance.generateTOTP(seconds, numberOfDigits, username, password);
```

**seconds**: specifies the time slot frame in which the pin is changed when finished. For example specifing a 5 seconds will result the pin to be changed every 5 seconds and makes it valid only for 5 seconds.
**numberOfDigits**: spicifies the length of the generated pin code.
**username**: pin code related user in ```String``` format.
**password**: pin code related user password in ```char[]``` format.

- NOTE: there's no validation on passwords and users since it should be done on the library user's end so keep in mind to do proper validation before using the library.

# Contributing

OTP is an open source library that adheres to the [Open-Code-of-Conduct](http://todogroup.org/opencodeofconduct/). Feel free to open PRs but keep in mind to write a specific comments related to your changes.

# Submitting a PR
1. Fork the project
2. Create a branch 
3. commit your changes with a msg indecating what you've done
4. open a PR with detailed description 
5. Enjoy the rest of your day :)

For more info or help contact support@github.com or see the following [link](https://help.github.com/articles/about-pull-requests/).
