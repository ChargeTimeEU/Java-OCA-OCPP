package eu.chargetime.ocpp;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (C) 2016-2018 Thomas Volden

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

/**
 * Exception used when validating fields.
 */
public class PropertyConstraintException extends Exception {
    private final String fieldKey;
    private final Object fieldValue;
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @return value of the failing field.
     */
    public Object getFieldValue() {
        return fieldValue;
    }

    /**
     * @return name of the failing field.
     */
    public String getFieldKey() {
        return fieldKey;
    }

    public PropertyConstraintException(String fieldKey, Object fieldValue) {
        this(fieldKey, fieldValue, null);
    }

    public PropertyConstraintException(String fieldKey, Object fieldValue, String message) {

        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.message = message;
    }

    @Override
    public String toString() {
        String output;
        if (fieldValue != null) {
            output = String.format("Field %s %s with value %s", fieldKey, message, fieldValue);
        }
        else {
            output = String.format("Field %s has invalid value %s", fieldKey, fieldValue);
        }
        return output;
    }

}
