[![Build Status](https://travis-ci.org/ChargeTimeEU/Java-OCA-OCPP.svg?branch=master)](https://travis-ci.org/ChargeTimeEU/Java-OCA-OCPP)
Java-OCA-OCPP
=============

A client and server library of Open Charge-Point Protocol from openchargealliance.org

With this library, you can easily get started with the Open Charge-Point Protocol.
The design is driven by test, which will ensure premium software that is easy to adapt and modify to your needs.

Once done, we will support every version of OCPP on any preferred connection SOAP or Web Socket.
However, the first release will aim to implement OCPP version 1.6 on Web Socket with json as format.

Incoming request events are split into feature profiles as described in the OCPP specification.
I recommend that you download and read the specification from openchargealliance.org

Dependencies
============

Java-OCA-OCPP uses the following libraries:

* [Groovy-2.4](http://www.groovy-lang.org/)
* [JUnit4](http://junit.org/junit4/)
* [org.hamcrest:hamcrest-all:1.3](http://hamcrest.org)
* [org.mockito:mockito-core:1.10.19](http://mockito.org)
* [spock-core-0.7-groovy-2.0](http://spockframework.org)

To use version 1.6 you need the following libraries:

* [org.json:json:20160212](https://github.com/stleary/JSON-java)
* [org.java_websocket](https://github.com/TooTallNate/Java-WebSocket)

License
=======

[MIT License](LICENSE)

About ChargeTime.eu
=======

We are devoted to push the marked for vehicles charging forward.
There are many standards out there, we intend implement and share them. Any help is much appreciated!

The market is in its defining state, the practices and standards we come up with now, may very well be stick around for decades to come. Just think, we can help mature these standards.

See our vision at http://www.chargetime.eu/
