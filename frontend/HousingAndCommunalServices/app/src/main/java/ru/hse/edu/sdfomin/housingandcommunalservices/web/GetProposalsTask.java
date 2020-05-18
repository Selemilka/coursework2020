package ru.hse.edu.sdfomin.housingandcommunalservices.web;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Proposal;

import static ru.hse.edu.sdfomin.housingandcommunalservices.web.ServerManager.SERVER_ADDRESS;

public class GetProposalsTask extends AsyncTask<GoogleSignInAccount, Integer, List<Proposal>> {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    protected List<Proposal> doInBackground(GoogleSignInAccount... googleSignInAccounts) {
        List<Proposal> proposals = null;

        Request request = new Request.Builder()
                .url(SERVER_ADDRESS + "/api/proposal/" + googleSignInAccounts[0].getId())
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                Type listType = new TypeToken<ArrayList<Proposal>>(){}.getType();
                proposals = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return proposals;
    }
}
