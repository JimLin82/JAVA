package tw.myjava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.synth.SynthFormattedTextFieldUI;

import com.mysql.cj.QueryReturnType;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.Client;

public class Guessing8 extends JFrame implements ActionListener {
	private JButton ans, guess, reStart, next;
	private JTextField input;
	private JTextArea log;
	private JPanel top, center, center_left;//容器
	private JLabel l1, l2, l3, log2;//顯示lable
	private int counter = 1,a = 0,b = 1;
	public String word, str;
	private	Properties prop;

	public void Guess()  {
			
		//設定按鈕
		guess = new JButton("猜");
		reStart = new JButton("清空");
		ans = new JButton("答案");
		next = new JButton("換下一題");
		input = new JTextField(20);
		log = new JTextArea();
		l1=new JLabel("請輸入成語：");
		l2=new JLabel("題目 =  "+topic(str)+ "__");
		l3=new JLabel("答對數："+ a );
		log2=new JLabel("答案 =      "+"\n");
			
		//上面顯示	
		top = new JPanel(new FlowLayout());
		top.add(l1);
		top.add(input);
		top.add(guess);top.add(next);top.add(reStart);top.add(ans);
		top.setBackground(Color.GRAY);
		l1.setFont(new Font("宋體", Font.PLAIN,15));

		//左邊顯示
		log2.setFont(new Font("宋體", Font.PLAIN,18));
		l2.setFont(new Font("宋體", Font.PLAIN,18));
		l3.setFont(new Font("宋體", Font.PLAIN,18));
		//插入圖片
		Container c=getContentPane();
		URL url=Guessing8.class.getResource("3.png");
		Icon icon=new ImageIcon(url);
		l3.setIcon(icon);
		c.add(l3);
		
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
		gbc.gridx = 0;
		gbc.gridy = 2;
		center_left.add(l3, gbc);
		
		//中間顯示
		log = new JTextArea();
		log.setFont(new Font("宋體", Font.PLAIN,18));
		log.setBackground(Color.lightGray);
		
		GridBagConstraints 	gbc2 = new GridBagConstraints();// 網格約束
		gbc2.fill = GridBagConstraints.BOTH;// 物件充滿網格
		gbc2.gridwidth = 1;// 設定物件占用網格寬
		gbc2.gridheight = 0;// 設定物件占用網格長
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
			log2.setText("答案 =  "+word);
			}
		//畫面清空
		if(e.getSource()==reStart){
			counter = 1;
			a = 0; b= 1;
			l3.setText("答對數：" + a );
			input.setText(null);
			log.setText(null);
			log2.setText("答案 =      "+"\n");
			}
		//換下一個題目(重新撈取資料)
		if(e.getSource()==next){
			GuessDB1(word);
			log2.setText("答案 =  ");
			l2.setText("題目 =  "+topic(str)+ "__");
			}
		//猜答案判斷
		if(e.getSource()==guess){
			if(input.getText().equals(word)){
				a = b++; 
				l3.setText("答對數："+ a++);
				log.append(counter++ + ". " + word +" = 答對"+ "\n");	
				GuessDB1(word);
				log2.setText("答案 =  ");
				l2.setText("題目 =  "+topic(str)+ "__");
			}			
			else {			
				log.append(counter++ + ". "+ input.getText()+" = XX 答錯"+ "\n");
			}
			input.setText("");
		}
	}
	public static void main(String[] args) {		
		new Guessing8();
		
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
//-------------------------------------------------------------------------------------------------------------//
	//登入畫面
		public  Guessing8() {
			super("猜成語遊戲");
		JFrame fr = new JFrame();
		
		fr.setTitle("Login");//設定窗體標題
		fr.setSize(350, 250);//設定窗體大小，只對頂層容器生效
		fr.setDefaultCloseOperation(3);//設定窗體關閉操作，3表示關閉窗體退出程式
		fr.setLocationRelativeTo(null);//設定窗體相對於另一組間的居中位置，引數null表示窗體相對於螢幕的中央位置
		fr.setResizable(false);//禁止調整窗體大小
		fr.setFont(new Font("宋體",Font.PLAIN,14));
		
		FlowLayout fl = new FlowLayout(FlowLayout.CENTER,10,10);
		fr.setLayout(fl);
		JLabel labname = new JLabel("帳號：");
		labname.setFont(new Font("宋體",Font.PLAIN,14));
		fr.add(labname);
		JTextField text_name = new JTextField();
		Dimension dim1 = new Dimension(300,30);
		text_name.setPreferredSize(dim1);
		fr.add(text_name);
		JLabel labpass = new JLabel("密碼：");
		labpass.setFont(new Font("宋體",Font.PLAIN,14));
		fr.add(labpass);
		
		JPasswordField text_password = new JPasswordField();
		text_password.setPreferredSize(dim1);
		fr.add(text_password);
		
		JButton button1 = new JButton();
		Dimension dim2 = new Dimension(70,30);
		button1.setText("登入");
		button1.setFont(new Font("宋體",Font.PLAIN,14));
		button1.setSize(dim2);
		fr.add(button1);
		fr.setVisible(true);
		LoginListener ll = new LoginListener(fr,text_name,text_password);
		button1.addActionListener(ll);					
	}
	//確認帳密是否正確
	public class LoginListener implements ActionListener{
		private JTextField text_name;
		private JPasswordField text_password;
		private JFrame login;
		
		public LoginListener(JFrame login,JTextField text_name,JPasswordField text_password)
		{//獲取登入介面、賬號密碼輸入框物件
			this.login=login;
			this.text_name=text_name;
			this.text_password=text_password;
		}

		int i=3;//3次登入機會
		
		public void actionPerformed(ActionEvent e)
		{
			Dimension dim2 = new Dimension(100,30);
			Dimension dim3 = new Dimension(300,30);
			
			//生成新介面
			JFrame login2 = new JFrame();
			login2.setSize(400,200);
			login2.setDefaultCloseOperation(3);
			login2.setLocationRelativeTo(null);
			login2.setFont(new Font("宋體",Font.PLAIN,14));  //宋體，正常風格，14號字型
			//建立元件
			JPanel jp1 = new JPanel();
			JPanel jp2 = new JPanel();

				if(text_name.getText().equals("123") && text_password.getText().equals("123"))
				{	
					JLabel message = new JLabel("登陸成功！");
					message.setFont(new Font("宋體",Font.PLAIN,14));  //宋體，正常風格，14號字型
					message.setPreferredSize(dim3);
					jp1.add(message);
					login2.add(jp1,BorderLayout.CENTER);
					
					JButton close = new JButton("確定");
					close.setFont(new Font("宋體",Font.PLAIN,14));
					//設定按鍵大小
					close.setSize(dim3);
					jp2.add(close);
					login2.add(jp2,BorderLayout.SOUTH);
					
					login2.setResizable(false);
					login2.setVisible(true);
					close.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							login2.dispose();
						}
					});
					
					//通過我們獲取的登入介面物件，用dispose方法關閉它
					login.dispose();
					Guess();
				}
				
				else if(i>=2)
				{
					JLabel message = new JLabel("帳號或密碼錯誤,您今天還有"+(i-1)+"次機會");
					message.setFont(new Font("宋體",Font.PLAIN,14));  //宋體，正常風格，14號字型
					message.setPreferredSize(dim3);
					//將textName標籤新增到窗體上
					jp1.add(message);
					login2.add(jp1,BorderLayout.CENTER);
					
					JButton close = new JButton("確定");
					close.setFont(new Font("宋體",Font.PLAIN,14));
					//設定按鍵大小
					close.setSize(dim3);
					jp2.add(close);
					login2.add(jp2,BorderLayout.SOUTH);
					
					i--;//次數減少
					close.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							login2.dispose();
						}
					});
					
					login2.setResizable(false);
					login2.setVisible(true);
				}
				
				else if(i == 1)
				{
					JLabel message = new JLabel("帳號已鎖定，請明天再試");
					message.setFont(new Font("宋體",Font.PLAIN,14));  //宋體，正常風格，14號字型
					message.setPreferredSize(dim3);
					//將textName標籤新增到窗體上
					jp1.add(message);
					login2.add(jp1,BorderLayout.CENTER);
					
					JButton close = new JButton("確定");
					close.setFont(new Font("宋體",Font.PLAIN,14));
					//設定按鍵大小
					close.setSize(dim3);
					jp2.add(close);
					login2.add(jp2,BorderLayout.SOUTH);
					
					close.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							login2.dispose();
						}
					});
					
					login2.setResizable(false);
					login2.setVisible(true);
					
					//通過我們獲取的登入介面物件，用dispose方法關閉它
					login.dispose();
					System.exit(0);
				}
		}
	}
}