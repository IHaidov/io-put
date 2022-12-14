package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.*;

import java.util.Arrays;
import java.util.Objects;


@RestController
@RequestMapping("/transform")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody Request input) {
        String text = input.getText();
        String[] transforms = input.getTransforms();
        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        TextTransformer transformer = new TextTransformer(text);
        ////
        for(String transformation : transforms){
            System.out.println(transformation);
            if(Objects.equals(transformation, "upperCase")){
                transformer = new UpperCase(transformer);
            }
            else if(Objects.equals(transformation, "lowerCase")){
                transformer = new LowerCase(transformer);
            }
            else if(Objects.equals(transformation, "inverse")){
                transformer = new Inverse(transformer);
            }
            else if(Objects.equals(transformation, "capitalize")){
                transformer = new Capitalize(transformer);
            }
            else if(Objects.equals(transformation, "signsInLatex")){
                transformer = new SignsInLatex(transformer);
            }
            else if(Objects.equals(transformation, "collapseToShortcuts")){
                transformer = new CollapseToShortcuts(transformer);
            }
            else if(Objects.equals(transformation, "extendShortcuts")){
                transformer = new ExtendShortcuts(transformer);
            }
            else if(Objects.equals(transformation, "numberToText")){
                transformer = new NumberToText(transformer);
            }
            else if(Objects.equals(transformation, "textToNumber")){
                transformer = new TextToNumber(transformer);
            }
            else if(Objects.equals(transformation, "repetitionsRemove")){
                transformer = new RepetitionsRemove(transformer);
            }
            else if(Objects.equals(transformation, "permutations")){
                transformer = new Permutations(transformer);
            }else if(Objects.equals(transformation, "caesar")) {
                transformer = new CipherCaesar(transformer);
            }else if(Objects.equals(transformation, "deCaesar")){
                transformer = new DecipherCaesar(transformer);
            }
        } ////
        return transformer.transform();
    }



}


