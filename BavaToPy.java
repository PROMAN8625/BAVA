package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class BavaToPy {
	
	String code;
	String File;
	String PyFile;
	Scanner in;

	public static void main(String[] args) {new BavaToPy();}

	BavaToPy(){
		getFile();
		readFile();
		MakePyFile();
		transFile();
	}

	public void getFile(){
		in = new Scanner(System.in);
		System.out.print("Bava File: ");
		File = in.next();
		System.out.println("Found " + File);
		PyFile = File.replace(".bava", ".py");
	}
	
	public void readFile(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(File));
			code = br.readLine();
			String codeTemp;
			while((codeTemp = br.readLine()) != null) {
				code = code+codeTemp;}
			br.close();}catch(Exception e) {}
	}
	
	public void MakePyFile(){
		File file = new File(PyFile);
		try{  
			boolean  result = file.createNewFile();
			if(result){  
				System.out.println("Selected "+file.getCanonicalPath());}  
			else{
				System.out.println("Selected "+file.getCanonicalPath());}
		}catch (Exception e){e.printStackTrace();} 
	}
	
	public void transFile(){
		// GENERAL
		code = code.replace("\",", "\")\n"); //		 ", == ")
		code = code.replace(",", ")\n"); // 		 , == )
		
		// PRINT
		code = code.replace("s.print-", "print(");
		// COMMENT
		code = code.replace("//", "#");
		// INPUT
		code = code.replace("s.in-", "input(");
		// VAR
		code = code.replace("var ", "");
		code = code.replace("--", "=");
		code = code.replace(",", "\n");
		code = code.replace("~", "+");
		// ...stuff...
		code = code.replace(".n.", "\\n");
		code = code.replace("...", ".");
		code = code.replace(".t.", "\\t");

		System.out.println("\n\nSuccesfully Compiled File To\n  "+PyFile);
		writeFile();
	}
	
	public void writeFile(){
		if(PyFile!=null) {
			try {
				FileWriter fw = new FileWriter(PyFile);
				fw.write(code);
				fw.close();
			}catch(Exception e){System.out.println(e);}}else { System.out.println("pyfile null"); }
	}
	
	
}
