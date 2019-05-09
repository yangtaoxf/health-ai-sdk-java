package api.evaluation;

import com.jiankangyouyi.health.ai.api.DefaultHealthAiClient;
import com.jiankangyouyi.health.ai.api.HealthAiClient;
import com.jiankangyouyi.health.ai.api.bean.evaluation.EvaluationUserInfoBean;
import com.jiankangyouyi.health.ai.api.bean.evaluation.EvaluationUserOptionsBean;
import com.jiankangyouyi.health.ai.api.request.evaluation.EvaluationBriefLoadRequest;
import com.jiankangyouyi.health.ai.api.request.evaluation.EvaluationContentLoadRequest;
import com.jiankangyouyi.health.ai.api.request.evaluation.EvaluationDataSaveRequest;
import com.jiankangyouyi.health.ai.api.request.evaluation.EvaluationResultLoadRequest;
import com.jiankangyouyi.health.ai.api.response.evaluation.EvaluationBriefLoadResponse;
import com.jiankangyouyi.health.ai.api.response.evaluation.EvaluationContentLoadResponse;
import com.jiankangyouyi.health.ai.api.response.evaluation.EvaluationDataSaveResponse;
import com.jiankangyouyi.health.ai.api.response.evaluation.EvaluationResultLoadResponse;
import com.jiankangyouyi.health.ai.api.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ApiTest
 * @Description 评测 测试类
 * @Author wanggang
 * @Date 2019/5/9 15:39
 **/
public class ApiTest {

    // 测试

    private static String APPID = "5b33941b84274a0aa482e105";
    private static String PRIVATE_KEY =
            "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDvGsh7" +
                    "/ollxwhrtRU3m746ERkgXW2gNlol+IcbvI" +
                    "/7APL+2ndMZvxpwliR59X7vMhPM1t33bkWDQLT1VIzmdkz" +
                    "/2ZTw7UN2EGgbTEflu3Bzt6UqQc0mOxJtsm6PMvfzrIhdr" +
                    "8rYDDR749QDhhKsEg2wzbJNBEhp7JCJRzTTqNpIvaTbaEc" +
                    "k8FSm8H6QYOmAcupExrMQA7pEUVBgduVT3oMmcT5RnHh8h" +
                    "D8BjjMV9V/xq8tVOF7F++tvBtdoIW9gT+joFKCWUOertiQ" +
                    "WENaub+lvKTeRaGeOlRjmZWp55rcx3hp56usGtSM3O8un8" +
                    "aKMbOJ448gka+XzQ/kOXUmPGlLAgMBAAECggEBAMYhKXQl" +
                    "zXC65q1pljVIJu8y7+Ezyf1yTG0ebcZ8D8rsVG7+VNDIne" +
                    "x9EKuGsngxTMLkSa0f25j8GxWxtLXxjFxcDEzo5QSd6HOp" +
                    "R4VnqYhphWMmVUQuW84RW0Op0T6Zpl24alzlDk9H9TdfFp" +
                    "39STmntaQwMunybfIFksc+aIZaylP2sPkvjh8D9LyuSKKp" +
                    "mO5RMO8jM2EEo0AenonV7eXEvVQls/zht8ZnPQzGLBwCS+" +
                    "LvmggYjTbkDEELaVZClxjSgw1Ksw8cY3M05JZAZVfK8sZk" +
                    "xAAqzcrXNLZ0YC/wo/aZNw7LGPD78FHe/AFKTZ9rFtUWtm" +
                    "F9egwLN78aQ6ECgYEA/biIBQzsbRGWB6s3dkRjCYeWptc8" +
                    "aiNg0pzn+vmLbV9fmQII7bLS+lUjzZthYsjHBEEYilcrOY" +
                    "fx9ODT/1mzux+8e/kjxCvzOxJWktE2CfaRn2LzL1fXei6h" +
                    "k4It9kXxUCbxMeH0gTWhp6P8zmJcHh9LIyMZ2O8kivXdGj" +
                    "bRQFMCgYEA8UCjwni8esFnYn4qUXwyXap01EXGOhLqFIwi" +
                    "yds+InnLT723AXFFIxPW91lDCZdWXloL83flyP82qXAjfM" +
                    "//4Dvtb7bq2Ee77uCJn9DaGdQC8xJj1HHkkFInQSexFrIK" +
                    "wcEAPEMZ/EgdPrVW7Fmypf4CVd79RrqqoiHNv1eu9CkCgY" +
                    "EA+ZIXcehZc5vV2StI/fOc+5W80dZsHfc42VLwOYZX6Ljk" +
                    "3ywKPk4li4pOEoQh0jqu0Pd7PTErWobnsLmqrsW8rnc0Pzo" +
                    "9PU0CD3wlXnueTUSVz5NUpKAiW5Qc6W0m6tW33eDAZhHeIW" +
                    "7w2WbMWPKVrCeaEDEJzsLFTk80MwHy6PMCgYBO9CCj3G+Sn" +
                    "QkAqtmeoU66aLajYdJl3xrMbrBT73R+Q+3NwwDJcBB4Ul3f" +
                    "xU6zLNwPNNkEbRC+nwd8nD6A8nGDKRmjnfFtpsxSeRmRPAC" +
                    "kw0XT1wjk1gE/WiOOpzbFfq8WidtR3YP7lH49r4sq/pAA0w" +
                    "hgogGxzBVbykRTgpa3QQKBgQCVWLYdXllqLUQhKZZa2ehSh" +
                    "roO14fdCANc0eE5L6wl84gCzGX5ePPvk/WKZtaImTZFGNJZ" +
                    "+XwTGEf9cJ0oMTvZ5kdb3DOSWOIueq4uGohsZUeXBMVjeU6" +
                    "uRqBTn46i7gOH3/FS4U6Vcpr4lY/YVsBnJiQ/oST2jWwghH" +
                    "dvd4SMYw==";
    private static String serverUrl = "https://api.hbox.jiankangyouyi.com/ego-gw";


//     生产
//     private static String APPID = "5b07af2f1d41c863dcd7388d";
//     private static String PRIVATE_KEY =
//     "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCp9lo5wFz+35hb/YhjnD8Ua3BS7DU3sawZS2AsnjSMOK5YZOeg9difG7TRD0mBX0A3mnQ3QpDCQB58LcpKc+WzHO/VAeSMlmnFEZuDBfSn+Ctv7QpazWvClOSAiLf0pw4EWxHKkp8X9B7Qi+B+MW3u4XScTNeakJQjRpG1EyCAMZIJIUTtcSBHut6p6lZ40o87dX+x6G06C7X65UbiC6UGagKPs4/vY5ur5KFFyTpK3VaH4w2sySU9HIImiS33G5hSh+xrkM46YtVv96frWcqeHvEExuVfwoBUM8Yu+/jttpprc/7wWR/c/oSTNownHG1hewTz+nJ+9rVfH0nIx2FvAgMBAAECggEAY+WgcoFWJ5hUfP0vxRx5Fw9vHc4oGWwcs2FSTedPC3vlPZ9RZZIamKo4RQivM5NAs23dEP3XB9WYFXT0iX5FbFl9vb03EEr4MBSBSymc+30qSscQZv6GcAADrmitducZngXJFz/GstWRpxIQCRwUeWualqOzte10pKc9zShI2g9S+psmHeM4pvftmPl25MP01NwtNfW8/5aQi6uWvxrXyzAgUoGEJI3vPIhH0wcVDL69Xgyyb4m6ivW+DaRvWGWiWvXyhP5CrQBnPHRrhSXwZ+6PcGAN9Q5i5R/ui9wo+MGHdwa32ebjIKUxkNyNfBZjiM/PSeycY3o17GNdKrCEgQKBgQDfJlFcPMdaMAwQbvhDU4bcpL6xxhmkmxGqBY6IoqvGsZWNTrq89Uu1+hcZb4NBMwQ/jBHahhc2wFt7r0h4bQKju8qnvHGswQMtlaTq5CVU2Pb3Yamz9G/PAJeWN/bZoZZkA2tF1dMFkeLQIowIfkfbgizBOetmM/8U9yVdTs8PVwKBgQDC+5m6DjqBLFxrVVKglQi/4AZhw85x7E+yup1i43AXf8oeyVVlrZJsI1Fo0Yx44bLZOYfTbAzwotHfXY51p05sXtvNke15jOsw/evA87xsvvFFMd8NO0jAMKkVJLnOL0rngKmdSwVMYUbcpb1LFdm6w17nvGzmOXwOhlOAB0AnqQKBgEUhas0nax7uiZ5Bspmw87PBOe57D5CmFafVht2ff5XTiCA5xrIpT+Y1bxiLKl91fZhuPU20gtaMawr7N3lPVCUDVXn3cEPzm5dwp6G5QjNx77idweQlEspPRabc+ZVccOnVAVOmNXShSGHwge9Tp9FCF7lYxytnalvFYQbyFkWLAoGAJ1cqai7KwRtZPlLFzB6l2Ej0IK+oLLY4UmBytuwaxrMC7flDYLOHsofhuhtlk1I+irOf4xmO9tJzM/UldDmgih8NjEmgN4EmRwlEkvbakrpn5cUtMvc+M4Dd7KUvVBmYrGL6VgE3/XQ3HvfV3Jt5BcS7llgfRMhebnPNzywnVpkCgYEAgjZSHwzyD36qK5mq/DzA7EmtSwYckjvZ9F/1E6UVBbJ7kI6/PMW7uMV+rBS9JjkwCK6j7rlFeXnMd+qvOnX/qEYyT4sTc+JctOWr5MGtRevc6xVxOsEq3HRqRdb3ibnxWHVvBmY7FZuqXCEKa6q8MhByiAFuj93cLF1c/cltqU4=";
//     private static String serverUrl = "https://api.jiankangyouyi.com/ego-gw";
//
//    private static String DATA_PATH = "/Users/yangsongbo/workspace2/health-ai-sdk-java/src/test/java/api/data/";


    /**
     * 获取评测简介
     *
     * @throws IOException
     */
    @Test
    public void loadEvaluationBrief() throws IOException {

        HealthAiClient client = new DefaultHealthAiClient(APPID,
                PRIVATE_KEY,
                DefaultHealthAiClient.Version.VERSION_2_0,
                serverUrl);

        EvaluationBriefLoadRequest request = new EvaluationBriefLoadRequest();
        request.setEvaluationCode("NCZCP");
        //request.setUiStyleType("1");

        EvaluationBriefLoadResponse response = client.execute(request);
        System.out.println(JsonUtil.formatJson(JsonUtil.toJson(response)));
    }

    /**
     * 获取评测内容
     *
     * @throws IOException
     */
    @Test
    public void loadEvaluationContent() throws IOException {

        HealthAiClient client = new DefaultHealthAiClient(APPID, PRIVATE_KEY,
                DefaultHealthAiClient.Version.VERSION_2_0,
                serverUrl);

        EvaluationUserInfoBean userInfo = new EvaluationUserInfoBean();
        userInfo.setBirthday("1988-11-26");
        userInfo.setGender("1");
        userInfo.setHeight("168");
        userInfo.setUserName("波波");
        userInfo.setWeight("70");

        EvaluationContentLoadRequest request = new EvaluationContentLoadRequest();
        request.setEvaluationCode("NCZCP");
        request.setUserInfo(userInfo);

        EvaluationContentLoadResponse response = client.execute(request);
        System.out.println(JsonUtil.formatJson(JsonUtil.toJson(response)));
        System.out.println(JsonUtil.toJson(response));
    }

    /**
     * 保存评测数据
     *
     * @throws IOException
     */
    @Test
    public void saveEvaluationData() throws IOException {

        HealthAiClient client = new DefaultHealthAiClient(APPID, PRIVATE_KEY,
                DefaultHealthAiClient.Version.VERSION_2_0,
                serverUrl);

        EvaluationUserInfoBean userInfo = new EvaluationUserInfoBean();
        userInfo.setBirthday("1988-11-26");
        userInfo.setGender("1");
        userInfo.setHeight("168");
        userInfo.setUserName("波波");
        userInfo.setWeight("70");

        List<EvaluationUserOptionsBean> userOptions = new ArrayList<>();
        userOptions.add(new EvaluationUserOptionsBean("1", Arrays.asList("1")));
        userOptions.add(new EvaluationUserOptionsBean("2", Arrays.asList("1", "3")));
        userOptions.add(new EvaluationUserOptionsBean("3", Arrays.asList("1")));
        userOptions.add(new EvaluationUserOptionsBean("4", Arrays.asList("3")));
        userOptions.add(new EvaluationUserOptionsBean("5", Arrays.asList("3")));
        userOptions.add(new EvaluationUserOptionsBean("6", Arrays.asList("3")));
        userOptions.add(new EvaluationUserOptionsBean("7", Arrays.asList("1")));
        userOptions.add(new EvaluationUserOptionsBean("8", Arrays.asList("2")));
        userOptions.add(new EvaluationUserOptionsBean("9", Arrays.asList("4")));
        userOptions.add(new EvaluationUserOptionsBean("10", Arrays.asList("1")));

        EvaluationDataSaveRequest request = new EvaluationDataSaveRequest();
        request.setQuesBankId("5b7e2546ae178f4a45f1fcb7");
        request.setUserInfo(userInfo);
        request.setEvaluationCode("NCZCP");
        request.setUserOptions(userOptions);

        EvaluationDataSaveResponse response = client.execute(request);
        System.out.println(JsonUtil.formatJson(JsonUtil.toJson(response)));
    }

    /**
     * 获取评测数据
     *
     * @throws IOException
     */
    @Test
    public void loadEvaluationResult() throws IOException {

        HealthAiClient client = new DefaultHealthAiClient(APPID, PRIVATE_KEY,
                DefaultHealthAiClient.Version.VERSION_2_0,
                serverUrl);

        EvaluationResultLoadRequest request = new EvaluationResultLoadRequest();
        request.setEvaluationId("5cd3f77a14cced7577f4b539");

        EvaluationResultLoadResponse response = client.execute(request);
        System.out.println(JsonUtil.formatJson(JsonUtil.toJson(response, true)));
        System.out.println(JsonUtil.toJson(response));
    }

}
