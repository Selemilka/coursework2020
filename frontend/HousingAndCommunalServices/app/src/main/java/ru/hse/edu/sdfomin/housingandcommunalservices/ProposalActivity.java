package ru.hse.edu.sdfomin.housingandcommunalservices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Proposal;
import ru.hse.edu.sdfomin.housingandcommunalservices.web.GetProposalsTask;

import static ru.hse.edu.sdfomin.housingandcommunalservices.web.ServerManager.SERVER_ADDRESS;

public class ProposalActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeLayout;
    ArrayList<Proposal> proposals;
    ListView listView;
    ProposalAdapter proposalAdapter;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        swipeLayout = findViewById(R.id.swipe_container);
        setSupportActionBar(toolbar);

        final Person person = (Person) Objects.requireNonNull(getIntent().getExtras()).get("person");
        account = (GoogleSignInAccount) Objects.requireNonNull(getIntent().getExtras()).get("account");
        listView = findViewById(R.id.listView);

        proposals = new ArrayList<>();
        try {
            proposals = (ArrayList<Proposal>) new GetProposalsTask().execute(account).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        proposalAdapter = new ProposalAdapter(this, proposals);
        listView.setAdapter(proposalAdapter);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    new GetProposalsTask2().execute(account).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProposalActivity.this, CreateProposalActivity.class);
                intent.putExtra("person", person);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }

    private static class ProposalAdapter extends ArrayAdapter<Proposal> {

        private Context mContext;
        private List<Proposal> proposalList;

        ProposalAdapter(@NonNull Context context, ArrayList<Proposal> list) {
            super(context, 0, list);
            mContext = context;
            proposalList = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listItem = convertView;
            if (listItem == null)
                listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

            Proposal currentProposal = proposalList.get(position);

            TextView name = listItem.findViewById(R.id.textViewProposalText);
            name.setText(currentProposal.getText());

            TextView release = listItem.findViewById(R.id.textViewProposalStatus);
            if (currentProposal.getAnswer() == null)
                currentProposal.setAnswer("");
            release.setText(currentProposal.getAnswer());

            switch (currentProposal.getProposalStatus()) {
                case PENDING:
                    listItem.setBackgroundColor(Color.rgb(253, 238, 189));
                    break;
                case ACCEPTED:
                    listItem.setBackgroundColor(Color.rgb(196, 230, 204));
                    break;
                case REJECTED:
                    listItem.setBackgroundColor(Color.rgb(243, 197, 204));
                    break;
            }

            return listItem;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetProposalsTask2 extends AsyncTask<GoogleSignInAccount, Integer, List<Proposal>> {
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

                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    Type listType = new TypeToken<ArrayList<Proposal>>() {
                    }.getType();
                    proposals = new Gson().fromJson(Objects.requireNonNull(response.body()).string(), listType);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return proposals;
        }

        @Override
        protected void onPostExecute(List<Proposal> proposals2) {
            super.onPostExecute(proposals2);
            if (proposals2.isEmpty()) {
                proposals2.add(new Proposal("У вас нет заявок! Чтобы создать заявку, нажмите на значок письма снизу справа", "", null, null));
            }
            proposals.clear();
            proposals.addAll(proposals2);
            proposalAdapter.notifyDataSetChanged();
            swipeLayout.setRefreshing(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            new GetProposalsTask2().execute(account).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
