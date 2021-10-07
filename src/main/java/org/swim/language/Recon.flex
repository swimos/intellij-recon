package org.swim.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.swim.language.psi.ReconTypes;
import com.intellij.psi.TokenType;

%%

%class ReconLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType


SP = \U000020 | \U000009
NL = \U00000A | \U00000D

NAME_START_CHAR =
  [A-Z] | "_" | [a-z] |
  [\U0000C0-\U0000D6] | [\U0000D8-\U0000F6] | [\U0000F8-\U0002FF] |
  [\U000370-\U00037D] | [\U00037F-\U001FFF] | [\U00200C-\U00200D] |
  [\U002070-\U00218F] | [\U002C00-\U002FEF] | [\U003001-\U00D7FF] |
  [\U00F900-\U00FDCF] | [\U00FDF0-\U00FFFD] | [\U010000-\U0EFFFF]

NAME_CHAR = {NAME_START_CHAR} | "-" | [0-9] | \U0000B7 | [\U000300-\U00036F] | [\U00203F-\U002040]
STRING_CHAR = [[\U000001-\U00D7FF] | [\U00E000-\U00FFFD] | [\U010000-\U10FFFF] -- ("\"" | "\\" | "@" | "\{" | "}" | "[" | "]" | "\b" | "\f" | "\n" | "\r" | "\t")]
CHAR_ESCAPE = "\\" ("\"" | "\\" | "\/" | "@" | "\{" | "}" | "[" | "]" | "b" | "f" | "n" | "r" | "t")
BASE_64_CHAR = [A-Za-z0-9+/]

STRING = ("\"" ({STRING_CHAR} | {CHAR_ESCAPE} | {SP})* "\"") | ("\'" ({STRING_CHAR} | {CHAR_ESCAPE} | {SP})* "\'")
IDENT = {NAME_START_CHAR} {NAME_CHAR}*
NUM = "-"? (([1-9] [0-9]*) | [0-9]) ("." [0-9]+)? (("E" | "e") ("+" | "-")? [0-9]+)?
BOOL = "true" | "false"
COMMENT = "#" [^\r\n]*
DATA = ({BASE_64_CHAR}{4})* ({BASE_64_CHAR} {BASE_64_CHAR} (({BASE_64_CHAR} "=") | ("=" "=")))?
HEX = "0x" [0-9a-fA-F]+

%%

<YYINITIAL> "("                                             { yybegin(YYINITIAL); return ReconTypes.OPEN_BRACK; }
<YYINITIAL> ")"                                             { yybegin(YYINITIAL); return ReconTypes.CLOSE_BRACK; }
<YYINITIAL> "{"                                             { yybegin(YYINITIAL); return ReconTypes.OPEN_CRL_BRACK; }
<YYINITIAL> "}"                                             { yybegin(YYINITIAL); return ReconTypes.CLOSE_CRL_BRACK; }
<YYINITIAL> ":"                                             { yybegin(YYINITIAL); return ReconTypes.COL; }
<YYINITIAL> "," | ";"                                       { yybegin(YYINITIAL); return ReconTypes.SEP; }
<YYINITIAL> {BOOL}                                          { yybegin(YYINITIAL); return ReconTypes.BOOL; }
<YYINITIAL> {STRING}                                        { yybegin(YYINITIAL); return ReconTypes.STRING; }
<YYINITIAL> {IDENT}                                         { yybegin(YYINITIAL); return ReconTypes.IDENT; }
<YYINITIAL> {NUM}                                           { yybegin(YYINITIAL); return ReconTypes.NUM; }
<YYINITIAL> {HEX}                                           { yybegin(YYINITIAL); return ReconTypes.HEX; }
<YYINITIAL> {COMMENT}                                       { yybegin(YYINITIAL); return ReconTypes.COMMENT; }
<YYINITIAL> "%" {DATA}                                      { yybegin(YYINITIAL); return ReconTypes.DATA; }
<YYINITIAL> "@" ({IDENT} | {STRING})                        { yybegin(YYINITIAL); return ReconTypes.ATTR; }

<YYINITIAL> {SP}                                            { yybegin(YYINITIAL); return ReconTypes.SP; }
<YYINITIAL> {NL}                                            { yybegin(YYINITIAL); return ReconTypes.NL; }
[^]                                                         { return TokenType.BAD_CHARACTER; }