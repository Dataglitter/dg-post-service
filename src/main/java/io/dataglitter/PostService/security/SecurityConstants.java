package io.dataglitter.PostService.security;

/**
 * Created by reddys on 14/12/2017.
 */

public class SecurityConstants {
    public static final String SECRET = "BeWhatYoToBeDoWhatYouWantToDo";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/sign-up";
    public static final String HEALTH_URL = "/api/health";
    public static final String POST_GET_URLS = "/api/post/**";
    public static final String POST_PUT_LIKED = "/api/post/*/liked";
    public static final String POST_PUT_VIEWED = "/api/post/*/viewed";
    public static final String POST_PUT_SHARED = "/api/post/*/shared";
    public static final String USER_GET_URLS = "/api/application-user/**";
    public static final String USER_GET_ALL_BY_IDS = "/api/application-user/list";
    public static final String USER_GET_BY_EMAIL = "/api/application-user/check-email";
    public static final String USER_GET_CHECK_BY_EMAIL = "/api/application-user/by-email";
    public static final String CSR_TXT = "/.well-known/pki-validation/*";
    public static final String INDEX = "/index.html";
}
