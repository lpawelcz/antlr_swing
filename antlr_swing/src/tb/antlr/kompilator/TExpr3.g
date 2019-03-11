tree grammar TExpr3;

options {
  tokenVocab=Expr;

  ASTLabelType=CommonTree;

  output=template;
  superClass = TreeParserTmpl;
}

@header {
package tb.antlr.kompilator;
}

prog    : e+=expr* -> template(name={$e}) "<name;separator=\" aaa!!! \">";

expr    : ^(PLUS  e1=expr e2=expr) -> dodaj(p1={$e1.st},p2={$e2.st})
        | ^(MINUS e1=expr e2=expr) 
        | ^(MUL   e1=expr e2=expr) 
        | ^(DIV   e1=expr e2=expr) 
        | ^(PODST i1=ID   e2=expr) 
        | INT                      -> int(i={$INT.text})
    ;
    