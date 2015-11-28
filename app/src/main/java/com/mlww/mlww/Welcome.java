package com.mlww.mlww;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mlww.mlww.Utility.Dialog;
import com.mlww.mlww.Utility.Dialog.DialogClickListener;
public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

Dialog.showListDialog(this,"请选择属性",new String[]{"chinese","american"},new Dialog.DialogItemClickListener(){
    @Override
    public void confirm(String paramString) {
        Toast.makeText(Welcome.this,paramString,Toast.LENGTH_LONG).show();
    }
} );

        //Dialog.showRadioDialog(this, "title","清空聊天记录?", new Dialog.DialogClickListener() {
        //    public void cancel() {
        //    }

        //    public void confirm() {
         //       Toast.makeText(Welcome.this,"清空聊天已确认",Toast.LENGTH_LONG).show();
        //    }
        //});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
