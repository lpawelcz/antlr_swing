package tb.antlr.interpreter;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import tb.antlr.symbolTable.GlobalSymbols;

public class MyTreeParser extends TreeParser {

	private GlobalSymbols g_sym = new GlobalSymbols();
	
    public MyTreeParser(TreeNodeStream input) {
        super(input);
    }

    public MyTreeParser(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    protected void drukuj(String text) {
        System.out.println(text.replace('\r', ' ').replace('\n', ' '));
    }

	protected Integer getInt(String text) {
		return Integer.parseInt(text);
	}
	
	protected Integer add(Integer a, Integer b) {
		return a+b;
	}
	
	protected Integer sub(Integer a, Integer b) {
		return a-b;
	}
	
	protected Integer mul(Integer a, Integer b) {
		return a*b;
	}
	
	protected Integer div(Integer a, Integer b) {
		return a/b;
	}
	
	protected Integer read(String n) {
		return g_sym.getSymbol(n);
	}
	
	protected void write(String n, Integer v) {
		g_sym.setSymbol(n, v);
	}
	
	protected void declr(String n) {
		g_sym.newSymbol(n);
	}
}
