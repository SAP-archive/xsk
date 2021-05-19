grammar Xshttpdest;

xshttpdest: property+;

property
    : host
    | port
    | description
    | useSSL
    | sslAuth
    | sslHostCheck
    | pathPrefix
    | authType
    | samlProvider
    | samlACS
    | samlAttributes
    | samlNameId
    | proxyType
    | proxyHost
    | proxyPort
    | timeout
    | remoteSID
    | remoteClient
    | oAuthAppConfigPackage
    | oAuthAppConfig;

host: 'host' EQ STRING SC;
port: 'port' EQ INT SC;
description: 'description' EQ STRING SC;
useSSL: 'useSSL' EQ BOOL SC;
sslAuth: 'sslAuth' EQ sslAuthValue SC;
sslHostCheck: 'sslHostCheck' EQ BOOL SC;
pathPrefix: 'pathPrefix' EQ STRING SC;
authType: 'authType' EQ authTypeValue SC;
samlProvider: 'samlProvider' EQ STRING SC;
samlACS: 'samlACS' EQ STRING SC;
samlAttributes: 'samlAttributes' EQ STRING SC;
samlNameId: 'samlNameId' EQ samlNameIdList SC;
proxyType: 'proxyType' EQ proxyTypeValue SC;
proxyHost: 'proxyHost' EQ STRING SC;
proxyPort: 'proxyPort' EQ INT SC;
timeout: 'timeout' EQ unsignedInt SC;
remoteSID: 'remoteSID' EQ STRING SC;
remoteClient: 'remoteClient' EQ STRING SC;
oAuthAppConfigPackage: 'oAuthAppConfigPackage' EQ STRING SC;
oAuthAppConfig: 'oAuthAppConfig' EQ STRING SC;

sslAuthValue: 'client' | 'anonymous';
samlNameIdList: '['STRING (',' STRING)*']';
authTypeValue: 'none' | 'basic' | 'AssertionTicket' | 'SamlAssertion' | 'SamlAssertionPropagation';
proxyTypeValue: 'none' | 'http' | 'socks';

unsignedInt: '-'? INT;

STRING: '"' (~["\\\r\n] | EscapeSequence)* '"';
INT: [0-9]+;
BOOL: 'true' | 'false';
EQ: '=';
SC: ';';
WS: [ \t\r\n\u000C]+ -> skip;
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ; // Match "//" stuff '\n'

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;