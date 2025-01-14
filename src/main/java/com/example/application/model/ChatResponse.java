package com.example.application.model;

import com.example.application.OpenAI;

import java.util.List;

public class ChatResponse {
    private String id;

    private String object;

    private long created;

    private String model;

    private Usage usage;
    private String service_tier;
    private String system_fingerprint;

    private List<Choice> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public String getService_tier() {
        return service_tier;
    }

    public String getSystem_fingerprint() {
        return system_fingerprint;
    }

    public void setService_tier(String service_tier) {
        this.service_tier = service_tier;
    }

    public void setSystem_fingerprint(String system_fingerprint) {
        this.system_fingerprint = system_fingerprint;
    }
}
