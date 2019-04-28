/**
 * Sample Java code for youtube.search.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/guides/code_samples#java
 */

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.TokenPagination;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class ApiExample {
    // You need to set this value for your code to compile.
    // For example: ... DEVELOPER_KEY = "YOUR ACTUAL KEY";
    //Ale
    //private static final String DEVELOPER_KEY = "YOUR API KEY";
    //Ale Secundaria
    private static final String DEVELOPER_KEY = "YOUR API KEY";

    private static final String APPLICATION_NAME = "API code samples";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();
        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        SearchListResponse response = request.setKey(DEVELOPER_KEY)
                .setMaxResults(50L)
                .setQ("anime")
                .setType("video")
                .execute();

        List listaTokens = new ArrayList();
        List listaVideos = new ArrayList();

        //Version 1.1
        String nextToken = response.getNextPageToken();

        //Version 2.1
        //String nextToken = "CLYHEAA";

        while (nextToken != null){

            //Version 1.2
            listaVideos.add(response.getItems());

            listaTokens.add(nextToken);
            response = request.setKey(DEVELOPER_KEY)
                    .setMaxResults(50L)
                    .setQ("anime")
                    .setType("video")
                    .setPageToken(nextToken)
                    .execute();
            nextToken = response.getNextPageToken();

            //Version 2.2
            //listaVideos.add(response.getItems());
        }

        System.out.println(listaVideos.size());




        FileHandler archivoVideos = new FileHandler("videos.txt");
        FileHandler archivoTokens = new FileHandler("tokens.txt");

        if(archivoVideos.createFile()){
            System.out.println("Funco");
        }else{
            System.out.println("No funco");
        }

        if(archivoTokens.createFile()){
            System.out.println("Funco");
        }else{
            System.out.println("No funco");
        }


        archivoVideos.writeToFile(listaVideos.toString());
        archivoTokens.writeToFile(listaTokens.toString());




    }
}
