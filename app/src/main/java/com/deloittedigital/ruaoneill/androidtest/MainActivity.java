package com.deloittedigital.ruaoneill.androidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uk.co.deanwild.materialshowcaseview.*;

public class MainActivity extends AppCompatActivity {

	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private static final String SHOWCASE_ID = "sequence 1";

	EditText editText;
	EditText editText2;
	Button sendButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editText);
		editText2 = (EditText) findViewById(R.id.editText2);
		sendButton = (Button) findViewById(R.id.button);

		presentShowcaseSequence();
	}

	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	private void presentShowcaseSequence() {

		ShowcaseConfig config = new ShowcaseConfig();
		config.setDelay(500); // half second between each showcase view

		MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

		sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
			@Override
			public void onShow(MaterialShowcaseView itemView, int position) {
				Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
			}
		});

		sequence.setConfig(config);

		sequence.addSequenceItem(
				new MaterialShowcaseView.Builder(this)
						.setTarget(editText)
						.setDismissText("GOT IT")
						.setContentText("Enter your name here!")
						.withRectangleShape(true)
						.build()
		);

		sequence.addSequenceItem(
				new MaterialShowcaseView.Builder(this)
						.setTarget(editText2)
						.setDismissText("GOT IT")
						.setContentText("This is a spare text box!")
						.withRectangleShape(true)
						.build()
		);

		sequence.addSequenceItem(sendButton, "And finally, this is the send button!", "GOT IT");

		sequence.start();

	}
}
