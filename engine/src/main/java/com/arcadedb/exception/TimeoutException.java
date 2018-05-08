/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.exception;

public class TimeoutException extends RuntimeException {
  public TimeoutException() {
  }

  public TimeoutException(String message) {
    super(message);
  }

  public TimeoutException(String message, Throwable cause) {
    super(message, cause);
  }

  public TimeoutException(Throwable cause) {
    super(cause);
  }

  public TimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
