grammar Language;
s
	: cmd (andexpr cmd)*
	;

cmd
	: 'GET' STRING getexpr (andexpr getexpr)* 			#getCommand
	| 'SET' setexpr (andexpr setexpr)*               	#setCommand
    ;

getexpr
	: 'time' op=compare DATE	        #getTimeExpr
	| 'time' DATE 'to' DATE         	#getTimeIntervalExpr
	| 'value' op=compare NUM	        #getValueExpr
    | 'alerts' op=assign ALERTTYPES     #getAlertExpr
    | 'status'                      	#getStatusExpr
	;

setexpr
    : 'guideline' op=assign STRING      #setGuidelineExpr
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

ALERTTYPES
    : 'immediate'
    | 'predicted'
    ;

NUM
	: INT+
	| INT+ PT INT+
	;

INT	: [0-9];
STRING: [a-zA-Z_0-9]+
	| '*';
DATE: INT+ DASH INT+ DASH INT INT INT INT (COL TIME (PT TIME (PT TIME)?)?)?;
EQ  : '=' ;
LT	: '<' ;
GT 	: '>' ;
LTEQ: '<=';
GTEQ: '>=';
DASH: '-' ;
COL : ':' ;
PT	: '.' ;
AND	: '&';
TIME: INT INT;

WS
   : [ \r\n\t] -> skip
   ;