package org.esisalama.mobile.interrogationdevmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView login1;
    private TextView login2;
    private TextView id1;
    private TextView id2;
    private Button btnSubmit;
    private ProgressBar progressBar;
    private EditText motcle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialisation();
        setEcouteurEvenement();


    }

    private void setEcouteurEvenement(){
        btnSubmit.setOnClickListener(v -> {
                    String textcle = motcle.getText().toString();

                    if (textcle == null || textcle.isEmpty()){
                        Toast.makeText(this, "Veuillez renseigner un  mot clé", Toast.LENGTH_LONG).show();
                    } else {
                        getMotCle(textcle);
                    }
                }

        );


    }


    private void  getMotCle(String motcle){
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ItemUsers itemUsers = retrofit.create(ItemUsers.class);
        Call<ItemsData> callback =  itemUsers.getItems(motcle);

        callback.enqueue(new Callback<ItemsData>() {
            @Override
            public void onResponse(@NonNull Call<ItemsData> call, @NonNull Response<ItemsData> response) {
                if(response.isSuccessful()){

                    ItemsData items = response.body();

                    if(items == null){
                        Toast.makeText(MainActivity.this, "Na pas été trouvé", Toast.LENGTH_LONG).show();
                    }
                        List<Items> myItems = items.getItems();

                        if( myItems.get(0) != null ){
                            id1.setText("" + myItems.get(0).getId());
                            id2.setText("" + myItems.get(1).getId());
                            login1.setText(myItems.get(0).getLogin());
                            login2.setText( myItems.get(1).getLogin());
                        }




                } else {
                    Toast.makeText(MainActivity.this, "Erreur", Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(@NonNull Call<ItemsData> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    private  void initialisation(){
        login1 = findViewById(R.id.textViewLogin1);
        login2 = findViewById(R.id.textViewLogin2);
        id1 = findViewById(R.id.textViewId1);
        id2 = findViewById(R.id.textViewId2);
        motcle = findViewById(R.id.editTextmotcle);

        btnSubmit = findViewById(R.id.btnSoumettre);
        progressBar = findViewById(R.id.progress_bar);


    }

}