//package com.niit.soft.client.api.util;
//
///**
// * @ClassName Segmentation
// * @Description TODO
// * @Author xiaobinggan
// * @Date 2020/6/20 10:59 上午
// * @Version 1.0
// **/
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.util.CoreMap;
//
//import java.util.List;
//
//public class Segmentation {
//    private String segtext = "";
//
//    public Segmentation(String text) {
//        CoreNLPHel coreNLPHel = CoreNLPHel.getInstance();
//        StanfordCoreNLP pipeline = coreNLPHel.getPipeline();
//        Annotation annotation = new Annotation(text);
//        pipeline.annotate(annotation);
//        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
//        //ArrayList<String> array = new ArrayList<String>();
//        StringBuffer sb = new StringBuffer();
//        for (CoreMap sentence : sentences) {
//            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//                String word = token.get(CoreAnnotations.TextAnnotation.class);
//                sb.append(word);
//                sb.append(" ");
//            }
//        }
//        segtext = sb.toString().trim();
//        //segtext = array.toString();
//    }
//
//    public static void main(String[] args) {
//        System.out.println(new Segmentation("在寂寞的时候又会想起他").getSegtext());
//    }
//
//    public String getSegtext() {
//        return segtext;
//    }
//}