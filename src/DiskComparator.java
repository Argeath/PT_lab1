import java.io.IOException;
import java.util.Comparator;

public class DiskComparator implements Comparator<DiskElement> {
    @Override
    public int compare(DiskElement o1, DiskElement o2) {
        return o1.file.getName().compareToIgnoreCase(o2.file.getName());
        //return o1.file.lastModified() > o2.file.lastModified() ? 1 : o1.file.lastModified() == o2.file.lastModified() ? 0 : -1;
    }
}
