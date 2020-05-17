package ir.hamyiar.hamyshow.security.auth;

public enum ApplicationPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    COMMENT_WRITE("comment:write"),
    COMMENT_CHANGE("comment:change"),
    MOVIE_DOWNLOAD("movie:download"),
    MOVIE_UPLOAD("movie:upload"),
    MOVIE_SUGGEST("movie:suggest"),
    ADMIN_ADD("admin:add");

    private final String permission;

    ApplicationPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
