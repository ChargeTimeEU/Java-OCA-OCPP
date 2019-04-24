package eu.chargetime.ocpp.test.features;
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

import static eu.chargetime.ocpp.utilities.TestUtilities.aList;

import eu.chargetime.ocpp.features.basic.GetVariablesFeature;
import eu.chargetime.ocpp.features.basic.handlers.IClientGetVariablesRequestHandler;
import eu.chargetime.ocpp.model.basic.GetVariablesConfirmation;
import eu.chargetime.ocpp.model.basic.GetVariablesRequest;
import eu.chargetime.ocpp.model.basic.types.*;

public class GetVariables implements IClientGetVariablesRequestHandler {
  private GetVariablesFeature feature;
  private GetVariablesConfirmation confirmation;

  public GetVariables() {
    feature = new GetVariablesFeature(this);
    confirmation = createConfirmation();
  }

  private GetVariablesConfirmation createConfirmation() {
    VariableType variableType = new VariableType();
    variableType.setName("A name");

    ComponentType componentType = new ComponentType();
    componentType.setName("A name");

    GetVariableResultType getVariableResultType = new GetVariableResultType();
    getVariableResultType.setAttributeStatus(GetVariableStatusEnumType.Accepted);
    getVariableResultType.setAttributeValue("Some value");
    getVariableResultType.setComponent(componentType);
    getVariableResultType.setVariable(variableType);

    GetVariablesConfirmation confirmation = new GetVariablesConfirmation();
    confirmation.setGetVariableResult(aList(getVariableResultType));
    return confirmation;
  }

  public GetVariablesConfirmation getConfirmation() {
    return confirmation;
  }

  public GetVariablesFeature getFeature() {
    return feature;
  }

  public GetVariablesRequest createRequest() {
    VariableType variableType = new VariableType();
    variableType.setName("A name");

    ComponentType componentType = new ComponentType();
    componentType.setName("A name");

    GetVariableDataType getVariableDataType = new GetVariableDataType();
    getVariableDataType.setComponent(componentType);
    getVariableDataType.setVariable(variableType);

    GetVariablesRequest request = new GetVariablesRequest();
    request.setGetVariableData(aList(getVariableDataType));

    return request;
  }

  @Override
  public GetVariablesConfirmation handleGetVariablesRequest(GetVariablesRequest request) {
    return confirmation;
  }
}
