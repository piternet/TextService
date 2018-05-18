package rtbhouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class TextServiceController {

    private final static Logger logger = Logger.getLogger(TextServiceController.class.getName());
    private final static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TextService textService;

    @RequestMapping("/textservice")
    public ResponseEntity<String> service(@RequestParam(value = "line") String line, @RequestParam(value = "user") String user) throws JsonProcessingException {
        log(line, user);
        Optional<String> response = prepareResponse(line);
        if (response.isPresent())
            return ResponseEntity.ok(mapper.writeValueAsString(new TextServiceResponse(response.get())));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TextServiceResponse.ERROR_JSON);
    }

    private void log(String line, String user) {
        logger.log(Level.INFO, "User [{0}] just sent a request for a line number [{1}].", new String[]{user, line});
    }

    private Optional<String> prepareResponse(String line) {
        Optional<String> response;
        try {
            response = Optional.of(textService.getLine(line));
        } catch (LineDoesNotExistException e) {
            response = Optional.empty();
        }
        return response;
    }
}