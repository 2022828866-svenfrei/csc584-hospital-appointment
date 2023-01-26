/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author frei-
 */
public class ExpertiseBean implements Serializable {
    private long expertiseId;
    private String name;
    private String description;
    
    public ExpertiseBean() {}

    public ExpertiseBean(long expertiseId, String name, String description) {
        this.expertiseId = expertiseId;
        this.name = name;
        this.description = description;
    }

    public long getExpertiseId() {
        return expertiseId;
    }

    public void setExpertiseId(long expertiseId) {
        this.expertiseId = expertiseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
