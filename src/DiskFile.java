import java.io.File;

public class DiskFile extends DiskElement {
    DiskFile(File f) {
        super(f);
    }

    @Override
    protected void print(int depth) {
        printElement(depth);
    }
}
