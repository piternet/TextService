package rtbhouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class TextServiceController {

    private static Logger logger = Logger.getLogger(TextServiceController.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TextService textService;

    @RequestMapping("/textservice")
    public String service(@RequestParam(value = "line") String line, @RequestParam(value = "user") String user) throws JsonProcessingException {
        logger.log(Level.INFO, "{0} just sent a request.", user);
        return mapper.writeValueAsString(new TextServiceResponse(prepareResponse(line)));
    }

    private String prepareResponse(String line) {
        String response;
        try {
            response = textService.getLine(line);
        } catch (LineDoesNotExistException e) {
            response = "Error - line with such number does not exist!";
        }
        return response;
    }
}