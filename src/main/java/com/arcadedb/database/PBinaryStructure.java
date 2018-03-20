package com.arcadedb.database;

import java.nio.ByteBuffer;

public interface PBinaryStructure {
  void append(PBinary toCopy);

  int position();

  void position(int index);

  void putByte(int index, byte value);

  void putByte(byte value);

  int putNumber(int index, long value);

  int putNumber(long value);

  int putUnsignedNumber(int index, long value);

  int putUnsignedNumber(long value);

  void putShort(int index, short value);

  void putShort(short value);

  void putInt(int index, int value);

  void putInt(int value);

  void putLong(int index, long value);

  void putLong(long value);

  int putString(int index, String value);

  int putString(String value);

  int putBytes(int index, byte[] value);

  int putBytes(byte[] value);

  void putByteArray(int index, byte[] value);

  void putByteArray(byte[] value);

  byte getByte(int index);

  byte getByte();

  long[] getNumberAndSize(int index);

  long getNumber(int index);

  long getNumber();

  long getUnsignedNumber();

  long[] getUnsignedNumberAndSize();

  short getShort(int index);

  short getShort();

  short getUnsignedShort();

  int getInt();

  int getInt(int index);

  long getLong();

  long getLong(int index);

  String getString();

  String getString(int index);

  void getByteArray(byte[] buffer);

  void getByteArray(int index, byte[] buffer);

  byte[] getBytes();

  byte[] getBytes(int index);

  byte[] toByteArray();

  ByteBuffer getByteBuffer();

  int size();
}
