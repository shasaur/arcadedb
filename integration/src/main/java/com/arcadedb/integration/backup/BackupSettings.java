/*
 * Copyright 2021 Arcade Data Ltd
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.arcadedb.integration.backup;

import java.text.*;
import java.util.*;

public class BackupSettings {
  public       String              format        = "full";
  public       String              databaseURL;
  public       String              file;
  public       boolean             overwriteFile = false;
  public       int                 verboseLevel  = 2;
  public final Map<String, String> options       = new HashMap<>();

  public BackupSettings() {
  }

  protected void parseParameters(final String[] args) {
    if (args != null)
      for (int i = 0; i < args.length; )
        i += parseParameter(args[i].substring(1), i < args.length - 1 ? args[i + 1] : null);

    if (format == null)
      throw new IllegalArgumentException("Missing backup format");

    if (file == null)
      // ASSIGN DEFAULT FILENAME
      switch (format) {
      case "full":
        file = "arcadedb-backup-%s.zip";
        break;
      }

    if (file == null) {
      final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
      file = String.format(file, dateFormat.format(System.currentTimeMillis()));
    }
  }

  public int parseParameter(final String name, final String value) {
    if ("format".equals(name))
      format = value.toLowerCase();
    else if ("f".equals(name))
      file = value;
    else if ("d".equals(name))
      databaseURL = value;
    else if ("o".equals(name)) {
      overwriteFile = true;
      return 1;
    } else
      // ADDITIONAL OPTIONS
      options.put(name, value);
    return 2;
  }
}
