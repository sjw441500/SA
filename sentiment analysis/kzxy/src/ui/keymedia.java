package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class keymedia extends JFrame {
	private JTextField textField;
	private JPanel contentPane;
	private JTable table;
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keymedia frame = new keymedia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public keymedia(JFrame parent){
		setTitle("关键媒体");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnn = new JTextPane();
		txtpnn.setBackground(UIManager.getColor("Button.background"));
		txtpnn.setText("    本功能用于计算中文媒体中起到关键作用的媒体，根据该媒体的文章转发以及被引用文章数计算得出。\n\n");
		txtpnn.setBounds(6, 6, 438, 38);
		contentPane.add(txtpnn);
		JLabel label = new JLabel("前");
		label.setBounds(100, 56, 126, 16);
		contentPane.add(label);
		
		
		textField = new JTextField();
		/*textField.setText("10");*/
		textField.setBounds(127, 51, 46, 26);
		contentPane.add(textField);
		/*textField.setColumns(10);*/
		
		JLabel label_1 = new JLabel("关键媒体");
		label_1.setBounds(173, 56, 61, 16);
		contentPane.add(label_1);
	
		
		String[] head = {"id","media","vlaue"};

		String[][]data1=new String[][]{{"id","media","value"},
				{"1","人民网","14859"},
				{"9","新华每日电讯","4869"},
				{"10","中新网","2868"},
				{"4","工人日报","2843"},
				{"27","文汇报","787"},
				{"329","人民日报","761"},
				{"8","环球时报","696"},
				{"5","光明日报","657"},
				{"2","中国青年报","560"},
				{"3","经济日报","235"},
				{"46","南方周末","226"},
				{"13","金融时报","216"},
				{"49","新京报","165"},
				{"155","国际在线","153"},
				{"45","中国台湾网","123"},
				{"344","纽约时报","91"},
				{"41","中国时报","80"},
				{"83","京华时报","73"},
				{"11","参考消息","71"},
				{"7","中国商报","69"},
				{"333","英国广播公司BBC","65"},
				{"163","中华人民共和国外交部","61"},
				{"56","中国教育报","59"},
		};
		
		

		table = new JTable(data1,head);
		
		table.setVisible(false);
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setBounds(6, 84, 438, 210);
		contentPane.add(table);
		JButton button = new JButton("查询");
		button.setBounds(311, 51, 117, 29);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				table.setVisible(true);
				String str=textField.getText();
				for(int i=Integer.parseInt(str);i<23;i++){
				data1[i+1][0]="";
				data1[i+1][1]="";
				data1[i+1][2]="";
				}
				
			}
		});
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("ID：媒体编号    Media：媒体名字    Value：媒体被引用或转载总数");
		lblNewLabel.setBounds(6, 300, 420, 16);
		contentPane.add(lblNewLabel);
		
		JButton button_1 = new JButton("返回");
		button_1.setBounds(333, 336, 111, 29);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				parent.setEnabled(true);
				dispose();
			}
		});
		
		
		
	}
}
