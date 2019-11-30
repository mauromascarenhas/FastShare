package br.edu.ufabc.fastsharecms.model;

/**
 *
 * @author Mauro
 */
public class User {
    private Long id;
    private String name;
    private String email;
    private String psalt;
    private String phash;
    private String username;
    private String approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsalt() {
        return psalt;
    }

    public void setPsalt(String psalt) {
        this.psalt = psalt;
    }

    public String getPhash() {
        return phash;
    }

    public void setPhash(String phash) {
        this.phash = phash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
