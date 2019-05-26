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
	: 'time' op=compare time	        #getTimeExpr
	| 'time' time 'to' time         	#getTimeIntervalExpr
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

time: DAY DASH MONTH DASH YEAR						#dateexpr
	| DAY DASH MONTH DASH YEAR COL HOUR PT MINUTE	#datetimeexpr
	;

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
DAY: INT+;
MONTH: INT+;
YEAR: INT INT INT INT;
HOUR: INT+;
MINUTE: INT+;
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