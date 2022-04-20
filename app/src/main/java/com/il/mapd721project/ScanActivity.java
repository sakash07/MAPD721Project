package com.il.mapd721project;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Set;

public class ScanActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter;
    private Set<BluetoothDevice> device;
    private ListView lv;
    private ArrayAdapter<String> adapter = null;
    private static final int BLUETOOTH_ON = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        lv = (ListView)findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        lv.setAdapter(adapter);
    }

    public void scannned(View v){

        if(!btAdapter.isEnabled()){

            Intent requestActivate = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(requestActivate , BLUETOOTH_ON);
        }
        else
            loadDevices();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==BLUETOOTH_ON && resultCode==RESULT_OK)
        {
            loadDevices();
        }
    }


    private void loadDevices()
    {
        device = btAdapter.getBondedDevices();
        adapter.clear();
        for(BluetoothDevice bt : device)
            adapter.add(bt.getName());
    }
}