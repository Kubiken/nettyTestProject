package testPairTcp.serverPart;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import models.QueueMessage;
import models.MessageCreator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SimpleProcessingHandlerTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;


    @BeforeAll
    static void outStreamsStateReading(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    static void returnOutStreamsStateToNormal(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void serverChannelActivationTest_expectingCorrectHandler(){
        ChannelHandlerContext ctx = Mockito.spy(ChannelHandlerContext.class);
        SimpleProcessingHandler sph = new SimpleProcessingHandler();

        when(ctx.alloc()).thenReturn(Mockito.mock(ByteBufAllocator.class));
        sph.handlerAdded(ctx);

        verify(ctx).alloc();
        String expected = "Handler added\n";
        String actual = outContent.toString();
        assertEquals(expected, actual);

    }
}