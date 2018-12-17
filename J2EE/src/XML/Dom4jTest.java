package XML;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Dom4jTest {
    public static void main(String[] args) throws DocumentException {
            SAXReader reader = new SAXReader();
            Document read = reader.read("C:\\github\\java_study\\JavaCore\\src\\main\\java\\XML\\students.xml");
            Element rootElement = read.getRootElement();

            //普通方式
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                if("002".equals(element.attributeValue("id"))){
                    List<Element> elem = element.elements();
                    for (Element e : elem) {
                        if("002".equals(e.attributeValue("id"))){
                            Element nameElement = e.element("name");
                            System.out.println("普通方式: "+nameElement.getText());
                        }
                    }
                }
            }
        //xpath方式
            Element nameElement = (Element)rootElement.selectSingleNode("/root/class[@id='002']/student[@id='002']/name");
            System.out.println("xpath方式: "+nameElement.getText());
        }
}
