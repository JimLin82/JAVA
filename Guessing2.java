package tw.myjava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Guessing2 extends JFrame implements ActionListener {
	private JButton ans, guess, reStart, next;
	private JTextField input;
	private JTextArea log;
	private JLabel l1, l2;
	private String idiom[]={"百_挑_","金_满_","满_经_","海_天_","珠_宝_","_虎藏_","花_公_","海_山_","高_流_"};
	private String answ[] = {"百里挑一","金玉满堂","满腹经伦","海阔天空","珠光宝气","卧虎藏龙","花花公子","海誓山盟","高山流水"};
	private int counter, m=0,n=0;

	public Guessing2() {
		super("猜謎遊戲");
		
		guess = new JButton("猜");
		reStart = new JButton("重新開始");
		ans = new JButton("答案");
		next = new JButton("換下一個");
		input = new JTextField(20);
		log = new JTextArea();
		l1=new JLabel("請輸入成語：");
		l2=new JLabel(idiom[0]);
		setLayout(new BorderLayout());
					
		JPanel top = new JPanel(new FlowLayout());
		
		top.add(l1, BorderLayout.EAST);
		top.add(input, BorderLayout.EAST);
		top.add(guess);top.add(next);top.add(reStart);top.add(ans);
		add(top,BorderLayout.NORTH);
		add(log,BorderLayout.CENTER);
		add(l2,BorderLayout.WEST);
	
		guess.addActionListener(this);
		next.addActionListener(this);
		reStart.addActionListener(this);
		ans.addActionListener(this);
		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		newRound();
	}

	
	public static void main(String[] args) {
		
		new Guessing2();
	}
	private void newRound(){
//		answ = createAnswer();
		counter = 0;
		log.setText("");
		//System.out.println(answer);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ans){
			input.setText(answ[n]);
		}
		
		if(e.getSource()==next){
			n=(n+1)%idiom.length;
			l2.setText(idiom[n]);
		}
		if(e.getSource()==reStart){
			input.setText(null);
		}
		if(e.getSource()==guess){
			if(input.getText().equals(answ[n])){
				input.setText("");
				n=(n+1)%idiom.length;
				l2.setText(idiom[n]);
			}			
			else {			
				JOptionPane.showMessageDialog(null, "答錯");
			}	
		}
	}

	private String check() {
		
		String gString = input.getText();
		
		
		return null;
		
	}

}
