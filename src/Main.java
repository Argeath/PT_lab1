import java.io.File;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("brak argumentow");
            return;
        }
        int sort = 0;

        if(args.length > 1 && args[1].equals("-s1"))
            sort = 1;
        else if(args.length > 1 && args[1].equals("-s2"))
            sort = 2;

        File file = new File(args[0]);
        boolean isDirectory = file.isDirectory();
        boolean isFile = file.isFile();
        DiskElement element = null;
        if(isDirectory)
            element = new DiskDirectory(file, sort);
        else if(isFile)
            element = new DiskFile(file);

        if(element != null)
            element.print();
    }
}
