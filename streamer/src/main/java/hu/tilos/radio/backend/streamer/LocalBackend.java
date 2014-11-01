package hu.tilos.radio.backend.streamer;

import hu.tilos.radio.backend.Mp3File;
import hu.tilos.radio.backend.ResourceCollection;
import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Named
public class LocalBackend implements Backend {

    @Inject
    @ConfigProperty(name = "archive.root")
    private String root;

    /**
     * Limit in Kbyte/sec.
     */
    @Inject
    @ConfigProperty(name = "throttle.limit")
    private int throttleLimit = 0;

    public LocalBackend(String root) {
        this.root = root;
    }

    public LocalBackend() {
    }

    @Override
    public void stream(ResourceCollection collection, int startOffset, int endPosition, OutputStream out) throws Exception {
        InputStream[] streams = new InputStream[collection.getCollection().size()];

        int i = 0;
        for (Mp3File file : collection.getCollection()) {
            streams[i++] = new LimitedInputStream(new FileInputStream(root + file.getName()), file.getStartOffset(), file.getEndOffset());
        }
        byte[] b = new byte[4096];
        int r;
        InputStream is = new LimitedInputStream(new CombinedInputStream(streams), startOffset, endPosition);
        if (throttleLimit > 0) {
            is = new ThrottledInputStream(is, throttleLimit * 1024);
        }


        try {
            while ((r = is.read(b)) != -1) {
                out.write(b, 0, r);
            }
        } catch (Exception ex) {
            //these exceptions about the stream is closing.
            //TODO: FIlter out the real excetptions...
        } finally {
            is.close();
            try {
                out.flush();
            } catch (Exception ex){
                //don't worry
            }
            try {
                out.close();
            } catch (Exception ex){
                //be happy
            }
        }


    }

    @Override
    public int getSize(ResourceCollection collection) {
        int size = 0;
        for (Mp3File file : collection.getCollection()) {
            size += size(file);
        }
        return size;
    }

    @Override
    public File getLocalFile(Mp3File mp3File) {
        return new File(root, mp3File.getName());
    }


    public long size(Mp3File file) {
        long size = new File(root + file.getName()).length();
        if (file.getEndOffset() < size) {
            size = file.getEndOffset();
        }
        return size - file.getStartOffset();
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public int getThrottleLimit() {
        return throttleLimit;
    }

    public void setThrottleLimit(int throttleLimit) {
        this.throttleLimit = throttleLimit;
    }
}
