//import Bouquet.Bouquet;
//import Flower.Flover;
//import Flower.FloverType;
//import json.JsonConverter;
//import json.JsonConverterData;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedHashSet;
//
//public class Main {
//    public static void main(String[] args) {
//        Flover s1 = Flover.builder()
//                .type(new FloverType("das"))
//                .length(10)
//                .pricePerFlover(12)
//                .levelOfFreshness(0.2)
//                .name("s1")
//                .build();
//        Flover s2 = Flover.builder()
//                .type(new FloverType("vrc"))
//                .length(10)
//                .pricePerFlover(12)
//                .levelOfFreshness(0.2)
//                .name("s2")
//                .build();
//        Flover s3 = Flover.builder()
//                .type(new FloverType("hba"))
//                .length(10)
//                .pricePerFlover(12)
//                .levelOfFreshness(0.2)
//                .name("s3")
//                .build();
//        JsonConverterData jsonConverterData = new JsonConverterData(
//                new ArrayList<>(Arrays.asList(s1,s2,s3)),
//                new Bouquet(new LinkedHashSet<>(Arrays.asList(s2,s1))),
//                new ArrayList<>(Arrays.asList(new FloverType("das"),new FloverType("das")))
//        );
//        JsonConverter.setPath("testTest");
//        JsonConverter.convertToJson(jsonConverterData);
//    }
////    public static void main(String[] args) {
////        Logger logger = LoggerFactory.getLogger(Main.class);
////        logger.error("error");
////    }
//}
