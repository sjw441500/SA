
package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.handle.PluginLoader;
import com.kzxy.plugin.PluginExectExcept;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class emotion extends JFrame {

	private JPanel contentPane;
	String filepath;

	/**
	 * Create the frame.
	 * @throws PluginExectExcept 
	 */
	public emotion(JFrame parent, List<Article> allArticle, GlobalData allData, PluginLoader pluginLoader) throws PluginExectExcept {
		setTitle("情感分析");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label_1 = new JLabel();
		label_1.setBounds(6, 6, 748, 605);
		contentPane.add(label_1);
		
		JButton button = new JButton("按年份显示");
		button.setEnabled(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false);
				int language = (int) allData.getSettings("language");
				if (language == 0) {
					try {
						pluginLoader.exect(allArticle, allData, "emotion_cn_by_year");
					} catch (PluginExectExcept e1) {
						e1.printStackTrace();
					}
					ImageIcon image = new ImageIcon("images/emotion_cn_by_year.png");
					label_1.setIcon(image);
					//Chinese
				} else {
					// English
					try {
						pluginLoader.exect(allArticle, allData, "emotion_eg_year");
					} catch (PluginExectExcept e1) {
						e1.printStackTrace();
					}
					ImageIcon image = new ImageIcon("images/emotion_eg_year.png");
					label_1.setIcon(image);
				}
				
			}
		});
		button.setBounds(524, 618, 117, 29);
		contentPane.add(button);
		
		JButton btn_back = new JButton("返回");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});
		btn_back.setEnabled(false);
		btn_back.setBounds(637, 618, 117, 29);
		contentPane.add(btn_back);
		
		//get language state from allData
		int language = (int) allData.getSettings("language");
		//start run plugin
		if (language == 0) {
			pluginLoader.exect(allArticle, allData, "emotion_cn");
			ImageIcon image = new ImageIcon("images/emotion_cn.png");
			label_1.setIcon(image);

		} else {
			// English
			pluginLoader.exect(allArticle, allData, "emotion_eg");
			ImageIcon image = new ImageIcon("images/emotion_eg.png");
			label_1.setIcon(image);
		}
		
		btn_back.setEnabled(true);
	}
}

/**package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class emotion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Create the frame.
	 */
	/**public emotion() {
		setTitle("情感分析");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setText("    本功能更具用户输入的时间段，来对导入数据进行分段后的情感分析，统计不同时间段每种情感类型的文章数量，将每个文本分类到“乐”，“好”，“怒”，“哀”，“惧”，“恶”，“惊”，“无”这八个类型中。");
		
		textPane.setBounds(6, 6, 488, 58);
		contentPane.add(textPane);
		
		JLabel label = new JLabel("时间段选择：");
		label.setBounds(16, 68, 100, 16);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setText("2004");
		textField.setBounds(26, 96, 53, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("2008\n");
		textField_1.setBounds(122, 96, 53, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("年 ——");
		label_1.setBounds(77, 101, 61, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("年 ——");
		label_2.setBounds(173, 101, 61, 16);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setText("2012\n");
		textField_2.setBounds(219, 96, 61, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("年 ——");
		label_3.setBounds(279, 101, 61, 16);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setText("2015");
		textField_3.setBounds(328, 96, 43, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_4 = new JLabel("年");
		label_4.setBounds(372, 101, 61, 16);
		contentPane.add(label_4);
//		String[] head = {"时间段","总文章数","乐","好","怒","哀","惧","恶","惊","无"};
//		Object[][] data = {{"时间段","总文章数","乐","好","怒","哀","惧","恶","惊","无"},
//				{"时间段","总文章数","乐","好","怒","哀","惧","恶","惊","无"},
//		};
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(27, 134, 449, 222);
		contentPane.add(table);
		
		JButton button = new JButton("分析");
		button.setBounds(383, 96, 93, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.setBounds(359, 363, 117, 29);
		contentPane.add(button_1);
	}
}
*/
