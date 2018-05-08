/*
 * Copyright (c) 2018 - Arcade Analytics LTD (https://arcadeanalytics.com)
 */

package com.arcadedb.console;

public class ConsoleException extends RuntimeException {
  public ConsoleException() {
  }

  public ConsoleException(String message) {
    super(message);
  }

  public ConsoleException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConsoleException(Throwable cause) {
    super(cause);
  }

  public ConsoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
