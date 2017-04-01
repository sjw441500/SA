package ui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.parser.ParseException;

import javafx.scene.control.ComboBox;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

import com.kzxy.data.Article;
import com.kzxy.handle.*;
import com.kzxy.plugin.PluginExectExcept;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class mainui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String filepath;
	private XlsReader reader;
	private List<Article> allArticle = null;
	private GlobalData allData = null;
	private PluginLoader pluginLoader = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainui frame = new mainui();
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
	public mainui() {
		JFrame point = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("新闻的语言");
		comboBox.setBounds(67, 40, 93, 27);
		comboBox.addItem("中文");
		comboBox.addItem("English");
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox);

		JLabel label = new JLabel("数据语言");
		label.setBounds(6, 44, 61, 16);
		contentPane.add(label);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(67, 6, 470, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		//button 4 最后几个
			
				JButton button_2 = new JButton("关键报道");
				button_2.setEnabled(false);
				button_2.setBounds(490, 693, 117, 29);
				contentPane.add(button_2);
				button_2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						keyreport keyreport = new keyreport(point, allArticle, allData, pluginLoader);
						keyreport.setVisible(true);
						
					}
				});
				
				JButton button_6 = new JButton("关键媒体");
				button_6.setEnabled(false);
				button_6.setBounds(350, 693, 117, 29);
				contentPane.add(button_6);
				button_6.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						keymedia keymedia = new keymedia(point);
						keymedia.setVisible(true);
					}
				});
		
				JButton button_3 = new JButton("情感分析");
				button_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						emotion emotion;
						try {
							emotion = new emotion(point, allArticle, allData, pluginLoader);
							emotion.setVisible(true);
							emotion.setAlwaysOnTop(true);
						} catch (PluginExectExcept e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						

					}
				});
				button_3.setEnabled(false);
				button_3.setBounds(677, 693, 117, 29);
				contentPane.add(button_3);
				
				JButton button_5 = new JButton("主题聚类");
				button_5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cluster_show frame_cluster;
						try {
							frame_cluster = new cluster_show(point,allArticle,allData,pluginLoader);
							
							frame_cluster.setVisible(true);
							frame_cluster.setAlwaysOnTop(true);
						} catch (PluginExectExcept e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (WriteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BiffException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				button_5.setEnabled(false);
				button_5.setBounds(213, 693, 117, 29);
				contentPane.add(button_5);
		//button 4 最后几个
		JLabel label_1 = new JLabel("                                                                                             数据尚未加载");
		label_1.setForeground(Color.BLACK);
		label_1.setBackground(new Color(255, 255, 255));
		label_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_1.setBounds(6, 75, 800, 600);
		contentPane.add(label_1);
		

		JButton button = new JButton("导入");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					reader = new XlsReader(filepath);
					Inition inition = new Inition();
					inition.initsys(reader);
					pluginLoader = inition.pluginLoader;
					allArticle = inition.allArticle;
					allData = inition.allData;
					allData.setSettings("language", comboBox.getSelectedIndex());
					//button_1.setEnabled(true);
					button_2.setEnabled(true);
					button_3.setEnabled(true);
					button_5.setEnabled(true);
					button_6.setEnabled(true);
				} catch (BiffException | IOException | PluginExectExcept | ParseException e1) {
					e1.printStackTrace();
				}
				try {
					if(comboBox.getSelectedIndex()==0){
					pluginLoader.exect(allArticle, allData, "base_count");
					ImageIcon image = new ImageIcon("images/base_count.png");
					label_1.setIcon(image);
					}
					else{
					pluginLoader.exect(allArticle, allData, "base_count_eg");					
					ImageIcon image = new ImageIcon("images/base_count_eg.png");
					label_1.setIcon(image);
					}

				} catch (PluginExectExcept e1) {
					e1.printStackTrace();
				}

			}
		});
		button.setBounds(677, 39, 117, 29);
		contentPane.add(button);

		JButton button_4 = new JButton("浏览");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fd = new JFileChooser();
				fd.setFileFilter(new FileNameExtensionFilter("Microsoft Word (*.xls)", "xls"));
				fd.showOpenDialog(null);
				File f = fd.getSelectedFile();
				if (f != null) {
					filepath = f.getAbsolutePath();
					// System.out.println(filepath);
					textField.setText(filepath);
					button.setEnabled(true);
				}
			}
		});

		button_4.setBounds(677, 6, 117, 29);
		contentPane.add(button_4);
		
		
		
		JLabel label_2 = new JLabel("路径");
		label_2.setBounds(6, 11, 61, 16);
		contentPane.add(label_2);
	}
}
