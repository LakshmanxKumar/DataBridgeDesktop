package desktopApp;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DnDTransferHandler extends TransferHandler {
	
	private JLabel label=null; 
	private String filePath;
	
	public String getFilePath() {return this.filePath;}
	
	public DnDTransferHandler(JLabel label) {
		this.label=label;
	}
	
	 @Override
     public boolean canImport(TransferSupport support) {
         return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
     }

     @Override
     public boolean importData(TransferSupport support) {
         if (!canImport(support)) {
             return false;
         }

         try {
             Transferable transferable = support.getTransferable();
             List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);

             for (File file : fileList) {
                 System.out.println("Dropped file: " + file.getAbsolutePath());
             }
             File one =fileList.get(0);
            
             label.setText(one.getName());
             filePath=one.getAbsolutePath();

             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }

}
