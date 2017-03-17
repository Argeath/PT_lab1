import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DiskElement implements Comparable {
    protected File file; //java.io.File;

    DiskElement(File f) {
        file = f;
    }

    protected abstract void print(int depth);

    public void print() { print(0); }

    protected void printElement(int depth) {
        Date lastM = new Date(file.lastModified());
        SimpleDateFormat template = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = template.format(lastM);

        String name = "";
        for(int i = 0; i < depth; i++)
            name += '-';

        name += " " + file.getName();

        if(file.isDirectory())
            name += "/";

        String paddedName = String.format("%0$-60s", name);
        String fileType = file.isDirectory() ? "K" : "P";
        System.out.println(" " + paddedName + " " + fileType + "\t" + formattedDate);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof DiskElement) {
            try {
                return file.getCanonicalPath().equals(((DiskElement)obj).file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        try {
            return file.getCanonicalPath().hashCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int compareTo(Object o) {
        if(equals(o)) return 0;

        if(o != null && o instanceof DiskElement) {
            DiskElement od = (DiskElement)o;
            if (file.isDirectory() && !od.file.isDirectory())
                return -1;
            else if(od.file.isDirectory() && !file.isDirectory())
                return 1;

            return file.getName().compareToIgnoreCase(od.file.getName());
        }
        return 1;
    }
}
