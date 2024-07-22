package classes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

public class MainMenu {
    private String folderPath;
    private JFrame mainFrame;
    private JLabel folderPathLabel;
    private JButton selectNewPathButton;
    private JScrollPane scrollableFileViewer;
    private JPanel filesHolder;
    private JPanel directorySearchPanel;
    private ArrayList<FileInstance> filesInFolder;

    public MainMenu(){
        folderPath = "";
        filesInFolder = new ArrayList<FileInstance>();
        mainFrame = new JFrame();
        directorySearchPanel = new JPanel();
        filesHolder = new JPanel();
        filesHolder.setLayout(new BoxLayout(filesHolder,BoxLayout.Y_AXIS));
        directorySearchPanel.setSize(new Dimension(1000,100));
        scrollableFileViewer = new JScrollPane(filesHolder);
        folderPathLabel = new JLabel();
        folderPathLabel.setText("Aktualna ścieżka: " + folderPath);
        selectNewPathButton = new JButton();
        selectNewPathButton.setText("Wybierz inną ścieżkę");
        selectNewPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                selectFolderPath();
                setFilesInCurrentDirectory();
                createFileViewer();
            }
        });
        directorySearchPanel.add(folderPathLabel);
        directorySearchPanel.add(selectNewPathButton);
        mainFrame.add(directorySearchPanel);
        mainFrame.add(scrollableFileViewer);
        
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setSize(1000,1000);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void runMainMenu(){
        this.selectFolderPath();
        this.setFilesInCurrentDirectory();
        this.setAllComponentsVisible();
        this.createFileViewer();
    }

    public void createFileViewer(){
        filesHolder.removeAll();
        for(FileInstance instance : filesInFolder){
            System.out.println(instance.getFileName());
            JPanel fileContent = new JPanel();
            fileContent.add(instance.getIsCheckedCheckBox());
            fileContent.add(new JLabel(instance.getFileName()));
            fileContent.add(new JLabel(instance.getTypeOfFile() == TypeOfFile.DIRECTORY ? "Folder" : "Plik"));
            fileContent.setVisible(true);
            
            filesHolder.add(fileContent);
        }
        filesHolder.revalidate();
        filesHolder.repaint();
    }

    public void selectFolderPath(){
        this.folderPath = JOptionPane.showInputDialog("Wprowadź ścieżkę");
        folderPathLabel.setText("Aktualna ścieżka: " + folderPath);
        this.setFilesInCurrentDirectory();
    }

    public void setFilesInCurrentDirectory(){
        filesInFolder.clear();
        File directory = new File(folderPath);
        if(!directory.exists() || !directory.isDirectory()) return; // Implement later
        String directoryContent[] = directory.list();
        for(String directoryElement : directoryContent){
            filesInFolder.add(new FileInstance(folderPath + "/" + directoryElement));
        }
    }   

    public void setAllComponentsVisible(){
        mainFrame.setVisible(true);
        scrollableFileViewer.setVisible(true);
    }
}
