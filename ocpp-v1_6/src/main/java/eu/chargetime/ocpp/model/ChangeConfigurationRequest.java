package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

/**
 ChargeTime.eu - Java-OCA-OCPP

 MIT License

 Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

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

public class ChangeConfigurationRequest implements Request {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) throws PropertyConstraintException {
        if (!isValidKey(key))
            throw new PropertyConstraintException("key", key);

        this.key = key;
    }

    private boolean isValidKey(String key) {
        return ModelUtil.validate(key, 50);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) throws PropertyConstraintException {
        if (!isValidValue(value))
            throw new PropertyConstraintException("value", value);

        this.value = value;
    }

    private boolean isValidValue(String value) {
        return ModelUtil.validate(value, 500);
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= isValidKey(this.key);
        valid &= isValidValue(this.value);
        return valid;
    }
}
