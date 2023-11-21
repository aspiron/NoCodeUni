package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {
    @JsonProperty("full_name")
    public String full_name;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
    @JsonProperty("generate_magic_link")
    public boolean generate_magic_link;

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isGenerate_magic_link() {
        return generate_magic_link;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGenerate_magic_link(boolean generate_magic_link) {
        this.generate_magic_link = generate_magic_link;
    }
}
