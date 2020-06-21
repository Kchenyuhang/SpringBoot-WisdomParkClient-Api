//package com.niit.soft.client.api.util;
//
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//
///**
// * @ClassName CoreNLPHel
// * @Description TODO
// * @Author xiaobinggan
// * @Date 2020/6/20 10:59 上午
// * @Version 1.0
// **/
//
//public class CoreNLPHel {
//    private static CoreNLPHel instance = new CoreNLPHel();
//    private StanfordCoreNLP pipeline;
//
//    private CoreNLPHel() {
//        String props = "CoreNLP-chinese.properties";  //第三步骤的配置文件，放在main/resources目录下
//        pipeline = new StanfordCoreNLP(props);
//    }
//
//    ;
//
//    public static CoreNLPHel getInstance() {
//        return instance;
//    }
//
//    public StanfordCoreNLP getPipeline() {
//        return pipeline;
//    }
//}