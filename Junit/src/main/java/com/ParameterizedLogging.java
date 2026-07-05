import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String val1 = "arg1";
        String val2 = "arg2";
        logger.info("This is a parameterized message: {} and {}", val1, val2);
    }

}
