package com.arcadedb.serializer;

import com.arcadedb.database.PBinary;
import com.arcadedb.database.PDatabase;
import com.arcadedb.database.PIdentifiable;

import java.math.BigDecimal;
import java.util.Date;

public class PBinaryComparator {

  private final PBinarySerializer serializer;
  protected static final long MILLISEC_PER_DAY = 86400000;

  public PBinaryComparator(final PBinarySerializer serializer) {
    this.serializer = serializer;
  }

  /**
   * Compares if two values are the same.
   *
   * @param buffer1 First value to compare
   * @param type1   Type of first value
   * @param buffer2 Second value to compare
   * @param type2   Type of second value
   *
   * @return true if they match, otherwise false
   */
  public boolean equals(final PDatabase database, final PBinary buffer1, final byte type1, final PBinary buffer2,
      final byte type2) {
    final Object value1 = serializer.deserializeValue(database, buffer1, type1);
    final Object value2 = serializer.deserializeValue(database, buffer2, type2);

    return equals(value1, type1, value2, type2);
  }

  public boolean equals(final Object value1, final byte type1, final Object value2, final byte type2) {
    switch (type1) {
    case PBinaryTypes.TYPE_INT: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).intValue() == ((Number) value2).intValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).intValue() == Integer.parseInt((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_LONG: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).longValue() == ((Number) value2).longValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).longValue() == Long.parseLong((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_SHORT: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).shortValue() == ((Number) value2).shortValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).shortValue() == Short.parseShort((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_STRING: {
      return value1.equals(value2.toString());
    }

    case PBinaryTypes.TYPE_DOUBLE: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).doubleValue() == ((Number) value2).doubleValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).doubleValue() == Double.parseDouble((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_FLOAT: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).floatValue() == ((Number) value2).floatValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).floatValue() == Float.parseFloat((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_BYTE: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Number) value1).byteValue() == ((Number) value2).byteValue();

      case PBinaryTypes.TYPE_STRING:
        return ((Number) value1).byteValue() == Byte.parseByte((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_BOOLEAN: {
      switch (type2) {
      case PBinaryTypes.TYPE_BOOLEAN:
        return value1.equals(value2);

      case PBinaryTypes.TYPE_STRING:
        return value1.equals(Boolean.parseBoolean((String) value2));

      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        if ((boolean) value1)
          return ((Number) value2).intValue() == 1;

        return ((Number) value2).intValue() == 0;
      }
      break;
    }

    case PBinaryTypes.TYPE_DATE:
    case PBinaryTypes.TYPE_DATETIME: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        return ((Date) value1).getTime() == ((Number) value2).longValue();

      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_DATETIME:
        return ((Date) value1).getTime() == ((Date) value2).getTime();

      case PBinaryTypes.TYPE_STRING:
        return ((Date) value1).getTime() == Long.parseLong((String) value2);
      }
      break;
    }

    case PBinaryTypes.TYPE_BINARY: {
      switch (type2) {
      case PBinaryTypes.TYPE_BINARY: {
      }
      }
      throw new UnsupportedOperationException("Comparing binary types");
    }

    case PBinaryTypes.TYPE_DECIMAL: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
        return value1.equals(new BigDecimal((Integer) value2));
      case PBinaryTypes.TYPE_SHORT:
        return value1.equals(new BigDecimal((Short) value2));
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
        return value1.equals(new BigDecimal((Long) value2));
      case PBinaryTypes.TYPE_BYTE:
        return value1.equals(new BigDecimal((Byte) value2));
      case PBinaryTypes.TYPE_DECIMAL:
        return value1.equals(value2);
      case PBinaryTypes.TYPE_FLOAT:
        return value1.equals(new BigDecimal((Float) value2));
      case PBinaryTypes.TYPE_DOUBLE:
        return value1.equals(new BigDecimal((Double) value2));
      case PBinaryTypes.TYPE_STRING:
        return value1.equals(new BigDecimal((String) value2));
      }
      break;
    }
    }

    return false;
  }

  public int compareStrings(final String string1, final PBinary buffer2) {
    final long b1Size = string1.length();
    final long b2Size = buffer2.getNumber();

    final int minSize = (int) Math.min(b1Size, b2Size);

    final byte[] buffer1 = string1.getBytes();

    for (int i = 0; i < minSize; ++i) {
      final byte b1 = buffer1[i];
      final byte b2 = buffer2.getByte();

      if (b1 > b2)
        return 1;
      else if (b1 < b2)
        return -1;
    }

    if (b1Size == b2Size)
      return 0;

    if (b1Size > b2Size)
      return 1;

    return -1;
  }

  public boolean equalsStrings(final PBinary buffer1, final PBinary buffer2) {
    final long b1Size = buffer1.getNumber();
    final long b2Size = buffer2.getNumber();

    if (b1Size != b2Size)
      return false;

    for (int i = 0; i < b1Size; ++i) {
      final byte b1 = buffer1.getByte();
      final byte b2 = buffer2.getByte();

      if (b1 != b2)
        return false;
    }
    return true;
  }

  /**
   * Compares if two values are the same.
   *
   * @param buffer1 First value to compare
   * @param type1   Type of first value
   * @param buffer2 Second value to compare
   * @param type2   Type of second value
   *
   * @return 0 if they matches, >0 if first value is major than second, <0 in case is minor
   */
  public int compare(final PDatabase database, final PBinary buffer1, final byte type1, final PBinary buffer2, final byte type2) {
    final Object value1 = serializer.deserializeValue(database, buffer1, type1);
    final Object value2 = serializer.deserializeValue(database, buffer2, type2);

    return compare(value1, type1, value2, type2);
  }

  public int compare(final Object value1, final byte type1, final Object value2, final byte type2) {

    switch (type1) {
    case PBinaryTypes.TYPE_INT: {
      final int v1 = ((Number) value1).intValue();
      final int v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).intValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (int) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Integer.parseInt((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_LONG: {
      final long v1 = ((Number) value1).longValue();
      final long v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).longValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (long) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Long.parseLong((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_SHORT: {
      final short v1 = ((Number) value1).shortValue();
      final short v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).shortValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (short) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Short.parseShort((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_STRING: {
      return ((String) value1).compareTo(value2.toString());
    }

    case PBinaryTypes.TYPE_DOUBLE: {
      final double v1 = ((Number) value1).doubleValue();
      final double v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).doubleValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (double) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Double.parseDouble((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_FLOAT: {
      final float v1 = ((Number) value1).floatValue();
      final float v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).floatValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (float) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Float.parseFloat((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_BYTE: {
      final byte v1 = ((Number) value1).byteValue();
      final byte v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).byteValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = (byte) (((Boolean) value2) ? 1 : 0);
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Byte.parseByte((String) value2);
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_BOOLEAN: {
      final int v1 = ((Boolean) value1) ? 1 : 0;
      final int v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).byteValue();
        break;

      case PBinaryTypes.TYPE_BOOLEAN:
        v2 = ((Boolean) value2) ? 1 : 0;
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Boolean.parseBoolean((String) value2) ? 1 : 0;
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_DATE:
    case PBinaryTypes.TYPE_DATETIME: {
      final long v1 = ((Boolean) value1) ? 1 : 0;
      final long v2;

      switch (type2) {
      case PBinaryTypes.TYPE_INT:
      case PBinaryTypes.TYPE_SHORT:
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
      case PBinaryTypes.TYPE_BYTE:
      case PBinaryTypes.TYPE_DECIMAL:
      case PBinaryTypes.TYPE_FLOAT:
      case PBinaryTypes.TYPE_DOUBLE:
        v2 = ((Number) value2).longValue();
        break;

      case PBinaryTypes.TYPE_STRING:
        v2 = Long.parseLong(value2.toString());
        break;

      default:
        return -1;
      }

      if (v1 == v2)
        return 0;
      if (v1 > v2)
        return 1;
      return -1;
    }

    case PBinaryTypes.TYPE_BINARY: {
      switch (type2) {
      case PBinaryTypes.TYPE_BINARY: {
      }
      }
      throw new UnsupportedOperationException("Comparing binary types");
    }

    case PBinaryTypes.TYPE_DECIMAL: {
      switch (type2) {
      case PBinaryTypes.TYPE_INT:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Integer) value2));
      case PBinaryTypes.TYPE_SHORT:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Short) value2));
      case PBinaryTypes.TYPE_LONG:
      case PBinaryTypes.TYPE_DATETIME:
      case PBinaryTypes.TYPE_DATE:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Long) value2));
      case PBinaryTypes.TYPE_BYTE:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Byte) value2));
      case PBinaryTypes.TYPE_DECIMAL:
        return ((BigDecimal) value1).compareTo((BigDecimal) value2);
      case PBinaryTypes.TYPE_FLOAT:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Float) value2));
      case PBinaryTypes.TYPE_DOUBLE:
        return ((BigDecimal) value1).compareTo(new BigDecimal((Double) value2));
      case PBinaryTypes.TYPE_STRING:
        return ((BigDecimal) value1).compareTo(new BigDecimal((String) value2));
      }
      break;
    }

    case PBinaryTypes.TYPE_RID: {
      switch (type2) {
      case PBinaryTypes.TYPE_RID:
        return ((PIdentifiable) value1).getIdentity().compareTo((PIdentifiable) value2);
      }
    }
    }

    // NO COMPARE SUPPORTED, RETURN NON EQUALS
    throw new IllegalArgumentException("Comparison between type " + type1 + " and " + type2 + " not supported");
  }
}
