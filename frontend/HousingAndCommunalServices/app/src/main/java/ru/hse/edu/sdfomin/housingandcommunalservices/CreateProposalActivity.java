package ru.hse.edu.sdfomin.housingandcommunalservices;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ru.hse.edu.sdfomin.housingandcommunalservices.model.Address;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Proposal;
import ru.hse.edu.sdfomin.housingandcommunalservices.service.GetAddress;
import ru.hse.edu.sdfomin.housingandcommunalservices.web.CreateProposalTask;

public class CreateProposalActivity extends AppCompatActivity {
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_proposal);

        final Address address = GetAddress.getAddress(getApplicationContext());

        final Person person = (Person) Objects.requireNonNull(getIntent().getExtras()).get("person");

        final EditText editTextRequest = findViewById(R.id.editTextRequest);
        final EditText editTextStreet = findViewById(R.id.editTextStreet);
        final EditText editTextHouseNumber = findViewById(R.id.editTextHouseNumber);
        final EditText editTextFlatNumber = findViewById(R.id.editTextFlatNumber);

        final Button buttonCreateProposal = findViewById(R.id.buttonCreateProposal);

        if (address != null) {
            editTextStreet.setText(address.getStreet());
            editTextHouseNumber.setText(address.getHouseNumber());
            editTextFlatNumber.setText(address.getFlatNumber());
        }

        buttonCreateProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textRequest = editTextRequest.getText().toString();
                String street = editTextStreet.getText().toString();
                String houseNumber = editTextHouseNumber.getText().toString();
                String flatNumber = editTextFlatNumber.getText().toString();

                boolean fail = false;

                if (TextUtils.isEmpty(textRequest)) {
                    editTextRequest.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(street)) {
                    editTextStreet.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(houseNumber)) {
                    editTextHouseNumber.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(flatNumber)) {
                    editTextFlatNumber.setError("Поле не должно быть пустым");
                    fail = true;
                }

                if (fail) return;

                Address address2 = new Address(flatNumber, street, houseNumber);
                Proposal proposal = new Proposal(textRequest, "В ожидании...", person, address2);

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Address", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("SavedAddress", gson.toJson(address2)).apply();

                try {

                    String res = new CreateProposalTask().execute(proposal).get();
                    if (!res.isEmpty())
                        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
                    finish();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
