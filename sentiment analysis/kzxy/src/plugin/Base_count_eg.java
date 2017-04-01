package plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.handle.XlsWriter;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;

import jxl.write.WriteException;

public class Base_count_eg implements PluginInterface {


	public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept {
		// 将数据传给python
		XlsWriter writer;
		try {
			writer = new XlsWriter("./xls/base_eg.xls");
			List<Article> articleToWrite = new ArrayList<>();
			for (Article article : allArticle) {
				Article tmp = new Article();
				tmp.set("source", article.get("source"));
				tmp.set("url", article.get("url"));
				tmp.set("time", article.get("time"));
				articleToWrite.add(tmp);
			}
			writer.write(articleToWrite);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	try {
		Process p = Runtime.getRuntime().exec("python ./plugin/base_count_eg/draw.py");
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
			sb.append(line);
		}
			if (!sb.toString().equals("")) {
				System.err.println(sb.toString());
				throw new PluginExectExcept("", 22);
		}
		}

	catch (Exception e) {
			e.printStackTrace();
			throw new PluginExectExcept("", 22);
		}
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
