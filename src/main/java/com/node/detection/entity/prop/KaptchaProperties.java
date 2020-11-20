package com.node.detection.entity.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xinyu
 */
@Data
@ConfigurationProperties(prefix = "kaptcha")
public class KaptchaProperties {
    private Border border = new Border();
    private Producer producer = new Producer();
    private TextProducer textProducer = new TextProducer();
    private Word word = new Word();
    private Image image = new Image();
    private Background background = new Background();
    private Noise noise = new Noise();
    private Session session = new Session();
    private Obscurificator obscurificator = new Obscurificator();

    @Data
    public static class Border {
        private String have;
        private String color;
    }

    @Data
    public static class Producer {
        private String impl;
    }

    @Data
    public static class TextProducer {
        private String impl;
        private Font font = new Font();
        private Chars chars = new Chars();

        @Data
        public static class Chars {
            private String length;
            private String space;
        }

        @Data
        public static class Font {
            private String color;
            private String size;
            private String names;
        }

    }

    @Data
    public static class Word {
        private String impl;
    }

    @Data
    public static class Image {
        private String width;
        private String height;
    }

    @Data
    public static class Background {
        private String impl;
        private Clear clear = new Clear();

        @Data
        public static class Clear {
            private String from;
            private String to;
        }
    }

    @Data
    public static class Noise {
        private String impl;
        private String color;
    }

    @Data
    public static class Obscurificator {
        private String impl;
    }

    @Data
    public static class Session {
        private String key;
        private String date;
    }


}