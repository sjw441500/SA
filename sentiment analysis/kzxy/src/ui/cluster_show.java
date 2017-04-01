package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.json.simple.parser.ParseException;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.handle.Inition;
import com.kzxy.handle.PluginLoader;
import com.kzxy.handle.XlsReader;
import com.kzxy.handle.XlsWriter;
import com.kzxy.plugin.PluginExectExcept;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class cluster_show extends JFrame {
	public int language;
	private List<Article> allArticle;
	private GlobalData allData;
	private PluginLoader pluginLoader;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 * 
	 * @throws BiffException
	 */
	public cluster_show(JFrame parent, List<Article> allArticle, GlobalData allData, PluginLoader pluginLoader)
			throws PluginExectExcept, IOException, WriteException, BiffException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("主题聚类\n");
		label.setBounds(16, 17, 152, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("聚类结果以及关键字");
		label_1.setBounds(16, 579, 163, 16);
		contentPane.add(label_1);

		JTextArea txtrn = new JTextArea();
		txtrn.setEditable(false);
		txtrn.setText(
				"1.孔子学院和文化传播\t\t2808\t18%\r\n2.孔子学院成立，揭牌，座谈会等\t2202\t14%\r\n3.美国孔子学院合作交流新闻\t\t549\t3%\r\n4.中国和其他国家的战略合作\t\t1609\t10%\r\n5.习近平行程（访问，出行）与中国文化影响力\t205\t1%\r\n6.孔子学院举行活动庆典\t\t1863\t12%\r\n7.汉语热和汉语推广\t\t2203\t14%\r\n8.法国孔院新闻\t\t\t119\t18%\r\n其他\t\t\t4462\t27%");
		txtrn.setBounds(16, 635, 559, 136);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBounds(16, 45, 790, 522);
		contentPane.add(lblNewLabel);

		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				dispose();
			}
		});
		button.setBounds(689, 12, 117, 29);
		contentPane.add(button);
//
//		JButton button_1 = new JButton("重新聚类");
//		button_1.setEnabled(false);
//		button_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				button_1.setEnabled(false);
//				language = (int) allData.getSettings("language");
//				if (language == 0) {
//					// Chinese
//
//					// pluginLoader.exect(allArticle, allData, "cluster_cn");
//				} else {
//					// English
//					try {
//						XlsWriter writer = new XlsWriter("./xls/input.xls");
//						pluginLoader.exect(allArticle, allData, "cluster_eg");
//
//						BufferedImage image = ImageIO.read(new File("./images/cluster_show_eg.png"));
//						lblNewLabel.setIcon(new ImageIcon(image));
//					} catch (PluginExectExcept e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//				table.removeAll();
//				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
//				// reader result
//				Workbook book = null;
//				try {
//					book = Workbook.getWorkbook(new File("./xls/input.xls"));
//					
//				} catch (BiffException | IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				Sheet sheet = book.getSheet(0);
//
//				// get number of class
//				Cell cell = sheet.getCell(1, 0);
//				int numb = Integer.parseInt(cell.getContents());
//				// get keywords,number,percent
//				String[] newdata = new String[4];
//				for (int j = 0; j < numb; j++) {
//					newdata[0] = Integer.toString(j + 1);
//					Cell cell1 = sheet.getCell(2, j + 1);
//					String temp = cell1.getContents();
//					newdata[1] = (temp.substring(1, temp.length() - 1)).replaceAll("\'", "");
//					cell1 = sheet.getCell(3, j + 1);
//					newdata[2] = cell1.getContents();
//					cell1 = sheet.getCell(4, j + 1);
//					newdata[3] = cell1.getContents() + "%";
//					tableModel.addRow(newdata);
//				}
//				table.invalidate();
//				
//
//				button_1.setEnabled(true);
//
//			}
//		});
//		button_1.setBounds(575, 12, 117, 29);
//		contentPane.add(button_1);
		setResizable(false);
		int language = (int) allData.getSettings("language");
		XlsWriter writer ;
		if(language==0){
			writer= new XlsWriter("./xls/input_cn.xls");
		}
		else{
			writer= new XlsWriter("./xls/input.xls");
		}
		
		System.out.println(allArticle.size());
		List<Article> articleToWrite = new ArrayList<>();
		for (Article article : allArticle) {
			Article tmp = new Article();
			tmp.set("content", article.get("content"));
			articleToWrite.add(tmp);
		}
		System.out.println(articleToWrite.size());
		writer.write(articleToWrite);
		File file;
		if (language == 0) {
			// Chinese
			file=new File("./xls/input_cn.xls");
			pluginLoader.exect(allArticle, allData, "cluster_cn");
			ImageIcon image = new ImageIcon("images/cluster_show_cn.png");
			lblNewLabel.setIcon(image);
		} else {
			// English
			file=new File("./xls/input.xls");
			pluginLoader.exect(allArticle, allData, "cluster_eg");
			ImageIcon image = new ImageIcon("images/cluster_show_eg.png");
			lblNewLabel.setIcon(image);
		}
//		button_1.setEnabled(true);
		// reader result
		Workbook book = Workbook.getWorkbook(file);
		Sheet sheet = book.getSheet(0);

		String[] head = { "类别", "关键词", "数目", "百分比" };
		// get number of class
		Cell cell = sheet.getCell(1, 0);
		int numb = Integer.parseInt(cell.getContents());

		Object[][] data = new String[numb][4];

		// get keywords,number,percent
		for (int j = 0; j < numb; j++) {
			data[j][0] = Integer.toString(j + 1);
			Cell cell1 = sheet.getCell(2, j + 1);
			String temp = cell1.getContents();
			data[j][1] = (temp.substring(1, temp.length() - 1)).replaceAll("\'", "");
			cell1 = sheet.getCell(3, j + 1);
			data[j][2] = cell1.getContents();
			cell1 = sheet.getCell(4, j + 1);
			data[j][3] = cell1.getContents() + "%";
		}
		table = new JTable(data, head);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		table.setBounds(16, 635, 781, 136);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(16, 605, 790, 166);
		contentPane.add(scrollPane);

	}
}
