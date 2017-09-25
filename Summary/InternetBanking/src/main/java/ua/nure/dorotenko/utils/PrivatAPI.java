package ua.nure.dorotenko.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PrivatAPI {

    private static final String EXCHANGE_RATES_URI = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final Logger LOG = Logger.getLogger(PrivatAPI.class);
    /**
     * Send request to PrivatBankAPI fro exchange rates
     * @return exchange rates from PrivatBankAPI as string
     * @throws IOException
     */
    public static String getExchangeRates() throws IOException {
        LOG.debug("Getting exchange rates started.");
        URL address = new URL(EXCHANGE_RATES_URI);
        LOG.trace("URL -> " + address);
        HttpURLConnection con = (HttpURLConnection) address.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer resp = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();
        JsonArray jsonArray = new JsonParser().parse(resp.toString()).getAsJsonArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < jsonArray.size(); i++){
            sb.append(jsonArray.get(i).getAsJsonObject().get("ccy"))
                    .append(": ");
            sb.append(jsonArray.get(i).getAsJsonObject().get("buy"))
                    .append(" | ").append(jsonArray.get(i).getAsJsonObject().get("sale")).append(" ");
        }
        LOG.debug("Getting exchange rates finished.");
        return sb.append(".").toString().replaceAll("\"","");
    }
}
