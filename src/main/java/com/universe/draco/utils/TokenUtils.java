package com.universe.draco.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @ClassName: TokenUtils
 * @Description: token生成帮助类
 * @Author: Liu Xiaonan
 * @data： 2019/7/20 18:06
 */
public class TokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 签名秘钥
     */
    private static final String SECRET = "cube007";

    /**
     * 功能描述: 生成token
     *
     * @param id: 一般传入userName
     * @author: Liu Xiaonan
     * @return: java.lang.String
     * @date: 2019/7/20 21:16
     */
    public static String createJwtToken(String id) {
        // 发行者
        String issuer = "LiuXiaonan";
        // 面向用户
        String subject = "draco";
        // 过期时间 200分钟
        long ttlMillis = 12000000;
        return createJwtToken(id, issuer, subject, ttlMillis);
    }

    /**
     * 功能描述: 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 签发时间 （有效时间，过期会报错）
     * @return token String
     * @author: Liu Xiaonan
     * @date: 2019/7/20 21:20
     */
    private static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        logger.info("==========正在生成用户id为：" + id + "的Token============");

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 设置JWT声明
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // 如果已经指定，添加到期日
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // 构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();

    }

    /**
     *
     * 功能描述: 验证和读取JWT的示例方法
     *
     * @param jwt:
     * @author: Liu Xiaonan
     * @return: io.jsonwebtoken.Claims
     * @date: 2019/8/12 10:43
     */
    public static Claims parseJwt(String jwt) {
        // 如果它不是签名的JWS（如预期的那样），则该行将抛出异常
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
    }

    public static void main(String[] args) {
        System.out.println(TokenUtils.createJwtToken("7eb0b5dd-9b3e-11e9-988a-507b9d7a8299"));
    }
}
