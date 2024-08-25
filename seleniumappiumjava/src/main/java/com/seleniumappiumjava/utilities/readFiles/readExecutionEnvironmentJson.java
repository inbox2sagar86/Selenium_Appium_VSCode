package com.seleniumappiumjava.utilities.readFiles;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seleniumappiumjava.utilities.setExecutionEnvironment;

public class readExecutionEnvironmentJson implements setExecutionEnvironment {
    JsonNode node;
    String env = setExecutionEnvironment.executionEnvironment;
    String browserName =  setExecutionEnvironment.browser;
    // TODO : Create constructor
    public readExecutionEnvironmentJson() {
        node = load_json_execution_environment();
    }

    public JsonNode load_json_execution_environment() {
        String jsonFilePath = setExecutionEnvironment.jsonFilePath;
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.readTree(new File(jsonFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public HashMap<String, String> readlocalBrowserParams() { 
        HashMap<String, String> map = new HashMap<>();
        JsonNode localBrowserNode = node.get(env);
        map.put(localBrowserNode.get(browserName).asText(),
                localBrowserNode.get(browserName).get("driverPath").asText());
        return map;
    }

}
