import org.falcon.jokegenerator.DadJokeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DadJokeGeneratorTest {
    DadJokeGenerator dadJokeGenerator = new DadJokeGenerator();
    @Test
    public void testJokeExists() {
        System.out.println(this.dadJokeGenerator.getJoke().getFullJoke());
        assertTrue(!this.dadJokeGenerator.getJoke().getFullJoke().isEmpty());
    }
}
