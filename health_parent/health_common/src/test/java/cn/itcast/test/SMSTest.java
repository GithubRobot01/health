package cn.itcast.test;

import cn.itcast.utils.SMSUtils;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;

public class SMSTest {
    @Test
    public void test() throws ClientException {
        SMSUtils.sendShortMessage("SMS_175574720","15839926035","nishiSB");
    }
}
