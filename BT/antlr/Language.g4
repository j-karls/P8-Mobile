grammar Language;

s
	: cmd
	;

cmd
	: 'GET' STRING getexpr (andexpr getexpr)*	#getCommand
	| 'SET' setexpr (andexpr setexpr)*          #setCommand
	| 'GET' 'config'					#getCfgCommand
    ;

getexpr
	: 'time' op=compare DATE	        #getTimeExpr
	| 'time' DATE 'to' DATE         	#getTimeIntervalExpr
	| 'value' op=compare NUM	        #getValueExpr
    | 'status'                      	#getStatusExpr
	;

setexpr
    : 'guideline' op=assign STRING      #setGuidelineExpr
    | 'alerts' op=assign ALERTTYPE      #setAlertExpr
    ;

compare
	: GT
	| LT
	| EQ
	| LTEQ
	| GTEQ
	;

assign: EQ;

andexpr: AND;

ALERTTYPE
    : 'true'
    | 'false'
    ;

NUM
	: INT+
	| INT+ PT INT+
	;

INT	: [0-9];
STRING: [a-zA-Z_0-9]+
	| '*';
DATE: INT+ DASH INT+ DASH INT INT INT INT COL INT+ DASH INT+;
EQ  : '=' ;
LT	: '<' ;
GT 	: '>' ;
LTEQ: '<=';
GTEQ: '>=';
DASH: '-' ;
COL : ':' ;
PT	: '.' ;
AND	: '&';

WS
   : [ \r\n\t] -> skip
   ;