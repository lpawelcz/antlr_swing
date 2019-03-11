package tb.antlr.symbolTable;

import java.util.HashMap;

public class GlobalSymbols {
	
	HashMap<String, Integer> memory = new HashMap<>();

	public GlobalSymbols() {
	}
	
	public boolean hasSymbol(String name) {
		return memory.containsKey(name);
	}

	public void newSymbol(String name) throws Exception{
		if (! hasSymbol(name))
			memory.put(name, null);
		else
			throw new Exception("Variable " + name +" exists!");
	}
	
	public void setSymbol(String name, Integer value) throws Exception {
		if( hasSymbol(name))
			memory.put(name, value);
		else
			throw new Exception("Variable " + name +" does not exist!");
	}
	
	public Integer getSymbol(String name) throws Exception {
		if( hasSymbol(name))
			return memory.get(name);
		else
			throw new Exception("Variable " + name +" does not exist!");
	}
	
}
