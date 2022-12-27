package commands.main.settings;

import json.JsonConverterData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SettingsTest {

    @Test
    public void test(){
        Settings settings = new Settings(mock(JsonConverterData.class));
        assertNull(settings.execute());
    }

}