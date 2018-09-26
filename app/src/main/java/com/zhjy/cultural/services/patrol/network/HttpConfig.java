package com.zhjy.cultural.services.patrol.network;

import android.content.Context;

import com.zhjy.cultural.services.patrol.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okio.Buffer;

public class HttpConfig {
//    public static String CER = "-----BEGIN CERTIFICATE-----\n" +
//            "MIIFCTCCA/GgAwIBAgISAy5a3cXBPq0lsY9GHmfjgsz/MA0GCSqGSIb3DQEBCwUA\n" +
//            "MEoxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1MZXQncyBFbmNyeXB0MSMwIQYDVQQD\n" +
//            "ExpMZXQncyBFbmNyeXB0IEF1dGhvcml0eSBYMzAeFw0xNzA5MTExNTQ0MDBaFw0x\n" +
//            "NzEyMTAxNTQ0MDBaMB4xHDAaBgNVBAMTE2FwaS5iaWxpYW5nd2FuZy5jb20wggEi\n" +
//            "MA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDCsoWdKD1DZlqk3YaO+94zV3Ur\n" +
//            "zMVv64UbuqBOimwpOJfpIRkIhsLsrjRpMxbG1XgTzhuuIxP+cc4khm5d/mByQqZA\n" +
//            "9l51zNiKlYjwnxJzyL89xiCdrSMl8m1hoL7UElfqZ7Es/xSj5uJ1yjTWgObKiQ9P\n" +
//            "OrfRuKx41przman2Pio1hKUhy5JypO3ZKzd8XKDXVNHxORQY8gto6ia2IOsIQyqV\n" +
//            "PsXYeWY5brHBrEh8UwEEL0mDgw9fx4lF8jBSIzArKeVJ+TbyY/FHmUVozq8hqGWr\n" +
//            "DirZ2RzzNLkmqqU8YRxeq+q2xvagQrXvq/NsA7zO8ML+OLSZkQ8nCtSlGhJfAgMB\n" +
//            "AAGjggITMIICDzAOBgNVHQ8BAf8EBAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwEG\n" +
//            "CCsGAQUFBwMCMAwGA1UdEwEB/wQCMAAwHQYDVR0OBBYEFLNJIgTFzmEjYwyyIA44\n" +
//            "8d0FqMEeMB8GA1UdIwQYMBaAFKhKamMEfd265tE5t6ZFZe/zqOyhMG8GCCsGAQUF\n" +
//            "BwEBBGMwYTAuBggrBgEFBQcwAYYiaHR0cDovL29jc3AuaW50LXgzLmxldHNlbmNy\n" +
//            "eXB0Lm9yZzAvBggrBgEFBQcwAoYjaHR0cDovL2NlcnQuaW50LXgzLmxldHNlbmNy\n" +
//            "eXB0Lm9yZy8wHgYDVR0RBBcwFYITYXBpLmJpbGlhbmd3YW5nLmNvbTCB/gYDVR0g\n" +
//            "BIH2MIHzMAgGBmeBDAECATCB5gYLKwYBBAGC3xMBAQEwgdYwJgYIKwYBBQUHAgEW\n" +
//            "Gmh0dHA6Ly9jcHMubGV0c2VuY3J5cHQub3JnMIGrBggrBgEFBQcCAjCBngyBm1Ro\n" +
//            "aXMgQ2VydGlmaWNhdGUgbWF5IG9ubHkgYmUgcmVsaWVkIHVwb24gYnkgUmVseWlu\n" +
//            "ZyBQYXJ0aWVzIGFuZCBvbmx5IGluIGFjY29yZGFuY2Ugd2l0aCB0aGUgQ2VydGlm\n" +
//            "aWNhdGUgUG9saWN5IGZvdW5kIGF0IGh0dHBzOi8vbGV0c2VuY3J5cHQub3JnL3Jl\n" +
//            "cG9zaXRvcnkvMA0GCSqGSIb3DQEBCwUAA4IBAQB9Jrey+U78FCaQu7+xyYE9JbpM\n" +
//            "6Vx+dxB/VKHOXWQX/8icubDjLQd62JMJWPRZ9VoRiCeWIAPvqMLieRKsoE7sZ6Mu\n" +
//            "cRS7EOAcGu68QRJwOsqfT4zI8y9glJ17zvQDhy2ZRdSkOqzL/+fP54sNHeIzleO/\n" +
//            "QgZCr/i4PboUyJNOU4PFu7iw4Qmx91TpygEyJ4zmvR86KFX69ZgpN6q4eBty0G9+\n" +
//            "1eKqzGD/A2QftGHNwT83oINoyHa2Uta3tFKn/kX3wCWilT+iSsWT2dvVgnvqP0vZ\n" +
//            "BGDSOOUNesaM5GH/k5gRz6zBhBYTKxIsf27Q+ke1YssoYjuLVNlSQf5C8H0o\n" +
//            "-----END CERTIFICATE-----";


    public static String CER = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFlTCCBH2gAwIBAgIQDqSSflYnSSkzy8VdYqb3/jANBgkqhkiG9w0BAQsFADBy\n" +
            "MQswCQYDVQQGEwJDTjElMCMGA1UEChMcVHJ1c3RBc2lhIFRlY2hub2xvZ2llcywg\n" +
            "SW5jLjEdMBsGA1UECxMURG9tYWluIFZhbGlkYXRlZCBTU0wxHTAbBgNVBAMTFFRy\n" +
            "dXN0QXNpYSBUTFMgUlNBIENBMB4XDTE4MDIyNDAwMDAwMFoXDTE5MDIyNDEyMDAw\n" +
            "MFowHjEcMBoGA1UEAxMTYXBpLmJpbGlhbmd3YW5nLmNvbTCCASIwDQYJKoZIhvcN\n" +
            "AQEBBQADggEPADCCAQoCggEBAK578J2BLbZmvTjPa1rRwYh6qe3ATlKUJ5TQ4jK+\n" +
            "om65IaM6bYDx94CJT3xKw9WlEuE5fmx98uUicNn0h0XrLBFPRJsxXX2xmBgJZxHM\n" +
            "OqJCqt4rk7dBgf16/eBF4MV1m/r/LPLIvXQKP+NPyHmQ8Dohs7701phtMEb7lYSv\n" +
            "fd2pXYEuqfuJkQU80sTOu7yNOZV+neLz0E3/du5KFqJgNYfeUGssSyc/oXBvSDuY\n" +
            "N2N0uYPKobCdGn74ptOEs/HoYa4TDIUr5eaQTQzk7wVsJGcn+7RG55r5Oenp3Iu1\n" +
            "Tv6r7ipC5iZKmPrZYDEArARZmqP7AFEfoaqVWHHgFySJNKECAwEAAaOCAnkwggJ1\n" +
            "MB8GA1UdIwQYMBaAFH/TmfOgRw4xAFZWIo63zJ7dygGKMB0GA1UdDgQWBBRWZXFw\n" +
            "4pFuARlQpvXh2r9iTF8GbTAeBgNVHREEFzAVghNhcGkuYmlsaWFuZ3dhbmcuY29t\n" +
            "MA4GA1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIw\n" +
            "TAYDVR0gBEUwQzA3BglghkgBhv1sAQIwKjAoBggrBgEFBQcCARYcaHR0cHM6Ly93\n" +
            "d3cuZGlnaWNlcnQuY29tL0NQUzAIBgZngQwBAgEwgYEGCCsGAQUFBwEBBHUwczAl\n" +
            "BggrBgEFBQcwAYYZaHR0cDovL29jc3AyLmRpZ2ljZXJ0LmNvbTBKBggrBgEFBQcw\n" +
            "AoY+aHR0cDovL2NhY2VydHMuZGlnaXRhbGNlcnR2YWxpZGF0aW9uLmNvbS9UcnVz\n" +
            "dEFzaWFUTFNSU0FDQS5jcnQwCQYDVR0TBAIwADCCAQUGCisGAQQB1nkCBAIEgfYE\n" +
            "gfMA8QB3ALvZ37wfinG1k5Qjl6qSe0c4V5UKq1LoGpCWZDaOHtGFAAABYcYSJzwA\n" +
            "AAQDAEgwRgIhANyQcX2RWM4KcPVsiMS45glqSTJ6Zk3rxfUyGf0JHdnjAiEA85Zx\n" +
            "pbyyZBEBMurT9i8VWgdoe64TKMxEnYu91t4+wZQAdgCHdb/nWXz4jEOZX73zbv9W\n" +
            "jUdWNv9KtWDBtOr/XqCDDwAAAWHGEibSAAAEAwBHMEUCIQDePY7Qs52dMQOn5LZE\n" +
            "1ssDrmISHfJTK3oDIwycbd+tawIgA2/J/WdcNAgiYCNiuTx60zXx2eLNCCzwZg9F\n" +
            "iKyCfskwDQYJKoZIhvcNAQELBQADggEBAGOa2XSk09TgtzCoffAE3FMJfEGcxdaq\n" +
            "K5rCMnS3Z9lkyy54o7UJePIaJZI12yLamXJxf6TZ0Dnlaq7b47nRQhttaRAGUYIE\n" +
            "NRY+tag+DavHIBrXBAm7yCDncsHC7RMHUlHdnN5VLEn522Yj+inMEfjIdR+88iS1\n" +
            "sJsvwZyEtl+nQHp3sEjnNV7oQa2FEjRs+MpVjxhOv5H/A1Dv1otJNiQFjN74rhU8\n" +
            "TP4fz2ky+x4iN52xO4ioDIdcVJvhYbeSKIm2kpELV5JE4j+iTSH+AntYgSO3UvPJ\n" +
            "xXf8IoWG0C8Z+9JxMVTqWZByMNuavjnodUGePKvzg/wJvTNn2AURSEM=\n" +
            "-----END CERTIFICATE-----";

    public static void init(Context context) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //设置缓存目录, 优先存储在sd卡
        File cacheFile = new File(context.getCacheDir(), "network");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//
//
//        OkHttpClient client;
//        if (BuildConfig.BUILD_TYPE.equals("release")) {
//            client = getTrusClient(new Buffer()
//                    .writeUtf8(CER)
//                    .inputStream());
//        } else {
//            client = builder
//                    //设置一下整体的超时
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .writeTimeout(30, TimeUnit.SECONDS)
////                .cache(cache)
//                    .retryOnConnectionFailure(false)
//                    .addInterceptor(new HeadRequestInterceptor())
//                .addNetworkInterceptor(new StethoInterceptor())
//                    //日志打印
//                    .addInterceptor(new LogginInterceptor())
//                    .build();
//        }

        GRetrofit.init();
    }

    public static OkHttpClient getNormalClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder
                //设置一下整体的超时
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
//                .cache(cache)
                .retryOnConnectionFailure(true)
                //日志打印
                .addInterceptor(new LogginInterceptor())
                .build();

        return client;
    }

    /**
     * 对外提供的获取支持自签名的okhttp客户端
     *
     * @param certificate 自签名证书的输入流
     * @return 支持自签名的客户端
     */
    public static OkHttpClient getTrusClient(InputStream certificate) {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        try {
            trustManager = trustManagerForCertificates(certificate);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //使用构建出的trustManger初始化SSLContext对象
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            //获得sslSocketFactory对象
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(500, TimeUnit.SECONDS)
                .readTimeout(500, TimeUnit.SECONDS)
                .writeTimeout(500, TimeUnit.SECONDS)
//                .cache(cache)
//                .retryOnConnectionFailure(false)
//                .addInterceptor(new HeadRequestInterceptor())
                .addInterceptor(new LogginInterceptor())
                .sslSocketFactory(sslSocketFactory, trustManager)
                .build();
    }

    /**
     * 获去信任自签证书的trustManager
     *
     * @param in 自签证书输入流
     * @return 信任自签证书的trustManager
     * @throws GeneralSecurityException
     */
    private static X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        //通过证书工厂得到自签证书对象集合
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        //为证书设置一个keyStore
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        //将证书放入keystore中
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }
        // Use it to build an X509 trust manager.
        //使用包含自签证书信息的keyStore去构建一个X509TrustManager
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default_iv trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(null, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}

