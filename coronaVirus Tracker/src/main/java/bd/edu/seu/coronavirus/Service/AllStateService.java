package bd.edu.seu.coronavirus.Service;

import bd.edu.seu.coronavirus.model.AllState;
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
import java.util.ArrayList;
import java.util.List;

@Service

public class AllStateService {

    private static final String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<AllState> allStateList = new ArrayList<>();

    public List<AllState> getAllStateList() {
        return allStateList;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void getRawDATA() throws IOException, InterruptedException {
        List<AllState> newState = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL)).build();
        HttpResponse<String> httpResponse = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        StringReader stringReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
        for (CSVRecord record : records) {
            String State = record.get("Province/State");
            String Country = record.get("Country/Region");
            int CurrentCases = Integer.parseInt(record.get(record.size()-1));
            int previousCases = Integer.parseInt(record.get(record.size()-2));
            int difference = CurrentCases - previousCases;
            AllState allState = new AllState();
            allState.setState(State);
            allState.setCountry(Country);
            allState.setTotalCurrentEffected(CurrentCases);
            allState.setDifferentFromPreviousDay(difference);
            newState.add(allState);
        }
        this.allStateList = newState;

    }
}
