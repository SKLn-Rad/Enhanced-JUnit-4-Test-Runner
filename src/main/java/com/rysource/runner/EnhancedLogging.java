package com.rysource.runner;

public class EnhancedLogging {

    private EnhancedLogging() {
    }

    private String message;

    private boolean isDebug;

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void debug(String message) {


        boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;


        if (isDebug) {

            System.out.println("DEBUG: " + message);
        }
    }

    public static void testlog(String message) {

        EnhancedAssertion.addlogInfo(true, message.replace("\n", "<br />"), false, true);

    }



//    private static void logInfo(String message, boolean isDebug) {
//
//        EnhancedLogging enhancedlog = new EnhancedLogging();
//        enhancedlog.setMessage(message);
//        enhancedlog.setDebug(isDebug);
//        //String logMessage = message;
//
//        if (isDebug) {
//
//            System.out.println("DEBUG: " + message);
////            logMessage = "<b><font color=\"blue\"> DEBUG: </font></b>" + message;
////            EnhancedAssertion.addlogInfo(true, logMessage.replace("\n", "<br />"), false, true);
//
//        } else {
//            EnhancedAssertion.addlogInfo(true, message.replace("\n", "<br />"), false, true);
// }
//
//    }

}
