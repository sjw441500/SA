package ui;

import java.awt.EventQueue;

import com.kzxy.data.Article;
import com.kzxy.handle.*;
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
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class keyreport extends JFrame {
	private JTextField textField;
	private JPanel contentPane;
	private JTable table;
	private List<Article> allArticle;
	private GlobalData allData;
	private PluginLoader pluginLoader;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keyreport frame = new keyreport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	public keyreport(JFrame parent,List<Article> allArticle, GlobalData allData, PluginLoader pluginLoader){
		setTitle("关键报道");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnn = new JTextPane();
		txtpnn.setBackground(UIManager.getColor("Button.background"));
		txtpnn.setText("    本功能用于计算中文媒体中起到关键作用的报道，根据该媒体的文章转发以及被引用文章数计算得出。\n\n");
		txtpnn.setBounds(6, 6, 438, 38);
		contentPane.add(txtpnn);
		JLabel label = new JLabel("前");
		label.setBounds(6, 56, 126, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setText("10");
		textField.setBounds(127, 51, 46, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("关键报道");
		label_1.setBounds(173, 56, 61, 16);
		contentPane.add(label_1);
		String[] head = {"id","media","vlaue"};
		KeyReport keyreport= new KeyReport(allData); 
		Object[][] data = keyreport.getKey();/*{{"QM","QN","QT"},
				{"1","15","泰国第十四家孔子学院揭牌"},
				{"2568","10","中国一些驻外使领馆举行新春招待会"},
				{"5192","9","中国面临十大战略机遇 国防现代化迈出重要步伐"},
				{"504","8","俄罗斯总理会见陈至立"},
				{"3216","8","2014年高考作文真题预测(16)：中国元素"},
				{"3862","8","内外统筹 全面开启中国文化产业国际化时代"},
				{"10598","8","中蒙建交65周年 习近平将到蒙古国“串门”"},
				{"1108","7","构建长期稳定的中巴友好合作关系"},
				{"1344","7","李克强同澳大利亚总理吉拉德会谈"},
				{"1798","7","华裔子弟积极学中文 马来西亚中文发展进高峰期"},
		};*/
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				table.setVisible(true);
				String str=textField.getText();
				for(int i=Integer.parseInt(str);i<10;i++){
				data[i+1][0]="";
				data[i+1][1]="";
				data[i+1][2]="";
				}
			}
		});
		button.setBounds(311, 51, 117, 29);
		contentPane.add(button);
//		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
//		firsetColumn.setPreferredWidth(10);
//		firsetColumn.setMaxWidth(10);
//		firsetColumn.setMinWidth(10);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(6, 280, 438, 16);
		contentPane.add(scrollBar);
		table = new JTable(data,head);
		table.setVisible(false);
		table.setBackground(UIManager.getColor("Button.highlight"));
		table.setBounds(6, 84, 438, 210);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("QM：文章编号    QN：文章被转载次数    QT：文章标题");
		lblNewLabel.setBounds(6, 306, 334, 16);
		contentPane.add(lblNewLabel);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});
		button_1.setBounds(333, 306, 111, 29);
		contentPane.add(button_1);
		
		
		
		
	}
}

//package ui;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JTextPane;
//import javax.swing.UIManager;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.JTable;
//import javax.swing.border.LineBorder;
//import javax.swing.table.TableColumn;
//
//import java.awt.Color;
//import javax.swing.JScrollBar;
//
//public class keyreport extends JFrame {
//
//	private JPanel contentPane;
//	private JTextField textField;
//	private JTable table;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					keyreport frame = new keyreport();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public keyreport() {
//		setTitle("关键报道");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 367);
//		contentPane = new JPanel();
//		contentPane.setBackground(UIManager.getColor("Button.background"));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JTextPane txtpnn = new JTextPane();
//		txtpnn.setBackground(UIManager.getColor("Button.background"));
//		txtpnn.setText("    本功能通过用户的输入的数字n，提取出被其他媒体引用和转载词数最多的n篇文章。\n\n");
//		txtpnn.setBounds(6, 6, 438, 38);
//		contentPane.add(txtpnn);
//		
//		JLabel label = new JLabel("被引用或显示最多的");
//		label.setBounds(6, 56, 126, 16);
//		contentPane.add(label);
//		
//		textField = new JTextField();
//		textField.setText("10");
//		textField.setBounds(127, 51, 46, 26);
//		contentPane.add(textField);
//		textField.setColumns(10);
//		
//		JLabel label_1 = new JLabel("篇报道");
//		label_1.setBounds(173, 56, 61, 16);
//		contentPane.add(label_1);
//		
//		JButton button = new JButton("查询");
//		button.setBounds(311, 51, 117, 29);
//		contentPane.add(button);
//		String[] head = {"QM","QN","QT"};
//		Object[][] data = {{"QM","QN","QT"},
//				{"1","15","泰国第十四家孔子学院揭牌"},
//				{"2568","10","中国一些驻外使领馆举行新春招待会"},
//				{"5192","9","中国面临十大战略机遇 国防现代化迈出重要步伐"},
//				{"504","8","俄罗斯总理会见陈至立"},
//				{"3216","8","2014年高考作文真题预测(16)：中国元素"},
//				{"3862","8","内外统筹 全面开启中国文化产业国际化时代"},
//				{"10598","8","中蒙建交65周年 习近平将到蒙古国“串门”"},
//				{"1108","7","构建长期稳定的中巴友好合作关系"},
//				{"1344","7","李克强同澳大利亚总理吉拉德会谈"},
//				{"1798","7","华裔子弟积极学中文 马来西亚中文发展进高峰期"},
//		};
////		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
////		firsetColumn.setPreferredWidth(10);
////		firsetColumn.setMaxWidth(10);
////		firsetColumn.setMinWidth(10);
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
//		scrollBar.setBounds(6, 278, 438, 16);
//		contentPane.add(scrollBar);
//		table = new JTable(data,head);
//		table.setBackground(UIManager.getColor("Button.highlight"));
//		table.setBounds(6, 84, 438, 210);
//		contentPane.add(table);
//		
//		JLabel lblNewLabel = new JLabel("QM：文章编号    QN：文章被转载次数    QT：文章标题");
//		lblNewLabel.setBounds(6, 306, 334, 16);
//		contentPane.add(lblNewLabel);
//		
//		JButton button_1 = new JButton("返回");
//		button_1.setBounds(333, 306, 111, 29);
//		contentPane.add(button_1);
//	}
//}

