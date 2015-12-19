/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;


public class MainActivity extends ActionBarActivity {

    final Context context = this;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());

      final EditText email = (EditText)findViewById(R.id.editText);
      Button login = (Button)findViewById(R.id.button);

      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
              query.whereEqualTo("email", email.getText().toString());
              query.getFirstInBackground(new GetCallback<ParseObject>() {
                  @Override
                  public void done(ParseObject object, com.parse.ParseException e) {
                      if (object == null) {
                          //new userCode
                          ParseObject gameScore = new ParseObject("User");
                          gameScore.put("email", email.getText().toString());
                          gameScore.saveInBackground();
                          Log.d("objectCreated",":)");
                      }
                  }
              });

              Intent nextScreen = new Intent(context,mainScreen.class);
              startActivity(nextScreen);
          }
      });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
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
