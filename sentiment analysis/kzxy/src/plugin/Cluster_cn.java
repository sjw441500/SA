package plugin;

import java.io.BufferedReader;
import java.io.File;
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
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
public class Cluster_cn implements PluginInterface{

	@Override
	public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept {
		// the python excute
		int language = (int) allData.getSettings("language");
		XlsWriter writer = null;
		try {
			writer = new XlsWriter("./xls/input_cn.xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(allArticle.size());
		List<Article> articleToWrite = new ArrayList<>();
		List<String>  timeToWrite=new ArrayList<>();
		for (Article article : allArticle) {
			Article tmp = new Article();
			tmp.set("content", article.get("content"));
			//tmp.set("time", article.get("time"));
			articleToWrite.add(tmp);
			timeToWrite.add(article.get("time").toString());
		}
		System.out.println(articleToWrite.size());
		try {
			writer.write(articleToWrite);
		} catch (WriteException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    try
	    {
	        Process p = Runtime.getRuntime().exec("python ./plugin/cluster_cn/kmean_cn.py");
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null)
	        {
	            sb.append(line);
	        }
	        //if (!sb.toString().equals(""))
	       // {
	           // System.err.println(sb.toString());
	           // throw new PluginExectExcept("", 22);
	        //}
	    }
	    
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        throw new PluginExectExcept("", 22);
	    }
		
		//read back and pose them into all data
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

}
