package ru.hse.edu.sdfomin.housingandcommunalservices.web;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Pair;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;

import static ru.hse.edu.sdfomin.housingandcommunalservices.web.ServerManager.SERVER_ADDRESS;

public class FullRegisterPersonTask extends AsyncTask<Pair<GoogleSignInAccount, Person>, Integer, Person> {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    @SafeVarargs
    @Override
    protected final Person doInBackground(Pair<GoogleSignInAccount, Person>... pairs) {
        // create person
        RequestBody body = RequestBody.create(gson.toJson(pairs[0].getB()), JSON);
        Request requestSendPerson = new Request.Builder()
                .url(SERVER_ADDRESS + "/api/person/")
                .post(body)
                .build();
        try (Response response = client.newCall(requestSendPerson).execute()) {
            Log.w("RESPONSE", Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get person
        Person person = null;

        Request requestGetPerson = new Request.Builder()
                .url(SERVER_ADDRESS + "/api/person/" + pairs[0].getA().getId())
                .build();

        try {
            try (Response response = client.newCall(requestGetPerson).execute()) {

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
