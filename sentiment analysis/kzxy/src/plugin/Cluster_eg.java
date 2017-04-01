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

public class Cluster_eg implements PluginInterface{

	@Override
	public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept {
		//first write confid
//		XlsWriter writer;
//		try {
//			writer = new XlsWriter("./xls/cluster_temp.xls");
//			List<Article> articleToWrite = new ArrayList<>();
//			Article tmp = new Article();
//			String temp = Integer.toString((int)allData.getSettings("nOfclass"));
//			System.out.println(temp);
//			tmp.set("nOfclass", (Object)temp);
//			articleToWrite.add(tmp);
//			writer.write(articleToWrite);
//		} catch (IOException | WriteException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
		 //the python excute
		int language = (int) allData.getSettings("language");
		XlsWriter writer = null;
		try {
			writer = new XlsWriter("./xls/input.xls");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(allArticle.size());
		List<Article> articleToWrite = new ArrayList<>();
		for (Article article : allArticle) {
			Article tmp = new Article();
			tmp.set("content", article.get("content"));
			articleToWrite.add(tmp);
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
	        Process p = Runtime.getRuntime().exec("python ./plugin/cluster_eg/cluster.py");
	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = br.readLine()) != null)
	        {
	            sb.append(line);
	        }
	        if (!sb.toString().equals(""))
	        {
	            System.err.println(sb.toString());
	            throw new PluginExectExcept("", 22);
	        }
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
