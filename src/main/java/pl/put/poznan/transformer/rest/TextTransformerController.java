package pl.put.poznan.transformer.rest;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.json_format.transInfo;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;


@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(value ="/TextTransform/{text}", method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        return listTrans(transforms).transform(text);
    }

    @RequestMapping(value = "/TextTransform", method = RequestMethod.GET, produces = "application/json")
    public String post(@RequestBody transInfo info) throws JSONException {

        // log the parameters
        logger.debug(info.getText());
        logger.debug(Arrays.toString(info.getTransforms()));

        JSONObject response = new JSONObject();
        response.put("text", listTrans(info.getTransforms()).transform(info.getText()));

        return response.toString();
    }

    private static IText listTrans(String[] transforms) {
        IText transformation = new InputString();
        for (String trans : transforms) transformation = chooseTransformation(trans, transformation);
        return transformation;
    }

    private static IText chooseTransformation(String name, IText transI) {
        return switch (name) {
            //case "upper" -> new ToUpper(transI);
            //case "reverse" -> new Reverse(transI);
            //case "lower" -> new ToLower(transI);
            case "capitalize" -> new Capitalize(transI);
            default -> transI;
        };
    }
}


