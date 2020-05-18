package ru.hse.edu.sdfomin.housingandcommunalservices.web;

import android.os.AsyncTask;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;

import static ru.hse.edu.sdfomin.housingandcommunalservices.web.ServerManager.SERVER_ADDRESS;

public class GetPersonTask extends AsyncTask<GoogleSignInAccount, Integer, Person> {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected Person doInBackground(GoogleSignInAccount... googleSignInAccounts) {
        Person person = null;

        Request request = new Request.Builder()
                .url(SERVER_ADDRESS + "/api/person/" + googleSignInAccounts[0].getId())
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                person = gson.fromJson(Objects.requireNonNull(response.body()).string(), Person.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }
}
