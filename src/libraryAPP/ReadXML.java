 /**

     * @author robin xu at mentor graphics

     * @date 25-6-2013 

     */


package libraryAPP;




import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


 

public class ReadXML 
{
    /**

     * 去掉字符串右边的空格

     * @param str 要处理的字符串

     * @return 处理后的字符串

     */


     public static void main(String args[]) throws Exception {
         ReadXML.xmlUpdateDemo("0.75","CL10");
    }
     
     
    public static Document load(String filename) {
        Document document = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filename));
            document.normalize();
        } catch (Exception ex) {
        }
        return document;
    }
 
    /**
     *
     * @param CSA
     * @param Color
     * @param Material
     * @return
     */
    public static String xmlUpdateDemo(String CSA, String Material )
    {
        String wirespec = null;
        //打开文件夹下面所有的文件
       File file1= new File("C:\\library\\");
       //System.out.print(file1.list().length);
        //System.out.println();
       String[] s = file1.list();
       //对每一个xml文件单独进行检索，并修改
       for(int numXML = 0;numXML < s.length;numXML++)
       {
        Document document = load("C:\\library\\"+s[numXML]);
//       System.out.print(document.getDocumentURI());
//        System.out.println();
        Node root = document.getDocumentElement();
        //判断更目录是否有子节点，并判断其名字是否为“object”
        if (root.hasChildNodes() && "chssystem".equals(root.getNodeName()))
            {
                System.out.print(root.getNodeName());
              {
                    NodeList ftpnodes = root.getChildNodes();
                    for (int i = 0; i < ftpnodes.getLength(); i++) 
                    {
                        
                        //获取下一级子元素
                        //获取值
                            Node subnode =ftpnodes.item(i); 
                             
                               //判断其元素关键词是否为“path”即我们需要修改的变量
                            if (subnode.getNodeType() == Node.ELEMENT_NODE
                                    &&"librarywirespec".equals(subnode.getNodeName())) 
                                    {
                                    
                                    String csa= subnode.getAttributes().getNamedItem("csa").getTextContent();
                                    
                                    String librarymaterial_id = subnode.getAttributes().getNamedItem("librarymaterial_id").getTextContent();
                                   
                                    String Materials = GetMaterials(librarymaterial_id );
                                    
                                    if(CSA.equals(csa)&&Materials.equals(Material))
                                    {
                                     wirespec= subnode.getAttributes().getNamedItem("wirespec").getTextContent();
                                    }
                                    }
                    }
                }
           
            }
        
        }
        return wirespec;
    }  
    
    
    public static String GetMaterials(String MaterialID)
    {
        String materialcode = null;
        //打开文件夹下面所有的文件
       File file1= new File("C:\\library\\");
       String[] s = file1.list();
        for(int numXML = 0;numXML < s.length;numXML++)
       {
        Document document = load("C:\\library\\"+s[numXML]);
        Node root = document.getDocumentElement();
        
        //判断更目录是否有子节点，并判断其名字是否为“object”
        if (root.hasChildNodes() && "chssystem".equals(root.getNodeName()))
            {
                {
                    
                     NodeList ftpnodes = root.getChildNodes();
                     
                    for (int i = 0; i < ftpnodes.getLength(); i++) 
                    {
                            Node subnode =ftpnodes.item(i);
                           
                            if (subnode.getNodeType() == Node.ELEMENT_NODE
                                    && "librarymaterial".equals(subnode.getNodeName())) 
                                    {
                                    String MID = subnode.getAttributes().getNamedItem("librarymaterial_id").getTextContent();
                                    if(MID.equals(MaterialID))
                                    {
                                        materialcode = subnode.getAttributes().getNamedItem("materialcode").getTextContent();
                                    }
                                    
                       }
                    }
                }
           
            }
        
        }
        return materialcode;
    }  
    
}