package com.example.walletbank;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.walletbank.actions.WalletAction;
import com.example.walletbank.actions.builder.ActionBuilder;
import com.example.walletbank.actions.builder.ActionName;
import com.veritran.sdk.Sdk;
import com.veritran.ui.interfaces.VeritranModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActionName selectedAction = ActionName.NO_ACTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSdk();
        initComponents();

    }

    private void initComponents() {
        Button execute_action_btn = (Button) findViewById(R.id.execute_action_btn);

        final ListView actions_lv = (ListView) findViewById(R.id.actions_lv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_list_view, R.id.textView, ActionName.getActionNames());
        actions_lv.setAdapter(arrayAdapter);

        actions_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAction = ActionName.valueOf(actions_lv.getItemAtPosition(i).toString()
                        .toUpperCase());
            }
        });

        execute_action_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map<String,String> parameters = new ArrayMap<>();
                //fill this map with parameters from the activity fro the desired WalletAction
                WalletAction walletAction =
                        ActionBuilder.buildExecutableActionMessage(MainActivity.this,
                                selectedAction,
                                parameters);
                walletAction.execute();
            }
        });


    }


    private void initSdk() {
        Map<String, String> constants = new HashMap<>();

        //CONFIGURATION PARAMETERS
        constants.put("ENVIRONMENT", "DEV");
        constants.put("MSTMVERSION", "2");
        constants.put("MODEL", "SMART__");
        constants.put("BRAND", "GOOG");
        constants.put("CLIENT", "bamx");
        constants.put("PRODUCT", "MB");

        //CONFIGURATION ENDPOINT
        constants.put("GATEWAY", "https://vtgateway.veritran.net/m-client/vt-gateway.php");
        constants.put("UAS", "https://vtgateway.veritran.net/m-client/");


        List<VeritranModule> modules = new ArrayList<>();

        Sdk.init(this.getApplication(), constants, modules);

    }
}
