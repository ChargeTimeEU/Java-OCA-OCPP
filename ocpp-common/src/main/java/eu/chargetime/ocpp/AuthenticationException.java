package eu.chargetime.ocpp;

import java.io.Serializable;

public class AuthenticationException extends Exception implements Serializable {
  private static final long serialVersionUID = -2323276402779073385L;
  private final int errorcode;

  public AuthenticationException(int errorcode) {
    this.errorcode = errorcode;
  }

  public AuthenticationException(int errorcode, String s) {
    super(s);
    this.errorcode = errorcode;
  }

  public AuthenticationException(int errorcode, Throwable t) {
    super(t);
    this.errorcode = errorcode;
  }

  public AuthenticationException(int errorcode, String s, Throwable t) {
    super(s, t);
    this.errorcode = errorcode;
  }

  public int getErrorCode() {
    return this.errorcode;
  }
}
