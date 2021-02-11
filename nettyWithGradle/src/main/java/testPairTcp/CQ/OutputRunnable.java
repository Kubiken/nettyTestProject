package testPairTcp.CQ;

import net.openhft.chronicle.core.Jvm;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;

public class OutputRunnable {
    public static void main(String[] args) {
        String path = "TestPairTcp";
        SingleChronicleQueue queue = SingleChronicleQueueBuilder.binary(path).build();
        ExcerptTailer tailer = queue.createTailer();

        while (true) {
            readTailer(tailer);
        }
    }

    public static void readTailer(ExcerptTailer tailer){
        tailer.readDocument(w -> w.read("Message").marshallable(
                m -> {
                    String t = m.read("message").text();
                    if (t==null)
                        Jvm.pause(10);
                    else
                        System.out.println(t+" "+m.read("price").float64()
                                +" "+m.read("number").int32());
                }));
    }
}
