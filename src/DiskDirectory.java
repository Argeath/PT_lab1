import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DiskDirectory extends DiskElement {
    private Set<DiskElement> children;

    DiskDirectory(File f, int sort) {
        super(f);

        if(sort == 0)
            children = new HashSet<DiskElement>();
        else if(sort == 1)
            children = new TreeSet<DiskElement>();
        else if(sort == 2)
            children = new TreeSet<DiskElement>(new DiskComparator());

        File[] files = f.listFiles();

        if (files != null) {
            for (File file : files) {
                DiskElement element = null;

                if(file.isDirectory())
                    element = new DiskDirectory(file, sort);
                else if(file.isFile())
                    element = new DiskFile(file);

                if(element != null)
                    children.add(element);
            }
        }
    }

    @Override
    protected void print(int depth) {
        printElement(depth);

        if(depth > 0)
            return;

        for(DiskElement it: children) {
            it.print(depth + 1);
        }
    }
}
