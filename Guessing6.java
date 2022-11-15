package tw.myjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

import com.mysql.cj.QueryReturnType;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.Client;

public class Guessing6 extends JFrame implements ActionListener {
	private JButton ans, guess, reStart, next;
	private JTextField input;
	private JTextArea log, log2;
	private JPanel top, center, center_left;//容器
	private JLabel l1, l2;//顯示lable
	private int counter = 1;
	public String word, str;
	private	Properties prop;

	
	public Guessing6()  {
		super("猜成語遊戲");
		
		//設定按鈕
		guess = new JButton("猜");
		reStart = new JButton("清空");
		ans = new JButton("答案");
		next = new JButton("換下一題");
		input = new JTextField(20);
		log = new JTextArea();
		l1=new JLabel("請輸入成語：");
		l2=new JLabel("題目 =  "+topic(str)+ "__");
		log2=new JTextArea("答案 =      "+"\n");
		
		
		//上面	
		top = new JPanel(new FlowLayout());
		top.add(l1);
		top.add(input);
		top.add(guess);top.add(next);top.add(reStart);top.add(ans);
		top.setBackground(Color.GRAY);
		l1.setFont(new Font("宋體", Font.PLAIN,15));

		//左邊顯示
		add(log2);
		log2.setFont(new Font("宋體", Font.PLAIN,18));
		add(l2);
		l2.setFont(new Font("宋體", Font.PLAIN,18));
		GridBagConstraints gbc = new GridBagConstraints();// 網格約束
		gbc.fill = GridBagConstraints.BOTH;// 物件充滿網格
		gbc.gridwidth = 1;// 設定物件占用網格寬
		gbc.gridheight = 1;// 設定物件占用網格長
		gbc.weightx = 1;// 設定容器放大時物件相隔距離
		gbc.weighty = 1;// 設定容器放大時物件相隔距離
		gbc.insets = new Insets(0, 0, 0, 0);// 設定物件間的距離
		
		center_left = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		center_left.add(l2, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		center_left.add(log2, gbc);
		
		//中間顯示
		log = new JTextArea();
		log.setFont(new Font("宋體", Font.PLAIN,18));
		log.setBackground(Color.lightGray);

		GridBagConstraints 	gbc2 = new GridBagConstraints();// 網格約束
		gbc2.fill = GridBagConstraints.BOTH;// 物件充滿網格
		gbc2.gridwidth = 1;// 設定物件占用網格寬
		gbc2.gridheight = 1;// 設定物件占用網格長
		gbc2.weightx = 1;// 設定容器放大時物件相隔距離
		gbc2.weighty = 1;// 設定容器放大時物件相隔距離
		gbc2.insets = new Insets(0, 0, 0, 0);// 設定物件間的距離
			
		center = new JPanel(new GridBagLayout());
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		center.add(center_left, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		center.add(log, gbc2);
		
		//主視窗
		setLayout(new BorderLayout());	
		add(top,BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);

		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		guess.addActionListener(this);
		next.addActionListener(this);
		reStart.addActionListener(this);
		ans.addActionListener(this);
	
	}

	//判斷按鈕作動
	public void actionPerformed(ActionEvent e) {
		//按下顯示答案
		if(e.getSource()==ans){
			input.setText(word);
			log2.append( word+ "\n");
			
			}
		//畫面清空
		if(e.getSource()==reStart){
			counter = 1;
			input.setText(null);
			log.setText(null);
			log2.setText("答案 =      "+"\n");
			}
		//換下一個題目(重新撈取資料)
		if(e.getSource()==next){
			GuessDB1(word);
			l2.setText("題目 =  "+topic(str)+ "__");
			}
		//猜答案判斷
		if(e.getSource()==guess){
			if(input.getText().equals(word)){
				log.append(counter++ + ". " + word +" = 答對"+ "\n");	
			}			
			else {			
				log.append(counter++ + ". "+ input.getText()+" = XX 答錯"+ "\n");
			}
			input.setText("");
		}
	}
	public static void main(String[] args) {		
		new Guessing6();
	}
	//從資料庫撈取資料(String)
	public  String  GuessDB1(String word2) {
		prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		String url = "jdbc:mysql://localhost/test";
		String sql = "SELECT * FROM abc ORDER BY rand() LIMIT 1;";
		try (Connection conn = DriverManager.getConnection(url,prop);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
					ResultSet rs = pstmt.executeQuery(sql);
					
					if(rs.next()) {
						word = rs.getString(2);
					};
			}catch(Exception e) {
				System.out.println(e);
		}return word;		
	}	
	//分割資料，產生題目
	public String topic(String str2){
		try {
			
			str = GuessDB1(word).substring(0,3);	
			
		}catch(Exception e2) {
			System.out.println(e2);
		}
		return str;
	}
	
}
