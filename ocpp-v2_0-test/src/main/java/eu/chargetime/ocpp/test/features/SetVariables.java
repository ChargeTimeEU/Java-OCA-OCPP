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

import eu.chargetime.ocpp.features.basic.SetVariablesFeature;
import eu.chargetime.ocpp.features.basic.handlers.IClientSetVariablesRequestHandler;
import eu.chargetime.ocpp.model.basic.SetVariablesConfirmation;
import eu.chargetime.ocpp.model.basic.SetVariablesRequest;
import eu.chargetime.ocpp.model.basic.types.*;

public class SetVariables implements IClientSetVariablesRequestHandler {
  private SetVariablesFeature feature;
  private SetVariablesConfirmation confirmation;

  public SetVariables() {
    feature = new SetVariablesFeature(this);
    confirmation = createConfirmation();
  }

  private SetVariablesConfirmation createConfirmation() {
    VariableType variableType = new VariableType();
    variableType.setName("A name");

    ComponentType componentType = new ComponentType();
    componentType.setName("A name");

    SetVariableResultType setVariableResultType = new SetVariableResultType();
    setVariableResultType.setAttributeStatus(SetVariableStatusEnumType.Accepted);
    setVariableResultType.setComponent(componentType);
    setVariableResultType.setVariable(variableType);

    SetVariablesConfirmation confirmation = new SetVariablesConfirmation();
    confirmation.setSetVariableResult(aList(setVariableResultType));
    return confirmation;
  }

  public SetVariablesConfirmation getConfirmation() {
    return confirmation;
  }

  public SetVariablesFeature getFeature() {
    return feature;
  }

  @Override
  public SetVariablesConfirmation handleSetVariablesRequest(SetVariablesRequest request) {
    return confirmation;
  }

  public SetVariablesRequest createRequest() {
    VariableType variableType = new VariableType();
    variableType.setName("A name");

    ComponentType componentType = new ComponentType();
    componentType.setName("A name");

    SetVariableDataType setVariableDataType = new SetVariableDataType();
    setVariableDataType.setAttributeValue("A variable");
    setVariableDataType.setComponent(componentType);
    setVariableDataType.setVariable(variableType);

    SetVariablesRequest request = new SetVariablesRequest();
    request.setSetVariableData(aList(setVariableDataType));

    return request;
  }
}
