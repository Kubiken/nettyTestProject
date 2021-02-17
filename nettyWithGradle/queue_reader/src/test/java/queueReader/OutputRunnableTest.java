package queueReader;

import net.openhft.chronicle.queue.ExcerptTailer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

class OutputRunnableTest {

    @Test
    void tryReadFromQueue_expectingNoErrors(){
        ExcerptTailer et = Mockito.mock(ExcerptTailer.class);

        try {
        OutputRunnable.readTailer(et);
        } catch (Exception e) {
            fail("Can't correctly start reader");
        }
    }

}