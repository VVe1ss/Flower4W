package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleCheck {
    protected ByteArrayOutputStream console;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        console = new ByteArrayOutputStream();
        System.setOut(new PrintStream(console));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
