package CQ;

import models.QueueMessage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QueueWriterTest {

    @Test
    void tryWriteToQueue_expectCorrectWritingInQueue() {
        QueueMessage qm = Mockito.spy(QueueMessage.class);
        QueueWriter queueWriter = new QueueWriter();

        when(qm.getSomeText()).thenReturn("Vamus");
        when(qm.getSomePrice()).thenReturn(2019.0);
        when(qm.getSomeNum()).thenReturn(11);

        queueWriter.write(qm);

        verify(qm).getSomeText();
        verify(qm).getSomePrice();
        verify(qm).getSomeNum();

    }
}