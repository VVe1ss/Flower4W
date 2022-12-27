package commands;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import test.ConsoleCheck;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandProcessorTest extends ConsoleCheck {

    public static Stream<Arguments> source() {
        Command command = mock(Command.class);
        when(command.updateData()).thenReturn(true);
        return Stream.of(
                Arguments.arguments((Object) null),
                Arguments.arguments(command)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    public void commandProcessorTest(Command command){
        CommandProcessor commandProcessor = new CommandProcessor(command);
        commandProcessor.execute();
        if (command == null) {
            assertTrue(console.toString().contains("program finished"));
        }else
            verify(command, times(1)).updateData();
    }

}