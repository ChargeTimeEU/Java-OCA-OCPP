/*
   ChargeTime.eu - Java-OCA-OCPP

   MIT License

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

package eu.chargetime.ocpp.v201.model.types;

import com.google.gson.annotations.SerializedName;

/** The connector type. */
public enum ConnectorEnum {
  cCCS1,
  cCCS2,
  cG105,
  cTesla,
  cType1,
  cType2,
  @SerializedName("s309-1P-16A")
  s309_1P_16A,
  @SerializedName("s309-1P-32A")
  s309_1P_32A,
  @SerializedName("s309-3P-16A")
  s309_3P_16A,
  @SerializedName("s309-3P-32A")
  s309_3P_32A,
  sBS1361,
  @SerializedName("sCEE-7-7")
  sCEE_7_7,
  sType2,
  sType3,
  Other1PhMax16A,
  Other1PhOver16A,
  Other3Ph,
  Pan,
  wInductive,
  wResonant,
  Undetermined,
  Unknown
}
