package tb.antlr;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import java.awt.event.InputMethodListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.awt.event.InputMethodEvent;

public class Okno extends JFrame {

	private JPanel contentPane;
	private JTextPane inputPane;
	private JTextPane resultPane;
	private JTextPane astPane;
	private JButton parseTButton;
	private CommonTreeNodeStream nodes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno frame = new Okno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Okno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputPane = new JTextPane();
		inputPane.setBounds(5, 5, 280, 280);
		contentPane.add(inputPane);
		
		resultPane = new JTextPane();
		resultPane.setEditable(false);
		resultPane.setBounds(290, 5, 280, 280);
		contentPane.add(resultPane);
		
		astPane = new JTextPane();
		astPane.setEditable(false);
		astPane.setBounds(5, 290, 565, 280);
		contentPane.add(astPane);
		
		JButton parseButton = new JButton("Parse");
		parseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doParse();
			}
		});
		parseButton.setBounds(5, 595, 150, 23);
		contentPane.add(parseButton);
		
		parseTButton = new JButton("Parse Tree");
		parseTButton.setEnabled(false);
		parseTButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doTreeParse();
			}
		});
		parseTButton.setBounds(165, 595, 150, 23);
		contentPane.add(parseTButton);
	}

	private void doParse() {
        // Tworzymy analizator leksykalny i ka¿emy mu czytaæ z stdin
        ANTLRStringStream input = new ANTLRStringStream(inputPane.getText());
        ExprLexer lexer = new ExprLexer(input);

        // Tworzymy bufor na tokeny pomiêdzy analizatorem leksykalnym a parserem
        CommonTokenStream tokens = new CommonTokenStream(lexer);


        // Tworzymy parser czytaj¹cy z powy¿szego bufora
        ExprParser parser = new ExprParser(tokens);


        // Wywo³ujemy parser generuj¹cy drzewo startuj¹c od regu³y prog (Z klasy Expr)
        ExprParser.prog_return root = null;
		try {
			root = parser.prog();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}

        // Wypisujemy drzewo na standardowe wyjœcie
//            System.out.println(((CommonTree)root.tree).toStringTree());
            astPane.setText(root.tree.toStringTree());

            // Tworzymy bufor na wêz³y drzewa
             nodes = new CommonTreeNodeStream(root.tree);
             parseTButton.setEnabled(true);
	}

	private void doTreeParse() {
	       // Tworzymy parser drzew korzystaj¹cy z powy¿szego bufora
        TExpr1 walker = new TExpr1(nodes);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        // Wywo³ujemy parser drzew - startuj¹c od regu³y prog (Tym razem z klasy TExpr1!)
        try {
            walker.prog();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }

        resultPane.setText(baos.toString());
        System.setOut(old);
        nodes.reset();
	}

}
