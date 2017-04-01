package plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.kzxy.data.Article;
import com.kzxy.handle.GlobalData;
import com.kzxy.plugin.PluginExectExcept;
import com.kzxy.plugin.PluginInterface;

public class Emotion_cn_by_year implements PluginInterface{

	@Override
	public void exect(List<Article> allArticle, GlobalData allData) throws PluginExectExcept {
		 try {
				Process p = Runtime.getRuntime().exec("python ./plugin/emotion_cn_by_year/emotion.py");
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
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}

}