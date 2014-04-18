package net.glamenvseptzen.quickstart;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class MyStringUtilTest {

    @Test
    public void splitAndCapitalize() {
        String src = "hello world";
        String r[] = MyStringUtil.splitAndCapitalize(src, " ");
        String expected[] = new String[] { "Hello", "World" };
        assertThat(r, is(expected));
    }

    @Test
    public void loadTestResource() throws Exception {
        Properties msg = new Properties();
        msg.load(getClass().getClassLoader().getResourceAsStream(
                "net/glamenvseptzen/quickstart/msg.properties"));
        assertThat(
                MyStringUtil.splitAndCapitalize(msg.getProperty("msg1"), " "),
                is(new String[] { "Good", "Evening" }));
        assertThat(
                MyStringUtil.splitAndCapitalize(msg.getProperty("msg2"), " "),
                is(new String[] { "Happy", "Programming" }));
    }
}
