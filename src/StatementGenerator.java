import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.aspose.words.Document;

public class StatementGenerator {
	public static void main(String[] args) throws Exception {
		try {
			if(args.length < 2) {
				System.err.println("Usage: templatePath xml1Path xml2Path ...");
			}
			
			String templatePath = args[0];
			Document doc = new Document(templatePath);
			
			for(int i = 1; i < args.length; i++) {
				String xmlPath = args[i];
				DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

				org.w3c.dom.Document xmlData = db.parse(xmlPath);

				doc.getMailMerge().executeWithRegions(new XmlMailMergeDataSet(xmlData));
		
				int extensionIndex = xmlPath.lastIndexOf('.');
				String pdfPath = xmlPath.substring(0, extensionIndex) + ".pdf";
				doc.save(pdfPath);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
