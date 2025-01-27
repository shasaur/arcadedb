/* GraphQLParserTokenManager.java */
/* Generated by:  JJTree&ParserGeneratorCC: Do not edit this line. GraphQLParserTokenManager.java */
package com.arcadedb.graphql.parser;

import java.io.IOException;

/** Token Manager. */
@SuppressWarnings ("unused")
public class GraphQLParserTokenManager implements GraphQLParserConstants {
private final int jjStopStringLiteralDfa_0(final int pos, final long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0xffff80000L) != 0x0L)
         {
            jjmatchedKind = 54;
            return 20;
         }
         if ((active0 & 0x4L) != 0x0L)
            return 5;
         if ((active0 & 0x20000L) != 0x0L)
            return 37;
         return -1;
      case 1:
         if ((active0 & 0x800000000L) != 0x0L)
            return 20;
         if ((active0 & 0x7fff80000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 1;
            return 20;
         }
         return -1;
      case 2:
         if ((active0 & 0x7fff80000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 2;
            return 20;
         }
         return -1;
      case 3:
         if ((active0 & 0x2f6f80000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 3;
            return 20;
         }
         if ((active0 & 0x509000000L) != 0x0L)
            return 20;
         return -1;
      case 4:
         if ((active0 & 0xc6e80000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 4;
            return 20;
         }
         if ((active0 & 0x230100000L) != 0x0L)
            return 20;
         return -1;
      case 5:
         if ((active0 & 0x86280000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 5;
            return 20;
         }
         if ((active0 & 0x40c00000L) != 0x0L)
            return 20;
         return -1;
      case 6:
         if ((active0 & 0x86280000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 6;
            return 20;
         }
         return -1;
      case 7:
         if ((active0 & 0x86000000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 7;
            return 20;
         }
         if ((active0 & 0x280000L) != 0x0L)
            return 20;
         return -1;
      case 8:
         if ((active0 & 0x4000000L) != 0x0L)
         {
            jjmatchedKind = 54;
            jjmatchedPos = 8;
            return 20;
         }
         if ((active0 & 0x82000000L) != 0x0L)
            return 20;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(final int pos, final long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(final int pos, final int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case '!':
         return jjStopAtPos(0, 3);
      case '#':
         return jjStartNfaWithStates_0(0, 2, 5);
      case '$':
         return jjStopAtPos(0, 4);
      case '(':
         return jjStopAtPos(0, 5);
      case ')':
         return jjStopAtPos(0, 6);
      case '+':
         return jjStopAtPos(0, 16);
      case ',':
         return jjStopAtPos(0, 1);
      case '-':
         return jjStartNfaWithStates_0(0, 17, 37);
      case '.':
         return jjMoveStringLiteralDfa1_0(0x80L);
      case ':':
         return jjStopAtPos(0, 8);
      case '=':
         return jjStopAtPos(0, 9);
      case '@':
         return jjStopAtPos(0, 10);
      case '[':
         return jjStopAtPos(0, 11);
      case ']':
         return jjStopAtPos(0, 12);
      case 'd':
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 'e':
         return jjMoveStringLiteralDfa1_0(0x48000000L);
      case 'f':
         return jjMoveStringLiteralDfa1_0(0x200080000L);
      case 'i':
         return jjMoveStringLiteralDfa1_0(0x26000000L);
      case 'm':
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 'n':
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 'o':
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 'q':
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 's':
         return jjMoveStringLiteralDfa1_0(0xc00000L);
      case 't':
         return jjMoveStringLiteralDfa1_0(0x101000000L);
      case 'u':
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case '{':
         return jjStopAtPos(0, 13);
      case '|':
         return jjStopAtPos(0, 14);
      case '}':
         return jjStopAtPos(0, 15);
      case 65279:
         return jjStopAtPos(0, 40);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(final long active0){
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case '.':
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 'a':
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000L);
      case 'c':
         return jjMoveStringLiteralDfa2_0(active0, 0xc00000L);
      case 'i':
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 'm':
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 'n':
         if ((active0 & 0x800000000L) != 0x0L)
            return jjStartNfaWithStates_0(1, 35, 20);
         return jjMoveStringLiteralDfa2_0(active0, 0x3a000000L);
      case 'r':
         return jjMoveStringLiteralDfa2_0(active0, 0x100080000L);
      case 'u':
         return jjMoveStringLiteralDfa2_0(active0, 0x400300000L);
      case 'x':
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000L);
      case 'y':
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case '.':
         if ((active0 & 0x80L) != 0x0L)
            return jjStopAtPos(2, 7);
         break;
      case 'a':
         return jjMoveStringLiteralDfa3_0(active0, 0x880000L);
      case 'e':
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 'h':
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 'i':
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000L);
      case 'l':
         return jjMoveStringLiteralDfa3_0(active0, 0x600000000L);
      case 'p':
         return jjMoveStringLiteralDfa3_0(active0, 0x25000000L);
      case 'r':
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 't':
         return jjMoveStringLiteralDfa3_0(active0, 0x42200000L);
      case 'u':
         return jjMoveStringLiteralDfa3_0(active0, 0x108000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 'e':
         if ((active0 & 0x1000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 24, 20);
         else if ((active0 & 0x100000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 32, 20);
         return jjMoveStringLiteralDfa4_0(active0, 0xc2400000L);
      case 'g':
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 'l':
         if ((active0 & 0x400000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 34, 20);
         return jjMoveStringLiteralDfa4_0(active0, 0x4800000L);
      case 'm':
         if ((active0 & 0x8000000L) != 0x0L)
            return jjStartNfaWithStates_0(3, 27, 20);
         break;
      case 'o':
         return jjMoveStringLiteralDfa4_0(active0, 0x10000000L);
      case 'r':
         return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 's':
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 'u':
         return jjMoveStringLiteralDfa4_0(active0, 0x20000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 'c':
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000L);
      case 'e':
         if ((active0 & 0x200000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 33, 20);
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000L);
      case 'm':
         return jjMoveStringLiteralDfa5_0(active0, 0x480000L);
      case 'n':
         if ((active0 & 0x10000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 28, 20);
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000L);
      case 'r':
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 't':
         if ((active0 & 0x20000000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 29, 20);
         return jjMoveStringLiteralDfa5_0(active0, 0x200000L);
      case 'y':
         if ((active0 & 0x100000L) != 0x0L)
            return jjStartNfaWithStates_0(4, 20, 20);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 'a':
         if ((active0 & 0x400000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 22, 20);
         break;
      case 'd':
         if ((active0 & 0x40000000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 30, 20);
         break;
      case 'e':
         return jjMoveStringLiteralDfa6_0(active0, 0x80000L);
      case 'f':
         return jjMoveStringLiteralDfa6_0(active0, 0x2000000L);
      case 'i':
         return jjMoveStringLiteralDfa6_0(active0, 0x200000L);
      case 'm':
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000L);
      case 'r':
         if ((active0 & 0x800000L) != 0x0L)
            return jjStartNfaWithStates_0(5, 23, 20);
         break;
      case 't':
         return jjMoveStringLiteralDfa6_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000L);
      case 'e':
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000L);
      case 'i':
         return jjMoveStringLiteralDfa7_0(active0, 0x80000000L);
      case 'n':
         return jjMoveStringLiteralDfa7_0(active0, 0x80000L);
      case 'o':
         return jjMoveStringLiteralDfa7_0(active0, 0x200000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 'c':
         return jjMoveStringLiteralDfa8_0(active0, 0x2000000L);
      case 'n':
         if ((active0 & 0x200000L) != 0x0L)
            return jjStartNfaWithStates_0(7, 21, 20);
         return jjMoveStringLiteralDfa8_0(active0, 0x4000000L);
      case 't':
         if ((active0 & 0x80000L) != 0x0L)
            return jjStartNfaWithStates_0(7, 19, 20);
         break;
      case 'v':
         return jjMoveStringLiteralDfa8_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 'e':
         if ((active0 & 0x2000000L) != 0x0L)
            return jjStartNfaWithStates_0(8, 25, 20);
         else if ((active0 & 0x80000000L) != 0x0L)
            return jjStartNfaWithStates_0(8, 31, 20);
         break;
      case 't':
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(final long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 's':
         if ((active0 & 0x4000000L) != 0x0L)
            return jjStartNfaWithStates_0(9, 26, 20);
         break;
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjStartNfaWithStates_0(final int pos, final int kind, final int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(final IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x30000000000L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec1 = {
   0xfffffffeL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec3 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec4 = {
   0xffffffffffL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec5 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec6 = {
   0x0L, 0x0L, 0x100000000L, 0x0L
};
private int jjMoveNfa_0(final int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 42;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         final long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0x0L)
                  {
                     if (kind > 36)
                        kind = 36;
                  }
                  else if ((0x2400L & l) != 0x0L)
                  {
                     if (kind > 41)
                        kind = 41;
                  }
                  else if ((0x280000000000L & l) != 0x0L)
                  {
                     if (kind > 18)
                        kind = 18;
                  }
                  else if (curChar == 34)
                     { jjCheckNAddStates(0, 4); }
                  else if (curChar == 35)
                  {
                     if (kind > 43)
                        kind = 43;
                     { jjCheckNAdd(5); }
                  }
                  else if (curChar == 44)
                  {
                     if (kind > 42)
                        kind = 42;
                  }
                  if ((0x3fe000000000000L & l) != 0x0L)
                  {
                     if (kind > 37)
                        kind = 37;
                     { jjCheckNAddStates(5, 11); }
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 51)
                        kind = 51;
                     { jjCheckNAddStates(12, 14); }
                  }
                  else if (curChar == 45)
                     { jjAddStates(15, 18); }
                  break;
               case 37:
                  if ((0x3fe000000000000L & l) != 0x0L)
                     { jjCheckNAddStates(19, 24); }
                  else if (curChar == 48)
                     { jjCheckNAddStates(12, 14); }
                  if ((0x3fe000000000000L & l) != 0x0L)
                  {
                     if (kind > 51)
                        kind = 51;
                     { jjCheckNAdd(22); }
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 51)
                        kind = 51;
                  }
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0x0L && kind > 36)
                     kind = 36;
                  break;
               case 2:
                  if ((0x2400L & l) != 0x0L && kind > 41)
                     kind = 41;
                  break;
               case 3:
                  if (curChar == 44 && kind > 42)
                     kind = 42;
                  break;
               case 4:
                  if (curChar != 35)
                     break;
                  if (kind > 43)
                     kind = 43;
                  { jjCheckNAdd(5); }
                  break;
               case 5:
                  if ((0xffffffffffffdbffL & l) == 0x0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  { jjCheckNAdd(5); }
                  break;
               case 6:
                  if (curChar == 34)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 7:
                  if ((0xfffffffbffffffffL & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 15:
                  if ((0x800400000000L & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 16:
                  if ((0xffffffff00002600L & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0x100001a00L & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 18:
                  if (curChar == 34 && kind > 53)
                     kind = 53;
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 21:
                  if ((0x3fe000000000000L & l) == 0x0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  { jjCheckNAddStates(5, 11); }
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  { jjCheckNAdd(22); }
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddTwoStates(23, 24); }
                  break;
               case 24:
                  if (curChar != 46)
                     break;
                  if (kind > 52)
                     kind = 52;
                  { jjCheckNAdd(25); }
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  { jjCheckNAdd(25); }
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddTwoStates(26, 27); }
                  break;
               case 28:
                  if ((0x280000000000L & l) != 0x0L)
                     { jjCheckNAdd(29); }
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  { jjCheckNAdd(29); }
                  break;
               case 30:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddTwoStates(30, 31); }
                  break;
               case 31:
                  if (curChar == 46)
                     { jjCheckNAddTwoStates(32, 33); }
                  break;
               case 32:
                  if ((0x3ff000000000000L & l) != 0x0L)
                     { jjCheckNAddTwoStates(32, 33); }
                  break;
               case 34:
                  if ((0x280000000000L & l) != 0x0L)
                     { jjCheckNAdd(35); }
                  break;
               case 35:
                  if ((0x3ff000000000000L & l) == 0x0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  { jjCheckNAdd(35); }
                  break;
               case 36:
                  if (curChar == 45)
                     { jjAddStates(15, 18); }
                  break;
               case 38:
                  if ((0x3fe000000000000L & l) == 0x0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  { jjCheckNAdd(22); }
                  break;
               case 39:
                  if (curChar == 48)
                     { jjCheckNAddStates(12, 14); }
                  break;
               case 40:
                  if ((0x3fe000000000000L & l) != 0x0L)
                     { jjCheckNAddStates(19, 24); }
                  break;
               case 41:
                  if (curChar != 48)
                     break;
                  if (kind > 51)
                     kind = 51;
                  { jjCheckNAddStates(12, 14); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         final long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 20:
                  if ((0x7fffffe87fffffeL & l) == 0x0L)
                     break;
                  if (kind > 54)
                     kind = 54;
                  { jjCheckNAdd(20); }
                  break;
               case 5:
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0xffffffffefffffffL & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 8:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 9:
                  if ((0x7e0000007eL & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 10:
                  if ((0x7e0000007eL & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0x7e0000007eL & l) != 0x0L)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 12:
                  if ((0x7e0000007eL & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 13:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 14:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 15:
                  if ((0x14404410000000L & l) != 0x0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 16:
                  jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 27:
                  if ((0x2000000020L & l) != 0x0L)
                     { jjAddStates(25, 26); }
                  break;
               case 33:
                  if ((0x2000000020L & l) != 0x0L)
                     { jjAddStates(27, 28); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         final int i2 = (curChar & 0xff) >> 6;
         final long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) != 0L && kind > 41)
                     kind = 41;
                  break;
               case 5:
                  if ((jjbitVec3[i2] & l2) == 0L)
                     break;
                  if (kind > 43)
                     kind = 43;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((jjbitVec3[i2] & l2) != 0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               case 16:
                  if ((jjbitVec3[i2] & l2) != 0L)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((jjbitVec6[i2] & l2) != 0L)
                     { jjCheckNAddStates(0, 4); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 42 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final IOException e) { return curPos; }
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", "\54", "\43", "\41", "\44", "\50", "\51", "\56\56\56", "\72", "\75",
"\100", "\133", "\135", "\173", "\174", "\175", "\53", "\55", null,
"\146\162\141\147\155\145\156\164", "\161\165\145\162\171", "\155\165\164\141\164\151\157\156",
"\163\143\150\145\155\141", "\163\143\141\154\141\162", "\164\171\160\145",
"\151\156\164\145\162\146\141\143\145", "\151\155\160\154\145\155\145\156\164\163", "\145\156\165\155",
"\165\156\151\157\156", "\151\156\160\165\164", "\145\170\164\145\156\144",
"\144\151\162\145\143\164\151\166\145", "\164\162\165\145", "\146\141\154\163\145", "\156\165\154\154", "\157\156",
null, null, null, null, null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   final String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = im == null ? input_stream.getImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   7, 13, 14, 16, 18, 22, 23, 24, 26, 27, 30, 31, 24, 27, 31, 37,
   38, 39, 40, 23, 24, 26, 27, 30, 31, 28, 29, 34, 35,
};

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken()
{
  final Token matchedToken;
  int curPos = 0;

  EOFLoop:
  for (;;)
  {
   try
   {
      curChar = input_stream.beginToken();
   }
   catch(final Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try {
     input_stream.backup(0);
      while (curChar <= 32 && (0x100000200L & (1L << curChar)) != 0x0L)
         curChar = input_stream.beginToken();
   }
   catch (final IOException e1) {
     continue EOFLoop;
   }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try {
     input_stream.readChar();
     input_stream.backup(1);
   }
   catch (final IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.getImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.getImage();
   }
   throw new TokenMgrException(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrException.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(final Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(final Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(final int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, final int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(final int state1, final int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, final int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public GraphQLParserTokenManager(final CharStream stream){
    input_stream = stream;
  }

  /** Constructor. */
  public GraphQLParserTokenManager (final CharStream stream, final int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */

  public void ReInit(final CharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 42; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(final CharStream stream, final int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(final int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrException.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
   -1, -1, -1, -1, -1,
};
static final long[] jjtoToken = {
   0x78003fffffffffL,
};
static final long[] jjtoSkip = {
   0xfc000000000L,
};
static final long[] jjtoSpecial = {
   0x0L,
};
static final long[] jjtoMore = {
   0x0L,
};
    protected CharStream  input_stream;

    private final int[] jjrounds = new int[42];
    private final int[] jjstateSet = new int[2 * 42];
    private final StringBuilder jjimage = new StringBuilder();
    private final StringBuilder image   = jjimage;
    private       int           jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
