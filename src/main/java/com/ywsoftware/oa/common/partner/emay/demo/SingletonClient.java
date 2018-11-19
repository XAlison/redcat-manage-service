package com.ywsoftware.oa.common.partner.emay.demo;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@SuppressWarnings("all")
public class SingletonClient {
    private static Client client = null;

    private SingletonClient() {
    }

    public synchronized static Client getClient(String softwareSerialNo, String key) {
        if (client == null) {
            try {
                client = new Client(softwareSerialNo, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public synchronized static Client getClient() {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("emay");
        if (client == null) {
            try {
                client = new Client(bundle.getString("emay.user"), bundle.getString("emay.key"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }


}
