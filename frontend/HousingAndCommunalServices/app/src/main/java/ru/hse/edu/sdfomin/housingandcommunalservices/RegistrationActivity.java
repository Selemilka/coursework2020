package ru.hse.edu.sdfomin.housingandcommunalservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import ru.hse.edu.sdfomin.housingandcommunalservices.model.House;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Pair;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Person;
import ru.hse.edu.sdfomin.housingandcommunalservices.model.Proposal;
import ru.hse.edu.sdfomin.housingandcommunalservices.web.FullRegisterPersonTask;
import ru.hse.edu.sdfomin.housingandcommunalservices.web.GetPersonTask;
import ru.hse.edu.sdfomin.housingandcommunalservices.web.RegisterPersonTask;

public class RegistrationActivity extends AppCompatActivity {

    GoogleSignInAccount account;

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        account = (GoogleSignInAccount) Objects.requireNonNull(getIntent().getExtras()).get("account");

        if (account == null) {
            Toast.makeText(getApplicationContext(), "Account is NULL!!??", Toast.LENGTH_LONG).show();
            return;
        }

        final EditText editTextGivenName = findViewById(R.id.editTextGivenName);
        final EditText editTextFamilyName = findViewById(R.id.editTextFamilyName);
        final EditText editTextEmail = findViewById(R.id.editTextEmail);
        final EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        final EditText editTextAddress = findViewById(R.id.editTextAddress);

        editTextGivenName.setText(account.getGivenName());
        editTextFamilyName.setText(account.getFamilyName());
        editTextEmail.setText(account.getEmail());
        //   editTextPhoneNumber.setText(phoneNumber);
        //   editTextAddress.setText(address);

        Button buttonRegistration = findViewById(R.id.buttonRegistration);

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String givenName = editTextGivenName.getText().toString();
                String familyName = editTextFamilyName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String address = editTextAddress.getText().toString();

                boolean fail = false;

                if (TextUtils.isEmpty(givenName)) {
                    editTextGivenName.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(familyName)) {
                    editTextFamilyName.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    editTextPhoneNumber.setError("Поле не должно быть пустым");
                    fail = true;
                }
                if (TextUtils.isEmpty(address)) {
                    editTextAddress.setError("Поле не должно быть пустым");
                    fail = true;
                }

                if (fail) return;

                Person person = new Person(givenName, familyName, email, phoneNumber,
                        account.getId(), new ArrayList<House>(), new ArrayList<Proposal>());

                try {
                    @SuppressWarnings("unchecked")
                    Person personFromServer = new FullRegisterPersonTask().execute(new Pair<>(account, person)).get();

                    if (personFromServer == null) {
                        Toast.makeText(getApplicationContext(), "Server error!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Intent intent = new Intent(RegistrationActivity.this, ProposalActivity.class);
                    intent.putExtra("person", personFromServer);
                    intent.putExtra("account", account);
                    startActivity(intent);
                    finish();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
