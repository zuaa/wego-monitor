package net.glamenvseptzen.quickstart;

import static org.hamcrest.CoreMatchers.*
import static org.junit.Assert.*

import org.junit.Test

class MyGroovyUtilTest
{
    @Test void testGreed()
    {
        assertThat MyGroovyUtil.greed("Bob"), is("Welcome to Groovy world, Bob ?")
    }
}
