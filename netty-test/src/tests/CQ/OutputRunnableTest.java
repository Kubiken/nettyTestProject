package tests.CQ;

import net.openhft.chronicle.queue.ExcerptTailer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testPairTcp.CQ.OutputRunnable;

import static org.junit.jupiter.api.Assertions.*;

class OutputRunnableTest {

    @Test
    void tryRead(){
        ExcerptTailer et = Mockito.mock(ExcerptTailer.class);
        try {
        OutputRunnable.readTailer(et);} catch (Exception e) {
            fail("Can't correctly start reader");
        }
    }

}