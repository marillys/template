package utils;

public class LogUtils {
    public void logInfo(String value) {
        if (!value.contains("{}")) {
            //ScenarioUtils.addText(value);
            ExtentReportsUtils.addDetalhesRequest(value);
        }
    }

    public void logError(String value) {
        if (!value.contains("{}")) {
            //ScenarioUtils .addText(value);
        }
    }
}