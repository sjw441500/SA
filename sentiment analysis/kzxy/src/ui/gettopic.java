package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class gettopic extends JFrame {

	private JPanel contentPane;
	private final JTextField txtBbc = new JTextField();
	private JTable table;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gettopic frame = new gettopic();
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
	public gettopic() {
		setTitle("议题提取");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("议题名称：");
		label.setBounds(6, 12, 73, 16);
		contentPane.add(label);
		txtBbc.setText("BBC专访许琳");
		txtBbc.setBounds(66, 6, 275, 29);
		contentPane.add(txtBbc);
		txtBbc.setColumns(10);

		JButton button = new JButton("提取");
		button.setBounds(349, 7, 95, 29);
		contentPane.add(button);

//		JLabel lblNewLabel = new JLabel("主要关键词：许琳、BBC");
		JLabel lblNewLabel = new JLabel("主要关键词：");
		lblNewLabel.setBounds(6, 40, 394, 16);
		contentPane.add(lblNewLabel);

//		JLabel lblBluc = new JLabel("次要关键字：中共价值观");
		JLabel lblBluc = new JLabel("次要关键字：");
		lblBluc.setBounds(6, 81, 394, 16);
		contentPane.add(lblBluc);

//		JLabel label_2 = new JLabel("提取找到的文章数量：35");
		JLabel label_2 = new JLabel("提取找到的文章数量： ");
		label_2.setBounds(6, 122, 168, 16);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("提取文章为：");
		label_3.setBounds(6, 188, 95, 16);
		contentPane.add(label_3);

		JButton btnBack = new JButton("返回");
		btnBack.setBounds(327, 523, 117, 29);
		contentPane.add(btnBack);

		JLabel label_4 = new JLabel("输入文章编号\n");
		label_4.setBounds(6, 155, 95, 16);
		contentPane.add(label_4);

		textField_1 = new JTextField();
//		textField_1.setText("871");
		textField_1.setBounds(93, 150, 81, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton button_1 = new JButton("提取");
		button_1.setBounds(295, 150, 117, 29);
		contentPane.add(button_1);
//		String[] head = { "文章编号", "标题", "作者", "时间", "来源" };
//		Object[][] data = { { "871", "孙祥是维也纳队的孔子", "空", "2009-3-7", "南方都市报" },
//				{ "1093", "海外反应 华彩北京全球瞩目", "空", "2008-08-09", "南方都市报" },
//				{ "1500", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "7", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//				{ "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" }, { "6", "十大文化新闻", "空", "2012-12-28", "深圳晚报" },
//
//		};

//		table = new JTable(data, head);
		table = new JTable();
		table.setBounds(6, 180, 426, 82);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 216, 438, 66);
		contentPane.add(scrollPane);
		JLabel label_5 = new JLabel("正文");
		label_5.setBounds(6, 307, 61, 16);
		contentPane.add(label_5);

		JTextPane textPane = new JTextPane();
//		textPane.setText("北京时间3月5日，孙祥效力的奥地利维也纳队在奥地利杯的1/4决赛中1比1战平格拉茨飓风队，点球大战5比3淘汰对手晋级半决赛。孙祥在加时赛第106分钟替换上场，于第114分钟利用直接任意球破门扳平比分，将比赛拖入点球大战，并踢入球队的制胜点球，成为本场比赛的最大功臣。赛后，维也纳队官网放出头条：孙祥是维也纳队的孔子，称他是“任意球之王”。\n\n“孔子在中国是一个完美的化身，一个偶像。而孙祥，他持续的积极进取态度给人们留下了深刻的印象，他的友善让他能赢得队友和教练的好感。”维也纳官网的文章丝毫不掩赞美之情。\n\n自从去年9月底在联赛中拉伤大腿肌肉后，孙祥缺席了球队冬歇期前的所有比赛，直到上周末球队客场与阿尔塔奇的比赛最后6分钟才获得了替补出场机会。但未能帮助球队改变1比2输球的结果。");
		
		textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane.setBounds(6, 335, 438, 176);
		
		JScrollPane scrollPane_1 = new JScrollPane(textPane);
		scrollPane_1.setBounds(6, 326, 427, 190);
		contentPane.add(scrollPane_1);

	}
}
