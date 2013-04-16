import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


public class AppMain{

	LinkedHashMap<String, Integer> nameId = new LinkedHashMap<>();
	private void readFile(){
		int count = 1;
		int numline = 0;
		File file = new File("CN.txt");
		BufferedReader bufferedReader = null;
		String lineContent="";
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			while((lineContent = bufferedReader.readLine()) != null){
				numline++;
				String[] edges = lineContent.split("\t");
				for(String s : edges){
					if(!nameId.containsKey(s))
						nameId.put(s, count++);
				}
			}
			System.out.println(numline);
		}catch(Exception e){
			System.err.println("读文件失败：" + e);
		}
	}
	
	private void writeNodeFile(){
		File file = new File("node.txt");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			Iterator iter = nameId.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				sb.append(val.toString() + "\t" + key.toString() + "\r\n");
				}
			
			bufferedWriter.write(sb.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private void writeEdgeFile(){
		File readFile = new File("CN.txt");
		File writeFile = new File("edge.txt");
		
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		StringBuilder sb = new StringBuilder();
		
		String lineContent="";
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile),"UTF-8"));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), "UTF-8"));
			while((lineContent = bufferedReader.readLine()) != null){
//				sb.append(lineContent);
				String[] edges = lineContent.split("\t");
				for(String s : edges){
					sb.append(nameId.get(s));
					sb.append("\t");
				}
				sb.append("\r\n");
			}
			bufferedWriter.write(sb.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void run(){
		readFile();
		writeNodeFile();
		writeEdgeFile();
	}
	public static  void main(String[] args){
		AppMain appMain = new AppMain();
		appMain.run();
	}
}
