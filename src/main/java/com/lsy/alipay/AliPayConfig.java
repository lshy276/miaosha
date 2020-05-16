package com.lsy.alipay;

/**
 * Created by 李帅豫 on 2020/5/10.
 */
public class AliPayConfig {
    // 作为身份标识的应用ID
    public static String app_id = "2016102400750620";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key  = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDnYSA6Dc+1//+oOXmFqcnFa3xVZTr2PJbSoSO9wm6HXIJ4xAZOJJEbHIrbD6Q8rdqfh4VRO6P4lQeaYQOjMMV+YIjLEDf97/PRL91VhGXAK2V+ZRpzCP+WYCFYOkfZ57YCK/Vu3BhsX91zOqVheRXpXpfqRz/oGNSskgKKLnvWDoF3Va9I8ilh5kOFG0GtGHdgG/+pCQm4/+TONueswhOcStmieBfioJS/AKW4HgMJ8Ur/jFBobn/6juL9qKkb1mFZa1qE6+HjvJrG7rZsYxRBlhrb6rg3dqC/DwzOhCdVN6qAQYxPFH7UIMxRWB1P34ZHYY1DqAYOsdZCd+jeQ9sRAgMBAAECggEAb94vvKHM2UvqiaUwMgXDPiYXuRn2hfMJYNDz/N0yn1OE6WqtOb7IQXltUTigyY2s9BCLzpitUJSNVoCtR4tfERkPSk6PI8GN+ofP1Xbr0xuoOJIdVZz2JfhYZtXWw24aO7aRNYZIcK1w3jNunk/y3iAUBvtn1Imc2crGW5TZ3sW9Td+LrVZTVKpqmnQU0ioRJNa26YDSTHGXPEqXEcIBsK5i+yuBwX9mEkwuadn742LXXhxvr5eVzVmsHA9myxoTQDrb2ZxaQ2jFlNiVUtAWce/srPv3XnpKbgS/cx0mWYvURCIS3kWm2RbR0xmrn/WI1rW6gdXNkO4idhlnFkZuOQKBgQD09E/ZRIVDBitXgOf/c2Ljs2joTdcstKq5hkFBbTIQho5gWqVNoT50Hbtxi6OX/PylwDOnD9kmmqxZayoVzNhTNsl1cWgSKgRxSNv1fuHwGObgfNhHL32eEskY6/Qfdtc7NxNoX4qxSsusUhycSHbNFljvmD2Bt1hoiUf72Gy3qwKBgQDx0BvAKe3rpntPxbWl3420ACS8gtV4Z04Db18nXlX9uxouEOmlnafOf5wp1P4YqcjhzVEmQF4boAnjSWWm68WYKMFviPxnW/mpLjM/Qp3Hu1YoN80zAtIkS+DBhRjAqx7M2B23FsA7ZHo//djNPFXLmBrAixSO60yrE1Y+j73MMwKBgDy/fjH2wUnO9N69lEuQ1Rx9OO4rp8ppf9GEzxgnuXvJsvOIZ2hqLa/kU8EkDU07K3j7wpOS5ZqiT03ZutUvORLiCnI6zsF6dCGYHRyz+9psgo13yLdWusOCCydIFQHcmiIQVZrT3vR7BSnWL0w+2F+iRCofSlmqG53JNfXqRYQDAoGBAMqhv39qI2tjGNYiMTOMf+wkaqRlZoj82EzcmPoefSSgEzIjDgewcxwBp1UOnNcB/8noCWZqbfHsTBzgNoSH8g6tqFrUid37Htz8UAlkw17Jx0rwZ82lQX8U7oR8Cd/6BVdWiqAQOooKir7w8yoYszRmg5Rxd4rD9n0tOQo33a4tAoGBAMgpWaJ5YaMDsh78GrdU7Ahf+mGPmIdsVYjpFLSz4qvj8y9c3gxeKvvcuf5Vs2rgS2EskMmRQ2vLLLFESkHRuaO9Csf3S+ntw1zxX5sd/2tTy/RY0wakc0IVJn24d03/4QRVnx65ENwxNWEQ6LOo4Qse76CMHtRuQwndE304ggXf";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkg3Q3w06nDg7ljCcoJ5IbLdmfzs4Gr47kbBCX87gCJcTr8SdqbDvHEsMYJlZB9y0ges53Xbiu54TFZVkvwGi70tewi/KanZZQri0V3aM7JcyQ/gDb1vf3qOyUTZQaS0W8S2fwIg/wpbFhO5mnz0UJTa99G6c8iAGbdCOnaMvUWFL3CTxDYmeTHRyndpBIC2ZZkPko4yY0TI2i8UF5oOusVrG7nUDlNLH/IXIFJ0x6ThB5BFxK9i/UJvHufV3grgpAYMWYxZxPH0wBBsTNEqn94fH9QY629bgDxTN8xyzRnEcWpZzl6kqLK2inGhwpZs+MvmMujiBP/GRicNDrcr6UQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}
