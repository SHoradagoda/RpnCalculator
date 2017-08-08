package com.anz.rpncalc.messages;

import com.anz.rpncalc.entries.operators.OperatorMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * Container to store user help messages injected from properties file.
 *
 */
@PropertySource( value={"classpath:app.properties"} )
public class UserMessageLoader {

    public static final String HELP_COMMAND = "help";
    public static final String QUIT_COMMAND = "quit";

    private static final String HELP_COMMAND_KEY= "<HELP_COMMAND>";
    private static final String QUIT_COMMAND_KEY= "<QUIT_COMMAND>";
    private static final String OPERATORS_KEY = "<OPERATORS>";
    private static final String APP_VERSION_KEY = "<APP_VERSION>";
    private static final String APP_NAME_KEY = "<APP_NAME>";

    @Autowired
    private OperatorMap operatorMap;

    private String appVersion, appName;
    private String initMessage;
    private String helpMessage;

    public String getInitMessage() {
        return initMessage.replaceAll(APP_VERSION_KEY, appVersion)
                .replaceAll(APP_NAME_KEY, appName)
                .replaceAll(HELP_COMMAND_KEY,HELP_COMMAND )
                .replaceAll(QUIT_COMMAND_KEY,QUIT_COMMAND );
    }


    public String getHelpMessage() {
        return helpMessage.replace(OPERATORS_KEY, getOperators());

    }

    public String getOperators() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : operatorMap.allKeys()) {
            stringBuilder.append("\n")
                    .append(key)
                    .append(" : ")
                    .append(operatorMap.get(key).getDescription());
        }
        return stringBuilder.toString();
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setInitMessage(String initMessage) {
        this.initMessage = initMessage;
    }

    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }
}
