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

import eu.chargetime.ocpp.PropertyConstraintException;
import eu.chargetime.ocpp.utilities.MoreObjects;
import java.util.Objects;
import javax.annotation.Nullable;

/**
 * Message Content
 *
 * <p>Message details, for a message to be displayed on a Charging Station.
 */
public final class MessageContent {
  /** Custom data */
  @Nullable private CustomData customData;

  /**
   * Message Content. Format. Message Format Code
   *
   * <p>Format of the message.
   */
  private MessageFormatEnum format;

  /**
   * Message Content. Language. Language Code
   *
   * <p>Message language identifier. Contains a language code as defined in [RFC5646].
   */
  @Nullable private String language;

  /**
   * Message Content. Content. Message
   *
   * <p>Message contents.
   */
  private String content;

  /**
   * Constructor for the MessageContent class
   *
   * @param format Format of the message.
   * @param content Message contents.
   */
  public MessageContent(MessageFormatEnum format, String content) {
    setFormat(format);
    setContent(content);
  }

  /**
   * Gets custom data
   *
   * @return Custom data
   */
  @Nullable
  public CustomData getCustomData() {
    return customData;
  }

  /**
   * Sets custom data
   *
   * @param customData Custom data
   */
  public void setCustomData(@Nullable CustomData customData) {
    if (!isValidCustomData(customData)) {
      throw new PropertyConstraintException(customData, "customData is invalid");
    }
    this.customData = customData;
  }

  /**
   * Returns whether the given customData is valid
   *
   * @param customData the customData to check the validity of
   * @return {@code true} if customData is valid, {@code false} if not
   */
  private boolean isValidCustomData(@Nullable CustomData customData) {
    return customData == null || customData.validate();
  }

  /**
   * Adds custom data
   *
   * @param customData Custom data
   * @return this
   */
  public MessageContent withCustomData(@Nullable CustomData customData) {
    setCustomData(customData);
    return this;
  }

  /**
   * Gets format of the message.
   *
   * @return Format of the message
   */
  public MessageFormatEnum getFormat() {
    return format;
  }

  /**
   * Sets format of the message.
   *
   * @param format Format of the message
   */
  public void setFormat(MessageFormatEnum format) {
    if (!isValidFormat(format)) {
      throw new PropertyConstraintException(format, "format is invalid");
    }
    this.format = format;
  }

  /**
   * Returns whether the given format is valid
   *
   * @param format the format to check the validity of
   * @return {@code true} if format is valid, {@code false} if not
   */
  private boolean isValidFormat(MessageFormatEnum format) {
    return format != null;
  }

  /**
   * Gets message language identifier. Contains a language code as defined in [RFC5646].
   *
   * @return Message language identifier
   */
  @Nullable
  public String getLanguage() {
    return language;
  }

  /**
   * Sets message language identifier. Contains a language code as defined in [RFC5646].
   *
   * @param language Message language identifier
   */
  public void setLanguage(@Nullable String language) {
    if (!isValidLanguage(language)) {
      throw new PropertyConstraintException(language, "language is invalid");
    }
    this.language = language;
  }

  /**
   * Returns whether the given language is valid
   *
   * @param language the language to check the validity of
   * @return {@code true} if language is valid, {@code false} if not
   */
  private boolean isValidLanguage(@Nullable String language) {
    return language == null || language.length() <= 8;
  }

  /**
   * Adds message language identifier. Contains a language code as defined in [RFC5646].
   *
   * @param language Message language identifier
   * @return this
   */
  public MessageContent withLanguage(@Nullable String language) {
    setLanguage(language);
    return this;
  }

  /**
   * Gets message contents.
   *
   * @return Message contents
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets message contents.
   *
   * @param content Message contents
   */
  public void setContent(String content) {
    if (!isValidContent(content)) {
      throw new PropertyConstraintException(content, "content is invalid");
    }
    this.content = content;
  }

  /**
   * Returns whether the given content is valid
   *
   * @param content the content to check the validity of
   * @return {@code true} if content is valid, {@code false} if not
   */
  private boolean isValidContent(String content) {
    return content != null && content.length() <= 512;
  }

  public boolean validate() {
    return isValidCustomData(customData)
        && isValidFormat(format)
        && isValidLanguage(language)
        && isValidContent(content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageContent that = (MessageContent) o;
    return Objects.equals(customData, that.customData)
        && Objects.equals(format, that.format)
        && Objects.equals(language, that.language)
        && Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customData, format, language, content);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("customData", customData)
        .add("format", format)
        .add("language", language)
        .add("content", content)
        .add("isValid", validate())
        .toString();
  }
}
