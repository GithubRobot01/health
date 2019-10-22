package cn.itcast.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class QiNiuTest {

    //上传图片
    @Test
    public void test1(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "b56C0XzkhHRnEmxSUk__2Ztm1bjL0kwas_fTgzsP";
        String secretKey = "7W2YBjBnJYyHxS7b4gz6iIpfJiKNOrlklcjAv8Hn";
        String bucket = "wqs-space";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\zy.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException e) {
            try {
                Response r = e.response;
                System.err.println(r.toString());

                System.err.println(r.bodyString());
            } catch (QiniuException ex) {
                ex.printStackTrace();
            }
        }
    }

    //删除上传的图片
    @Test
    public void test2(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        String accessKey = "b56C0XzkhHRnEmxSUk__2Ztm1bjL0kwas_fTgzsP";
        String secretKey = "7W2YBjBnJYyHxS7b4gz6iIpfJiKNOrlklcjAv8Hn";
        String bucket = "wqs-space";
        String key = "FrgTLsah2N_LGWOyS8f9SWaPthct";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
