package com.example.readcontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Adapter adapter;
    ImageView image;
    //TextView tv_phonebook;
    ArrayList<Contact> contactList = new ArrayList<>();
    private ProgressDialog progressDialog;

    //ArrayList<Contact> Mobile = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //image =(ImageView) findViewById(R.id.imageContact);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    1);
        } else {


            new FetchContacts().execute();
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*adapter = new Adapter(this, contactList);*/
        recyclerView.setAdapter(adapter);
    }


   /* private void getcontact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));/// voici sa
            Contact contact = new Contact(name, mobile);

            contactList.add(contact);
            Collections.sort(contactList);

        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new FetchContacts().execute();
            }
        }
    }

    // class to fetch contacts asynchronously
    public class FetchContacts extends AsyncTask<Void, Integer, ArrayList<Contact>> {

        @Override
        protected ArrayList<Contact> doInBackground(Void... params) {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));/// voici sa
                Contact contact = new Contact(name, mobile);
                contactList.add(contact);

                int contactcounter =0;
                for(Contact comp: contactList){
                    // each time a contacts in put in the contact list this variable is updated
                    contactcounter++;

                    if (contactcounter % (contactList.size()/25) == 0){
                        //each time the contactcounter is updated the progress bar increases by 4%
                        publishProgress(contactcounter*100/contactList.size());
                    }
                }


                Collections.sort(contactList);

            }
            return contactList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // before the execution of he asynchronous classe we create a progress dialog to show the fetch contacts progress
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setMessage(getString(R.string.Downloading_Contacts));
            progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Contact> contacts) {
            super.onPostExecute(contacts);
            //after execution we dismiss the progress dialog
            progressDialog.dismiss();

            adapter = new Adapter(MainActivity.this, contactList);
          //  recyclerView.setAdapter(adapter);
        }
    }
}