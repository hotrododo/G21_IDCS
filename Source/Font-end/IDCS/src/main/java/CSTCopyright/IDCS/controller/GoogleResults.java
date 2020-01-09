/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSTCopyright.IDCS.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.SitesListResponse;
import com.google.api.services.webmasters.model.WmxSite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mac
 */
public class GoogleResults {

    private static String CLIENT_ID = "128593348135-fsrmg47no6o5pkhma32m8ku1ifitr4oe.apps.googleusercontent.com";
    private static String CLIENT_SECRET = "KjUMqQxRddh6G8wrL3EE_jQZ";

    private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    private static String OAUTH_SCOPE = "https://www.googleapis.com/auth/webmasters.readonly";

    public static void doSearch(String key) throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(OAUTH_SCOPE))
                .setAccessType("online")
                .setApprovalPrompt("auto").build();

        
        String code = key;

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

        // Create a new authorized API client
        Webmasters service = new Webmasters.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("WebmastersCommandLine")
                .build();

        List<String> verifiedSites = new ArrayList<String>();
        Webmasters.Sites.List request = service.sites().list();

        // Get all sites that are verified
        try {
            SitesListResponse siteList = request.execute();
            for (WmxSite currentSite : siteList.getSiteEntry()) {
                String permissionLevel = currentSite.getPermissionLevel();
                if (permissionLevel.equals("siteOwner")) {
                    verifiedSites.add(currentSite.getSiteUrl());
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

        // Print all verified sites
        for (String currentSite : verifiedSites) {
            System.out.println(currentSite);
        }
    }
}
