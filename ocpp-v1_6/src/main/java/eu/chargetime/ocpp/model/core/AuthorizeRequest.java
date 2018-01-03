package eu.chargetime.ocpp.model.core;

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.utilities.ModelUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
 * Sent by the Charge Point to the Central System.
 */
@XmlRootElement
public class AuthorizeRequest implements Request
{
    private String idTag;

    public AuthorizeRequest() {}

    /**
     * Handle required fields.
     *
     * @param idToken authorize id.
     * @throws PropertyConstraintException field isn't filled out correct.
     */
    public AuthorizeRequest(String idToken) throws PropertyConstraintException
    {
         setIdTag(idToken);
    }

    /**
     * This contains the identifier that needs to be authorized.
     *
     * @return String, max 20 characters. Case insensitive.
     */
    public String getIdTag()
    {
        return idTag;
    }

    /**
     * Required. This contains the identifier that needs to be authorized.
     *
     * @param idTag                         String, max 20 characters. Case insensitive.
     * @throws PropertyConstraintException  field isn't filled out correct.
     */
    @XmlElement
    public void setIdTag(String idTag) throws PropertyConstraintException {
        if (!ModelUtil.validate(idTag, 20))
            throw new PropertyConstraintException("idTag", idTag, "Exceeded limit");

        this.idTag = idTag;
    }

    @Override
    public boolean validate() {
        boolean valid = true;
        valid &= ModelUtil.validate(idTag, 20);
        return valid;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }
}
