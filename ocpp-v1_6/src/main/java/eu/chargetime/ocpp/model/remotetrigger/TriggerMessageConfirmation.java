package eu.chargetime.ocpp.model.remotetrigger;

import eu.chargetime.ocpp.model.Confirmation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 ChargeTime.eu - Java-OCA-OCPP
 Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>

 MIT License

 Copyright (C) 2017 Emil Christopher Solli Melar

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

@XmlRootElement(name = "triggerMessageResponse")
public class TriggerMessageConfirmation implements Confirmation {
    private TriggerMessageStatus status;

    public TriggerMessageConfirmation() {
    }

    /**
     * Set required values.
     *
     * @param status the {@link TriggerMessageStatus}, see {@link #setStatus(TriggerMessageStatus)}.
     */
    public TriggerMessageConfirmation(TriggerMessageStatus status) {
        setStatus(status);
    }

    /**
     * This indicates the success or failure of the trigger message request.
     *
     * @return the {@link TriggerMessageStatus}.
     */
    public TriggerMessageStatus getStatus() {
        return status;
    }

    /**
     * Required. This indicates the success or failure of trigger message request.
     *
     * @param status the {@link TriggerMessageStatus}.
     */
    @XmlElement
    public void setStatus(TriggerMessageStatus status) {
        this.status = status;
    }


    @Override
    public boolean validate() {
        return this.status != null;
    }
}
