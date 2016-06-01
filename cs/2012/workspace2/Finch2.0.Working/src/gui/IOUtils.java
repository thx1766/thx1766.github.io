package gui;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.util.Hashtable;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import codetree.CodeTree;

/**

 *This class contain any IO methods needed for saving/loading different elements for
Farrell

 *

 */

public class IOUtils{

   /**

    *This saves a map object into file.

    *@param m map that the users want to save
    *@param parentFrame is the MapEditorGui of which this file dialog box is popped up in front of

    */

   public static void saveMap(int m[][], JFrame parentFrame) throws IOException{
	   FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir = new File(d.getCanonicalPath() + "/farrell");
	   File defaultMapDir = new File(defaultDir.getCanonicalPath() + "/map");
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultMapDir.mkdir();
	   }else{
		   if(!defaultMapDir.exists()){
			   defaultMapDir.mkdir();
		   }
	   }
       JFileChooser fc = new JFileChooser(defaultMapDir);
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Farrell Map *.map", "map");
       fc.setAcceptAllFileFilterUsed(true);
       fc.addChoosableFileFilter(filter);
       int returnVal = fc.showSaveDialog(parentFrame);

       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File f =fc.getSelectedFile();
           if(m != null){
               if(!f.exists()){
                   f.createNewFile();
               }
               FileOutputStream fout = new FileOutputStream(f);
               ObjectOutputStream oos = new ObjectOutputStream(fout);
               oos.writeObject(m);

           }
       }

   }

   /**

    *This saves a program as a CodeTree object into file.

    *@param ct codeTree object (users known a codetree as a program) that the users want to
save
    *@param parentFrame is the RunProgramGui of which this file dialog box is popped up in front of
    */

   public static void saveProgram(CodeTree ct, JFrame parentFrame){
	   FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir=null;
	   File defaultProgDir=null;
	try {
		defaultDir = new File(d.getCanonicalPath() + "/farrell");
		defaultProgDir = new File(defaultDir.getCanonicalPath() + "/program");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultProgDir.mkdir();
	   }else{
		   if(!defaultProgDir.exists()){
			   defaultProgDir.mkdir();
		   }
	   }
       JFileChooser fc = new JFileChooser(defaultProgDir);
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Program *.prog", "prog");
       fc.setAcceptAllFileFilterUsed(true);
       fc.addChoosableFileFilter(filter);
       int returnVal = fc.showSaveDialog(parentFrame);

       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File f =fc.getSelectedFile();
           if(ct != null){
               if(!f.exists()){
                   try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               }
               FileOutputStream fout = null;
			try {
				fout = new FileOutputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(fout);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               try {
				oos.writeObject(ct);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

           }
       }


   }

   /**

    *This saves a macro object into file.
    *@param mac Macro that the users want to save
    *@param functionList is the name of the Macro
    * @throws IOException 
    */

   public static void saveMacro(Hashtable<String,CodeTree> mac , FunctionList functionList) throws IOException{
	   FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir = new File(d.getCanonicalPath() + "/farrell");
	   File defaultMacroDir = new File(defaultDir.getCanonicalPath() + "/macro");
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultMacroDir.mkdir();
	   }else{
		   if(!defaultMacroDir.exists()){
			   defaultMacroDir.mkdir();
		   }
	   }
	   File f = new File(defaultMacroDir.getCanonicalPath() + "/" + "functionList" + ".macro");
	   if(mac != null){
		   if(!f.exists()){
			   f.createNewFile();
		   }
		   FileOutputStream fout = new FileOutputStream(f);
		   ObjectOutputStream oos = new ObjectOutputStream(fout);
		   oos.writeObject(mac);
	   }

   }
   
   
   public static Hashtable<String, CodeTree> loadMacro() throws IOException{
	   Hashtable<String, CodeTree> flist = null;
	   FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir = new File(d.getCanonicalPath() + "/farrell");
	   File defaultMacroDir = new File(defaultDir.getCanonicalPath() + "/macro");
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultMacroDir.mkdir();
	   }else{
		   if(!defaultMacroDir.exists()){
			   defaultMacroDir.mkdir();
		   }
	   }
	   
	   File fn = new File(defaultMacroDir.getCanonicalPath() + "/functionList.macro");
	   FileInputStream fin = new FileInputStream(fn);
	   ObjectInputStream ois= new ObjectInputStream(fin);
	   try
	   {
		   return (Hashtable<String, CodeTree>)ois.readObject();
	   }
	   catch(Exception e)
	   {
		   System.out.println("Load failed");
		   return null;
	   }
	   
	   
	   
	   
   }
  

   /**

    *This loads a map object onto the program.
    *@param parentFrame is the MapEditorGui of which this file dialog box is popped up in front of
    *@return Map that the user wants to load to Farrell

    */

   public static int[][] loadMap(JFrame parentFrame) throws IOException, ClassNotFoundException{
       int m[][] = null;
       FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir = new File(d.getCanonicalPath() + "/farrell");
	   File defaultMapDir = new File(defaultDir.getCanonicalPath() + "/map");
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultMapDir.mkdir();
	   }else{
		   if(!defaultMapDir.exists()){
			   defaultMapDir.mkdir();
		   }
	   }
       JFileChooser fc = new JFileChooser(defaultMapDir);
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Farrell Map *.map", "map");
       fc.setAcceptAllFileFilterUsed(true);
       fc.addChoosableFileFilter(filter);
       int returnVal = fc.showOpenDialog(parentFrame);

       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File f =fc.getSelectedFile();
           if(f.exists()){
               FileInputStream fin = new FileInputStream(f);
               ObjectInputStream ois= new ObjectInputStream(fin);
               m = (int[][])ois.readObject();
           }
       }

       return m;

   }

   /**

    *This loads a program (as a code tree) onto the program
    *@param parentFrame is the RunProgramGui of which this file dialog box is popped up in front of
    *@return CodeTree that Farrell will load as a program

    */

   public static CodeTree loadProgram(JFrame parentFrame){
       CodeTree ct = null;
       FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir=null;
	   File defaultProgDir=null;
	try {
		defaultDir = new File(d.getCanonicalPath() + "/farrell");
		defaultProgDir = new File(defaultDir.getCanonicalPath() + "/program");
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultProgDir.mkdir();
	   }else{
		   if(!defaultProgDir.exists()){
			   defaultProgDir.mkdir();
		   }
	   }
       JFileChooser fc = new JFileChooser(defaultProgDir);
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Program *.prog", "prog");
       fc.setAcceptAllFileFilterUsed(true);
       fc.addChoosableFileFilter(filter);
       int returnVal = fc.showOpenDialog(parentFrame);

       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File f =fc.getSelectedFile();
           if(f.exists()){
               FileInputStream fin = null;
			try {
				fin = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               try {
				ct = (CodeTree)ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
       }

       return ct;
   }
   

   /**

    *This load a macro object onto the program
    *@param parentFrame is the MacroEditorGui of which this file dialog box is popped up in front of
    *@return Macro that the user wants to load to the current program studio
 * @throws IOException 

    */

   public static Hashtable<String, CodeTree> loadMacro(FunctionList parentFrame) throws IOException{
	   
	   Hashtable<String, CodeTree> m = null;
	   FileSystemView fsv = FileSystemView.getFileSystemView();
	   File d = fsv.getDefaultDirectory();
	   File defaultDir = new File(d.getCanonicalPath() + "/farrell");
	   File defaultMacroDir = new File(defaultDir.getCanonicalPath() + "/macro");
	   if(!defaultDir.exists()){
		   defaultDir.mkdir();
		   defaultMacroDir.mkdir();
	   }else{
		   if(!defaultMacroDir.exists()){
			   defaultMacroDir.mkdir();
		   }
	   }
	   File f = new File(defaultMacroDir.getCanonicalPath() + "/" + "functionList" + ".macro");
	   if(f.exists()){
		   FileInputStream fin = new FileInputStream(f);
		   ObjectInputStream ois= new ObjectInputStream(fin);
		   try {
			m = (Hashtable<String, CodeTree>)ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			m = new Hashtable<String, CodeTree>();
		}
	   }

	   return m;
	   
	  
   }

}