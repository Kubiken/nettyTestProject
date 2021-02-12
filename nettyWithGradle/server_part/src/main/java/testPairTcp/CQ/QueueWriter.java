package testPairTcp.CQ;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.wire.DocumentContext;
import testPairTcp.models.QueueMessage;

public class QueueWriter {

    private ExcerptAppender appender;

    public void write(QueueMessage msg) {
        try (ChronicleQueue queue = ChronicleQueue.singleBuilder("TestPairTcp").build()) {
            appender = queue.acquireAppender();
            try (final DocumentContext dc = appender.writingDocument()) {

                appender.writeDocument(w -> w.write("Message").marshallable(
                        m -> m.write("message").text(msg.getSomeText())
                                .write("price").float64(msg.getSomePrice())
                .write("number").int32(msg.getSomeNum())));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
