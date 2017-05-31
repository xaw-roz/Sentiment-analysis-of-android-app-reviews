package mainClasses;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Lemmatizer {
    public ArrayList<String> returnLemma(String text) {
        text=text.replaceAll("doesn't","doesnt");
        text=text.replaceAll("can't","cant");
        text=text.replaceAll("won't","wont");
        text=text.replaceAll("isn't","isnt");
        text=text.replaceAll("don't","dont");
        text=text.replaceAll("aren't","arent");
        text=text.replaceAll("wasn't","wasnt");
        text=text.replaceAll("weren't","werent");
        text=text.replaceAll("haven't","havent");
        text=text.replaceAll("wouldn't","wouldnt");
        text=text.replaceAll("couldn't","couldnt");



        ArrayList<String> lemmas = new ArrayList<>();
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props, false);

        // String text = /* the string you want */;
        Annotation document = pipeline.process(text);
        boolean negation=false;
        ArrayList <String> negatives=new ArrayList<>(Arrays.asList("wont","couldnt","seldom","werent","wouldnt","havent","cant","isnt","not","never","dont","didnt","doesnt","cannot","arent","wasnt"));
        for (CoreMap sentence : document.get(SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
                String pos = token.get(PartOfSpeechAnnotation.class);


                if (pos.startsWith("J") || pos.startsWith("N") || pos.startsWith("V") || pos.startsWith("R") ) {

                    if(negatives.contains(token.get(LemmaAnnotation.class)))
                    {
                        negation=true;
                    }
                    else {
                        if (negation == false) {
                            lemmas.add(token.get(LemmaAnnotation.class));
                        } else {
                            lemmas.add("not_" + token.get(LemmaAnnotation.class));
                          //  System.out.println("not_" + token.get(LemmaAnnotation.class));
                            negation = false;
                        }
                    }
                }





                //only noun, verb, adverb and adjective are sufficient of sentimental analysis


            }
        }
            return lemmas;


    }


    public static void main(String[] args) {
        Lemmatizer lemmatizer=new Lemmatizer();
        System.out.println(lemmatizer.returnLemma("hello this is awesome"));

    }
}