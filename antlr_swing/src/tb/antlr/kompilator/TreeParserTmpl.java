/**
 * 
 */
package tb.antlr.kompilator;

import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;

/**
 * @author tb
 *
 */
public class TreeParserTmpl extends TreeParser {

	/**
	 * @param input
	 */
	public TreeParserTmpl(TreeNodeStream input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param input
	 * @param state
	 */
	public TreeParserTmpl(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
		// TODO Auto-generated constructor stub
	}

}
