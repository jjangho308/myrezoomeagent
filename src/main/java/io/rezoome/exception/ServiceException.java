package io.rezoome.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends RuntimeException {

  private static final Logger LOG = LoggerFactory.getLogger("ERROR_LOG");
  private static final long serialVersionUID = 1234567890123456789L;
  private String errorCode;

  public ServiceException(String errorCode) {
    super(errorCode);
    this.errorCode = String.valueOf(errorCode);
  }

  public ServiceException(Integer errorCode) {
    super(String.valueOf(errorCode));
    this.errorCode = String.valueOf(errorCode);
  }

  public ServiceException(String errorCode, String message) {
    super(errorCode + ": " + message);
    this.errorCode = String.valueOf(errorCode);
  }

  public ServiceException(String errorCode, Throwable e) {
    super(errorCode, e);
    this.errorCode = String.valueOf(errorCode);
    // String errorMessage = String.format("%s:%s - %d %s %s %s", resultCode, resultMessage,
    // httpStatusCode, method, lookupPath, companyErrorDetail);
    LOG.error(errorCode, e);
  }

  public ServiceException(String errorCode, String message, Throwable e) {
    super(errorCode + ": " + message, e);
    this.errorCode = String.valueOf(errorCode);
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = String.valueOf(errorCode);
  }

  public String getErrorCode() {
    return errorCode;
  }

}
