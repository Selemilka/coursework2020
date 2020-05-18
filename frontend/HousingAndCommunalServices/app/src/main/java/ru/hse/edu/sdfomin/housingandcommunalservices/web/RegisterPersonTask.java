package ru.hse.edu.sdfomin.housingandcommunalservices.web;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;

import static ru.hse.edu.sdfomin.housingandcommunalservices.web.ServerManager.SERVER_ADDRESS;

public class RegisterPersonTask extends AsyncTask<Person, Integer, String> {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected String doInBackground(Person... persons) {
        RequestBody body = RequestBody.create(gson.toJson(persons[0]), JSON);

        Request request = new Request.Builder()
                .url(SERVER_ADDRESS + "/api/person/")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ERROR CREATING PERSON";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.w("BAD", s);
    }
}
