package rtbhouse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextServiceResponse {

    public final static String ERROR_JSON = "{'error':'Bad line number'}";

    public final String content;
    public final String date;

    public TextServiceResponse(String content) {
        this.content = content;
        this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
