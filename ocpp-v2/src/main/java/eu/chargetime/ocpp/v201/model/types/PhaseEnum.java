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

/**
 * Sampled Value. Phase. Phase Code
 *
 * <p>How the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please
 * note that not all values of phase are applicable to all Measurands. When phase is absent, the
 * measured value is interpreted as an overall value.
 */
public enum PhaseEnum {
  L1,
  L2,
  L3,
  N,
  @SerializedName("L1-N")
  L1_N,
  @SerializedName("L2-N")
  L2_N,
  @SerializedName("L3-N")
  L3_N,
  @SerializedName("L1-L2")
  L1_L2,
  @SerializedName("L2-L3")
  L2_L3,
  @SerializedName("L3-L1")
  L3_L1
}
