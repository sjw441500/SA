package com.kzxy.handle;

import java.util.ArrayList;
import java.util.List;

import com.kzxy.data.*;

public class KeyReport{
	private List<Article> keyArticle;
	private String[][] keyArticles={{"QM","QN","QT"},
			{"1","15","泰国第十四家孔子学院揭牌"},
			{"2568","10","中国一些驻外使领馆举行新春招待会"},
			{"5192","9","中国面临十大战略机遇 国防现代化迈出重要步伐"},
			{"504","8","俄罗斯总理会见陈至立"},
			{"3216","8","2014年高考作文真题预测(16)：中国元素"},
			{"3862","8","内外统筹 全面开启中国文化产业国际化时代"},
			{"10598","8","中蒙建交65周年 习近平将到蒙古国“串门”"},
			{"1108","7","构建长期稳定的中巴友好合作关系"},
			{"1344","7","李克强同澳大利亚总理吉拉德会谈"},
			{"1798","7","华裔子弟积极学中文 马来西亚中文发展进高峰期"}};
	public KeyReport(GlobalData alldata){
		keyArticle = new ArrayList<>();
	}
	public String[][] getKey(){
		return keyArticles;
	}
}