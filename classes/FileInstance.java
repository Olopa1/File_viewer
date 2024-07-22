package classes;

import java.io.File;

import javax.swing.JCheckBox;
import java.awt.event.*;;

public class FileInstance implements ActionListener{
    private String fileName;
    private String filePath;
    private File file;
    private TypeOfFile typeOfFile;
    private JCheckBox fileCheckBox;
    private boolean isChecked;

    public FileInstance(String filePath){
        this.fileCheckBox = new JCheckBox();
        this.filePath = filePath;
        this.file = new File(filePath);
        this.typeOfFile = file.isDirectory() ? TypeOfFile.DIRECTORY : TypeOfFile.FILE;
        this.fileName = file.getName();
    }
    
    public String getFileName(){
        return fileName;
    }
    public TypeOfFile getTypeOfFile(){
        return typeOfFile;
    }
    public String getFilePath(){
        return filePath;
    }
    public File getFile(){
        return file;
    }
    public JCheckBox getIsCheckedCheckBox(){
        return fileCheckBox;
    }
    public boolean getCheckBoxState(){
        return isChecked;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isChecked = fileCheckBox.isSelected() ? true : false;
    }
}
