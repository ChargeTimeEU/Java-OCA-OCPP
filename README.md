[![Java CI with Maven](https://github.com/ChargeTimeEU/Java-OCA-OCPP/actions/workflows/maven.yml/badge.svg)](https://github.com/ChargeTimeEU/Java-OCA-OCPP/actions/workflows/maven.yml)
[![codecov](https://codecov.io/gh/ChargeTimeEU/Java-OCA-OCPP/branch/master/graph/badge.svg)](https://codecov.io/gh/ChargeTimeEU/Java-OCA-OCPP)
[![Gitter](https://badges.gitter.im/ChargeTimeEU/Java-OCA-OCPP.svg)](https://gitter.im/ChargeTimeEU/Java-OCA-OCPP?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

Java-OCA-OCPP
=============

A library for the Open Charge-Point Protocol from openchargealliance.org

This library is made to support anyone who wants to implement a Central System or Charge Point that follows the Open Charge-Point Protocol.
Please note, this is a library and not an application, so there is no main method. 

The design is driven by test, which will ensure premium software that is easy to adapt and modify to your needs.

The library supports OCPP 1.6 with JSON and SOAP transports and OCPP 2.0.1.

The following packages are built from the sources:

| Package     | Description                                                       |
|-------------|-------------------------------------------------------------------|
| ocpp16j     | OCPP 1.6 with JSON transport (only)                               |
| ocpp16s     | OCPP 1.6 with SOAP transport (only)                               |
| ocpp2       | OCPP 2.0.1 (multi-protocol capable, ocpp16j integration possible) |
|             |                                                                   |
| ocpp16      | OCPP 1.6 base dependency package                                  |
| ocpp-common | common dependency package                                         |
| ocpp-json   | JSON transport dependency package                                 |
|             |                                                                   |
| ocpp16-test | OCPP 1.6 integration tests                                        |
| ocpp2-test  | OCPP 1.6 and 2.0.1 integration tests                              |

Incoming request events are split into feature profiles as described in the OCPP specification.
I recommend that you download and read the specification from openchargealliance.org

See the project plan here:
    https://tree.taiga.io/project/tvolden-java-oca-ocpp/

Maven
=====

Find the maven repo here: https://mvnrepository.com/artifact/eu.chargetime.ocpp

License
=======

[MIT License](LICENSE)

About ChargeTime.eu
=======

We are devoted to push the marked for vehicles charging forward.
There are many standards out there, we intend to implement and share them. Any help is much appreciated!

The market is in its defining state, the practices and standards we come up with now, may very well stick around for decades to come.

See our vision at http://www.chargetime.eu/
