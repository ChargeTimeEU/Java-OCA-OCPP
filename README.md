[![Java CI with Maven](https://github.com/ChargeTimeEU/Java-OCA-OCPP/actions/workflows/maven.yml/badge.svg)](https://github.com/ChargeTimeEU/Java-OCA-OCPP/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/ChargeTimeEU/Java-OCA-OCPP/branch/master/graph/badge.svg)](https://codecov.io/gh/ChargeTimeEU/Java-OCA-OCPP)
[![Gitter](https://badges.gitter.im/ChargeTimeEU/Java-OCA-OCPP.svg)](https://gitter.im/ChargeTimeEU/Java-OCA-OCPP?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

Java-OCA-OCPP
=============

A client and server library of Open Charge-Point Protocol from openchargealliance.org

With this library, you can easily get started with the Open Charge-Point Protocol.
The design is driven by test, which will ensure premium software that is easy to adapt and modify to your needs.

Please note, this is a library and not an application, so there is no main method. 

Currently we support 1.6 SOAP or Web socket.

Incoming request events are split into feature profiles as described in the OCPP specification.
I recommend that you download and read the specification from openchargealliance.org

See the project plan here:
    https://tree.taiga.io/project/tvolden-java-oca-ocpp/

!! If you have an implementation for 0.4 or earlier, please read:  
https://github.com/ChargeTimeEU/Java-OCA-OCPP/wiki/External-interface-change-from-version-0.4-to-0.5

Maven
=====

Find the maven repo here: https://mvnrepository.com/artifact/eu.chargetime.ocpp

Dependencies
============

Java-OCA-OCPP uses the following libraries:

* [Groovy-2.4](http://www.groovy-lang.org/)
* [JUnit4](http://junit.org/junit4/)
* [org.hamcrest:hamcrest-all:1.3](http://hamcrest.org)
* [org.mockito:mockito-core:1.10.19](http://mockito.org)
* [spock-core-0.7-groovy-2.0](http://spockframework.org)

To use version 1.6 you need the following libraries:

* [com.google.code.gson](https://github.com/google/gson)
* [org.java_websocket](https://github.com/TooTallNate/Java-WebSocket)

License
=======

[MIT License](LICENSE)

About ChargeTime.eu
=======

We are devoted to push the marked for vehicles charging forward.
There are many standards out there, we intend to implement and share them. Any help is much appreciated!

The market is in its defining state, the practices and standards we come up with now, may very well stick around for decades to come.

See our vision at http://www.chargetime.eu/
