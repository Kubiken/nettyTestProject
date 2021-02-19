package CQ;

import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.wire.DocumentContext;
import models.QueueMessage;

public class QueueWriter {

    final DocumentContext dc;
    final ExcerptAppender appender;

    public QueueWriter(String path){
        ChronicleQueue queue = ChronicleQueue.singleBuilder(path).build();
            appender = queue.acquireAppender();
            dc = appender.writingDocument();
    }

    public void write(QueueMessage msg) { //переделать запись в очередь
            try {
                appender.writeDocument(w -> w.write("Message").marshallable(
                        m -> m.write("message").text(msg.getSomeText())
                                .write("price").float64(msg.getSomePrice())
                .write("number").int32(msg.getSomeNum())));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


