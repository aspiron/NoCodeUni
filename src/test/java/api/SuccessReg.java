package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessReg {
    @JsonProperty("full_name")
    private String full_name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("magic_link")
    private Object magic_link;
    @JsonProperty("created")
    private String created;
    @JsonProperty("updated")
    private String updated;

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public Object getMagic_link() {
        return magic_link;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }
}
