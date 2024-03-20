import java.io.File;

public class FolderCounter {

    public static void main(String[] args) {
        String targetFolderPath = "C:/Users/Newtech/IdeaProjects/untitled/TestFolder";
        File targetFolder = new File(targetFolderPath);

        if (targetFolder.exists()) {
            int[] folderFileCounts = countFoldersAndFiles(targetFolder);

            System.out.println("Niit Folder: " + folderFileCounts[0]);
            System.out.println("Niit File: " + folderFileCounts[1]);
            System.out.println("Ug Folder dotorh File bolon Folder niileed: " + (folderFileCounts[0] + folderFileCounts[1]));
        } else {
            System.out.println("Tiim folder oldsongue");
        }
    }

    private static int[] countFoldersAndFiles(File folder) {
        int totalFolders = 0;
        int totalFiles = 0;

        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    totalFolders++;
                    int[] subFolderFileCounts = countFoldersAndFiles(file);
                    totalFolders += subFolderFileCounts[0];
                    totalFiles += subFolderFileCounts[1];
                } else {
                    totalFiles++;
                }
            }
        }

        int[] folderFileCounts = {totalFolders, totalFiles};
        return folderFileCounts;
    }
}
