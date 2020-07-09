package com.shibofu.common.token;

/**
 * token缓存
 *
 * @author potter.fu
 * @date 2018-12-13 18:04
 */
public class TokenCache {
    /**
     * token单例模式对象
     */
    private static TokenCache tokenCache;

    /**
     * 线程共享token
     */
    private static ThreadLocal<Token> threadTokens = new ThreadLocal<>();

    /**
     * 获取单例token
     *
     * @return com.shibofu.common.token.TokenCache
     * @author potter.fu
     * @date 2018-12-13 18:07
     */
    public static synchronized TokenCache getInstance() {
        if (tokenCache == null) {
            tokenCache = new TokenCache();
        }
        return tokenCache;
    }

    /**
     * token缓存set token
     *
     * @param token com.shibofu.common.token.Token
     * @author potter.fu
     * @date 2018-12-13 18:07
     */
    public void setToken(Token token) {
        threadTokens.set(token);
    }

    /**
     * 清除token缓存
     *
     * @author potter.fu
     * @date 2018-12-13 18:09
     */
    public void remove() {
        threadTokens.remove();
    }

    /**
     * 获取缓存token
     *
     * @return com.shibofu.common.token.Token
     * @author potter.fu
     * @date 2018-12-13 18:10
     */
    public Token getToken() {
        return threadTokens.get();
    }
}
