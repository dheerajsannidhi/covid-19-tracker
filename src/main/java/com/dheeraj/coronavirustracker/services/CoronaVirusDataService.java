package com.dheeraj.coronavirustracker.services;

import com.dheeraj.coronavirustracker.model.CoronaModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


/**
 * Created by DHEERAJ on 23-04-2021.
 */
@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<CoronaModel> coronamodel = new ArrayList<>();

    public List<CoronaModel> getCoronamodel() {
        return coronamodel;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<CoronaModel> newcoronamodel = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());

        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            CoronaModel coronaModel = new CoronaModel();
            coronaModel.setState(record.get("Province/State"));
            coronaModel.setCountry(record.get("Country/Region"));
            int latestCases= Integer.parseInt(record.get(record.size()-1));
            int previousCases= Integer.parseInt(record.get(record.size()-2));
            coronaModel.setTotalCases(latestCases);
            coronaModel.setDiffCases(latestCases-previousCases);

            newcoronamodel.add(coronaModel);
            //System.out.println(newcoronamodel);


        }
        this.coronamodel =newcoronamodel;
    }
}
