package com.node.detection.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.node.detection.entity.prop.KaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * '@EnableConfigurationProperties(KaptchaProperties.class)' 引入属性类
 * @author xinyu
 */
@Configuration
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaAutoConfiguration {


    private final KaptchaProperties kaptchaProperties;

    @Autowired
    public KaptchaAutoConfiguration(KaptchaProperties kaptchaProperties) {
        this.kaptchaProperties = kaptchaProperties;
    }

    @Bean(name = "kaptchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();

        properties.setProperty(Constants.KAPTCHA_BORDER, this.kaptchaProperties.getBorder().getHave());
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, kaptchaProperties.getBorder().getColor());

        properties.setProperty(Constants.KAPTCHA_PRODUCER_IMPL, kaptchaProperties.getProducer().getImpl());

        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL, kaptchaProperties.getTextProducer().getImpl());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, kaptchaProperties.getTextProducer().getFont().getColor());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, kaptchaProperties.getTextProducer().getFont().getSize());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, kaptchaProperties.getTextProducer().getFont().getNames());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, kaptchaProperties.getTextProducer().getChars().getLength());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, kaptchaProperties.getTextProducer().getChars().getSpace());

        properties.setProperty(Constants.KAPTCHA_WORDRENDERER_IMPL, kaptchaProperties.getWord().getImpl());

        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, kaptchaProperties.getImage().getWidth());
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, kaptchaProperties.getImage().getHeight());

        properties.setProperty(Constants.KAPTCHA_BACKGROUND_IMPL, kaptchaProperties.getBackground().getImpl());
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, kaptchaProperties.getBackground().getClear().getFrom());
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, kaptchaProperties.getBackground().getClear().getTo());

        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, kaptchaProperties.getNoise().getColor());
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, kaptchaProperties.getNoise().getImpl());
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, kaptchaProperties.getObscurificator().getImpl());
        properties.setProperty(Constants.KAPTCHA_SESSION_KEY, kaptchaProperties.getSession().getKey());
        properties.setProperty(Constants.KAPTCHA_SESSION_DATE, kaptchaProperties.getSession().getDate());

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
