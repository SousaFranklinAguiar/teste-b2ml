package com.franklin.testeb2ml.service.selicservice;

import com.franklin.testeb2ml.model.Selic;
import com.franklin.testeb2ml.util.Util;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SelicService {

    final static String webService = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=json&dataInicial=01/01/2010&dataFinal=31/12/2022";

    public static Selic getSelic(){

        try{
            URL url = new URL(webService);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);

            Gson gson = new Gson();

            Selic[] listaSelic = gson.fromJson(jsonEmString, Selic[].class);
            return listaSelic[listaSelic.length-1];

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
