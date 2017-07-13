package io.corbs;

import cworks.json.Json;

import java.util.Collections;
import java.util.Map;

public class CfUtils {

    public static Map<String, Object> getVcapApplication() {
        return getInfo("VCAP_APPLICATION");
    }

    public static Map<String, Object> getVcapServices() {
        return getInfo("VCAP_SERVICES");
    }

    public static Map<String, Object> getVcapAppHost() {
        return getInfo("VCAP_APP_HOST");
    }

    public static Map<String, Object> getCfInstanceAddr() {
        return getInfo("CF_INSTANCE_ADDR");
    }

    public static Map<String, Object> getCfInstanceGuid() {
        return getInfo("CF_INSTANCE_GUID");
    }

    public static Map<String, Object> getCfInstanceIndex() {
        return getInfo("CF_INSTANCE_INDEX");
    }

    public static Map<String, Object> getCfInstanceIp() {
        return getInfo("CF_INSTANCE_IP");
    }

    public static Map<String, Object> getCfInstanceInternalIp() {
        return getInfo("CF_INSTANCE_INTERNAL_IP");
    }

    public static Map<String, Object> getCfInstancePort() {
        return getInfo("CF_INSTANCE_PORT");
    }

    public static Map<String, Object> getCfInstancePorts() {
        return getInfo("CF_INSTANCE_PORTS");
    }

    public static Map<String, Object> getCfHome() {
        return getInfo("HOME");
    }

    public static Map<String, Object> getCfMemoryLimit() {
        return getInfo("MEMORY_LIMIT");
    }

    public static Map<String, Object> getCfPwd() {
        return getInfo("PWD");
    }

    public static Map<String, Object> getCfTmpDir() {
        return getInfo("TMPDIR");
    }

    public static Map<String, Object> getCfUser() {
        return getInfo("USER");
    }

    public static Map<String, Object> getInfo(String envVar) {
        String envString = getEnvVar(envVar);
        if(envString != null) {
            return Json.asMap(envString);
        }

        return Collections.emptyMap();
    }

    public static String getEnvVar(String varName){
        String value;
        value = System.getenv(varName);

        if(value != null){
            return value;
        }

        value = System.getProperty(varName);

        if(value != null){
            return value;
        }

        return value;
    }
}

