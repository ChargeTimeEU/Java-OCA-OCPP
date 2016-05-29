package eu.chargetime.ocpp.model;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.ModelUtil;

import java.util.Calendar;

/**
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2015-2016 Thomas Volden <tv@chargetime.eu>

 MIT License

 Copyright (c) 2016 Thomas Volden

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
public class IdTagInfo implements Validatable
{
    private Calendar expiryDate;
    private String parentIdTag;

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getParentIdTag() {
        return parentIdTag;
    }

    public void setParentIdTag(String parentIdTag) throws PropertyConstraintException {
        if (!"".equals(parentIdTag) && parentIdTag.length() > 20)
            throw new PropertyConstraintException("parentIdTag", parentIdTag, "Exceeded limit");

        this.parentIdTag = parentIdTag;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws PropertyConstraintException {
        if (! isValidStatus(status))
            throw new PropertyConstraintException("status", status, "Illegal value");

        this.status = status;
    }

    private boolean isValidStatus(String status) {
        return ModelUtil.isAmong(status, "Accepted", "Blocked", "Expired", "Invalid", "ConcurrentTx");
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= isValidStatus(this.status);
        return valid;
    }
}
