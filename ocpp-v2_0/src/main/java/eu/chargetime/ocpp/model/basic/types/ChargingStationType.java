package eu.chargetime.ocpp.model.basic.types;
/*
    ChargeTime.eu - Java-OCA-OCPP
    
    MIT License

    Copyright (C) 2018 Thomas Volden <tv@chargetime.eu>

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Validatable;
import eu.chargetime.ocpp.model.validation.Validator;
import eu.chargetime.ocpp.model.validation.OCPP2PrimDatatypes;
import eu.chargetime.ocpp.model.validation.ValidatorBuilder;

public class ChargingStationType implements Validatable {
    private Validator serialNumberValidator = new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string20()).build();
    private Validator modelValidator = new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string20()).build();
    private Validator vendorNameValidator = new ValidatorBuilder().setRequired(true).addRule(OCPP2PrimDatatypes.string50()).build();
    private Validator firmwareVersionValidator = new ValidatorBuilder().addRule(OCPP2PrimDatatypes.string50()).build();

    private String serialNumber;
    private String model;
    private String vendorName;
    private String firmwareVersion;
    private ModemType modem;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws PropertyConstraintException {
        serialNumberValidator.validate(serialNumber);

        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        modelValidator.validate(model);

        this.model = model;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        vendorNameValidator.validate(vendorName);

        this.vendorName = vendorName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        firmwareVersionValidator.validate(firmwareVersion);

        this.firmwareVersion = firmwareVersion;
    }

    public ModemType getModem() {
        return modem;
    }

    public void setModem(ModemType modem) {
        this.modem = modem;
    }

    @Override
    public boolean validate() {
        return serialNumberValidator.safeValidate(serialNumber) &&
                modelValidator.safeValidate(model) &&
                vendorNameValidator.safeValidate(vendorName) &&
                firmwareVersionValidator.safeValidate(firmwareVersion) &&
                (modem == null || modem.validate());
    }
}
