package ru.hse.edu.sdfomin.housingandcommunalservices.service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import ru.hse.edu.sdfomin.housingandcommunalservices.model.Address;

import static android.content.Context.MODE_PRIVATE;

public class GetAddress {
    private static Address address = null;

    public static Address getAddress(Context context) {
        if (address == null) {
            Gson gson = new Gson();
            SharedPreferences mPrefs = context.getSharedPreferences("Address", MODE_PRIVATE);

            String savedAddressJson = mPrefs.getString("SavedAddress", "");
            address = gson.fromJson(savedAddressJson, Address.class);
        }
        return address;
    }

    public static void setAddress(Address address) {
        GetAddress.address = address;
    }
}
