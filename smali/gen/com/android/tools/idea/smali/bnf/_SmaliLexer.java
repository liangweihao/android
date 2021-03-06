/* The following code was generated by JFlex 1.7.0-SNAPSHOT tweaked for IntelliJ platform */

package com.android.tools.idea.smali.bnf;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.android.tools.idea.smali.psi.SmaliTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-SNAPSHOT
 * from the specification file <tt>_SmaliLexer.flex</tt>
 */
public class _SmaliLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [7, 7, 7]
   * Total runtime size is 1928 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>14]|((ch>>7)&0x7f)]<<7)|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 68 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\103\200");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\53\2\1\3\22\2\1\4\37\2\1\3\237\2");

  /* The ZZ_CMAP_A table has 640 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\3\1\2\2\1\1\2\22\0\1\46\1\0\1\14\1\4\1\7\2\0\1\30\1\52\1\53\1\0\1"+
    "\22\1\0\1\16\1\20\1\7\1\17\11\11\1\0\1\10\1\12\1\0\1\13\2\0\4\25\1\21\1\25"+
    "\5\6\1\5\13\6\1\24\2\6\1\0\1\15\2\0\1\6\1\0\1\34\1\54\1\32\1\42\1\36\1\23"+
    "\1\47\1\44\1\41\2\6\1\33\1\43\1\45\1\40\1\35\1\6\1\37\1\26\1\27\1\31\1\55"+
    "\1\6\1\24\1\56\1\57\1\50\1\0\1\51\7\0\1\1\32\0\1\3\337\0\1\3\177\0\13\3\35"+
    "\0\2\1\5\0\1\3\57\0\1\3\40\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\1\5\1\1\1\6"+
    "\1\1\1\5\1\1\3\4\1\1\4\4\1\7\1\10"+
    "\1\11\1\12\3\4\1\0\2\5\1\0\1\6\1\0"+
    "\1\5\13\0\5\4\1\0\7\4\1\0\1\13\2\5"+
    "\1\14\1\15\14\0\6\4\1\0\10\4\1\15\14\0"+
    "\5\4\1\16\1\4\1\0\10\4\4\0\1\17\7\0"+
    "\1\20\1\21\4\4\1\22\10\4\1\23\1\24\1\0"+
    "\1\25\1\0\1\26\6\0\1\27\4\4\1\30\3\4"+
    "\1\31\1\32\1\4\1\33\6\0\1\34\5\4\1\35"+
    "\2\4\6\0\3\4\1\36\2\4\1\37\1\0\1\40"+
    "\4\0\1\41\1\4\1\42\1\43\1\44\3\0\1\45"+
    "\1\0\1\4\1\46\1\0\1\47\1\50\1\4\1\0"+
    "\1\51\2\0\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\60\0\140\0\220\0\300\0\360\0\u0120\0\u0150"+
    "\0\u0180\0\u01b0\0\u01e0\0\u0210\0\u0240\0\u0270\0\u02a0\0\u02d0"+
    "\0\u0300\0\u0330\0\u0360\0\u0390\0\60\0\60\0\60\0\60"+
    "\0\u03c0\0\u03f0\0\u0420\0\u0450\0\u0480\0\60\0\u04b0\0\60"+
    "\0\u04e0\0\u0510\0\u0540\0\u0570\0\u05a0\0\u05d0\0\u0600\0\u0630"+
    "\0\u0660\0\u0690\0\u06c0\0\u06f0\0\u0720\0\u0750\0\u0780\0\u07b0"+
    "\0\u07e0\0\u0810\0\u0840\0\u0870\0\u08a0\0\u08d0\0\u0900\0\u0930"+
    "\0\u0960\0\u0990\0\u09c0\0\60\0\u09f0\0\u0a20\0\60\0\u0a50"+
    "\0\u0a80\0\u0ab0\0\u0ae0\0\u0b10\0\u0b40\0\u0b70\0\u0ba0\0\u0bd0"+
    "\0\u0c00\0\u0c30\0\u0c60\0\u0c90\0\u0cc0\0\u0cf0\0\u0d20\0\u0d50"+
    "\0\u0d80\0\u0db0\0\u0de0\0\u0e10\0\u0e40\0\u0e70\0\u0ea0\0\u0ed0"+
    "\0\u0f00\0\u0f30\0\u0f60\0\60\0\u0f90\0\u0fc0\0\u0ff0\0\u1020"+
    "\0\u1050\0\u1080\0\u10b0\0\u10e0\0\u1110\0\u1140\0\u1170\0\u11a0"+
    "\0\u11d0\0\u1200\0\u1230\0\u1260\0\u1290\0\360\0\u12c0\0\u12f0"+
    "\0\u1320\0\u1350\0\u1380\0\u13b0\0\u13e0\0\u1410\0\u1440\0\u1470"+
    "\0\u14a0\0\u14d0\0\u1500\0\u1530\0\60\0\u1560\0\u1590\0\u15c0"+
    "\0\u15f0\0\u1620\0\u1650\0\u1680\0\360\0\360\0\u16b0\0\u16e0"+
    "\0\u1710\0\u1740\0\60\0\u1770\0\u17a0\0\u17d0\0\u1800\0\u1830"+
    "\0\u1860\0\u1890\0\u18c0\0\60\0\60\0\u18f0\0\60\0\u1920"+
    "\0\60\0\u1950\0\u1980\0\u19b0\0\u19e0\0\u1a10\0\u1a40\0\360"+
    "\0\u1a70\0\u1aa0\0\u1ad0\0\u1b00\0\360\0\u1b30\0\u1b60\0\u1b90"+
    "\0\360\0\360\0\u1bc0\0\60\0\u1bf0\0\u1c20\0\u1c50\0\u1c80"+
    "\0\u1cb0\0\u1ce0\0\60\0\u1d10\0\u1d40\0\u1d70\0\u1da0\0\u1dd0"+
    "\0\360\0\u1e00\0\u1e30\0\u1e60\0\u1e90\0\u1ec0\0\u1ef0\0\u1f20"+
    "\0\u1f50\0\u1f80\0\u1fb0\0\u1fe0\0\360\0\u2010\0\u2040\0\360"+
    "\0\u2070\0\60\0\u20a0\0\u20d0\0\u2100\0\u2130\0\360\0\u2160"+
    "\0\360\0\360\0\360\0\u2190\0\u21c0\0\u21f0\0\60\0\u2220"+
    "\0\u2250\0\60\0\u2280\0\60\0\60\0\u22b0\0\u22e0\0\360"+
    "\0\u2310\0\u2340\0\60";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\3\3\1\4\1\5\1\6\2\2\1\7\1\10"+
    "\1\2\1\11\1\2\1\12\1\13\1\14\1\6\1\2"+
    "\1\15\2\6\1\16\1\17\1\20\3\6\1\21\1\22"+
    "\3\6\1\23\3\6\1\24\1\3\1\6\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\2\6\61\0\3\3\42\0"+
    "\1\3\11\0\1\4\2\0\55\4\5\0\2\33\2\0"+
    "\1\6\5\0\1\6\1\0\1\33\1\0\5\33\1\0"+
    "\15\33\1\0\1\33\4\0\4\33\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\15\6\1\0\1\6\4\0\4\6\11\0\1\7\5\0"+
    "\1\7\1\34\1\35\1\0\1\36\12\0\1\35\26\0"+
    "\2\37\12\0\1\37\1\0\5\37\1\0\15\37\1\0"+
    "\1\37\4\0\4\37\2\11\1\0\11\11\1\40\1\41"+
    "\42\11\11\0\1\7\5\0\1\42\60\0\1\34\1\35"+
    "\1\0\1\36\1\43\11\0\1\35\44\0\1\44\2\0"+
    "\1\45\3\0\1\46\1\47\1\50\1\51\1\52\1\53"+
    "\1\0\1\54\1\0\1\55\21\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\3\6"+
    "\1\56\4\6\1\57\4\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\4\6\1\60\1\0\15\6\1\0\1\6\4\0"+
    "\2\6\1\61\1\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\6\6\1\62"+
    "\6\6\1\0\1\6\4\0\4\6\15\0\1\63\47\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\15\6\1\0\1\6\4\0\1\64\3\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\1\65\5\6\1\66\6\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\14\6\1\67"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\3\6"+
    "\1\70\11\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\6\6\1\71\6\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\7\6\1\72\5\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\33\1\73\1\74\1\33\5\0"+
    "\1\33\1\0\1\33\1\0\5\33\1\0\15\33\1\0"+
    "\1\33\4\0\4\33\11\0\1\75\5\0\1\75\51\0"+
    "\1\76\4\0\2\76\2\0\1\76\1\36\41\0\2\37"+
    "\2\0\1\37\1\0\1\77\3\0\1\37\1\0\1\37"+
    "\1\0\5\37\1\0\15\37\1\0\1\37\4\0\4\37"+
    "\2\11\1\0\55\11\20\0\1\34\1\35\1\0\1\36"+
    "\12\0\1\35\32\0\1\100\5\0\1\100\1\0\1\100"+
    "\1\0\1\100\1\0\1\100\4\0\1\100\1\0\1\100"+
    "\1\0\1\100\3\0\1\100\11\0\1\100\44\0\1\101"+
    "\47\0\1\102\6\0\1\103\52\0\1\104\65\0\1\105"+
    "\63\0\1\106\46\0\1\107\2\0\1\110\65\0\1\111"+
    "\50\0\1\112\64\0\1\113\52\0\1\114\26\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\2\6\1\115\12\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\14\6\1\116\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\3\6\1\117\11\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\14\6\1\120"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\1\121"+
    "\2\6\1\122\11\6\1\0\1\6\4\0\4\6\31\0"+
    "\1\123\33\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\3\6\1\124\1\6\1\0\15\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\15\6\1\0"+
    "\1\6\4\0\1\125\3\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\7\6"+
    "\1\126\1\127\4\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\4\6\1\130\1\0\15\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\4\6\1\131\1\0\15\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\10\6\1\132\4\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\2\6\1\133"+
    "\12\6\1\0\1\6\4\0\4\6\5\0\3\73\1\74"+
    "\1\73\5\0\1\73\1\0\1\73\1\0\5\73\1\0"+
    "\15\73\1\0\1\73\4\0\4\73\11\0\1\75\5\0"+
    "\1\75\1\0\1\35\1\0\1\36\12\0\1\35\32\0"+
    "\1\76\5\0\1\76\3\0\1\36\41\0\1\134\3\0"+
    "\1\100\5\0\1\100\1\0\1\100\1\0\1\100\1\0"+
    "\1\100\2\134\2\0\1\100\1\0\1\100\1\0\1\100"+
    "\3\0\1\100\11\0\1\100\41\0\1\135\56\0\1\136"+
    "\53\0\1\137\62\0\1\140\70\0\1\141\57\0\1\142"+
    "\51\0\1\143\60\0\1\144\61\0\1\145\64\0\1\146"+
    "\45\0\1\147\51\0\1\150\35\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\3\6\1\151\1\6"+
    "\1\0\15\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\3\6\1\152\11\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\4\6\1\153\1\0\15\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\4\6\1\154\1\0\1\6\1\155\13\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\5\6"+
    "\1\156\7\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\14\6\1\157\1\0\1\6\4\0\4\6\11\0"+
    "\1\160\5\0\1\160\45\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\4\6\1\161\1\0\15\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\2\6"+
    "\1\162\12\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\4\6"+
    "\1\163\1\0\15\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\15\6\1\0\1\6\4\0\1\6\1\164"+
    "\2\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\5\6\1\165\7\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\10\6\1\166"+
    "\4\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\11\6\1\167\3\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\3\6\1\170\11\6\1\0\1\6\4\0"+
    "\4\6\33\0\1\171\62\0\1\172\60\0\1\173\46\0"+
    "\1\174\67\0\1\175\61\0\1\176\53\0\1\177\56\0"+
    "\1\200\72\0\1\201\52\0\1\202\51\0\1\203\70\0"+
    "\1\204\20\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\5\6\1\205\7\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\2\6\1\206"+
    "\12\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\10\6\1\207\4\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\13\6\1\210\1\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\13\6\1\211\1\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\3\6\1\212\1\6\1\0"+
    "\15\6\1\0\1\6\4\0\4\6\11\0\1\160\5\0"+
    "\1\160\10\0\1\213\34\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\6\6\1\214"+
    "\6\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\10\6\1\215\4\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\5\6\1\216\7\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\3\6\1\217\11\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\6\6\1\220"+
    "\6\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\15\6\1\0\1\6\4\0\1\6\1\221\2\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\15\6\1\0\1\222\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\4\6\1\223\1\0\15\6\1\0\1\6\4\0\4\6"+
    "\42\0\1\224\54\0\1\225\52\0\1\226\53\0\1\227"+
    "\60\0\1\230\73\0\1\231\54\0\1\232\53\0\1\233"+
    "\6\0\1\234\42\0\1\235\67\0\1\236\61\0\1\237"+
    "\24\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\1\6\1\240\13\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\5\6\1\241\7\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\6\6"+
    "\1\242\6\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\10\6\1\243\4\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\3\6\1\244\11\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\1\6\1\245\13\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\1\6"+
    "\1\246\13\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\4\6"+
    "\1\247\1\0\15\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\1\250\4\6\1\0\15\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\5\6\1\251\7\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\5\6\1\252\7\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\10\6"+
    "\1\253\4\6\1\0\1\6\4\0\4\6\36\0\1\254"+
    "\55\0\1\255\72\0\1\256\55\0\1\257\50\0\1\260"+
    "\50\0\1\261\73\0\1\262\56\0\1\263\22\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\4\6"+
    "\1\264\1\0\15\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\7\6\1\265\5\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\5\6\1\266\7\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\1\6\1\267"+
    "\13\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\4\6\1\270"+
    "\1\0\15\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\5\6\1\271\7\6\1\0\1\6\4\0\4\6"+
    "\5\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\3\6\1\272\11\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\2\6\1\273\12\6"+
    "\1\0\1\6\4\0\4\6\27\0\1\274\61\0\1\275"+
    "\73\0\1\276\41\0\1\277\66\0\1\300\57\0\1\301"+
    "\26\0\2\6\2\0\1\6\5\0\1\6\1\0\1\6"+
    "\1\0\5\6\1\0\10\6\1\302\4\6\1\0\1\6"+
    "\4\0\4\6\5\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\14\6\1\303\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\14\6\1\304"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\4\6\1\305\1\0"+
    "\15\6\1\0\1\6\4\0\4\6\5\0\2\6\2\0"+
    "\1\6\5\0\1\6\1\0\1\6\1\0\5\6\1\0"+
    "\5\6\1\306\7\6\1\0\1\6\4\0\4\6\5\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\1\6\1\307\13\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\5\6\1\310\7\6\1\0"+
    "\1\6\4\0\4\6\41\0\1\311\54\0\1\312\61\0"+
    "\1\313\63\0\1\314\52\0\1\315\65\0\1\316\17\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\1\6\1\317\13\6\1\0\1\6\4\0"+
    "\4\6\5\0\2\6\2\0\1\6\5\0\1\6\1\0"+
    "\1\6\1\0\5\6\1\0\10\6\1\320\4\6\1\0"+
    "\1\6\4\0\4\6\5\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\4\6\1\321\1\0\15\6"+
    "\1\0\1\6\4\0\4\6\5\0\2\6\2\0\1\6"+
    "\5\0\1\6\1\0\1\6\1\0\5\6\1\0\11\6"+
    "\1\322\3\6\1\0\1\6\4\0\4\6\5\0\2\6"+
    "\2\0\1\6\5\0\1\6\1\0\1\6\1\0\5\6"+
    "\1\0\5\6\1\323\7\6\1\0\1\6\4\0\4\6"+
    "\40\0\1\324\46\0\1\325\70\0\1\326\45\0\1\327"+
    "\60\0\1\330\35\0\2\6\2\0\1\6\5\0\1\6"+
    "\1\0\1\6\1\0\5\6\1\0\15\6\1\0\1\6"+
    "\4\0\3\6\1\331\45\0\1\332\46\0\1\333\65\0"+
    "\1\334\43\0\1\335\36\0\2\6\2\0\1\6\5\0"+
    "\1\6\1\0\1\6\1\0\5\6\1\0\5\6\1\336"+
    "\7\6\1\0\1\6\4\0\4\6\27\0\1\337\35\0"+
    "\2\6\2\0\1\6\5\0\1\6\1\0\1\6\1\0"+
    "\5\6\1\0\11\6\1\340\3\6\1\0\1\6\4\0"+
    "\4\6\41\0\1\341\56\0\1\342\64\0\1\343\12\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[9072];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\22\1\4\11\3\1\1\0\1\1\1\11"+
    "\1\0\1\11\1\0\1\1\13\0\5\1\1\0\7\1"+
    "\1\0\1\11\2\1\1\11\1\1\14\0\6\1\1\0"+
    "\10\1\1\11\14\0\7\1\1\0\10\1\4\0\1\11"+
    "\7\0\6\1\1\11\10\1\2\11\1\0\1\11\1\0"+
    "\1\11\6\0\14\1\1\11\6\0\1\11\10\1\6\0"+
    "\7\1\1\0\1\11\4\0\5\1\3\0\1\11\1\0"+
    "\1\1\1\11\1\0\2\11\1\1\1\0\1\1\2\0"+
    "\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  public _SmaliLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _SmaliLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return BAD_CHARACTER;
            }
          case 43: break;
          case 2: 
            { return WHITE_SPACE;
            }
          case 44: break;
          case 3: 
            { return COMMENT;
            }
          case 45: break;
          case 4: 
            { return IDENTIFIER;
            }
          case 46: break;
          case 5: 
            { return REGULAR_NUMBER;
            }
          case 47: break;
          case 6: 
            { return DOUBLE_QUOTED_STRING;
            }
          case 48: break;
          case 7: 
            { return L_CURLY;
            }
          case 49: break;
          case 8: 
            { return R_CURLY;
            }
          case 50: break;
          case 9: 
            { return L_PARENTHESIS;
            }
          case 51: break;
          case 10: 
            { return R_PARENTHESIS;
            }
          case 52: break;
          case 11: 
            { return JAVA_IDENTIFIER;
            }
          case 53: break;
          case 12: 
            { return CONSTRUCTOR_INIT;
            }
          case 54: break;
          case 13: 
            { return HEX_NUMBER;
            }
          case 55: break;
          case 14: 
            { return TRUE;
            }
          case 56: break;
          case 15: 
            { return DOT_LINE;
            }
          case 57: break;
          case 16: 
            { return FALSE;
            }
          case 58: break;
          case 17: 
            { return AM_FINAL;
            }
          case 59: break;
          case 18: 
            { return CHAR;
            }
          case 60: break;
          case 19: 
            { return DOT_FIELD;
            }
          case 61: break;
          case 20: 
            { return DOT_SUPER;
            }
          case 62: break;
          case 21: 
            { return DOT_CLASS;
            }
          case 63: break;
          case 22: 
            { return DOT_PARAM;
            }
          case 64: break;
          case 23: 
            { return AM_STATIC;
            }
          case 65: break;
          case 24: 
            { return AM_PUBLIC;
            }
          case 66: break;
          case 25: 
            { return AM_NATIVE;
            }
          case 67: break;
          case 26: 
            { return AM_BRIDGE;
            }
          case 68: break;
          case 27: 
            { return DOT_SOURCE;
            }
          case 69: break;
          case 28: 
            { return DOT_METHOD;
            }
          case 70: break;
          case 29: 
            { return AM_PRIVATE;
            }
          case 71: break;
          case 30: 
            { return AM_ABSTRACT;
            }
          case 72: break;
          case 31: 
            { return AM_VOLATILE;
            }
          case 73: break;
          case 32: 
            { return DOT_PROLOGUE;
            }
          case 74: break;
          case 33: 
            { return AM_SYNTHETIC;
            }
          case 75: break;
          case 34: 
            { return AM_TRANSIENT;
            }
          case 76: break;
          case 35: 
            { return AM_PROTECTED;
            }
          case 77: break;
          case 36: 
            { return AM_INTERFACE;
            }
          case 78: break;
          case 37: 
            { return DOT_REGISTERS;
            }
          case 79: break;
          case 38: 
            { return DOT_ANNOTATION;
            }
          case 80: break;
          case 39: 
            { return DOT_METHOD_END;
            }
          case 81: break;
          case 40: 
            { return DOT_IMPLEMENTS;
            }
          case 82: break;
          case 41: 
            { return AM_SYNCHRONIZED;
            }
          case 83: break;
          case 42: 
            { return DOT_ANNOTATION_END;
            }
          case 84: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
